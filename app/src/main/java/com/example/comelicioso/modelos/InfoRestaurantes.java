package com.example.comelicioso.modelos;


import org.json.JSONException;
import org.json.JSONObject;

public class InfoRestaurantes {

    String id, nombre, tipoComida, ubicacion, telefono, costoAproximado;
    boolean enFavoritos, enProximos;
    double calificacion;
    int icon;
    String horarios [];

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnFavoritos() {
        return enFavoritos;
    }

    public void setEnFavoritos(boolean enFavoritos) {
        this.enFavoritos = enFavoritos;
    }

    public boolean isEnProximos() {
        return enProximos;
    }

    public void setEnProximos(boolean enProximos) {
        this.enProximos = enProximos;
    }


    public InfoRestaurantes(String id, String nombre, String tipoComida, String ubicacion, String telefono, String costoAproximado, double calificacion, String[] horarios) {
        this.id=id;
        this.nombre = nombre;
        this.tipoComida = tipoComida;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
        this.costoAproximado = costoAproximado;
        this.calificacion = calificacion;
        this.horarios = horarios;
        this.enFavoritos=false;
        this.enProximos=false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCostoAproximado() {
        return costoAproximado;
    }

    public void setCostoAproximado(String costoAproximado) {
        this.costoAproximado = costoAproximado;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String[] getHorarios() {
        return horarios;
    }

    public void setHorarios(String[] horarios) {
        this.horarios = horarios;
    }

    public String textoHorarios(){
        String texto = "";
        if(this.horarios.length==0){
            texto = "Sin horarios presentes";
        }else{
            for(int i=0; i<this.horarios.length; i++) {
                texto = texto + horarios[i];
                if(i<this.horarios.length-1){
                    texto = texto + "/";
                }
            }
        }
        return texto;
    }

    public JSONObject infoEnJson(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", this.id);
            obj.put("nombre", this.nombre);
            obj.put("tipoComida", this.tipoComida);
            obj.put("ubicacion", this.ubicacion);
            obj.put("telefono", this.telefono);
            obj.put("costoAproximado", this.costoAproximado);
            obj.put("enFavoritos", this.enFavoritos);
            obj.put("enProximos", this.enProximos);
            obj.put("calificacion", this.calificacion);
            obj.put("icon", this.icon);
            obj.put("horarios", this.textoHorarios());
        } catch (JSONException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return obj;
    }
}
