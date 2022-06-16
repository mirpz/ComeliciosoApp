package com.example.comelicioso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecuperarContrasenia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasenia);
    }

    public void IniciarSesion(View view){
        Intent intent = new Intent(RecuperarContrasenia.this, Login.class);
        startActivity(intent);
        finish();
    }
}