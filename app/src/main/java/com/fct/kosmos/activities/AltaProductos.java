package com.fct.kosmos.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.fct.kosmos.R;
import com.fct.kosmos.javabeans.Productos;
import com.fct.kosmos.utilities.Util;

public class AltaProductos extends AppCompatActivity {

    private static final int FOTO_PRODUCTO = 1;
    private String accion;
    private long idProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_productos);

        Button btAlta = findViewById(R.id.btAlta);
        btAlta.setOnClickListener(this);
        Button btCancelar = findViewById(R.id.btCancelar);
        btCancelar.setOnClickListener(this);
        ImageButton ibImagen = findViewById(R.id.ibImagen);
        ibImagen.setOnClickListener(this);

        accion = getIntent().getStringExtra("accion");
        if (accion.equals("modificar")) {
            Productos producto = (Productos) getIntent().getSerializableExtra("producto");
            Bitmap imagenProducto = Util.getBitmap(getIntent().getByteArrayExtra("imagen"));
            rellenarDatos(producto, imagenProducto);
            btAlta.setText(R.string.guardar);
        }
    }
}
