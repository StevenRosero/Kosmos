package com.fct.kosmos.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fct.kosmos.R;
import com.fct.kosmos.activities.javabean.Productos;
import com.fct.kosmos.activities.javabean.ProductosAdaptador;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvProductos;
    private ProductosAdaptador adapter;
    private LinearLayoutManager manager;

    private ArrayList<Productos> datos;

    private DatabaseReference dbR;
    private ChildEventListener cel;

    private String remitente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Iniciar atributos
        remitente = "persona1";
        rvProductos = findViewById(R.id.rvConsulta);
        datos = new ArrayList<Productos>();
        adapter = new ProductosAdaptador(datos, getApplicationContext());
        manager = new LinearLayoutManager(this);

        rvProductos.setAdapter(adapter);
        rvProductos.setLayoutManager(manager);
        rvProductos.setItemAnimator(new DefaultItemAnimator());
        //FloatBoton
        FloatingActionButton fab1 = findViewById(R.id.floatingActions);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AltaProducto.class));
            }
        });

        dbR = FirebaseDatabase.getInstance().getReference().child("Coleccion");
        addChildEventListener();

    }

    private void siguiente(View v) {

    }

    private void addChildEventListener() {
        if(cel == null) {
            cel = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Productos c = dataSnapshot.getValue(Productos.class);
                    datos.add(c);
                    adapter.notifyItemChanged(datos.size()-1);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    /*Productos c = dataSnapshot.getValue(Productos.class);
                    int pos = 0;
                    for(int i = 0; i < datos.size(); i++) {
                        if(datos.get(i).getMatricula().equals(c.getMatricula())) {        // se busca el mismo coche
                            datos.set(i, c);
                            pos = i;
                        }
                    }
                    adapter.notifyItemChanged(pos);*/
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    /*
                    Eliminar con dbR.child(?/matricula = AAA12345).removeValue
                    */
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            dbR.addChildEventListener(cel);
        }
    }

    /**
     * Liberar recursos cuando se
     * pausa la actividad
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(cel != null) {
            dbR.removeEventListener(cel);
            cel = null;
        }
        adapter.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addChildEventListener();
    }
}

