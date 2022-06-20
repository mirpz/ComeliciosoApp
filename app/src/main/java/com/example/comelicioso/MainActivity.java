package com.example.comelicioso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.comelicioso.modelos.Global;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global gb = (Global)getApplicationContext();
        gb.inicializacionArchivos();

        if(!gb.abrirArchivo(Global.nameFileUsuarios+Global.typeExtention).equals("")){
            try {
                gb.setListaUsuarios(gb.obtenerUsuarios(gb.abrirArchivo(Global.nameFileUsuarios+Global.typeExtention)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        return (preferences.getString("id","").equals(""))?false:true;
    }
}