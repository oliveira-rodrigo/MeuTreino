package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.meutreino.adapter.ListaAdapter;
import app.meutreino.entidade.Entidade;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends MainActivity {

    private List<Entidade> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListaAdapter mAdapter;
    FloatingActionButton fabNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_lista);
        getLayoutInflater().inflate(R.layout.activity_lista, frameLayout);

        fabNovo = (FloatingActionButton) findViewById(R.id.fab_novo);
        fabNovo.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.azul)));

        // Setting title
        setTitle("Lista");

        recyclerView = (RecyclerView) findViewById(R.id.recycleLista);

        prepareMovieData();

        List<Entidade> lista = Entidade.listAll(Entidade.class);

        /*TESTE DATABASE
        List<Entidade> lista = new Select()
                .from(Entidade.class)
                //.where("Category = ?", category.getId())
                //.orderBy("Name ASC")
                .execute();
        //TESTE DATABASE*/

        mAdapter = new ListaAdapter(lista);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void prepareMovieData() {
        for (int i = 0; i <= 30; i++) {
            Entidade entidade = new Entidade(i);
            list.add(entidade);
        }
    }

    public void abrirCadastro(View v){
        startActivity(new Intent(this, CadastroActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(1).setChecked(true);
    }
}
