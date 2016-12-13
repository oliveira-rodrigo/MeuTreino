package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.orm.query.Condition;

import java.util.ArrayList;
import java.util.List;

import app.meutreino.entidade.Categoria;
import app.meutreino.entidade.Exercicio;

public class ExercicioFormActivity extends MainActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Campo obrigatório")
    EditText etNomeExercicio;
    //@Select(message = "Selecione uma categoria")
    Spinner spinnerCategoria;

    Validator validator;

    FloatingActionButton fabCancelar, fabSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_exercicio_form, frameLayout);

        fabCancelar = (FloatingActionButton) findViewById(R.id.fabCancelar);
        fabSalvar = (FloatingActionButton) findViewById(R.id.fabSalvar);

        fabCancelar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.vermelho)));
        fabSalvar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.verde)));

        // Setting title
        setTitle("Cadastro de exercício");

        etNomeExercicio = (EditText) findViewById(R.id.et_nome_exercicio);
        spinnerCategoria = (Spinner) findViewById(R.id.spinner_categoria);

        carregarCategorias();
        spinnerCategoria.setPrompt("Categoria");

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void salvar(View v) {
        validator.validate();
    }

    public void cancelar(View v) {
        startActivity(new Intent(this, ExercicioActivity.class));
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
            String text = spinnerCategoria.getSelectedItem().toString();

            Categoria categoria = Categoria
                    .find(Categoria.class, "nome = ?", text).get(0);

            Exercicio exercicio = new Exercicio(categoria, etNomeExercicio.getText().toString(), true);
            exercicio.save();

            startActivity(new Intent(this, ExercicioActivity.class));

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

    private void carregarCategorias(){

        List<Categoria> categorias = Categoria.listAll(Categoria.class);
        List<String> arrayCategorias = new ArrayList<>();

        for (Categoria cat : categorias)
            arrayCategorias.add(cat.getNome());

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayCategorias);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerCategoria.setAdapter(dataAdapter);
    }
}
