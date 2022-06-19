package com.example.comelicioso.modelos;

public class InfoRestaurantes {

    String id, nombre, tipoComida, ubicacion, telefono, costoAproximado;
    double calificacion;
    int icon;
    String horarios [];

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InfoRestaurantes(String id, String nombre, String tipoComida, String ubicacion, String telefono, String costoAproximado, double calificacion, String[] horarios) {
        this.id=id;
        this.nombre = nombre;
        this.tipoComida = tipoComida;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.costoAproximado = costoAproximado;
        this.calificacion = calificacion;
        this.horarios = horarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCostoAproximado() {
        return costoAproximado;
    }

    public void setCostoAproximado(String costoAproximado) {
        this.costoAproximado = costoAproximado;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String[] getHorarios() {
        return horarios;
    }

    public void setHorarios(String[] horarios) {
        this.horarios = horarios;
    }
}
