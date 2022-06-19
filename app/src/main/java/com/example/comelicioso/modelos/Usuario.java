package com.example.comelicioso.modelos;

import java.util.ArrayList;

public class Usuario {
    private String id, nombre, correo, contresenia;
    private ArrayList<String> favoritos, proximos;
    private ArrayList<Reservaciones> reservaciones;

    public Usuario(String id, String nombre, String correo, String contresenia) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contresenia = contresenia;
        favoritos=new ArrayList<>();
        proximos=new ArrayList<>();
        reservaciones=new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContresenia() {
        return contresenia;
    }

    public void setContresenia(String contresenia) {
        this.contresenia = contresenia;
    }

    public ArrayList<String> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(ArrayList<String> favoritos) {
        this.favoritos = favoritos;
    }

    public ArrayList<String> getProximos() {
        return proximos;
    }

    public void setProximos(ArrayList<String> proximos) {
        this.proximos = proximos;
    }

    public ArrayList<Reservaciones> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(ArrayList<Reservaciones> reservaciones) {
        this.reservaciones = reservaciones;
    }
}
