package com.example.comelicioso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registro extends AppCompatActivity {

    EditText nombre, correo, contasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();
        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.drawable.nombre);
        actionBar.setDisplayShowHomeEnabled(true);
        
        nombre = findViewById(R.id.ARE_editTNombre);
    }

    public void IniciarSesion(View view){
        Intent intent = new Intent(Registro.this, Login.class);
        startActivity(intent);
        finish();
    }

}