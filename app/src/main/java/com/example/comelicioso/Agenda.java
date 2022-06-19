package com.example.comelicioso;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.comelicioso.adaptadores.ListAdapterOldReservaciones;
import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.Reservaciones;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Agenda#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Agenda extends Fragment {

    ArrayList<Reservaciones> elements;
    RecyclerView recyclerView;
    ListAdapterOldReservaciones listAdapter;
    Global gb;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Agenda() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Agenda.
     */
    // TODO: Rename and change types and number of parameters
    public static Agenda newInstance(String param1, String param2) {
        Agenda fragment = new Agenda();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_agenda, container, false);
        recyclerView = vista.findViewById(R.id.FAG_recyclerViewReservaciones);
        TextView txtSinReservaciones = vista.findViewById(R.id.FAG_txtVacio);
        ImageButton imgAddReservacion = vista.findViewById(R.id.FAG_imgBtnNuevaReservacion);
        gb = (Global)vista.getContext().getApplicationContext();
        /*try {
            elements = gb.obtenerReservaciones(gb.abrirArchivo(Global.nameFileReservaciones));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        txtSinReservaciones.setVisibility((elements.size()==0)?View.VISIBLE:View.GONE);
        listAdapter= new ListAdapterOldReservaciones(elements);
        listAdapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mostrarDialogoConInfoReservacion(view, elements.get(recyclerView.getChildAdapterPosition(view)),recyclerView.getChildAdapterPosition(view));
            }
        });

        imgAddReservacion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mostrarDialogNuevaReservacion(view);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(listAdapter);
        return vista;
    }

    private String [] listaDeRestaurantes(){
        String restau [] = new String[gb.getDatosRestaurantes().size()];
        for(int i=0; i<gb.getDatosRestaurantes().size(); i++){
            restau[i]=gb.getDatosRestaurantes().get(i).getNombre();
        }
        return restau;
    }

    private String [] listaDeIdsRestaurantes(){
        String restau [] = new String[gb.getDatosRestaurantes().size()];
        for(int i=0; i<gb.getDatosRestaurantes().size(); i++){
            restau[i]=gb.getDatosRestaurantes().get(i).getId();
        }
        return restau;
    }

    @SuppressLint("SetTextI18n")
    private void mostrarDialogNuevaReservacion(View view) {
        AlertDialog.Builder cuadroP = new AlertDialog.Builder(view.getContext(), R.style.AlertDialog);
        View vistaCuadroP = LayoutInflater.from(view.getContext())
                .inflate(R.layout.dialog_formulario_reservacion, (ConstraintLayout) view.findViewById(R.id.DFR_contenedor));

        final int[] restaurante = {0};
        Calendar calendario = Calendar.getInstance();

        cuadroP.setView(vistaCuadroP);

        EditText reservado, fecha, tiempo, asistentes;
        reservado = (EditText)vistaCuadroP.findViewById(R.id.DFR_edtReservadoPor);
        asistentes = (EditText)vistaCuadroP.findViewById(R.id.DFR_edtNumeroAsistentes);
        fecha = (EditText) vistaCuadroP.findViewById(R.id.DFR_edtFechaReservacion);
        tiempo = (EditText) vistaCuadroP.findViewById(R.id.DFR_edtHoraReservacion);

        DatePickerDialog.OnDateSetListener date= new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendario.set(Calendar. YEAR, i);
                calendario.set(Calendar.MONTH, i1);
                calendario.set(Calendar.DAY_OF_MONTH, i2);
                updateCalendar();
            }
            private void updateCalendar (){
                String Format="dd-MM-yyyy";
                SimpleDateFormat sdf =new SimpleDateFormat(Format, Locale.US);
                fecha.setText(sdf.format(calendario.getTime()));
            }
        };
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(view.getContext(),
                        date,
                        calendario.get(Calendar.YEAR),
                        calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tiempo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new TimePickerDialog(
                        view.getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendario.set(Calendar.HOUR_OF_DAY,i);
                        calendario.set(Calendar.MINUTE,i);
                        String Format="HH:mm";
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf =new SimpleDateFormat(Format);
                        tiempo.setText(sdf.format(calendario.getTime()));
                    }
                },calendario.get(Calendar.HOUR_OF_DAY),calendario.get(Calendar.MINUTE),true).show();
            }
        });

        String[] valores = listaDeRestaurantes();
        String[] ids = listaDeIdsRestaurantes();

        ((Spinner) vistaCuadroP.findViewById(R.id.DFR_spinRestaurantes)).setAdapter(new ArrayAdapter<String>(vistaCuadroP.getContext(), android.R.layout.simple_spinner_item, valores));
        ((Spinner) vistaCuadroP.findViewById(R.id.DFR_spinRestaurantes)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                restaurante[0]=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });

        final AlertDialog alertDialog = cuadroP.create();

        ((Button) vistaCuadroP.findViewById(R.id.DFR_btnCancelar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        ((Button) vistaCuadroP.findViewById(R.id.DFR_btnAceptar)).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                if(reservado.getText().equals("")||
                        asistentes.getText().toString().isEmpty()||
                        tiempo.getText().equals("")||
                        fecha.getText().equals("")){
                    Toast.makeText(view.getContext(),"No se ha completado el formulario", Toast.LENGTH_SHORT).show();

                }else{
                    elements.add(0,new Reservaciones(""+(elements.size()+1),
                            ids[restaurante[0]],
                            valores[restaurante[0]],
                            fecha.getText().toString(),
                            tiempo.getText().toString(),
                            asistentes.getText().toString(),
                            reservado.getText().toString()));
                    //saveData();
                    listAdapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                }
            }
        });
        //Mostrar el cuadro de dialogo personalizado
        alertDialog.show();
    }

    private void mostrarDialogoConInfoReservacion(View view, Reservaciones reservaciones, int index) {
        //Crear la instancia del AlertDialog
        AlertDialog.Builder cuadroP= new AlertDialog.Builder(view.getContext(), R.style.AlertDialog);
        //Nueva vista para asociar con el cuadro personalizado
        View vistaCuadroP= LayoutInflater.from(view.getContext())
                .inflate(R.layout.dialog_formulario_reservacion, (ConstraintLayout) view.findViewById(R.id.DFR_contenedor));
        //Asociar el objeto AlertDialog con la vista

        cuadroP.setView(vistaCuadroP);

        EditText reservado, fecha, tiempo, asistentes;
        reservado = (EditText)vistaCuadroP.findViewById(R.id.DFR_edtReservadoPor);
        asistentes = (EditText)vistaCuadroP.findViewById(R.id.DFR_edtNumeroAsistentes);
        fecha = (EditText) vistaCuadroP.findViewById(R.id.DFR_edtFechaReservacion);
        tiempo = (EditText) vistaCuadroP.findViewById(R.id.DFR_edtHoraReservacion);

        String[] valores= {reservaciones.getRestaurante()};
        ((Spinner)vistaCuadroP.findViewById(R.id.DFR_spinRestaurantes)).setAdapter(new ArrayAdapter<String>(vistaCuadroP.getContext(), android.R.layout.simple_spinner_item, valores));

        //Construye el objeto AlertDialog
        final AlertDialog alertDialog = cuadroP.create();

        Button eliminar = vistaCuadroP.findViewById(R.id.DFR_btnCancelar);
        eliminar.setText("Eliminar");
        eliminar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                elements.remove(index);
                //saveData();
                listAdapter.notifyItemRemoved(index);
                alertDialog.dismiss();
            }
        });

        //Asociar con los botones del cuadro de dialogo
        ((Button) vistaCuadroP.findViewById(R.id.DFR_btnAceptar)).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                if(reservado.getText().equals("")||
                        asistentes.getText().toString().isEmpty()||
                        tiempo.getText().equals("")||
                        fecha.getText().equals("")){
                    Toast.makeText(view.getContext(),"No se ha completado el formulario", Toast.LENGTH_SHORT).show();

                }else{
                    if(reservado.getText().equals(elements.get(index).getReservadoPor())||
                            asistentes.getText().toString().equals(elements.get(index).getAsistentes())||
                            tiempo.getText().equals(elements.get(index).getHora())||
                            fecha.getText().equals(elements.get(index).getFecha())){
                        alertDialog.dismiss();
                    }else{
                        elements.get(index).setFecha(fecha.getText().toString());
                        elements.get(index).setHora(tiempo.getText().toString());
                        elements.get(index).setAsistentes(asistentes.getText().toString());
                        elements.get(index).setReservadoPor(reservado.getText().toString());
                        //saveData();
                        listAdapter.notifyDataSetChanged();
                        alertDialog.dismiss();
                    }
                }
                alertDialog.dismiss();//Remover el cuadro
            }
        });
        //Mostrar el cuadro de dialogo personalizado
        alertDialog.show();
    }

    public void saveData(){
        gb.guardarArchivo(Global.nameFileReservaciones+Global.typeExtention,"");
        gb.guardarArchivo(Global.nameFileReservaciones+Global.typeExtention,gb.crearJsonReservaciones(elements).toString());
    }
}