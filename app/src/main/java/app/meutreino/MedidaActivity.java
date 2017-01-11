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
import app.meutreino.adapter.MedidaAdapter;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Categoria;
import app.meutreino.entidade.Exercicio;
import app.meutreino.entidade.Medida;

public class MedidaActivity extends MainActivity {

    private RecyclerView recyclerView;
    private MedidaAdapter mAdapter;
    private List<Medida> medidas;
    FloatingActionButton fabNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_medida, frameLayout);

        fabNovo = (FloatingActionButton) findViewById(R.id.fab_novo);
        fabNovo.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.azul)));

        // Setting title
        setTitle("Medidas");

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycleLista);
        medidas = Medida.listAll(Medida.class);
        mAdapter = new MedidaAdapter(medidas, new RecyclerViewClickListener() {
            @Override
            public void onViewClicked(View v, int position) {
                if(v.getId() == R.id.item_remover){
                    Snackbar.make(fabNovo, "Remover item", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    try {
                        Medida medida = Medida.findById(Medida.class, medidas.get(position).getId());
                        medida.delete();

                        medidas.remove(position);
                        recyclerView.removeViewAt(position);
                        mAdapter.notifyItemRemoved(position);
                        mAdapter.notifyItemRangeChanged(position, medidas.size());
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
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    public void abrirCadastro(View v) {
        startActivity(new Intent(this, MedidaFormActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(5).setChecked(true);
    }
}
