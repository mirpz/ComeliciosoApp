package com.example.comelicioso.controladores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.comelicioso.Agenda;
import com.example.comelicioso.Feed;
import com.example.comelicioso.Lista;
import com.example.comelicioso.ListaFavoritos;
import com.example.comelicioso.ListaProximos;
import com.example.comelicioso.Restaurantes;

public class ControlMenuListas extends FragmentPagerAdapter {
    int numOpciones;

    public ControlMenuListas(FragmentManager fm, int behavior) {
        super(fm, behavior);//Variable para obtener cantidad de opciones del menú
        numOpciones = behavior;
    }//ControladorMenu

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ListaFavoritos();
            case 1: return new ListaProximos();
            default: return null;
        }//switch
    }//getItem

    @Override
    public int getCount() {
        //Retorna el número de opción
        return numOpciones;
    }//getCount
}