package com.example.comelicioso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Registrarse(View view){
        Intent intent = new Intent(Login.this, Registro.class);
        startActivity(intent);
        finish();
    }

    public void RecuperacionContrasena(View view){
        Intent intent = new Intent(Login.this, Registro.class);
        startActivity(intent);
    }
}