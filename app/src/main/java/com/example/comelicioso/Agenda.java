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

import java.util.ArrayList;
import java.util.Calendar;
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
        elements = new ArrayList<>();

        //en este apatado es donde se tiene que realizar la busqueda y absorción de la información

        elements.add(new Reservaciones("@uno","12-08-2022", "13:40","3", "Cesar"));

        txtSinReservaciones.setVisibility((elements.size()==0)?View.VISIBLE:View.GONE);
        listAdapter= new ListAdapterOldReservaciones(elements);
        listAdapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mostrarDialogoConInfoReservacion(view, elements.get(recyclerView.getChildAdapterPosition(view)));
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

    @SuppressLint("SetTextI18n")
    private void mostrarDialogNuevaReservacion(View view) {
        AlertDialog.Builder cuadroP = new AlertDialog.Builder(view.getContext(), R.style.AlertDialog);
        View vistaCuadroP = LayoutInflater.from(view.getContext())
                .inflate(R.layout.dialog_formulario_reservacion, (ConstraintLayout) view.findViewById(R.id.DFR_contenedor));

        final String[] restaurante = {null};
        Calendar calendario = Calendar.getInstance(TimeZone.getTimeZone("CDT"));
        int dia = calendario.get(Calendar.DAY_OF_MONTH), mes = calendario.get(Calendar.MONTH), anio = calendario.get(Calendar.YEAR), hora = calendario.get(Calendar.HOUR_OF_DAY),
                minutos = calendario.get(Calendar.MINUTE);

        cuadroP.setView(vistaCuadroP);

        EditText reservado, fecha, tiempo, asistentes;
        reservado = (EditText)vistaCuadroP.findViewById(R.id.DFR_edtReservadoPor);
        asistentes = (EditText)vistaCuadroP.findViewById(R.id.DFR_edtNumeroAsistentes);
        fecha = (EditText) vistaCuadroP.findViewById(R.id.DFR_edtFechaReservacion);
        tiempo = (EditText) vistaCuadroP.findViewById(R.id.DFR_edtHoraReservacion);
        tiempo.setText(hora + ":" + minutos);
        fecha.setText(dia + "-" + mes + "-" + anio);

        String[] valores = listaDeRestaurantes();
        ((Spinner) vistaCuadroP.findViewById(R.id.DFR_spinRestaurantes)).setAdapter(new ArrayAdapter<String>(vistaCuadroP.getContext(), android.R.layout.simple_spinner_item, valores));
        ((Spinner) vistaCuadroP.findViewById(R.id.DFR_spinRestaurantes)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                restaurante[0] = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });

        final AlertDialog alertDialog = cuadroP.create();

        Button cancel = vistaCuadroP.findViewById(R.id.DFR_btnCancelar);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();//Remover el cuadro
            }
        });

        Button aceptar = vistaCuadroP.findViewById(R.id.DFR_btnAceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reservado.getText().equals("")||
                        asistentes.getText().toString().isEmpty()||
                        tiempo.getText().equals("")||
                        fecha.getText().equals("")){
                    Toast.makeText(view.getContext(),"No se ha completado el formulario", Toast.LENGTH_SHORT).show();

                }else{
                    elements.add(0,new Reservaciones(restaurante[0],
                            fecha.getText().toString(),
                            tiempo.getText().toString(),
                            asistentes.getText().toString(),
                            reservado.getText().toString()));
                    listAdapter.updateData(elements);
                    recyclerView.setAdapter(listAdapter);
                    alertDialog.dismiss();
                }
            }
        });
        //Mostrar el cuadro de dialogo personalizado
        alertDialog.show();
    }

    private void mostrarDialogoConInfoReservacion(View view, Reservaciones reservaciones) {
        //Crear la instancia del AlertDialog
        AlertDialog.Builder cuadroP= new AlertDialog.Builder(view.getContext(), R.style.AlertDialog);
        //Nueva vista para asociar con el cuadro personalizado
        View vistaCuadroP= LayoutInflater.from(view.getContext())
                .inflate(R.layout.dialog_formulario_reservacion, (ConstraintLayout) view.findViewById(R.id.DFR_contenedor));
        //Asociar el objeto AlertDialog con la vista

        cuadroP.setView(vistaCuadroP);

        ((EditText)vistaCuadroP.findViewById(R.id.DFR_edtReservadoPor)).setText(reservaciones.getReservadoPor());
        ((EditText)vistaCuadroP.findViewById(R.id.DFR_edtReservadoPor)).setEnabled(false);
        ((EditText)vistaCuadroP.findViewById(R.id.DFR_edtNumeroAsistentes)).setText(reservaciones.getAsistentes());
        ((EditText)vistaCuadroP.findViewById(R.id.DFR_edtNumeroAsistentes)).setEnabled(false);
        ((EditText)vistaCuadroP.findViewById(R.id.DFR_edtHoraReservacion)).setText(reservaciones.getHora());
        ((EditText)vistaCuadroP.findViewById(R.id.DFR_edtHoraReservacion)).setEnabled(false);
        ((EditText)vistaCuadroP.findViewById(R.id.DFR_edtFechaReservacion)).setText(reservaciones.getFecha());
        ((EditText)vistaCuadroP.findViewById(R.id.DFR_edtFechaReservacion)).setEnabled(false);

        String[] valores= {reservaciones.getRestaurante()};
        ((Spinner)vistaCuadroP.findViewById(R.id.DFR_spinRestaurantes)).setAdapter(new ArrayAdapter<String>(vistaCuadroP.getContext(), android.R.layout.simple_spinner_item, valores));

        ((Button)vistaCuadroP.findViewById(R.id.DFR_btnCancelar)).setVisibility(View.INVISIBLE);

        //Construye el objeto AlertDialog
        final AlertDialog alertDialog = cuadroP.create();

        //Asociar con los botones del cuadro de dialogo
        Button si = vistaCuadroP.findViewById(R.id.DFR_btnAceptar);
        //Al boton aceptar se le genera un metodo de escucha
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();//Remover el cuadro
            }
        });
        //Mostrar el cuadro de dialogo personalizado
        alertDialog.show();
    }
}