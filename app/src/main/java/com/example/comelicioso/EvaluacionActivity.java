package com.example.comelicioso;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.PublicacionesEvaluaciones;
import com.example.comelicioso.modelos.Reservaciones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class EvaluacionActivity extends AppCompatActivity {

    EditText opinion, gasto, tiempo, asistentes;
    ArrayList<Reservaciones> reservaciones;
    ArrayList<PublicacionesEvaluaciones> publicaciones;
    int index;
    Global gb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_evaluacion_restaurante);
        gb = (Global) getApplicationContext();
        try {
            publicaciones=gb.obtenerPublicaciones(gb.abrirArchivo(Global.nameFilePublicaciones+Global.typeExtention));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();
        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.drawable.nombre);
        actionBar.setDisplayShowHomeEnabled(true);


        reservaciones = (ArrayList<Reservaciones>) getIntent().getSerializableExtra("reservacion");
        String idReservacion = getIntent().getStringExtra("idReservacion");
        index = returnIndex(idReservacion);

        opinion = findViewById(R.id.DER_edtOpinion);
        gasto = findViewById(R.id.DER_edtGasto);
        tiempo = findViewById(R.id.DER_edtTiempo);
        asistentes = findViewById(R.id.DER_editPersonas);
        Button btnAceptar = findViewById(R.id.DER_btnAceptar);
        Button btnCancelar = findViewById(R.id.DER_btnCancelar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(opinion.getText().toString().equals("")||
                        gasto.getText().toString().equals("")||
                        tiempo.getText().toString().equals("")||
                        asistentes.getText().toString().equals("")){
                    Toast.makeText(view.getContext(),"No se ha completado el formulario. La imagen es opcional", Toast.LENGTH_SHORT).show();
                }else{
                    reservaciones.get(index).setEvaluado(true);
                    publicaciones.add(new PublicacionesEvaluaciones(
                            ""+publicaciones.size(),
                            nombreUsuario(),
                            opinion.getText().toString(),
                            reservaciones.get(index).getRestaurante(),
                            reservaciones.get(index).getFecha(),
                            Integer.parseInt(asistentes.getText().toString()),
                            Integer.parseInt(gasto.getText().toString()),
                            Integer.parseInt(tiempo.getText().toString()),
                            (Double.parseDouble(gasto.getText().toString())/Double.parseDouble(asistentes.getText().toString()))
                            ));
                    saveData();
                    finish();
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public int returnIndex(String id){
        for(int i=0;i<reservaciones.size();i++){
            if(reservaciones.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public void saveData(){
        gb.getListaUsuarios().get(Integer.parseInt(idUsuario())).setReservaciones(reservaciones);
        gb.guardarArchivo(Global.nameFileUsuarios+Global.typeExtention,"");
        gb.guardarArchivo(Global.nameFileUsuarios+Global.typeExtention,gb.crearJsonUsuarios(gb.getListaUsuarios()).toString());
        gb.guardarArchivo(Global.nameFilePublicaciones+Global.typeExtention,"");
        gb.guardarArchivo(Global.nameFilePublicaciones+Global.typeExtention,gb.crearJsonPublicaciones(publicaciones).toString());
    }

    private String nombreUsuario(){
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        return preferences.getString("usuario","");
    }

    private String idUsuario(){
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        return preferences.getString("id","");
    }
}
