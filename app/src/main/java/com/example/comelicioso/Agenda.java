package com.example.comelicioso;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.comelicioso.adaptadores.ListAdapterOldReservaciones;
import com.example.comelicioso.modelos.Reservaciones;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Agenda#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Agenda extends Fragment {

    ArrayList<Reservaciones> elements;
    RecyclerView recyclerView;
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
        elements = new ArrayList<>();

        //en este apatado es donde se tiene que realizar la busqueda y absorción de la información

        elements.add(new Reservaciones("@uno","12-08-2022", "13:40:00","3"));
        elements.add(new Reservaciones("@dos","12-08-2022", "13:40:00","2"));
        elements.add(new Reservaciones("@tres","12-08-2022", "13:40:00","1"));
        elements.add(new Reservaciones("@cuatro","12-08-2022", "13:40:00","5"));
        elements.add(new Reservaciones("@cinco","12-08-2022", "13:40:00","2"));
        elements.add(new Reservaciones("@seis","12-08-2022", "13:40:00","3"));
        elements.add(new Reservaciones("@siete","12-08-2022", "13:40:00","10"));

        txtSinReservaciones.setVisibility((elements.size()==0)?View.VISIBLE:View.GONE);
        ListAdapterOldReservaciones listAdapter= new ListAdapterOldReservaciones(elements);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(listAdapter);
        // Inflate the layout for this fragment
        return vista;
    }
}