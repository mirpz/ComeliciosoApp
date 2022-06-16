package com.example.comelicioso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.InfoRestaurantes;
import com.example.comelicioso.modelos.Publicaciones;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<InfoRestaurantes> elements = new ArrayList<>();
        ArrayList<InfoRestaurantes> elementsFav = new ArrayList<>();
        ArrayList<InfoRestaurantes> elementsProx = new ArrayList<>();

        //en este apatado es donde se tiene que realizar la busqueda y absorción de la información
        elements.add(new InfoRestaurantes("@uno","italiana", "Aqui estamos",
                "3333333333","300 - 400", 4.5f,
                new String[]{"L:9:00-5:00", "MA:9:00-5:00", "MI:9:00-5:00", "J:9:00-5:00", "V:9:00-5:00", "S:9:00-5:00","D:Cerrado"}));
        elements.add(new InfoRestaurantes("@dos","mexicana","Aqui estamos",
                "3333333333","300 - 400",5.0f,
                new String[]{"L:9:00-5:00", "MA:9:00-5:00", "MI:9:00-5:00", "J:9:00-5:00", "V:9:00-5:00", "S:9:00-5:00","D:Cerrado"}));
        elements.add(new InfoRestaurantes("@tres","rapida","Aqui estamos",
                "3333333333","300 - 400",3.0f,
                new String[]{"L:9:00-5:00", "MA:9:00-5:00", "MI:9:00-5:00", "J:9:00-5:00", "V:9:00-5:00", "S:9:00-5:00","D:Cerrado"}));
        elements.add(new InfoRestaurantes("@cuatro","francesa","Aqui estamos",
                "3333333333","300 - 400",4.0f,
                new String[]{"L:9:00-5:00", "MA:9:00-5:00", "MI:9:00-5:00", "J:9:00-5:00", "V:9:00-5:00", "S:9:00-5:00","D:Cerrado"}));
        elements.add(new InfoRestaurantes("@cinco","indu","Aqui estamos",
                "3333333333","300 - 400",1.8f,
                new String[]{"L:9:00-5:00", "MA:9:00-5:00", "MI:9:00-5:00", "J:9:00-5:00", "V:9:00-5:00", "S:9:00-5:00","D:Cerrado"}));
        elements.get(4).setEnFavoritos(true);
        elements.get(3).setEnFavoritos(true);

        elements.get(1).setEnProximos(true);

        /*elementsFav.add(elements.get(4));
        elementsFav.add(elements.get(3));

        elementsProx.add(elements.get(1));*/

        Global gb = (Global)getApplicationContext();
        gb.setDatosRestaurantes(elements);
        gb.setRestaurantesFav(elementsFav);
        gb.setRestaurantesProx(elementsProx);

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