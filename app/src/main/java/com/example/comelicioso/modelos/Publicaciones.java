package com.example.comelicioso.modelos;

import org.json.JSONException;
import org.json.JSONObject;

public class Publicaciones {

    private String id, usario, linkImagen, texto;

    public Publicaciones(String id, String usario, String linkImagen, String texto) {
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

    public JSONObject infoEnJson(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", this.id);
            obj.put("usario", this.usario);
            obj.put("linkImagen", this.linkImagen);
            obj.put("texto", this.texto);
        } catch (JSONException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return obj;
    }
}
