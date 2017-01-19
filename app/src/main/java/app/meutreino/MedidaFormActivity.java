package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.Date;
import java.util.List;

import app.meutreino.entidade.Medida;

public class MedidaFormActivity extends MainActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Campo obrigatório")
    EditText editBracoEsq, editBracoDir, editCoxaEsq, editCoxaDir, editPanturrilhaEsq, editPanturrilhaDir,
            editPeitoral, editQuadril, editCintura;

    Validator validator;

    FloatingActionButton fabCancelar, fabSalvar;

    int medidaID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_medida_form, frameLayout);

        fabCancelar = (FloatingActionButton) findViewById(R.id.fabCancelar);
        fabSalvar = (FloatingActionButton) findViewById(R.id.fabSalvar);

        fabCancelar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.vermelho)));
        fabSalvar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.verde)));

        // Setting title
        setTitle("Cadastro de medidas");

        editBracoEsq = (EditText) findViewById(R.id.editBracoEsq);
        editBracoDir = (EditText) findViewById(R.id.editBracoDir);
        editCoxaEsq = (EditText) findViewById(R.id.editCoxaEsq);
        editCoxaDir = (EditText) findViewById(R.id.editCoxaDir);
        editPanturrilhaEsq = (EditText) findViewById(R.id.editPanturrilhaEsq);
        editPanturrilhaDir = (EditText) findViewById(R.id.editPanturrilhaDir);
        editPeitoral = (EditText) findViewById(R.id.editPeitoral);
        editQuadril = (EditText) findViewById(R.id.editQuadril);
        editCintura = (EditText) findViewById(R.id.editCintura);

        if (getIntent().hasExtra("MedidaID")) {
            medidaID = Integer.parseInt(getIntent().getSerializableExtra("MedidaID").toString());
            if (medidaID > 0) {
                Snackbar.make(fabSalvar, "Carregar dados para edição " + medidaID, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Setting title
                setTitle("Editar medidas");
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
        startActivity(new Intent(this, MedidaActivity.class));
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

            Medida medida;

            if (medidaID > 0) {
                medida = Medida.findById(Medida.class, medidaID);
            } else {
                medida = new Medida();
                medida.setDataMedicao(new Date());
            }

            medida.setBracoEsq(Long.parseLong(editBracoEsq.getText().toString()));
            medida.setBracoDir(Long.parseLong(editBracoDir.getText().toString()));
            medida.setCoxaEsq(Long.parseLong(editCoxaEsq.getText().toString()));
            medida.setCoxaDir(Long.parseLong(editCoxaDir.getText().toString()));
            medida.setPanturrilhaEsq(Long.parseLong(editPanturrilhaEsq.getText().toString()));
            medida.setPanturilhaDir(Long.parseLong(editPanturrilhaDir.getText().toString()));
            medida.setPeitoral(Long.parseLong(editPeitoral.getText().toString()));
            medida.setQuadril(Long.parseLong(editQuadril.getText().toString()));
            medida.setCintura(Long.parseLong(editCintura.getText().toString()));
            medida.save();

            startActivity(new Intent(this, MedidaActivity.class));
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
        Medida medida = Medida.findById(Medida.class, medidaID);
        editBracoEsq.setText(String.valueOf(medida.getBracoEsq()));
        editBracoDir.setText(String.valueOf(medida.getBracoDir()));
        editCoxaEsq.setText(String.valueOf(medida.getCoxaEsq()));
        editCoxaDir.setText(String.valueOf(medida.getCoxaDir()));
        editPanturrilhaEsq.setText(String.valueOf(medida.getPanturrilhaEsq()));
        editPanturrilhaDir.setText(String.valueOf(medida.getPanturilhaDir()));
        editPeitoral.setText(String.valueOf(medida.getPeitoral()));
        editQuadril.setText(String.valueOf(medida.getQuadril()));
        editCintura.setText(String.valueOf(medida.getCintura()));
    }
}
