package com.example.comelicioso.modelos;

public class Publicaciones {

    private String usario, linkImagen, texto;

    public Publicaciones(String usario, String linkImagen, String texto) {
        this.usario = usario;
        this.linkImagen = linkImagen;
        this.texto = texto;
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
