package com.example.comelicioso;

import android.content.SharedPreferences;
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
import android.widget.ImageView;
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
        try {
            if(gb.abrirArchivo(Global.nameFileRestaurantes+Global.typeExtention).equals("")){
                ArrayList<InfoRestaurantes> elements = new ArrayList<>();
                elements.add(new InfoRestaurantes("1",
                        "Sagrantino",
                        "Alta Cocina",
                        "Diag. Golfo de Cortés 4152, Monraz, 44670 Guadalajara, Jal.",
                        "33 3813 1379",
                        "300 - 400",
                        4.6,
                        new String[]{"L:13:00-00:00", "MA:13:00-00:00", "MI:13:00-00:00", "J:13:00-00:00", "V:13:00-01:00", "S:13:00-01:00","D:13:00-22:00"}));
                elements.get(0).setIcon(R.mipmap.sangrantino);
                elements.add(new InfoRestaurantes("2",
                        "Magno Brasserie",
                        "Francesa",
                        "C. José Guadalupe Zuno Hernández 2061, Col Americana, Americana, 44160 Guadalajara, Jal.",
                        "33 2001 0724",
                        "500 - 800",
                        4.6,
                        new String[]{"L:Cerrado", "MA:14:00-23:00", "MI:14:00-23:00", "J:14:00-23:00", "V:14:00-23:00", "S:14:00-23:00","D:14:00-18:00"}));
                elements.get(1).setIcon(R.mipmap.magno);
                elements.add(new InfoRestaurantes("3",
                        "Porfirio's Guadalajara",
                        "Mexicana",
                        "Punto, São Paulo 2334 A, Providencia, 44630 Guadalajara, Jal.",
                        "33 1001 7729",
                        "200 - 500",
                        4.4,
                        new String[]{"L:14:00-23:00", "MA:14:00-23:00", "MI:14:00-23:00", "J:14:00-01:00", "V:14:00-01:00", "S:14:00-01:00","D:14:00-23:00"}));
                elements.get(2).setIcon(R.mipmap.porfirios);
                elements.add(new InfoRestaurantes("4",
                        "Brick SteakHouse",
                        "Carnes",
                        "Av. de las Américas 1254, Country Club, 44610 Guadalajara, Jal.",
                        "33 2471 7066",
                        "300 - 500",
                        4.6,
                        new String[]{"L:13:00-00:00", "MA:13:00-00:00", "MI:13:00-00:00", "J:13:00-00:00", "V:13:00-00:00", "S:13:00-00:00","D:13:00-19:00"}));
                elements.get(3).setIcon(R.mipmap.brick);
                elements.add(new InfoRestaurantes("5",
                        "Suehiro",
                        "Japonecesa",
                        "Av. de la Paz 1701, Col Americana, Americana, 44160 Guadalajara, Jal.",
                        "33 3826 0094",
                        "300 - 700",
                        4.7,
                        new String[]{"L:13:30-23:00", "MA:13:30-23:00", "MI:13:30-23:00", "J:13:30-23:00", "V:13:30-23:00", "S:13:30-23:00","D:13:30-18:00"}));
                elements.get(4).setIcon(R.mipmap.suehiro);
                gb.guardarArchivo(Global.nameFileRestaurantes+Global.typeExtention,gb.crearJsonRestaurantes(elements).toString());
            }
            gb.setDatosRestaurantes(gb.obtenerRestaurantes(gb.abrirArchivo(Global.nameFileRestaurantes+Global.typeExtention)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        recyclerView = vista.findViewById(R.id.FRE_recviewRestaurantes);
        TextView txtSinRestaurantes  = vista.findViewById(R.id.FRE_txtVacio);



        txtSinRestaurantes.setVisibility((gb.getDatosRestaurantes().size()==0)?View.VISIBLE:View.GONE);
        ListAdapterRestaurantes listAdapter= new ListAdapterRestaurantes(gb.getDatosRestaurantes(), idUsuario());

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
        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtHorario)).setText(gb.arryToString(info.getHorarios(),"/","Sin horarios presentes"));
        ((TextView)vistaCuadroP.findViewById(R.id.DDR_txtGastoAproximado)).setText(info.getCostoAproximado());
        if(info.getIcon()!=0){
            ((ImageView)vistaCuadroP.findViewById(R.id.DDR_iconRestaurant)).setImageResource(info.getIcon());
        }

        //Construye el objeto AlertDialog
        final AlertDialog alertDialog = cuadroP.create();

        //Asociar con los botones del cuadro de dialogo
        ((Button) vistaCuadroP.findViewById(R.id.DDR_btnAceptar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();//Remover el cuadro
            }
        });
        //Mostrar el cuadro de dialogo personalizado
        alertDialog.show();
    }//mostrarDialogoPersonalizado

    private String idUsuario(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("user.dat", this.getActivity().MODE_PRIVATE);
        return preferences.getString("id","");
    }
}