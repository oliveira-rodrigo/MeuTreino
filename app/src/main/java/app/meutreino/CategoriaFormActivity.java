package app.meutreino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appnirman.vaidationutils.ValidationUtils;
import com.github.pierry.simpletoast.SimpleToast;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import app.meutreino.entidade.Categoria;

public class CategoriaFormActivity extends MainActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Campo obrigatório")
    EditText editText;

    Validator validator;

    Button btnCancelar, btnSalvar;

    int categoriaID = 0;

    private ValidationUtils validationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_categoria_form, frameLayout);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        // Setting title
        setTitle("Cadastro de categoria");

        editText = (EditText) findViewById(R.id.etNome);

        if (getIntent().hasExtra("CategoriaID")) {
            categoriaID = Integer.parseInt(getIntent().getSerializableExtra("CategoriaID").toString());
            if (categoriaID > 0) {
                Snackbar.make(btnSalvar, "Carregar dados para edição " + categoriaID, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Setting title
                setTitle("Editar categoria");
                carregarDados();
            }
        }

        //validationUtils = new ValidationUtils(getApplicationContext());

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void salvar(View v) {

        validator.validate();

    }

    public void cancelar(View v) {

        startActivity(new Intent(this, CategoriaActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(1).setChecked(true);
    }

    @Override
    public void onValidationSucceeded() {
        // a validação passou , siga em frente
        try {
            if (categoriaID > 0) {
                Categoria categoria = Categoria.findById(Categoria.class, categoriaID);
                categoria.setNome(editText.getText().toString().trim());
                categoria.save();
            } else {
                Categoria categoria = new Categoria(editText.getText().toString().trim(), true);
                categoria.save();
            }
            startActivity(new Intent(this, CategoriaActivity.class));
        } catch (Exception e) {
            //Snackbar.make(btnSalvar, "Ocorreu um erro na operação: " + e.getMessage(), Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();

            SimpleToast.error(getApplicationContext(), "Ocorreu um erro na operação: " + e.getMessage());
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        // algum campo não foi validado , temos a lista dos
        // erros para descobrirmos o que aconteceu , veremos
        // mais a frente

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
                ((EditText) view).requestFocus();

                /*Snackbar.make(view, "Verifique se os campos estão preenchidos corretamente", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                SimpleToast.error(getApplicationContext(), "Verifique se os campos estão preenchidos corretamente");
            } else {
                //quando não for um EditText alertar de outra foma
                /*Snackbar.make(view, "Verifique se os campos estão preenchidos corretamente", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                SimpleToast.error(getApplicationContext(), "Verifique se os campos estão preenchidos corretamente");
            }
        }
    }

    public void carregarDados() {
        Categoria categoria = Categoria.findById(Categoria.class, categoriaID);
        editText.setText(categoria.getNome());
    }
}
