package com.example.comelicioso.modelos;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

public class PublicacionesEvaluaciones {

    private String id, usario, linkImagen, texto;
    Uri foto;

    public PublicacionesEvaluaciones(String id, String usario, String linkImagen, String texto) {
        this.id = id;
        this.usario = usario;
        this.linkImagen = linkImagen;
        this.texto = texto;
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

    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
