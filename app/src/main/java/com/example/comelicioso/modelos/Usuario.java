package com.example.comelicioso.modelos;

public class Usuario {
    private String id, nombre, correo, contresenia;

    public Usuario(String id, String nombre, String correo, String contresenia) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contresenia = contresenia;
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
}
