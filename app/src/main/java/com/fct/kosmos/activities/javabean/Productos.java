package com.fct.kosmos.activities.javabean;

public class Productos {

    private String idFirebase;
    private String nombreP;
    private String tipoP;
    private String colorP;
    private int cantidadP;
    private double precioP;

    public Productos() {
    }

    public Productos(String nombreP, String tipoP, String colorP, int cantidadP, double precioP) {
        this.nombreP = nombreP;
        this.tipoP = tipoP;
        this.colorP = colorP;
        this.cantidadP = cantidadP;
        this.precioP = precioP;
    }

    public String getIdFirebase() {
        return idFirebase;
    }

    public String getNombreP() {
        return nombreP;
    }

    public String getTipoP() {
        return tipoP;
    }

    public String getColorP() {
        return colorP;
    }

    public int getCantidadP() {
        return cantidadP;
    }

    public double getPrecioP() {
        return precioP;
    }

    public void setIdFirebase(String idFirebase) {
        this.idFirebase = idFirebase;
    }
}
