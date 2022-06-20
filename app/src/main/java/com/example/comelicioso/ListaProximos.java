package com.example.comelicioso;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.comelicioso.adaptadores.ListAdapterRestaurantes;
import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.InfoRestaurantes;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaProximos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaProximos extends Fragment {

    RecyclerView recyclerViewPX;
    Global gb;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaProximos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaProximos.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaProximos newInstance(String param1, String param2) {
        ListaProximos fragment = new ListaProximos();
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


        View vista = inflater.inflate(R.layout.fragment_lista_proximos, container, false);
        gb = (Global)vista.getContext().getApplicationContext();
        recyclerViewPX = vista.findViewById(R.id.FLP_recviewRestaurantes);
        TextView txtSinRestaurantes  = vista.findViewById(R.id.FLP_txtVacio);
        ArrayList<InfoRestaurantes> elem = listaProximos();
        txtSinRestaurantes.setVisibility((elem.size()==0)?View.VISIBLE:View.GONE);
        ListAdapterRestaurantes listAdapter= new ListAdapterRestaurantes(elem, idUsuario());
        recyclerViewPX.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerViewPX.setAdapter(listAdapter);
        // Inflate the layout for this fragment
        return vista;
    }

    public ArrayList<InfoRestaurantes> listaProximos(){
        ArrayList<InfoRestaurantes> list=new ArrayList<>();
        for(int h=0; h<gb.getDatosRestaurantes().size();h++){
            for(int i=0; i<gb.getListaUsuarios().get(Integer.parseInt(idUsuario())).getProximos().size();i++){
                if(gb.getListaUsuarios().get(Integer.parseInt(idUsuario())).getProximos().get(i)
                        .equals(gb.getDatosRestaurantes().get(h).getId())){
                    list.add(gb.getDatosRestaurantes().get(h));
                }
            }
        }
        return list;
    }

    private String idUsuario(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("user.dat", this.getActivity().MODE_PRIVATE);
        return preferences.getString("id","");
    }
}