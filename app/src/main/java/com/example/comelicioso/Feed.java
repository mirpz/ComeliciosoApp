package com.example.comelicioso;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.comelicioso.adaptadores.ListAdapterPublicaciones;
import com.example.comelicioso.modelos.PublicacionesEvaluaciones;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Feed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Feed extends Fragment {

    ArrayList<PublicacionesEvaluaciones> elements;
    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public Feed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Feed.
     */
    // TODO: Rename and change types and number of parameters
    public static Feed newInstance(String param1, String param2) {
        Feed fragment = new Feed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = vista.findViewById(R.id.FFE_recviewPublicaciones);
        TextView txtSinReservaciones  = vista.findViewById(R.id.FFE_txtVacio);
        elements = new ArrayList<>();

    //en este apatado es donde se tiene que realizar la busqueda y absorción de la información
        /*elements.add(new Publicaciones("1","@uno","", "Aqui estamos"));
        elements.add(new Publicaciones("1","@dos","","Aqui estamos"));
        elements.add(new Publicaciones("1","@tres","","Aqui estamos"));
        elements.add(new Publicaciones("1","@cuatro","","Aqui estamos"));
        elements.add(new Publicaciones("1","@cinco","","Aqui estamos"));*/

        txtSinReservaciones.setVisibility((elements.size()==0)?View.VISIBLE:View.GONE);
        ListAdapterPublicaciones listAdapter= new ListAdapterPublicaciones(elements);
        recyclerView.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(listAdapter);
        // Inflate the layout for this fragment
        return vista;
    }
}