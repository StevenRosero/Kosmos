package com.fct.kosmos.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ListView;

import com.fct.kosmos.R;
import com.fct.kosmos.adapters.ProductosAdapter;
import com.fct.kosmos.database.ConexionSQLiteHelper;
import com.fct.kosmos.javabeans.Productos;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declaracion de las varables a utilizar para visualizar el contenido
    private List<Productos> productos;
    private ProductosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conexión a la base de datos
        ConexionSQLiteHelper db = new ConexionSQLiteHelper(this);
        productos = db.getProductosList();

        // Conectar el List View del main_activity al adapter
        ListView lvProductos = findViewById(R.id.lvProductos);
        adapter = new ProductosAdapter(this,
                R.layout.item_productos, productos);
        lvProductos.setAdapter(adapter);
        registerForContextMenu(lvProductos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Método para que se refresque la lista
        refrescarListaProductos();
    }

    private void refrescarListaProductos() {

        productos.clear();
        ConexionSQLiteHelper db = new ConexionSQLiteHelper(this);
        productos.addAll(db.getProductosList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_add_new_producto, menu);
    }



}
