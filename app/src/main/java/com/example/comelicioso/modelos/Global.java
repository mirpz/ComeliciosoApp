package com.example.comelicioso.modelos;

import android.app.Application;

import java.util.ArrayList;

public class Global extends Application {
    ArrayList<InfoRestaurantes> datosRestaurantes;

    public ArrayList<InfoRestaurantes> getDatosRestaurantes() {
        return datosRestaurantes;
    }

    public void setDatosRestaurantes(ArrayList<InfoRestaurantes> datosRestaurantes) {
        this.datosRestaurantes = datosRestaurantes;
    }
}
