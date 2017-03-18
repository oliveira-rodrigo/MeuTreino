package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.pierry.simpletoast.SimpleToast;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import app.meutreino.comum.Mask;
import app.meutreino.comum.Util;
import app.meutreino.entidade.Peso;

public class PesoFormActivity extends MainActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Campo obrigatório")
    EditText editPeso, editDtPesagem;

    Validator validator;

    Button btnCancelar, btnSalvar;

    int pesoID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_peso_form, frameLayout);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        // Setting title
        setTitle("Cadastro de peso");

        editPeso = (EditText) findViewById(R.id.editPeso);
        editDtPesagem = (EditText) findViewById(R.id.editDtPesagem);

        if (getIntent().hasExtra("PesoID")) {
            pesoID = Integer.parseInt(getIntent().getSerializableExtra("PesoID").toString());
            if (pesoID > 0) {
                //Snackbar.make(fabSalvar, "Carregar dados para edição " + pesoID, Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                // Setting title
                setTitle("Editar peso");
                carregarDados();
            }
        }

        //mascaras
        editDtPesagem.addTextChangedListener(Mask.insert("##/##/####", editDtPesagem));

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void salvar(View v) {
        validator.validate();
    }

    public void cancelar(View v) {
        startActivity(new Intent(this, PesoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(5).setChecked(true);
    }

    @Override
    public void onValidationSucceeded() {
        // a validação passou , siga em frente
        try {

            Peso peso;

            if (pesoID > 0) {
                peso = Peso.findById(Peso.class, pesoID);
                peso.setValor(Float.valueOf(editPeso.getText().toString()));
                peso.setDataPesagem(new Util().stringToDate(editDtPesagem.getText().toString()));
            } else {
                peso = new Peso(Float.valueOf(editPeso.getText().toString()), new Util().stringToDate(editDtPesagem.getText().toString()));
                peso.save();
            }
            peso.save();
            startActivity(new Intent(this, PesoActivity.class));
        } catch (Exception e) {
            //Snackbar.make(fabSalvar, "Ocorreu um erro na operação: " + e.getMessage(), Snackbar.LENGTH_LONG)
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
                //Snackbar.make(view, "Verifique se os campos estão preenchidos corretamente", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                SimpleToast.error(getApplicationContext(), "Verifique se os campos estão preenchidos corretamente");

            } else {
                //quando não for um EditText alertar de outra foma
                //Snackbar.make(view, "Verifique se os campos estão preenchidos corretamente", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                SimpleToast.error(getApplicationContext(), "Verifique se os campos estão preenchidos corretamente");
            }
        }
    }

    public void carregarDados() {
        Peso peso = Peso.findById(Peso.class, pesoID);
        editPeso.setText(String.valueOf(peso.getValor()));
        editDtPesagem.setText(new Util().dateToString(peso.getDataPesagem()));
    }
}
