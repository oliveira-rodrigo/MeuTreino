package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import app.meutreino.comum.Mask;
import app.meutreino.comum.MaskDecimal;
import app.meutreino.comum.MaskMoney;
import app.meutreino.comum.Util;
import app.meutreino.entidade.Peso;

public class PesoFormActivity extends MainActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Campo obrigatório")
    EditText editPeso, editDtPesagem;

    Validator validator;

    FloatingActionButton fabCancelar, fabSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_peso_form, frameLayout);

        fabCancelar = (FloatingActionButton) findViewById(R.id.fabCancelar);
        fabSalvar = (FloatingActionButton) findViewById(R.id.fabSalvar);

        fabCancelar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.vermelho)));
        fabSalvar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.verde)));

        // Setting title
        setTitle("Cadastro de peso");

        editPeso = (EditText) findViewById(R.id.editPeso);
        //editPeso.addTextChangedListener(new MaskDecimal(editPeso));

        editDtPesagem = (EditText) findViewById(R.id.editDtPesagem);
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
        navigationView.getMenu().getItem(2).setChecked(true);
    }

    @Override
    public void onValidationSucceeded() {
        // a validação passou , siga em frente
        try {
            Peso peso = new Peso(Float.valueOf(editPeso.getText().toString()), new Util().stringToDate(editDtPesagem.getText().toString()));
            peso.save();
            startActivity(new Intent(this, PesoActivity.class));
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
}
