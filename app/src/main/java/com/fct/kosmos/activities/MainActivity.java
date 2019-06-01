package com.fct.kosmos.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.fct.kosmos.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //declaracion de las varables a utilizar para visualizar el contenido
    private List<Productos> productos;
    private ProductosAdapter adapter;

    ListView lvProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conexión a la base de datos
        ConexionSQLiteHelper db = new ConexionSQLiteHelper(this);
        productos = db.getProductosList();

        // Conectar el List View del main_activity al adapter
        lvProductos = findViewById(R.id.lvProductos);
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
        getMenuInflater().inflate(R.menu.menu_modify_delete_producto, menu);
    }

   /* @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int posicion = menuInfo.position;

        switch (item.getItemId()) {
            case R.id.menu_modificar:
                Intent intent = new Intent(this, AltaProductos.class);
                Productos producto = productos.get(posicion);
                intent.putExtra("accion", "modificar");
                intent.putExtra("producto", producto);
                intent.putExtra("imagen", Util.getBytes(producto.getImagen()));
                startActivity(intent);
                return true;
            case R.id.menu_eliminar:
                ConexionSQLiteHelper db = new ConexionSQLiteHelper(this);
                db.eliminarProductos(productos.get(posicion));
                refrescarListaProductos();
                return true;
            default:
                return false;
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_producto, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_alta_productos:
                Intent intent = new Intent(this, AltaProductos.class);
                intent.putExtra("accion", "nuevo");
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }
}
