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
import com.example.comelicioso.adaptadores.ListAdapterRestaurantes;
import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.Publicaciones;

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
        recyclerView = vista.findViewById(R.id.FRE_recviewRestaurantes);
        TextView txtSinRestaurantes  = vista.findViewById(R.id.FRE_txtVacio);

        txtSinRestaurantes.setVisibility((gb.getDatosRestaurantes().size()==0)?View.VISIBLE:View.GONE);
        ListAdapterRestaurantes listAdapter= new ListAdapterRestaurantes(gb.getDatosRestaurantes());
        recyclerView.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(listAdapter);
        // Inflate the layout for this fragment
        return vista;
    }
}