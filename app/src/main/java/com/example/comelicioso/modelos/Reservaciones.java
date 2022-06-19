package com.example.comelicioso.modelos;

public class Reservaciones {
    private String id, IdRestaurante, restaurante, fecha, hora, asistentes, reservadoPor;

    public Reservaciones(String id, String IdRestaurante, String restaurante, String fecha, String hora, String asistentes, String reservadoPor) {
        this.id = id;
        this.IdRestaurante=IdRestaurante;
        this.restaurante = restaurante;
        this.fecha = fecha;
        this.hora = hora;
        this.asistentes = asistentes;
        this.reservadoPor=reservadoPor;
    }

    public String getIdRestaurante() {
        return IdRestaurante;
    }

    public void setIdRestaurante(String idRestaurante) {
        IdRestaurante = idRestaurante;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
