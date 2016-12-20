package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import app.meutreino.adapter.CategoriaAdapter;
import app.meutreino.adapter.PesoAdapter;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Peso;

public class PesoActivity extends MainActivity {

    private RecyclerView recyclerView;
    private PesoAdapter mAdapter;
    FloatingActionButton fabNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_peso, frameLayout);

        fabNovo = (FloatingActionButton) findViewById(R.id.fab_novo);
        fabNovo.setBackgroundTintList(ColorStateList
                .valueOf(getResources().getColor(R.color.azul)));

        // Setting title
        setTitle("Pesos");

        recyclerView = (RecyclerView) findViewById(R.id.recycleLista);

        mAdapter = new PesoAdapter(Peso.listAll(Peso.class), new RecyclerViewClickListener() {
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
        startActivity(new Intent(this, PesoFormActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(1).setChecked(true);
    }
}
