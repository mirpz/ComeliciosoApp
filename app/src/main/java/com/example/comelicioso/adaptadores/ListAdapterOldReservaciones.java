package com.example.comelicioso.adaptadores;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comelicioso.R;
import com.example.comelicioso.modelos.Reservaciones;

import java.util.ArrayList;

public class ListAdapterOldReservaciones extends RecyclerView.Adapter<ListAdapterOldReservaciones.ViewHolderDatos>{

    private final ArrayList<Reservaciones> data;

    public ListAdapterOldReservaciones(ArrayList<Reservaciones> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listelement_recervaciones_anteriores,null,false);
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

        TextView txtRestaurante, txtFecha, txtHora, txtAsistentes;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtRestaurante = itemView.findViewById(R.id.LER_txtRestaurante);
            txtAsistentes = itemView.findViewById(R.id.LER_txtAsistentes);
            txtFecha = itemView.findViewById(R.id.LER_txtFecha);
            txtHora = itemView.findViewById(R.id.LER_txtHora);
        }

        @SuppressLint("SetTextI18n")
        public void asignarDatos(Reservaciones datos){
            txtRestaurante.setText(datos.getRestaurante());
            txtAsistentes.setText(datos.getAsistentes());
            txtFecha.setText(datos.getFecha());
            txtHora.setText(datos.getHora());
        }
    }
}
