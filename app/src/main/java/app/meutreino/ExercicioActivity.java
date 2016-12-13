package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.meutreino.adapter.CategoriaAdapter;
import app.meutreino.adapter.ExercicioAdapter;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Categoria;
import app.meutreino.entidade.Exercicio;

public class ExercicioActivity extends MainActivity {

    private RecyclerView recyclerView;
    private ExercicioAdapter mAdapter;
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
        mAdapter = new ExercicioAdapter(Exercicio.listAll(Exercicio.class), new RecyclerViewClickListener() {
            @Override
            public void onViewClicked(View v, int position) {
                if(v.getId() == R.id.item_remover){
                    Snackbar.make(fabNovo, "Remover item", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onRowClicked(int position) {
                Snackbar.make(fabNovo, "Detalhe item", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
