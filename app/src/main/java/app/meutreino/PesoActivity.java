package app.meutreino;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.pierry.simpletoast.SimpleToast;

import java.util.List;

import app.meutreino.adapter.PesoAdapter;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Peso;

public class PesoActivity extends MainActivity {

    private RecyclerView recyclerView;
    private PesoAdapter mAdapter;
    private List<Peso> pesos;
    Button btnNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLayoutInflater().inflate(R.layout.activity_peso, frameLayout);

        btnNovo = (Button) findViewById(R.id.btnNovo);

        // Setting title
        setTitle("Pesos");

        recyclerView = (RecyclerView) findViewById(R.id.recycleLista);
        pesos = Peso.listAll(Peso.class);
        mAdapter = new PesoAdapter(pesos, new RecyclerViewClickListener() {
            @Override
            public void onViewClicked(View v, int position) {
                if (v.getId() == R.id.btnRemover) {
                    //Snackbar.make(v, "Remover item", Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();

                    try {

                        Peso peso = Peso.findById(Peso.class, pesos.get(position).getId());

                        peso.delete();
                        pesos.remove(position);
                        recyclerView.removeViewAt(position);
                        mAdapter.notifyItemRemoved(position);
                        mAdapter.notifyItemRangeChanged(position, pesos.size());

                    } catch (Exception ex) {
                        //Snackbar.make(v, "Ocorreu um erro ao removero item: " + ex.getMessage(), Snackbar.LENGTH_LONG)
                        //        .setAction("Action", null).show();
                        SimpleToast.error(getApplicationContext(), "Ocorreu um erro ao remover o item: " + ex.getMessage());
                    }
                } else if (v.getId() == R.id.btnEditar) {
                    //Snackbar.make(v, "Detalhe item " + pesos.get(position).getId(), Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();

                    Intent intent = new Intent(getApplicationContext(), PesoFormActivity.class);
                    intent.putExtra("PesoID", pesos.get(position).getId());
                    startActivity(intent);
                }
            }

            @Override
            public void onRowClicked(int position) {
                return;
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
        navigationView.getMenu().getItem(4).setChecked(true);
    }
}
