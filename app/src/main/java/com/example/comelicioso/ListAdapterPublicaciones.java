package com.example.comelicioso;

import android.annotation.SuppressLint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapterPublicaciones extends RecyclerView.Adapter<ListAdapterPublicaciones.ViewHolderDatos>{

    private final ArrayList<Publicaciones> data;

    public ListAdapterPublicaciones(ArrayList<Publicaciones> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listelement_publicacion,null,false);

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

        ImageView img;
        TextView txt;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.LEP_imgPublicacion);
            txt= itemView.findViewById(R.id.LEP_txtPieImagen);

        }

        @SuppressLint("SetTextI18n")
        public void asignarDatos(Publicaciones datos){
            txt.setText(Html.fromHtml("<b>"+datos.getUsario()+"</b><br>"+datos.getTexto()));
        }
    }
}
