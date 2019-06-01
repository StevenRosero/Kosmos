package com.fct.kosmos.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.fct.kosmos.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.fct.kosmos.utilities.Util.imageViewToByte;

public class AltaProductos extends Activity  {

    //APROVECHAR DE AQUI:
            /*
                -ACCESO A LAS FOTOS
                -LAYOUTS
                -BTN_CANCELAR: ON BACK PRESSED
                -METODOS DE LIMPIAR PARÁMETROS EN INSERTAR
                -COMPROBACIÓN DE LOS PARÁMETROS INTRODUCIDOS
             */
    private static final int FOTO_PRODUCTO = 1;
    private String accion;
    private long idProducto;

    EditText etNombre, etDescripcion, etPrecio, etFecha, etCantidad;
    Button escogerImagen, btnAlta, btnCancelar;
    ImageView imageView;

    ConexionSQLiteHelper db;

    final int REQUEST_GALLERY_CODE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_productos);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        etFecha = findViewById(R.id.etFecha);
        etCantidad = findViewById(R.id.etCantidad);
        imageView = (ImageView) findViewById(R.id.ivImagen);

        // Asignarle al boton que añada los datos
        btnAlta= findViewById(R.id.btAlta);
        btnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Insertar ek nuevo producto
                    db.insertNewProducto(
                            etNombre.getText().toString().trim(),
                            etDescripcion.getText().toString().trim(),
                            etPrecio.getText().toString().trim(),
                            etFecha.getText().toString().trim(),
                            etCantidad.getText().toString().trim(),
                            imageViewToByte(imageView)
                    );

                    Toast.makeText(getApplicationContext(), "¡ Añadido correctamente!", Toast.LENGTH_SHORT).show();
                    // Funcion de vaciar los parámetros
                    limpiarParametros();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        btnCancelar= findViewById(R.id.btCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        escogerImagen = findViewById(R.id.btnEscogerImagen);

        // Asignarle al boton que recoja las imagenes del almacenamiento interno
        escogerImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AltaProductos.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_GALLERY_CODE
                );
            }
        });
    }

    private void limpiarParametros() {
        etNombre.setText("");
        etDescripcion.setText("");
        etPrecio.setText("");
        etFecha.setText("");
        etCantidad.setText("");
        // Habría que establecer un icono para la imagen por defecto
        imageView.setImageResource(R.mipmap.ic_launcher);
    }


    // Recoge la respuesta del permiso de elegir una imagen
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_GALLERY_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // MINUTO: 7:51 FALTA CAMBIAR LA VERSION DE LA BBDD QUE SE EJECUTA
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_GALLERY_CODE);
            } else {
                Toast.makeText(getApplicationContext(), "No tienes permisos de acceso, intentalo de nuevo", Toast.LENGTH_SHORT).show();
            }
            return;
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // Si la respuesta sale ok recoge la imagen y se la asigna a el ImageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_GALLERY_CODE && resultCode == RESULT_OK && data != null) {

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}


























/*      LO ANTERIOR


    // Para formatear la fecha deberia valer esto
        etFecha.setText(Util.formatearFecha(producto.getFecha()));

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btAlta:


                //TODO: Comprobacion de los parametros de cantidad y precio --Habría que añadir más
                try {
                    if (etPrecioR.getText().toString().equals("")) {
                        Toast.makeText(this, R.string.mensaje_precio,
                                Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (etCantidadR.getText().toString().equals(""))
                        etCantidadR.setText("0");
                    // todo va ok, habria que meter más comprobaciones en un bucle
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




}
*/
