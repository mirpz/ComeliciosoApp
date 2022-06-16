package com.example.comelicioso.modelos;

import android.text.Editable;

public class Reservaciones {
    private String restaurante, fecha, hora, asistentes, reservadoPor;

    public Reservaciones(String restaurante, String fecha, String hora, String asistentes, String reservadoPor) {
        this.restaurante = restaurante;
        this.fecha = fecha;
        this.hora = hora;
        this.asistentes = asistentes;
        this.reservadoPor=reservadoPor;
    }

    public String getReservadoPor() {
        return reservadoPor;
    }

    public void setReservadoPor(String reservadoPor) {
        this.reservadoPor = reservadoPor;
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
