package com.fct.kosmos.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.fct.kosmos.R;
import com.fct.kosmos.activities.javabean.Productos;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AltaProducto extends AppCompatActivity {

    private DatabaseReference dbR;

    private EditText etNombreP;
    private EditText etTipoP;
    private EditText etColorP;
    private EditText etCantidadP;
    private EditText etPrecioP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_producto);

        etNombreP = findViewById(R.id.etNombrePAlta);
        etTipoP = findViewById(R.id.etTipoPAlta);
        etColorP = findViewById(R.id.etColorPAlta);
        etCantidadP = findViewById(R.id.etCantidadPAlta);
        etPrecioP = findViewById(R.id.etPrecioPAlta);

        dbR = FirebaseDatabase.getInstance().getReference().child("Coleccion");

    }

    public void crear(View v) {
        String matricula = etNombreP.getText().toString();
        String tipoP = etTipoP.getText().toString();
        String colorP = etColorP.getText().toString();
        String cantidadP = etCantidadP.getText().toString();
        String precioP = etPrecioP.getText().toString();

        if(matricula.isEmpty() || tipoP.isEmpty() || colorP.isEmpty() || cantidadP.isEmpty() || precioP.isEmpty()
                || matricula.trim().equals("") || tipoP.trim().equals("") || colorP.trim().equals("")) {
            Toast.makeText(getBaseContext(), getString(R.string.toast_error_alta), Toast.LENGTH_SHORT).show();
        } else {
            Productos c = new Productos(matricula, tipoP, colorP, Integer.parseInt(cantidadP), Double.parseDouble(precioP));
            dbR.push().setValue(c);
            startActivity(new Intent(AltaProducto.this, MainActivity.class));
        }

    }

    public void cancelar(View v) {
        startActivity(new Intent(AltaProducto.this, MainActivity.class));
    }
}
