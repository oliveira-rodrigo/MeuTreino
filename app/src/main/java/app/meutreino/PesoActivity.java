package app.meutreino;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import app.meutreino.adapter.CategoriaAdapter;

public class PesoActivity extends MainActivity {

    private RecyclerView recyclerView;
    private CategoriaAdapter mAdapter;
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
    }

    public void abrirCadastro(View v) {
        startActivity(new Intent(this, PesoFormActivity.class));
    }
}
