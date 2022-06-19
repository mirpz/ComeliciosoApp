package com.example.comelicioso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.Toast;

import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.InfoRestaurantes;

import java.io.IOException;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void Registrarse(View view){
        Intent intent = new Intent(Login.this, Registro.class);
        startActivity(intent);
    }

    public void RecuperacionContrasena(View view){
        Intent intent = new Intent(Login.this, RecuperarContrasenia.class);
        startActivity(intent);
    }

    public void IniciarSesion(View view){
        Intent intent = new Intent(Login.this, MenuActivity.class);
        startActivity(intent);
        finish();
    }
}