package com.example.comelicioso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecuperarContrasenia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasenia);

        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();
        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.drawable.nombre);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    public void IniciarSesion(View view){
        Intent intent = new Intent(RecuperarContrasenia.this, Login.class);
        startActivity(intent);
        finish();
    }

    //Alert de Funcionalidad aun no disponible
    public void mostrarAlertDialog(View view){
        //Crear la instancia del Alert Dialog
        AlertDialog.Builder cuadroAlert = new AlertDialog.Builder(RecuperarContrasenia.this, R.style.AlertDialog);
        //Define el titulo del Cuadro de dialogo
        cuadroAlert.setTitle("Error");
        //Definir el mensaje y las opciones de respuesta del cuadro de dialogo
        cuadroAlert.setMessage("Está función no está disponible por el momento.")
                .setPositiveButton("Por supuesto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }//mostrarAlertDialog
}