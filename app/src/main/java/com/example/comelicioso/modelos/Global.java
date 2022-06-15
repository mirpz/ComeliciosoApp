package com.example.comelicioso.modelos;

import android.app.Application;

import java.util.ArrayList;

public class Global extends Application {
    ArrayList<InfoRestaurantes> datosRestaurantes;
    ArrayList<InfoRestaurantes> restaurantesFav;
    ArrayList<InfoRestaurantes> restaurantesProx;

    public ArrayList<InfoRestaurantes> getRestaurantesFav() {
        return restaurantesFav;
    }

    public void setRestaurantesFav(ArrayList<InfoRestaurantes> restaurantesFav) {
        this.restaurantesFav = restaurantesFav;
    }

    public ArrayList<InfoRestaurantes> getRestaurantesProx() {
        return restaurantesProx;
    }

    public void setRestaurantesProx(ArrayList<InfoRestaurantes> restaurantesProx) {
        this.restaurantesProx = restaurantesProx;
    }

    public ArrayList<InfoRestaurantes> getDatosRestaurantes() {
        return datosRestaurantes;
    }

    public void setDatosRestaurantes(ArrayList<InfoRestaurantes> datosRestaurantes) {
        this.datosRestaurantes = datosRestaurantes;
    }
}
