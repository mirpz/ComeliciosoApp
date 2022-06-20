package com.example.comelicioso.adaptadores;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comelicioso.R;
import com.example.comelicioso.modelos.Global;
import com.example.comelicioso.modelos.InfoRestaurantes;

import java.util.ArrayList;

public class ListAdapterRestaurantes extends RecyclerView.Adapter<ListAdapterRestaurantes.ViewHolderDatos>
        implements View.OnClickListener{

    private final ArrayList<InfoRestaurantes> data;
    private View.OnClickListener listener;
    private static String idUsuario;
    static Global gb;

    public ListAdapterRestaurantes(ArrayList<InfoRestaurantes> data, String idUsuario) {
        this.data = data;
        ListAdapterRestaurantes.idUsuario =idUsuario;

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listelement_restautantes_conbotones,null,false);
        view.setOnClickListener(this);
        gb =(Global)view.getContext().getApplicationContext();
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        //Validar si el escucha no esta vacio
        if(listener != null){
            listener.onClick(view);//Escucha para los componentes de la visto
        }
    }

    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageButton fav, prox;
        TextView name, foodType;
        ImageView icon;
        boolean favBool, prxBool;
        String idRes;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.LERCB_txtNombreRestaurante);
            foodType = itemView.findViewById(R.id.LERCB_txtTipoComida);
            icon = itemView.findViewById(R.id.LERCB_imageViewLogo);
            fav = itemView.findViewById(R.id.LERCB_imageBtnFav);
            prox = itemView.findViewById(R.id.LERCB_imageBtnSave);

            fav.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(favBool){
                        fav.setImageResource(R.drawable.ic_baseline_star_border_24);
                        quitarDeFavoritos(idRes);
                        favBool=false;
                    }else{
                        fav.setImageResource(R.drawable.ic_baseline_star_24);
                        agregarDeFavoritos(idRes);
                        favBool=true;
                    }
                }
            });

            prox.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(prxBool){
                        prox.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
                        quitarDeProximos(idRes);
                        prxBool=false;
                    }else{
                        prox.setImageResource(R.drawable.ic_baseline_bookmark_24);
                        agregarDeProximos(idRes);
                        prxBool=true;
                    }
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void asignarDatos(InfoRestaurantes datos){
            idRes = datos.getId();
            name.setText(datos.getNombre());
            favBool = comparadorExistenciaFavoritos(datos.getId());
            icon.setImageResource(datos.getIcon());
            foodType.setText(datos.getTipoComida());
            if(favBool){
                fav.setImageResource(R.drawable.ic_baseline_star_24);
            }else{
                fav.setImageResource(R.drawable.ic_baseline_star_border_24);
            }

            prxBool = comparadorExistenciaProximos(datos.getId());
            if(prxBool){
                prox.setImageResource(R.drawable.ic_baseline_bookmark_24);
            }else{
                prox.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
            }
        }

        private void quitarDeFavoritos(String id){
            int index=0;
            boolean flag =false;
            for(int i=0; i<gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getFavoritos().size();i++){
                if(gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getFavoritos().get(i).equals(id)){
                    flag=true;
                    index=i;
                }
            }
            if(flag){
                gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getFavoritos().remove(index);
            }
        }

        private void agregarDeFavoritos(String id){
            boolean flag =true;
            for(int i=0; i<gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getFavoritos().size();i++){
                if(gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getFavoritos().get(i).equals(id)){
                    flag=false;
                }
            }
            if(flag){
                gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getFavoritos().add(id);
            }
        }

        private void quitarDeProximos(String id){
            int index=0;
            boolean flag =false;
            for(int i=0; i<gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getProximos().size();i++){
                if(gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getProximos().get(i).equals(id)){
                    flag=true;
                    index=i;
                }
            }
            if(flag){
                gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getProximos().remove(index);
            }
        }

        private void agregarDeProximos(String id){
            boolean flag =true;
            for(int i=0; i<gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getProximos().size();i++){
                if(gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getProximos().get(i).equals(id)){
                    flag=false;
                }
            }
            if(flag){
                gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getProximos().add(id);
            }
        }

        private boolean comparadorExistenciaFavoritos(String id){
            for(int i=0; i<gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getFavoritos().size();i++){
                if(gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getFavoritos().get(i).equals(id)){
                    return true;
                }
            }
            return false;
        }

        private boolean comparadorExistenciaProximos(String id){
            for(int i=0; i<gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getProximos().size();i++){
                if(gb.getListaUsuarios().get(Integer.parseInt(idUsuario)).getProximos().get(i).equals(id)){
                    return true;
                }
            }
            return false;
        }

    }
}