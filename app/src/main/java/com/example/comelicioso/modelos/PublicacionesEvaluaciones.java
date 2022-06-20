package com.example.comelicioso.modelos;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

public class PublicacionesEvaluaciones {

    private String id, usario, opinion, restaurante, fecha;
    private boolean siImagen;
    private int personas, gasto, tiempo;
    private double gastoPorPersona;


    public PublicacionesEvaluaciones(String id, String usario, String opinion,
                                     String restaurante, String fecha,
                                     int personas, int gasto, int tiempo, double gastoPorPersona) {
        this.id = id;
        this.usario = usario;
        this.opinion = opinion;
        this.restaurante = restaurante;
        this.fecha = fecha;
        this.gasto = gasto;
        this.personas = personas;
        this.tiempo = tiempo;
        this.gastoPorPersona = gastoPorPersona;
        siImagen=false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsario() {
        return usario;
    }

    public void setUsario(String usario) {
        this.usario = usario;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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

    public boolean isSiImagen() {
        return siImagen;
    }

    public void setSiImagen(boolean siImagen) {
        this.siImagen = siImagen;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public int getGasto() {
        return gasto;
    }

    public void setGasto(int gasto) {
        this.gasto = gasto;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public double getGastoPorPersona() {
        return gastoPorPersona;
    }

    public void setGastoPorPersona(double gastoPorPersona) {
        this.gastoPorPersona = gastoPorPersona;
    }
}
