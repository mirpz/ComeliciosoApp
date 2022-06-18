package com.example.comelicioso.modelos;

import android.text.Editable;

import org.json.JSONException;
import org.json.JSONObject;

public class Reservaciones {
    private String id, restaurante, fecha, hora, asistentes, reservadoPor;

    public Reservaciones(String restaurante, String fecha, String hora, String asistentes, String reservadoPor) {
        this.restaurante = restaurante;
        this.fecha = fecha;
        this.hora = hora;
        this.asistentes = asistentes;
        this.reservadoPor=reservadoPor;
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

    public JSONObject infoEnJson(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", this.id);
            obj.put("restaurante", this.restaurante);
            obj.put("fecha", this.fecha);
            obj.put("hora", this.hora);
            obj.put("asistentes", this.asistentes);
            obj.put("reservadoPor", this.reservadoPor);
        } catch (JSONException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return obj;
    }
}
