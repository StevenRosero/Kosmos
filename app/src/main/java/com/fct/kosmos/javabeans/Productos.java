package com.fct.kosmos.javabeans;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

public class Productos implements Serializable {

    private long id;
    private String nombre;
    private String descripcion;
    private float precio;
    private Date fecha;
    private int cantidad;
    private transient Bitmap imagen;

    public Productos() {}

    public Productos(String nombre, String descripcion, float precio, Date fecha, int cantidad, Bitmap imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public Productos(long id, String nombre, String descripcion, float precio, Date fecha, int cantidad, Bitmap imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Bitmap  getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
