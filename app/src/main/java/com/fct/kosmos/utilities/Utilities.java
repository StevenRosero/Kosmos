package com.fct.kosmos.utilities;


import android.provider.BaseColumns;

public class Utilities {
    //Constantes campos tabla productos
    public static final String TABLA_PRODUCTOS=" productos";
    public static final String CAMPO_ID_PRO = BaseColumns._ID;
    public static final String CAMPO_NOMBRE="nombre ";
    public static final String CAMPO_DESCRIPCION="descripcion ";
    public static final String CAMPO_PRECIO="precio ";
    public static final String CAMPO_FECHA="fecha ";
    public static final String CAMPO_CANTIDAD="cantidad ";
    public static final String CAMPO_IMAGEN="imagen";

    //Creación de la tabla PRODUCTOS
    public static final String CREAR_TABLA_PRODUCTOS = "CREATE TABLE" +
            " "+TABLA_PRODUCTOS+" ("+CAMPO_ID_PRO+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, "
            +CAMPO_NOMBRE+" TEXT NOT NULL, "
            +CAMPO_DESCRIPCION+" TEXT NOT NULL, "
            +CAMPO_PRECIO + " TEXT NOT NULL, "
            +CAMPO_FECHA + " TEXT NOT NULL, "
            +CAMPO_CANTIDAD + " TEXT NOT NULL, "
            +CAMPO_IMAGEN+ " BLOB)";



    //Constantes campos tabla clientes
    public static final String TABLA_CLIENTES="clientes";
    public static final String CAMPO_ID_CLI = BaseColumns._ID;
    public static final String CAMPO_NOMBRE_EMPRESA="nombre";
    public static final String CAMPO_CONTACTO="contacto";
    public static final String CAMPO_TELEFONO="telefono";
    public static final String CAMPO_IMAGEN_LOGO="logo";
    public static final String CAMPO_ID_PRODUCTO="idProducto";

    //Creación de la tabla CLIENTES
    public static final String CREAR_TABLA_CLIENTES = "CREATE TABLE" +
            ""+TABLA_PRODUCTOS+" ("+CAMPO_ID_CLI+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, "
            +CAMPO_NOMBRE_EMPRESA+" TEXT NOT NULL, "
            +CAMPO_CONTACTO+" INTEGER NOT NULL, "
            +CAMPO_TELEFONO+ "INTEGER NOT NULL, "
            +CAMPO_IMAGEN_LOGO+ "BLOB, "
            +CAMPO_ID_PRODUCTO+ "INTEGER "
            +"FOREIGN KEY("+CAMPO_ID_PRODUCTO+") AUTOINCREMENT REFERENCES TABLA_PRODUCTOS("+CAMPO_ID_PRO+")";
    //En caso de que no se relacionen bien las claves foráneas este es otro método
    //+CAMPO_ID_PRODUCTO+ "FOREIGN KEY NOT NULL UNIQUE)";


}
