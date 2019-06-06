package com.fct.kosmos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fct.kosmos.jin.AltaActivity;
import com.fct.kosmos.jin.BorradoActivity;
import com.fct.kosmos.jin.ModificacionActivity;

public class AdministrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);
    }


    // MÉTODOS PARA LOS BOTONES
    public void registrar(View v) {
        Intent i = new Intent(this, AltaActivity.class);
        startActivity(i);

    }

    public void modificar(View v) {
        Intent i = new Intent(this, ModificacionActivity.class);
        startActivity(i);
    }

    public void borrar(View v) {
        Intent i = new Intent(this, BorradoActivity.class);
        startActivity(i);
    }

    public void Volver(View v) {
        Intent i = new Intent(this, HomeLogIn.class);
        startActivity(i);

    }
}


