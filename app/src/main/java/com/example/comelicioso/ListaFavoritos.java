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
import android.widget.Toast;

import com.example.comelicioso.adaptadores.ListAdapterRestaurantes;
import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.InfoRestaurantes;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaFavoritos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaFavoritos extends Fragment {

    RecyclerView recyclerViewFV;
    Global gb;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListaFavoritos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaFavoritos.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaFavoritos newInstance(String param1, String param2) {
        ListaFavoritos fragment = new ListaFavoritos();
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
        View vista = inflater.inflate(R.layout.fragment_lista_favoritos, container, false);
        gb = (Global)vista.getContext().getApplicationContext();
        recyclerViewFV = vista.findViewById(R.id.FLF_recviewRestaurantes);
        TextView txtSinRestaurantes  = vista.findViewById(R.id.FLF_txtVacio);
        ArrayList<InfoRestaurantes> elem = listaFavoritos();

        txtSinRestaurantes.setVisibility((elem.size()==0)?View.VISIBLE:View.GONE);
        ListAdapterRestaurantes listAdapter= new ListAdapterRestaurantes(elem, idUsuario());
        recyclerViewFV.setLayoutManager(new LinearLayoutManager(vista.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerViewFV.setAdapter(listAdapter);

        // Inflate the layout for this fragment
        return vista;
    }

    public ArrayList<InfoRestaurantes> listaFavoritos(){
        ArrayList<InfoRestaurantes> list=new ArrayList<>();
        for(int h=0; h<gb.getDatosRestaurantes().size();h++){
            for(int i=0; i<gb.getListaUsuarios().get(Integer.parseInt(idUsuario())).getFavoritos().size();i++){
                if(gb.getListaUsuarios().get(Integer.parseInt(idUsuario())).getFavoritos().get(i)
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