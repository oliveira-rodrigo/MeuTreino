package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.pierry.simpletoast.SimpleToast;
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

    Button btnCancelar, btnSalvar;

    int exercicioID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_exercicio_form, frameLayout);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);

        // Setting title
        setTitle("Cadastro de exercício");

        etNomeExercicio = (EditText) findViewById(R.id.etNomeExercicio);
        spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);

        carregarCategorias();
        spinnerCategoria.setPrompt("Categoria");

        if (getIntent().hasExtra("ExercicioID")) {
            exercicioID = Integer.parseInt(getIntent().getSerializableExtra("ExercicioID").toString());
            if (exercicioID > 0) {
                //Snackbar.make(btnSalvar, "Carregar dados para edição " + exercicioID, Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                // Setting title
                setTitle("Editar exercício");
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

            if (exercicioID > 0) {
                Exercicio exercicio = Exercicio.findById(Exercicio.class, exercicioID);
                exercicio.setNome(etNomeExercicio.getText().toString().trim());
                exercicio.setCategoria(categoria);
                exercicio.save();
            } else {
                Exercicio exercicio = new Exercicio(categoria, etNomeExercicio.getText().toString(), true);
                exercicio.save();
            }

            startActivity(new Intent(this, ExercicioActivity.class));

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

    private void carregarCategorias(){

        List<Categoria> categorias = com.orm.query.Select.from(Categoria.class).orderBy("nome").list();
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

    public void carregarDados() {
        Exercicio exercicio = Exercicio.findById(Exercicio.class, exercicioID);
        etNomeExercicio.setText(exercicio.getNome());

        for (int i = 0; i < spinnerCategoria.getCount(); i++) {
            if (spinnerCategoria.getItemAtPosition(i).equals(exercicio.getCategoria().getNome())) {
                spinnerCategoria.setSelection(i);
                break;
            }
        }
    }
}
