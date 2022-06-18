package com.example.comelicioso.modelos;

import android.app.Activity;
import android.app.Application;
import android.util.JsonReader;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Global extends Application {
    ArrayList<InfoRestaurantes> datosRestaurantes;
    ArrayList<InfoRestaurantes> restaurantesFav;
    ArrayList<InfoRestaurantes> restaurantesProx;

    public ArrayList<InfoRestaurantes> getRestaurantesFav() {
        return restaurantesFav;
    }

    public void setRestaurantesFav(ArrayList<InfoRestaurantes> restaurantesFav) {
        this.restaurantesFav = restaurantesFav;
    }

    public ArrayList<InfoRestaurantes> getRestaurantesProx() {
        return restaurantesProx;
    }

    public void setRestaurantesProx(ArrayList<InfoRestaurantes> restaurantesProx) {
        this.restaurantesProx = restaurantesProx;
    }

    public ArrayList<InfoRestaurantes> getDatosRestaurantes() {
        return datosRestaurantes;
    }

    public void setDatosRestaurantes(ArrayList<InfoRestaurantes> datosRestaurantes) {
        this.datosRestaurantes = datosRestaurantes;
    }

    //Metodo para validar la exixtencia del archivo dentro del dispocitivo
    public boolean existeArchivo(String[] archivos, String archivoBuscado){
        //Recorrer la lista de archivos para validar la existencia del archivo
        for(int i=0; i< archivos.length;i++){
            //Valida la ecistencia del archivo a buscar}
            if(archivoBuscado.equals(archivos[i])){
                //En caso de estar el archivo dentro de la lista, retorna verdadero
                return true;
            }
        }
        //SI concluye el ciclo for sin existo, retorna falso (no encontrado a buscar)
        return false;
    }

    //Metodo para abrir archivos dentro del dispositivo (aLmacenamento nterno)
    public String abrirArchivo (String archivoName){
        //Obtener La Lista de archiovos internos del dispositivo
        String listaArchivos [] = fileList () ;
        String textoLeido;
        //VaLidar La existencia del anhivo interno
        if(existeArchivo(listaArchivos, archivoName)){
            textoLeido="";
            //Manejo de excepcion para abrir archivo interno
            try {
                //Abrir el archivo rara Leer contenido
                //1.Crear la instancia para asociar el archivoaleer
                InputStreamReader archivoInterno=new InputStreamReader(openFileInput(archivoName));
                //2.Crear instancia para leer el contenido del archivo
                BufferedReader leerArchivo=new BufferedReader(archivoInterno);
                //3.Leer el contenido del archivoycolocarlo en una variable
                String linea=leerArchivo.readLine();
                //4.Variable que guardar el cotenido
                //5.Ciclo repetitivo para leer contenido del archivo
                while(linea!=null){
                    textoLeido+=linea+"\n";// Se Lee La Lineayse agrega un salto
                    linea=leerArchivo.readLine();// Se Lee contenido del archivo
                }// while
                // Cerrar el archivo
                leerArchivo.close();
                // Cerrer el flujo del archivo
                archivoInterno.close();
                // Colocar el contenido dentro del componente multilinea
            }catch (IOException e){
                textoLeido=null;
                //Enviar mensaje en caso de error al leer archivo.
            }
        }else{
            textoLeido=null;
            //El archivop no existe, informar al usuario
        }
        return textoLeido;
    }

    public void guardarArchivo(String archivoName, String texto){
        // En un excepción colocar las instrucciones de lectura del archivo
        try{
            // Definir una instancia para asociar con el archivo a escribir, en modo privado
            OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput(archivoName, Activity.MODE_PRIVATE));
            // Escribir la información del texto multineal en el archivo
            archivo.write(texto);
            // Limpia el bufferI
            archivo.flush();
            // Cerrar el archivo
            archivo.close();
        }catch(IOException e){
        }// catch
        // Se borra el contenido del editor
    }// guardarArchivo

    public String arryToString(String[] cadena,String caracter, String error){
        String texto = "";
        if(cadena.length==0){
            texto = error;
        }else{
            for(int i=0; i<cadena.length; i++) {
                texto = texto + cadena[i];
                if(i<cadena.length-1){
                    texto = texto + caracter;
                }
            }
        }
        return texto;
    }

    public String[] stringToArray(String cadena,String caracter){
        return cadena.split(caracter);
    }

    public ArrayList<InfoRestaurantes> obtenerRestaurantes(String in) throws IOException {
        ArrayList<InfoRestaurantes>list = new ArrayList<>();
        try {
            JSONArray myJsonArray = new JSONArray(in);
            for(int i = 0; i<myJsonArray.length();i++){
                list.add(new InfoRestaurantes(
                        myJsonArray.getJSONObject(i).getString("id"),
                        myJsonArray.getJSONObject(i).getString("nombre"),
                        myJsonArray.getJSONObject(i).getString("tipoComida"),
                        myJsonArray.getJSONObject(i).getString("ubicacion"),
                        myJsonArray.getJSONObject(i).getString("telefono"),
                        myJsonArray.getJSONObject(i).getString("costoAproximado"),
                        myJsonArray.getJSONObject(i).getDouble("calificacion"),
                        stringToArray(myJsonArray.getJSONObject(i).getString("horarios"),"/")
                        ));
                list.get(i).setEnFavoritos(myJsonArray.getJSONObject(i).getBoolean("enFavoritos"));
                list.get(i).setEnFavoritos(myJsonArray.getJSONObject(i).getBoolean("enProximos"));
                if(myJsonArray.getJSONObject(i).has("icon")){
                    list.get(i).setIcon(myJsonArray.getJSONObject(i).getInt("icon"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
