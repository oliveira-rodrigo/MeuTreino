package app.meutreino;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import app.meutreino.entidade.Categoria;

public class CategoriaFormActivity extends MainActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Campo obrigatório")
    EditText editText;

    Validator validator;

    FloatingActionButton fabCancelar, fabSalvar;

    int categoriaID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_categoria_form, frameLayout);

        fabCancelar = (FloatingActionButton) findViewById(R.id.fabCancelar);
        fabSalvar = (FloatingActionButton) findViewById(R.id.fabSalvar);

        fabCancelar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.vermelho)));
        fabSalvar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.verde)));

        // Setting title
        setTitle("Cadastro de categoria");

        editText = (EditText) findViewById(R.id.editTextNome);

        if (getIntent().hasExtra("CategoriaID")) {
            categoriaID = Integer.parseInt(getIntent().getSerializableExtra("CategoriaID").toString());
            if (categoriaID > 0) {
                Snackbar.make(fabSalvar, "Carregar dados para edição " + categoriaID, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Setting title
                setTitle("Editar categoria");
                carregarDados();
            }
        }

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
            Snackbar.make(fabSalvar, "Ocorreu um erro na operação: " + e.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
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
                Snackbar.make(view, "Verifique se os campos estão preenchidos corretamente", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                //quando não for um EditText alertar de outra foma
                Snackbar.make(view, "Verifique se os campos estão preenchidos corretamente", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    public void carregarDados() {
        Categoria categoria = Categoria.findById(Categoria.class, categoriaID);
        editText.setText(categoria.getNome());
    }
}
