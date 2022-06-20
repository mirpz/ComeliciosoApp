package com.example.comelicioso.adaptadores;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comelicioso.EvaluacionActivity;
import com.example.comelicioso.R;
import com.example.comelicioso.modelos.Reservaciones;

import java.util.ArrayList;

public class ListAdapterOldReservaciones extends RecyclerView.Adapter<ListAdapterOldReservaciones.ViewHolderDatos>
        implements View.OnClickListener{

    private final ArrayList<Reservaciones> data, dataComplete;
    private View.OnClickListener listener;

    public ListAdapterOldReservaciones(ArrayList<Reservaciones> data) {
        this.data=new ArrayList<>();
        for(int i = 0; i<data.size();i++){
            if(!data.get(i).isEvaluado()){
                this.data.add(data.get(i));
            }
        }
        this.dataComplete=data;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listelement_recervaciones_anteriores,null,false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, @SuppressLint("RecyclerView") int position) {
        holder.asignarDatos(data.get(position));
        holder.btnImagen.setOnClickListener(new View.OnClickListener (){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), EvaluacionActivity.class);
                intent.putExtra("reservacion", dataComplete);
                intent.putExtra("idReservacion", data.get(position).getId());
                view.getContext().startActivities(new Intent[]{intent});
            }
        });
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
            listener.onClick(view);//Escucha para los componentes de la vista
        }
    }


    public static class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView txtRestaurante, txtFecha, txtHora;
        ImageButton btnImagen;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtRestaurante = itemView.findViewById(R.id.LER_txtRestaurante);
            txtFecha = itemView.findViewById(R.id.LER_txtFecha);
            txtHora = itemView.findViewById(R.id.LER_txtHora);
            btnImagen = itemView.findViewById(R.id.LER_imgBtnCheckEvaluacion);
        }

        @SuppressLint("SetTextI18n")
        public void asignarDatos(Reservaciones datos){
            txtRestaurante.setText(datos.getRestaurante());
            txtFecha.setText(datos.getFecha());
            txtHora.setText(datos.getHora());
        }
    }
}
