package com.fct.kosmos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.fct.kosmos.javabeans.Clientes;
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
    private static final String DATABASE_NAME = "kosmosdb.db";

    //Definición del SQLiteHelper con version y nombre
    public ConexionSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creación de las tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilities.CREAR_TABLA_PRODUCTOS);
        db.execSQL(Utilities.CREAR_TABLA_CLIENTES);
    }

    // Arranque de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLA_PRODUCTOS);
        db.execSQL("DROP TABLE IF EXISTS "+Utilities.TABLA_CLIENTES);
        onCreate(db);
    }

    //Insertar nuevo prodcuto
    public void nuevoProducto(Productos producto) {
        // Abrir la conexión a la BBDD
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
    }


    //Insertar nuevo cliente
    public void nuevoCliente(Clientes cliente) {
        // Abrir la conexión a la BBDD
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilities.CAMPO_NOMBRE_EMPRESA, cliente.getNombreEmpresa());
        values.put(Utilities.CAMPO_CONTACTO, cliente.getContacto());
        values.put(Utilities.CAMPO_TELEFONO, cliente.getTelefono());
        values.put(Utilities.CAMPO_IMAGEN, Util.getBytes(cliente.getImagen()));

        db.insertOrThrow(Utilities.TABLA_CLIENTES, null, values);
        db.close();
    }

    // Eliminación de Productos
    public void eliminarProductos(Productos producto) {

        SQLiteDatabase db = getWritableDatabase();

        String[] args = {String.valueOf(producto.getId())};
        db.delete(Utilities.TABLA_PRODUCTOS, _ID + " = ?", args);
        db.close();
    }


    // Eliminación de Clientes
    public void eliminarClientes(Clientes cliente) {
        // Abrir la conexión a la BBDD
        SQLiteDatabase db = getWritableDatabase();

        String[] args = {String.valueOf(cliente.getId())};
        db.delete(Utilities.TABLA_CLIENTES, _ID + " = ?", args);
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
            producto.setImagen(Util.getBitmap(cursor.getBlob(6)));
            productos.add(producto);
        }

        return productos;
    }

    public List<Productos> getProductos(String busqueda) {
        return null;
    }

    // Llamada a lista de Clientes
    public List<Clientes> getClientesList() {

        final String[] COLUMNAS = {_ID, Utilities.CAMPO_NOMBRE_EMPRESA, Utilities.CAMPO_CONTACTO, Utilities.CAMPO_TELEFONO,
                Utilities.CAMPO_IMAGEN};

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(Utilities.TABLA_CLIENTES, COLUMNAS, null, null, null,
                null, null);

        List<Clientes> clientes = new ArrayList<>();
        Clientes cliente = null;
        while (cursor.moveToNext()) {
            cliente = new Clientes();
            cliente.setId(cursor.getLong(0));
            cliente.setNombreEmpresa(cursor.getString(1));
            cliente.setContacto(cursor.getString(2));
            cliente.setTelefono(cursor.getInt(3));
            cliente.setImagen(Util.getBitmap(cursor.getBlob(4)));
            clientes.add(cliente);
        }

        return clientes;
    }

    public List<Clientes> getClientes(String busqueda) {
        return null;
    }

     // Modificación de Productos y Falta para la tabla Clientes
    public void modificarProducto(Productos producto) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilities.CAMPO_NOMBRE, producto.getNombre());
        values.put(Utilities.CAMPO_DESCRIPCION, producto.getDescripcion());
        values.put(Utilities.CAMPO_PRECIO, producto.getPrecio());
        values.put(Utilities.CAMPO_FECHA, Util.formatearFecha(producto.getFecha()));
        values.put(Utilities.CAMPO_CANTIDAD, producto.getCantidad());
        values.put(Utilities.CAMPO_IMAGEN, Util.getBytes(producto.getImagen()));

        String[] args = {String.valueOf(producto.getId())};
        db.update(Utilities.TABLA_PRODUCTOS, values, _ID + " = ?", args);
        db.close();
    }

    // Modificación de Clientes
    public void modificarCliente(Clientes cliente) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilities.CAMPO_NOMBRE_EMPRESA, cliente.getNombreEmpresa());
        values.put(Utilities.CAMPO_CONTACTO, cliente.getContacto());
        values.put(Utilities.CAMPO_TELEFONO, cliente.getTelefono());
        values.put(Utilities.CAMPO_IMAGEN, Util.getBytes(cliente.getImagen()));

        String[] args = {String.valueOf(cliente.getId())};
        db.update(Utilities.TABLA_CLIENTES, values, _ID + " = ?", args);
        db.close();
    }
}
