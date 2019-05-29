package com.fct.kosmos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.fct.kosmos.javabeans.Productos;
import com.fct.kosmos.utilities.Util;
import com.fct.kosmos.utilities.Utilities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.provider.BaseColumns._ID;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    // Versión de la base de datos
    private static final int DATABASE_VERSION = 2;
    // Nombre de la base de datos
    private static final String DATABASE_NAME = "kosmosdb.sqlite";

    //Definición del SQLiteHelper con version y nombre
    public ConexionSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creación de las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilities.CREAR_TABLA_PRODUCTOS);
        //db.execSQL(Utilities.CREAR_TABLA_CLIENTES);
    }

    // Arranque de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLA_PRODUCTOS);
       // db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLA_CLIENTES);
        onCreate(db);
    }

/*    // Creación de nuevos Productos
    public void nuevoProducto(Productos producto) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilities.CAMPO_NOMBRE, producto.getNombre());
        values.put(Utilities.CAMPO_DESCRIPCION, producto.getDescripcion());
        values.put(Utilities.CAMPO_PRECIO, producto.getPrecio());
        values.put(Utilities.CAMPO_FECHA, Util.formatearFecha(producto.getFecha()));
        values.put(Utilities.CAMPO_CANTIDAD, producto.getCantidad());
        values.put(Utilities.CAMPO_IMAGEN, Util.getBytes(producto.getImagen()));

        db.insertOrThrow(Utilities.TABLA_PRODUCTOS, null, values);
        db.close();
    }*/

    // Eliminación de Productos
    public void eliminarProductos(Productos producto) {

        SQLiteDatabase db = getWritableDatabase();

        String[] args = {String.valueOf(producto.getId())};
        db.delete(Utilities.TABLA_PRODUCTOS, _ID + " = ?", args);
        db.close();
    }
    // Modificación de Productos
    public void modificarEvento(Productos producto) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilities.CAMPO_NOMBRE, producto.getNombre());
        values.put(Utilities.CAMPO_DESCRIPCION, producto.getDescripcion());
        values.put(Utilities.CAMPO_PRECIO, producto.getPrecio());
        values.put(Utilities.CAMPO_FECHA, Util.formatearFecha(producto.getFecha()));
        values.put(Utilities.CAMPO_CANTIDAD, producto.getCantidad());
        //
        // values.put(Utilities.CAMPO_IMAGEN, Util.getBytes(producto.getImagen()));

        String[] args = {String.valueOf(producto.getId())};
        db.update(Utilities.TABLA_PRODUCTOS, values, _ID + " = ?", args);
        db.close();
    }

    // Llamada a lista de Productos
    public List<Productos> getProductosList() {

        final String[] COLUMNAS = {_ID, Utilities.CAMPO_NOMBRE, Utilities.CAMPO_DESCRIPCION, Utilities.CAMPO_PRECIO,
                Utilities.CAMPO_FECHA,Utilities.CAMPO_CANTIDAD, Utilities.CAMPO_IMAGEN};

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Utilities.TABLA_PRODUCTOS, COLUMNAS, null, null, null,
                null, null);

        List<Productos> productos = new ArrayList<>();
        Productos producto = null;
        while (cursor.moveToNext()) {
            producto = new Productos();
            producto.setId(cursor.getLong(0));
            producto.setNombre(cursor.getString(1));
            producto.setDescripcion(cursor.getString(2));
            producto.setPrecio(cursor.getFloat(3));
            try {
                producto.setFecha(Util.parsearFecha(cursor.getString(4)));
            } catch (ParseException pe) {
                producto.setFecha(new Date());
            }
            producto.setCantidad(cursor.getInt(5));
            //producto.setImagen(Util.getBitmap(cursor.getBlob(6)));
            productos.add(producto);
        }

        return productos;
    }

    public List<Productos> getProductos(String busqueda) {
        return null;
    }


    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // Prueba nuevos paramatros para las imagenes
    public void insertNewProducto(String nombre, String descripcion, String precio, String fecha, String cantidad, byte[] imagen){
        SQLiteDatabase db = getWritableDatabase();
        String queryInsertSQL = "INSERT INTO productos VALUES(NULL, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = db.compileStatement(queryInsertSQL);
        statement.clearBindings();

        statement.bindString(1, nombre);
        statement.bindString(2, descripcion);
        statement.bindString( 3, precio);
        statement.bindString(4, fecha);
        statement.bindString(5, cantidad);
        statement.bindBlob(6, imagen);

        statement.executeInsert();
    }



}
