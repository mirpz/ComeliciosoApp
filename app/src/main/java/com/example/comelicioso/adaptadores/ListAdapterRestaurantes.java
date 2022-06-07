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
import com.example.comelicioso.modelos.InfoRestaurantes;

import java.util.ArrayList;

public class ListAdapterRestaurantes extends RecyclerView.Adapter<ListAdapterRestaurantes.ViewHolderDatos>{

    private final ArrayList<InfoRestaurantes> data;

    public ListAdapterRestaurantes(ArrayList<InfoRestaurantes> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listelement_restautantes_conbotones,null,false);

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


    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageButton fav, prox;
        TextView name;
        ImageView icon;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.LERCB_txtNombreRestaurante);
            icon = itemView.findViewById(R.id.LERCB_imageViewLogo);
            fav = itemView.findViewById(R.id.LERCB_imageBtnFav);
            prox = itemView.findViewById(R.id.LERCB_imageBtnSave);
        }

        @SuppressLint("SetTextI18n")
        public void asignarDatos(InfoRestaurantes datos){
            name.setText(datos.getNombre());
        }
    }
}