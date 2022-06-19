package com.example.comelicioso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.InfoRestaurantes;
import com.example.comelicioso.modelos.Publicaciones;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //en este apatado es donde se tiene que realizar la busqueda y absorción de la información
        /*
        elements.get(4).setEnFavoritos(true);
        elements.get(3).setEnFavoritos(true);

        elements.get(1).setEnProximos(true);*/

        /*elementsFav.add(elements.get(4));
        elementsFav.add(elements.get(3));

        elementsProx.add(elements.get(1));*/
        Global gb = (Global)getApplicationContext();
        gb.inicializacionArchivos();

        TimerTask tarea=new TimerTask(){

            @Override
            public void run() {
                Intent intent;
                if(nuevoUsuario()){
                    intent=new Intent(MainActivity.this, MenuActivity.class);
                }else{
                    intent=new Intent(MainActivity.this, Login.class);
                }
                startActivity(intent);
                finish();
            }
        };
        Timer tiempo= new Timer();
        tiempo.schedule(tarea, 2000);
    }

    private boolean nuevoUsuario(){
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        return preferences.getBoolean("registrado",false);
    }
}