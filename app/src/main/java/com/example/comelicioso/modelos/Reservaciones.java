package com.example.comelicioso.modelos;

public class Reservaciones {
    private String restaurante, fecha, hora, asistentes;

    public Reservaciones(String restaurante, String fecha, String hora, String asistentes) {
        this.restaurante = restaurante;
        this.fecha = fecha;
        this.hora = hora;
        this.asistentes = asistentes;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(String asistentes) {
        this.asistentes = asistentes;
    }
}
