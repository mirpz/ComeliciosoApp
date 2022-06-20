package com.example.comelicioso.modelos;

import android.app.Activity;
import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Global extends Application {
    ArrayList<InfoRestaurantes> datosRestaurantes;
    ArrayList<Usuario> listaUsuarios;

    public static final String nameFileRestaurantes = "restaurantes";
    public static final String nameFilePublicaciones= "publicaciones";
    public static final String nameFileUsuarios = "usuarios";
    public static final String typeExtention = ".txt";


    public void inicializacionArchivos(){
        crearArchivo(nameFileRestaurantes+typeExtention);
        crearArchivo(nameFilePublicaciones+typeExtention);
        crearArchivo(nameFileUsuarios+typeExtention);
        this.datosRestaurantes = new ArrayList<>();
        this.listaUsuarios = new ArrayList<>();
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
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
        for (String archivo : archivos) {
            //Valida la ecistencia del archivo a buscar}
            if (archivoBuscado.equals(archivo)) {
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

    public void crearArchivo (String archivoName){
        //Obtener La Lista de archiovos internos del dispositivo
        String listaArchivos [] = fileList () ;
        //VaLidar La existencia del anhivo interno
        if(!existeArchivo(listaArchivos, archivoName)){
            //Manejo de excepcion para abrir archivo interno
            try{
                // Definir una instancia para asociar con el archivo a escribir, en modo privado
                OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput(archivoName, Activity.MODE_PRIVATE));
                // Escribir la información del texto multineal en el archivo
                archivo.write("");
                // Limpia el bufferI
                archivo.flush();
                // Cerrar el archivo
                archivo.close();
            }catch(IOException e){
            }// catch
        }
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

    public String arryListToString(ArrayList<String> cadena,String caracter){
        String texto = "";
        for(int i=0; i<cadena.size(); i++) {
            texto = texto + cadena.get(i);
            if(i<cadena.size()-1){
                texto = texto + caracter;
            }
        }
        return texto;
    }

    public ArrayList<String> stringToArrayList(String cadena,String caracter){
        ArrayList<String>list=new ArrayList<String>(Arrays.asList(cadena.split(caracter)));
        if(list.size()==1&&list.get(0).equals("")){
            list = new ArrayList<>();
        }
        return list;
    }

    public String[] stringToArray(String cadena,String caracter){
        return cadena.split(caracter);
    }

    //USUARIOS
    public JSONArray crearJsonUsuarios(ArrayList<Usuario> elements){
        JSONArray json = new JSONArray();
        for(int i = 0; i<elements.size();i++){
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", elements.get(i).getId());
                obj.put("nombre", elements.get(i).getNombre());
                obj.put("correo", elements.get(i).getCorreo());
                obj.put("contrasenia", elements.get(i).getContresenia());
                obj.put("favoritos", arryListToString(elements.get(i).getFavoritos(),"/"));
                obj.put("proximos", arryListToString(elements.get(i).getProximos(),"/"));
                obj.put("reservaciones", crearJsonReservaciones(elements.get(i).getReservaciones()));
            } catch (JSONException e) {
                // TODO Auto-generated catch block e.printStackTrace();
            }
            json.put(obj);
        }
        return json;
    }

    public ArrayList<Usuario> obtenerUsuarios(String in) throws IOException {
        ArrayList<Usuario>list = new ArrayList<>();
        try {
            JSONArray myJsonArray = new JSONArray(in);
            for(int i = 0; i<myJsonArray.length();i++){
                list.add(new Usuario(
                        myJsonArray.getJSONObject(i).getString("id"),
                        myJsonArray.getJSONObject(i).getString("nombre"),
                        myJsonArray.getJSONObject(i).getString("correo"),
                        myJsonArray.getJSONObject(i).getString("contrasenia")
                ));
                list.get(i).setFavoritos(stringToArrayList(myJsonArray.getJSONObject(i).getString("favoritos"),"/"));
                list.get(i).setProximos(stringToArrayList(myJsonArray.getJSONObject(i).getString("proximos"),"/"));
                list.get(i).setReservaciones(obtenerReservaciones(myJsonArray.getJSONObject(i).getString("reservaciones")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //RESERVACIONES

    public JSONArray crearJsonReservaciones(ArrayList<Reservaciones> elements){
        JSONArray json = new JSONArray();
        for(int i = 0; i<elements.size();i++){
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", elements.get(i).getId());
                obj.put("IdRestaurante", elements.get(i).getIdRestaurante());
                obj.put("restaurante", elements.get(i).getRestaurante());
                obj.put("fecha", elements.get(i).getFecha());
                obj.put("hora", elements.get(i).getHora());
                obj.put("asistentes", elements.get(i).getAsistentes());
                obj.put("reservadoPor", elements.get(i).getReservadoPor());
            } catch (JSONException e) {
                // TODO Auto-generated catch block e.printStackTrace();
            }
            json.put(obj);
        }
        return json;
    }

    public ArrayList<Reservaciones> obtenerReservaciones(String in) throws IOException {
        ArrayList<Reservaciones>list = new ArrayList<>();
        try {
            JSONArray myJsonArray = new JSONArray(in);
            for(int i = 0; i<myJsonArray.length();i++){
                list.add(new Reservaciones(
                        myJsonArray.getJSONObject(i).getString("id"),
                        myJsonArray.getJSONObject(i).getString("IdRestaurante"),
                        myJsonArray.getJSONObject(i).getString("restaurante"),
                        myJsonArray.getJSONObject(i).getString("fecha"),
                        myJsonArray.getJSONObject(i).getString("hora"),
                        myJsonArray.getJSONObject(i).getString("asistentes"),
                        myJsonArray.getJSONObject(i).getString("reservadoPor")
                        ));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //RESTAURANTES

    public JSONArray crearJsonRestaurantes(ArrayList<InfoRestaurantes> elements){
        JSONArray json = new JSONArray();
        for(int i = 0; i<elements.size();i++){
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", elements.get(i).getId());
                obj.put("nombre", elements.get(i).getNombre());
                obj.put("tipoComida", elements.get(i).getTipoComida());
                obj.put("ubicacion", elements.get(i).getUbicacion());
                obj.put("telefono", elements.get(i).getTelefono());
                obj.put("costoAproximado", elements.get(i).getCostoAproximado());
                obj.put("calificacion", elements.get(i).getCalificacion());
                obj.put("icon", elements.get(i).getIcon());
                obj.put("horarios", arryToString(elements.get(i).getHorarios(),"/","Sin horarios presentes"));
            } catch (JSONException e) {
                // TODO Auto-generated catch block e.printStackTrace();
            }
            json.put(obj);
        }
        return json;
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
                if(myJsonArray.getJSONObject(i).has("icon")){
                    list.get(i).setIcon(myJsonArray.getJSONObject(i).getInt("icon"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //PUBLICACIONES

    public JSONArray crearJsonPublicaciones(ArrayList<PublicacionesEvaluaciones> elements){
        JSONArray json = new JSONArray();
        for(int i = 0; i<elements.size();i++){
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", elements.get(i).getId());
                obj.put("usario", elements.get(i).getUsario());
                obj.put("linkImagen", elements.get(i).getLinkImagen());
                obj.put("texto", elements.get(i).getTexto());
            } catch (JSONException e) {
                // TODO Auto-generated catch block e.printStackTrace();
            }
            json.put(obj);
        }
        return json;
    }

    public ArrayList<PublicacionesEvaluaciones> obtenerPublicaciones(String in) throws IOException {
        ArrayList<PublicacionesEvaluaciones>list = new ArrayList<>();
        try {
            JSONArray myJsonArray = new JSONArray(in);
            for(int i = 0; i<myJsonArray.length();i++){
                list.add(new PublicacionesEvaluaciones(
                        myJsonArray.getJSONObject(i).getString("id"),
                        myJsonArray.getJSONObject(i).getString("usario"),
                        myJsonArray.getJSONObject(i).getString("linkImagen"),
                        myJsonArray.getJSONObject(i).getString("texto")
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
