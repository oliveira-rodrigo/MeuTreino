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

import java.util.List;

import app.meutreino.adapter.ExercicioAdapter;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Exercicio;
import app.meutreino.entidade.TreinoExercicio;

public class ExercicioActivity extends MainActivity {

    private RecyclerView recyclerView;
    private ExercicioAdapter mAdapter;
    private List<Exercicio> exercicios;
    FloatingActionButton fabNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_exercicio);
        getLayoutInflater().inflate(R.layout.activity_exercicio, frameLayout);

        fabNovo = (FloatingActionButton) findViewById(R.id.fab_novo);
        fabNovo.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.azul)));

        // Setting title
        setTitle("Exercicios");

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycleLista);
        exercicios = Exercicio.listAll(Exercicio.class);
        mAdapter = new ExercicioAdapter(exercicios, new RecyclerViewClickListener() {
            @Override
            public void onViewClicked(View v, int position) {
                if (v.getId() == R.id.item_remover) {
                    Snackbar.make(fabNovo, "Remover item", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    try {
                        Exercicio exercicio = Exercicio.findById(Exercicio.class, exercicios.get(position).getId());

                        long qtde = TreinoExercicio.count(TreinoExercicio.class, "Exercicio = ?", new String[]{exercicio.getId().toString()});

                        if (qtde == 0) {
                            exercicio.delete();

                            exercicios.remove(position);
                            recyclerView.removeViewAt(position);
                            mAdapter.notifyItemRemoved(position);
                            mAdapter.notifyItemRangeChanged(position, exercicios.size());
                        } else {
                            Snackbar.make(fabNovo, "Esse exercício não pode ser removido. Existem treinos vinculados a ele.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    } catch (Exception ex) {
                        Snackbar.make(fabNovo, "Ocorreu um erro ao removero item: " + ex.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onRowClicked(int position) {
                Snackbar.make(fabNovo, "Detalhe item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(getApplicationContext(), ExercicioFormActivity.class);
                intent.putExtra("ExercicioID", exercicios.get(position).getId());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void abrirCadastro(View v) {
        startActivity(new Intent(this, ExercicioFormActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(2).setChecked(true);
    }
}
