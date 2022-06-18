package com.example.comelicioso;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.comelicioso.controladores.ControlMenu;
import com.google.android.material.tabs.TabLayout;

public class MenuActivity extends AppCompatActivity {

    TabLayout menu;
    ViewPager escenario;
    ControlMenu menuCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Asociar instancias con componentes
        menu = findViewById(R.id.AME_tablayMenu);
        escenario = findViewById(R.id.AME_viepagContenedor);
        //Se crea una instancia del controlador de menu
        menuCtrl = new ControlMenu(getSupportFragmentManager(),menu.getTabCount());
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
                    case 2:
                    case 3:
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
    }

    // Método para los casos del menú
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch(id){
            case R.id.MOV_itemCerrarSesion:
                CerrarSesion();
                break;
        }// switch
        return super.onOptionsItemSelected(item);
    }// onOptionsItemSelected

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overflow,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void CerrarSesion(){
        Intent intent = new Intent(MenuActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}