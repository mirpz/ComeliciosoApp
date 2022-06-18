package com.example.comelicioso;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comelicioso.adaptadores.ListAdapterPublicaciones;
import com.example.comelicioso.adaptadores.ListAdapterRestaurantes;
import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.InfoRestaurantes;
import com.example.comelicioso.modelos.Publicaciones;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Restaurantes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Restaurantes extends Fragment {

    RecyclerView recyclerView;
    Global gb;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Restaurantes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Restaurantes.
     */
    // TODO: Rename and change types and number of parameters
    public static Restaurantes newInstance(String param1, String param2) {
        Restaurantes fragment = new Restaurantes();
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
        View vista = inflater.inflate(R.layout.fragment_restaurantes, container, false);
        gb = (Global)vista.getContext().getApplicationContext();
        ArrayList<InfoRestaurantes> elements = gb.getDatosRestaurantes();
        try {
            gb.setDatosRestaurantes(gb.obtenerRestaurantes(gb.abrirArchivo("restaurantes.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView = vista.findViewById(R.id.FRE_recviewRestaurantes);
        TextView txtSinRestaurantes  = vista.findViewById(R.id.FRE_txtVacio);

        txtSinRestaurantes.setVisibility((gb.getDatosRestaurantes().size()==0)?View.VISIBLE:View.GONE);
        ListAdapterRestaurantes listAdapter= new ListAdapterRestaurantes(gb.getDatosRestaurantes());

        listAdapter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mostrarDialogoPersonalizado(view, gb.getDatosRestaurantes().get(recyclerView.getChildAdapterPosition(view)));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(listAdapter);
        // Inflate the layout for this fragment
        return vista;
    }

    public void mostrarDialogoPersonalizado(View view, InfoRestaurantes info){
        //Crear la instancia del AlertDialog
        AlertDialog.Builder cuadroP= new AlertDialog.Builder(view.getContext(), R.style.AlertDialog);
        //Nueva vista para asociar con el cuadro personalizado
        View vistaCuadroP= LayoutInflater.from(view.getContext())
                .inflate(R.layout.dialog_detalles_restaurante, (ConstraintLayout) view.findViewById(R.id.DDR_contenedor));
        //Asociar el objeto AlertDialog con la vista

        cuadroP.setView(vistaCuadroP);

        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtNombre)).setText(info.getNombre());
        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtTipoComida)).setText(info.getTipoComida());
        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtCalificacionNumero)).setText(String.valueOf(info.getCalificacion()));
        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtUbicacion)).setText(info.getUbicacion());
        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtTelefono)).setText(info.getTelefono());
        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtHorario)).setText(info.textoHorarios());
        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtGastoAproximado)).setText(info.getCostoAproximado());

        //Construye el objeto AlertDialog
        final AlertDialog alertDialog = cuadroP.create();

        //Asociar con los botones del cuadro de dialogo
        Button si = vistaCuadroP.findViewById(R.id.DDR_btnAceptar);
        //Al boton aceptar se le genera un metodo de escucha
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();//Remover el cuadro
            }
        });
        //Mostrar el cuadro de dialogo personalizado
        alertDialog.show();
    }//mostrarDialogoPersonalizado
}