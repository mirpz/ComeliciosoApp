package com.example.comelicioso.adaptadores;

import android.annotation.SuppressLint;
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

    public ListAdapterRestaurantes(ArrayList<InfoRestaurantes> data) {
        this.data = data;

    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listelement_restautantes_conbotones,null,false);
        view.setOnClickListener(this);
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
                        favBool=false;
                    }else{
                        fav.setImageResource(R.drawable.ic_baseline_star_24);
                        favBool=true;
                    }
                }
            });

            prox.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(prxBool){
                        prox.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
                        prxBool=false;
                    }else{
                        prox.setImageResource(R.drawable.ic_baseline_bookmark_24);
                        prxBool=true;
                    }
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void asignarDatos(InfoRestaurantes datos){
            name.setText(datos.getNombre());
            favBool = false;
            icon.setImageResource(datos.getIcon());
            foodType.setText(datos.getTipoComida());
            if(favBool){
                fav.setImageResource(R.drawable.ic_baseline_star_24);
            }else{
                fav.setImageResource(R.drawable.ic_baseline_star_border_24);
            }

            prxBool = false;
            if(prxBool){
                prox.setImageResource(R.drawable.ic_baseline_bookmark_24);
            }else{
                prox.setImageResource(R.drawable.ic_baseline_bookmark_border_24);
            }
        }


    }
}