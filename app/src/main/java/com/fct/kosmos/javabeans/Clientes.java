package com.fct.kosmos.javabeans;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

public class Clientes implements Serializable {

    private long id;
    private String nombreEmpresa;
    private String contacto;
    private int telefono;
    private transient Bitmap imagen;

    public Clientes(){
    }

    public Clientes(String nombreEmpresa, String contacto, int telefono, Bitmap imagen) {
        this.nombreEmpresa = nombreEmpresa;
        this.contacto = contacto;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public Clientes(long id, String nombreEmpresa, String contacto, int telefono, Bitmap imagen) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.contacto = contacto;
        this.telefono = telefono;
        this.imagen = imagen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
