package com.fct.kosmos.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.fct.kosmos.javabeans.Productos;

import java.util.ArrayList;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    // Versión de la base de datos
    private static final int DATABASE_VERSION = 1;
    // Nombre de la base de datos
    private static final String DATABASE_NAME = "kosmos_database";
    //Carga inicial para la comprobación de la tabla Productos
    private ArrayList<Productos> cargaInicial = new ArrayList<>();

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
    
}
