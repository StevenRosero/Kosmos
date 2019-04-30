package com.fct.kosmos.utilities;

public class utilities_productos {

    //Constantes campos tabla productos
    public static final String TABLA_PRODUCTOS="producto";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_CANTIDAD="cantidad";

    public static final String CREAR_TABLA_PRODUCTOS="CREATE TABLE" +
            ""+TABLA_PRODUCTOS+" ("+CAMPO_ID+
            "INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_DESCRIPCION+" TEXT"+CAMPO_CANTIDAD +
            "INTEGER";

}
