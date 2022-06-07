package com.example.comelicioso;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comelicioso.controladores.ControlMenuListas;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Lista#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Lista extends Fragment {

    TabLayout menu;
    ViewPager escenario;
    ControlMenuListas menuCtrl;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Lista() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Lista.
     */
    // TODO: Rename and change types and number of parameters
    public static Lista newInstance(String param1, String param2) {
        Lista fragment = new Lista();
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
        View vista = inflater.inflate(R.layout.fragment_lista, container, false);
        //Asociar instancias con componentes
        menu = vista.findViewById(R.id.FLI_menuListas);
        escenario = vista.findViewById(R.id.FLI_viepagContenedor);
        //Se crea una instancia del controlador de menu
        menuCtrl = new ControlMenuListas(getActivity().getSupportFragmentManager(),menu.getTabCount());
        //Se establece quien controla el cambio de opciones
        escenario.setAdapter(menuCtrl);
        //Se crea un escucha para el menú, que permita definir acción por pestaña
        menu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Se identifica la opción elegida
                escenario.setCurrentItem(tab.getPosition());
                //Para opción elegida, notifica el cambio
                switch (tab.getPosition()) {
                    case 0:
                    case 1:
                        menuCtrl.notifyDataSetChanged();
                        break;
                }
            }//onTabSelected

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }//onTabUnselected

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }//onTabReselected
        });
        //Asocia el menu con el Viewpager
        escenario.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(menu));
        // Inflate the layout for this fragment
        return vista;
    }
}