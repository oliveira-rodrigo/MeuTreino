package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.github.pierry.simpletoast.SimpleToast;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import app.meutreino.adapter.TreinoExercicioAdapter;
import app.meutreino.comum.Mask;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.comum.Util;
import app.meutreino.entidade.Exercicio;
import app.meutreino.entidade.Treino;
import app.meutreino.entidade.TreinoExercicio;

public class TreinoFormActivity extends MainActivity implements Validator.ValidationListener {

    @NotEmpty(message = "Campo obrigatório")
    EditText editNomeTreino, editDtInicio, editDtFim;
    Spinner spinnerExercicio;
    Spinner spinnerDia;
    EditText editSeries, editCarga;

    Validator validator;

    FloatingActionButton fabCancelar, fabSalvar;

    RecyclerView recycleLista;
    private TreinoExercicioAdapter mAdapter;

    ArrayList<TreinoExercicio> listExerciciosTreino;

    int treinoID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_treino_form, frameLayout);

        fabCancelar = (FloatingActionButton) findViewById(R.id.fabCancelar);
        fabSalvar = (FloatingActionButton) findViewById(R.id.fabSalvar);

        fabCancelar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.vermelho)));
        fabSalvar.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.verde)));

        // Setting title
        setTitle("Cadastro de treino");

        editNomeTreino = (EditText) findViewById(R.id.editNomeTreino);

        editDtInicio = (EditText) findViewById(R.id.editDtInicio);

        editDtFim = (EditText) findViewById(R.id.editDtFim);

        spinnerExercicio = (Spinner) findViewById(R.id.spinner_exercicio);
        carregarExercicios();
        spinnerExercicio.setPrompt("Exercício");

        spinnerDia = (Spinner) findViewById(R.id.spinner_dia);
        carregarDias();

        editSeries = (EditText) findViewById(R.id.edit_series);
        editCarga = (EditText) findViewById(R.id.edit_carga);

        listExerciciosTreino = new ArrayList<TreinoExercicio>();
        recycleLista = (RecyclerView) findViewById(R.id.recycleLista);

        if (getIntent().hasExtra("TreinoID")) {
            treinoID = Integer.parseInt(getIntent().getSerializableExtra("TreinoID").toString());
            if (treinoID > 0) {
                //Snackbar.make(fabSalvar, "Carregar dados para edição " + treinoID, Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                // Setting title
                setTitle("Editar treino");
                carregarDados();
            }
        }

        //mascaras
        editDtInicio.addTextChangedListener(Mask.insert("##/##/####", editDtInicio));
        editDtFim.addTextChangedListener(Mask.insert("##/##/####", editDtFim));

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public void salvar(View v) {
        validator.validate();
    }

    public void cancelar(View v) {
        startActivity(new Intent(this, TreinoActivity.class));
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

            Treino treino;
            Treino savedTreino;

            if (treinoID > 0) {
                treino = Treino.findById(Treino.class, treinoID);
                treino.setNome(editNomeTreino.getText().toString().trim());
                treino.setDataInicio(new Util().stringToDate(editDtInicio.getText().toString()));
                treino.setDataFim(new Util().stringToDate(editDtFim.getText().toString()));
            } else {
                treino = new Treino(editNomeTreino.getText().toString().trim(),
                        new Util().stringToDate(editDtInicio.getText().toString()),
                        new Util().stringToDate(editDtFim.getText().toString()));
            }

            treino.save();
            savedTreino = treino;

            TreinoExercicio.deleteAll(TreinoExercicio.class,  "TREINO = ?", new String[]{savedTreino.getId().toString()});

            if (listExerciciosTreino.size() > 0) {
                for (TreinoExercicio te : listExerciciosTreino) {

                    TreinoExercicio treinoExer =
                            new TreinoExercicio(savedTreino, te.getExercicio(), te.getDia(), te.getSeries(), te.getCarga());

                    treinoExer.save();
                }
            }

            startActivity(new Intent(this, TreinoActivity.class));
        } catch (Exception e) {
            //Snackbar.make(fabSalvar, "Ocorreu um erro na operação: " + e.getMessage(), Snackbar.LENGTH_LONG)
            //       .setAction("Action", null).show();
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

    private void carregarExercicios() {

        List<Exercicio> exercicios = com.orm.query.Select.from(Exercicio.class).orderBy("nome").list();
        List<String> arrayExercicios = new ArrayList<>();

        for (Exercicio exe : exercicios)
            arrayExercicios.add(exe.getNome());

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayExercicios);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerExercicio.setAdapter(dataAdapter);
    }

    private void carregarDias() {

        List<String> arrayDias = new ArrayList<>();
        arrayDias.add("Todos");
        arrayDias.add("A");
        arrayDias.add("B");
        arrayDias.add("C");
        arrayDias.add("D");
        arrayDias.add("E");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayDias);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerDia.setAdapter(dataAdapter);
    }

    public void adicionarExercicio(View v) {

        Exercicio exercicio = Exercicio
                .find(Exercicio.class, "nome = ?",
                        spinnerExercicio.getItemAtPosition(spinnerExercicio.getSelectedItemPosition()).toString()).get(0);

        TreinoExercicio objTreinoExer = new TreinoExercicio();
        objTreinoExer.setCarga(Long.parseLong(editCarga.getText().toString()));
        objTreinoExer.setDia(spinnerDia.getItemAtPosition(spinnerDia.getSelectedItemPosition()).toString());
        objTreinoExer.setExercicio(exercicio);
        objTreinoExer.setSeries(editSeries.getText().toString());

        listExerciciosTreino.add(objTreinoExer);

        carregarListaExercicios();

        limparCampos();

        //Snackbar.make(v, "Exercício adicionado com sucesso!", Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();
        SimpleToast.info(getApplicationContext(), "Exercício adicionado com sucesso!");
    }

    public void carregarListaExercicios() {
        if (listExerciciosTreino.size() > 0) {
            mAdapter = new TreinoExercicioAdapter(listExerciciosTreino, new RecyclerViewClickListener() {
                @Override
                public void onViewClicked(View v, int position) {
                    if (v.getId() == R.id.item_remover) {
                        //Snackbar.make(fabSalvar, "Remover item", Snackbar.LENGTH_LONG)
                        //        .setAction("Action", null).show();
                    }
                }

                @Override
                public void onRowClicked(int position) {
                    //Snackbar.make(fabSalvar, "Detalhe item", Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();
                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recycleLista.setLayoutManager(mLayoutManager);
            recycleLista.setItemAnimator(new DefaultItemAnimator());
            recycleLista.setAdapter(mAdapter);
        }
    }

    public void limparCampos() {
        spinnerExercicio.setSelection(0);
        editCarga.setText("");
        spinnerDia.setSelection(0);
        editSeries.setText("");
    }

    public void carregarDados() {
        Treino treino = Treino.findById(Treino.class, treinoID);
        editNomeTreino.setText(treino.getNome());
        editDtInicio.setText(new Util().dateToString(treino.getDataInicio()));
        editDtFim.setText(new Util().dateToString(treino.getDataFim()));

        listExerciciosTreino.addAll(TreinoExercicio.find(TreinoExercicio.class, "TREINO = ?", new String[]{treino.getId().toString()}));
        carregarListaExercicios();

        limparCampos();
    }
}

