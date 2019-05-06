package com.fct.kosmos.utilities;

public class Utilities {
    //Constantes campos tabla productos
    public static final String TABLA_PRODUCTOS="producto";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_PRECIO="precio";
    public static final String CAMPO_FECHA="fecha";
    public static final String CAMPO_CANTIDAD="cantidad";
    public static final String CAMPO_IMAGEN="imagen";

    public static final String CREAR_TABLA_PRODUCTOS="CREATE TABLE" +
            ""+TABLA_PRODUCTOS+"("+CAMPO_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE+" TEXT,"+CAMPO_DESCRIPCION+" TEXT"+CAMPO_PRECIO +
            "INTEGER"+CAMPO_CANTIDAD + "INTEGER)";




}
