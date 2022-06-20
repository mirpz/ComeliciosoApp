package com.example.comelicioso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.Usuario;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {

    EditText nombre, correo, contasenia;
    Global gb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Obtenemos la ActionBar instalada por AppCompatActivity
        ActionBar actionBar = getSupportActionBar();
        //Establecemos el icono en la ActionBar
        actionBar.setIcon(R.drawable.nombre);
        actionBar.setDisplayShowHomeEnabled(true);
        gb = (Global)getApplicationContext();
        
        nombre = findViewById(R.id.ARE_editTNombre);
        correo = findViewById(R.id.ARE_editEmail);
        contasenia = findViewById(R.id.ARE_editContrasena);
    }

    public void Registrar(View view){
        if(nombre.getText().equals("")||correo.getText().equals("")||contasenia.getText().equals("")){
            Toast.makeText(view.getContext(),"No se completo el formulario. Es necesario completarlo",Toast.LENGTH_SHORT).show();
        }else{
            ArrayList<Usuario> usuarios = gb.getListaUsuarios();
            usuarios.add(new Usuario(""+usuarios.size(),
                    nombre.getText().toString(),
                    correo.getText().toString(),
                    contasenia.getText().toString()));
            gb.setListaUsuarios(usuarios);
            gb.guardarArchivo(Global.nameFileUsuarios+Global.typeExtention,gb.crearJsonUsuarios(usuarios).toString());
            Toast.makeText(view.getContext(),gb.abrirArchivo(Global.nameFileUsuarios+Global.typeExtention),Toast.LENGTH_SHORT).show();
            nombre.setText("");
            correo.setText("");
            contasenia.setText("");
            Intent intent = new Intent(Registro.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

}