package com.fct.kosmos.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.fct.kosmos.R;
import com.fct.kosmos.database.ConexionSQLiteHelper;
import com.fct.kosmos.javabeans.Productos;
import com.fct.kosmos.utilities.Util;
import java.text.ParseException;

public class AltaProductos extends Activity implements View.OnClickListener {

    private static final int FOTO_PRODUCTO = 1;
    private String accion;
    private long idProducto;

    EditText etNombre, etDescripcion, etPrecio, etFecha, etCantidad;
    ImageButton ibImagen;
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

    //Función de relleno de datos para enviar a la BBDD con el id
    private void rellenarDatos(Productos producto, Bitmap imagenProducto) {
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        etFecha = findViewById(R.id.etFecha);
        etCantidad = findViewById(R.id.etCantidad);
        ibImagen = findViewById(R.id.ibImagen);

        etNombre.setText(producto.getNombre());
        etDescripcion.setText(producto.getDescripcion());
        etPrecio.setText(String.valueOf(producto.getPrecio()));
        etFecha.setText(Util.formatearFecha(producto.getFecha()));
        etCantidad.setText(producto.getCantidad());
        ibImagen.setImageBitmap(imagenProducto);

        idProducto = producto.getId();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btAlta:
                EditText etNombreR = findViewById(R.id.etNombre);
                EditText etDescripcionR = findViewById(R.id.etDescripcion);
                EditText etPrecioR = findViewById(R.id.etPrecio);
                EditText etFechaR = findViewById(R.id.etFecha);
                EditText etCantidadR = findViewById(R.id.etCantidad);
                ImageButton ibImagenR = findViewById(R.id.ibImagen);

                //Comprobacion de los parametros de cantidad y precio --Habría que añadir más
                try {
                    if (etPrecioR.getText().toString().equals("")) {
                        Toast.makeText(this, R.string.mensaje_precio,
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (etCantidadR.getText().toString().equals(""))
                        etCantidadR.setText("0");
                    // Si todo va ok, habria que meter más comprobaciones en un bucle
                    //Relleno de la BBDD
                    Productos producto = new Productos();
                    producto.setNombre(etNombreR.getText().toString());
                    producto.setDescripcion(etDescripcionR.getText().toString());
                    producto.setPrecio(Float.parseFloat(etPrecioR.getText().toString()));
                    producto.setFecha(Util.parsearFecha(etFechaR.getText().toString()));
                    producto.setCantidad(Integer.parseInt(etCantidadR.getText().toString()));

                    // Important asi se pasan las imagenes a la BBDD
                    producto.setImagen(((BitmapDrawable) ibImagenR.getDrawable()).getBitmap());

                    // Abrimos la conexión para la BBDD
                    ConexionSQLiteHelper db = new ConexionSQLiteHelper(this);
                    switch (accion) {
                        case "nuevo":
                            db.nuevoProducto(producto);
                            break;
                        case "modificar":
                            producto.setId(idProducto);
                            db.modificarEvento(producto);
                            break;
                        default:
                            break;
                    }
                    // Mensaje error al introducir un formato de fecha no válido(estaría bien meter un calendario)
                    Toast.makeText(this, "El producto " + producto.getNombre() +
                            " ha sido guardado", Toast.LENGTH_LONG).show();

                    // Limpieza de los textos
                    etNombre.setText("");
                    etNombre.requestFocus();
                    etDescripcion.setText("");
                    etPrecio.setText("");
                    etFecha.setText("");
                    etCantidad.setText("");
                } catch (ParseException pe) {
                    Toast.makeText(this, "Formato de fecha no válido, tiene que ser con .", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btCancelar:
                onBackPressed();
                break;
            case R.id.ibImagen:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, FOTO_PRODUCTO);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data){

        if ((resultCode == RESULT_OK) && (data != null)) {

            switch (requestCode) {
                case FOTO_PRODUCTO:
                    // Obtiene el Uri de la imagen seleccionada por el usuario
                    Uri imagenSeleccionada = data.getData();
                    String[] ruta = {MediaStore.Images.Media.DATA};

                    // Realiza una consulta a la galería de imágenes solicitando la imagen seleccionada
                    Cursor cursor = getContentResolver().query(imagenSeleccionada, ruta, null, null, null);
                    cursor.moveToFirst();

                    // Obtiene la ruta a la imagen
                    int indice = cursor.getColumnIndex(ruta[0]);
                    String picturePath = cursor.getString(indice);
                    cursor.close();

                    // Carga la imagen en una vista ImageView que se encuentra en
                    // en layout de la Activity actual
                    ImageButton ibImagen = findViewById(R.id.ibImagen);
                    ibImagen.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
                default:
                    break;
            }
        }
    }

}
