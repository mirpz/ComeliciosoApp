package com.example.comelicioso.adaptadores;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comelicioso.R;
import com.example.comelicioso.modelos.Reservaciones;

import java.util.ArrayList;

public class ListAdapterOldReservaciones extends RecyclerView.Adapter<ListAdapterOldReservaciones.ViewHolderDatos>
        implements View.OnClickListener{

    private final ArrayList<Reservaciones> data;
    private View.OnClickListener listener;

    public ListAdapterOldReservaciones(ArrayList<Reservaciones> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listelement_recervaciones_anteriores,null,false);
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

        TextView txtRestaurante, txtFecha, txtHora;
        ImageButton btnImagen;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtRestaurante = itemView.findViewById(R.id.LER_txtRestaurante);
            txtFecha = itemView.findViewById(R.id.LER_txtFecha);
            txtHora = itemView.findViewById(R.id.LER_txtHora);
            btnImagen = itemView.findViewById(R.id.LER_imgBtnCheckEvaluacion);

            btnImagen.setOnClickListener(new View.OnClickListener (){

                @Override
                public void onClick(View view) {
                    mostrarDialogoParaEvaluacion(view);
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public void asignarDatos(Reservaciones datos){
            txtRestaurante.setText(datos.getRestaurante());
            txtFecha.setText(datos.getFecha());
            txtHora.setText(datos.getHora());
        }

        private void mostrarDialogoParaEvaluacion(View view) {
            //Crear la instancia del AlertDialog
            AlertDialog.Builder cuadroP= new AlertDialog.Builder(view.getContext(), R.style.AlertDialog);
            //Nueva vista para asociar con el cuadro personalizado
            View vistaCuadroP= LayoutInflater.from(view.getContext())
                    .inflate(R.layout.dialog_evaluacion_restaurante, (ConstraintLayout) view.findViewById(R.id.DER_contenedor));
            //Asociar el objeto AlertDialog con la vista

            cuadroP.setView(vistaCuadroP);

            //Construye el objeto AlertDialog
            final AlertDialog alertDialog = cuadroP.create();

            ((Button) vistaCuadroP.findViewById(R.id.DFR_btnAceptar)).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

            //Asociar con los botones del cuadro de dialogo
            ((Button) vistaCuadroP.findViewById(R.id.DFR_btnAceptar)).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    /*if(reservado.getText().equals("")||
                            asistentes.getText().toString().isEmpty()||
                            tiempo.getText().equals("")||
                            fecha.getText().equals("")){
                        Toast.makeText(view.getContext(),"No se ha completado el formulario", Toast.LENGTH_SHORT).show();

                    }else{
                        if(reservado.getText().equals(elements.get(index).getReservadoPor())||
                                asistentes.getText().toString().equals(elements.get(index).getAsistentes())||
                                tiempo.getText().equals(elements.get(index).getHora())||
                                fecha.getText().equals(elements.get(index).getFecha())){
                            alertDialog.dismiss();
                        }else{
                            elements.get(index).setFecha(fecha.getText().toString());
                            elements.get(index).setHora(tiempo.getText().toString());
                            elements.get(index).setAsistentes(asistentes.getText().toString());
                            elements.get(index).setReservadoPor(reservado.getText().toString());
                            //saveData();
                            listAdapter.notifyDataSetChanged();
                            alertDialog.dismiss();
                        }
                    }*/
                    alertDialog.dismiss();//Remover el cuadro
                }
            });
            //Mostrar el cuadro de dialogo personalizado
            alertDialog.show();
        }
    }
}
