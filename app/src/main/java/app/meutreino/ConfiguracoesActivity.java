package app.meutreino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConfiguracoesActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_configuracoes);
        getLayoutInflater().inflate(R.layout.activity_configuracoes, frameLayout);

        // Setting title
        setTitle("Configurações");
    }
}
