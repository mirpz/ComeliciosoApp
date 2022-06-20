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
import com.example.comelicioso.modelos.Reservaciones;

import java.util.ArrayList;

public class EvaluacionActivity extends AppCompatActivity {

    EditText opinion, gasto, tiempo, asistentes;
    ImageView imagen;
    ArrayList<Reservaciones> reservaciones;
    int index;
    Global gb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_evaluacion_restaurante);
        gb = (Global) getApplicationContext();
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
        imagen = findViewById(R.id.DER_imgVFoto);
        Button btn = findViewById(R.id.DER_btnFoto);
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

                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarImagen();
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

    private void cargarImagen(){
        Intent intent=new Intent (Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/" );
        startActivityForResult(intent.createChooser(intent, "Seleccione"), 10);
    }

    public void saveData(){
        gb.getListaUsuarios().get(Integer.parseInt(idUsuario())).setReservaciones(reservaciones);
        gb.guardarArchivo(Global.nameFileUsuarios+Global.typeExtention,"");
        gb.guardarArchivo(Global.nameFileUsuarios+Global.typeExtention,gb.crearJsonUsuarios(gb.getListaUsuarios()).toString());
    }

    private String nombreUsuario(){
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        return preferences.getString("usuario","");
    }

    private String idUsuario(){
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        return preferences.getString("id","");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri path = data.getData();
            Toast.makeText(this,""+path, Toast.LENGTH_SHORT).show();
            imagen.setImageURI(path);
        }
    }
}
