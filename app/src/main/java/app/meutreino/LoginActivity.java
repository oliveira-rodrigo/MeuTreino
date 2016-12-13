package app.meutreino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void acessar(View v){
        startActivity(new Intent(this, MainActivity.class));
    }

    public void criarConta(View v){
        startActivity(new Intent(this, CriarContaActivity.class));
    }
}
