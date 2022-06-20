package com.example.comelicioso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.InfoRestaurantes;
import com.example.comelicioso.modelos.Usuario;

import java.io.IOException;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ArrayList<Usuario> usuarios;
    EditText correo, contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();
        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.drawable.nombre);
        actionBar.setDisplayShowHomeEnabled(true);
        Global gb = (Global)getApplicationContext();
        usuarios = gb.getListaUsuarios();
        correo = findViewById(R.id.ALO_editEmail);
        contrasenia = findViewById(R.id.ALO_editContrasena);
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
        if(correo.getText().equals("")||contrasenia.getText().equals("")){
            Toast.makeText(this, "No se ha completado el fomulario.",Toast.LENGTH_SHORT).show();
        }else{
            if(validacionUsuario()!=-1){
                guardarPreferences(usuarios.get(validacionUsuario()));
                Intent intent = new Intent(Login.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "El usuario ingresado o la contraseña son incorrectos.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public int validacionUsuario(){
        for(int i=0; i<usuarios.size();i++){
            if(correo.getText().toString().equals(usuarios.get(i).getCorreo())&&
                    contrasenia.getText().toString().equals(usuarios.get(i).getContresenia())){
                return i;
            }
        }
        return -1;
    }

    private void guardarPreferences(Usuario usr){
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("id", usr.getId());
        editor.putString("usuario", usr.getNombre());
        editor.putString("contraseña", usr.getContresenia());
        editor.putString("correo", usr.getCorreo());
        editor.apply();
    }
}