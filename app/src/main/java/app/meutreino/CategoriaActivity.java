package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import app.meutreino.adapter.CategoriaAdapter;
import app.meutreino.comum.RecyclerViewClickListener;
import app.meutreino.entidade.Categoria;
import app.meutreino.entidade.Exercicio;

public class CategoriaActivity extends MainActivity {

    private RecyclerView recyclerView;
    private CategoriaAdapter mAdapter;
    private List<Categoria> categorias;
    Button btnNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_categoria, frameLayout);

        btnNovo = (Button) findViewById(R.id.btnNovo);

        // Setting title
        setTitle("Categorias");

        recyclerView = (RecyclerView) findViewById(R.id.recycleLista);

        categorias = Categoria.listAll(Categoria.class);
        mAdapter = new CategoriaAdapter(categorias, new RecyclerViewClickListener() {
            @Override
            public void onViewClicked(View v, int position) {
                if (v.getId() == R.id.btnRemover) {
                    //Snackbar.make(v, "Remover item", Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();

                    try {

                        Categoria categoria = Categoria.findById(Categoria.class, categorias.get(position).getId());

                        long qtde = Exercicio.count(Exercicio.class, "Categoria = ?", new String[]{categoria.getId().toString()});
                        //Snackbar.make(fabNovo, String.valueOf(qtdeExercicios), Snackbar.LENGTH_LONG).setAction("Action", null).show();

                        if (qtde == 0) {
                            categoria.delete();

                            categorias.remove(position);
                            recyclerView.removeViewAt(position);
                            mAdapter.notifyItemRemoved(position);
                            mAdapter.notifyItemRangeChanged(position, categorias.size());
                        } else {
                            //Snackbar.make(v, "Essa categoria não pode ser removida. Existem exercícios vinculadas a ela.", Snackbar.LENGTH_LONG)
                            //        .setAction("Action", null).show();
                            SimpleToast.error(getApplicationContext(), "Essa categoria não pode ser removida. Existem exercícios vinculadas a ela.");
                        }
                    } catch (Exception ex) {
                        //Snackbar.make(v, "Ocorreu um erro ao removero item: " + ex.getMessage(), Snackbar.LENGTH_LONG)
                        //        .setAction("Action", null).show();
                        SimpleToast.error(getApplicationContext(), "Ocorreu um erro ao remover o item: " + ex.getMessage());
                    }
                } else if (v.getId() == R.id.btnEditar) {
                    //Snackbar.make(v, "Detalhe item " + categorias.get(position).getId(), Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();

                    Intent intent = new Intent(getApplicationContext(), CategoriaFormActivity.class);
                    intent.putExtra("CategoriaID", categorias.get(position).getId());
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
        startActivity(new Intent(this, CategoriaFormActivity.class));
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
