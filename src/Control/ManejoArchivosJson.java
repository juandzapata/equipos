package Control;

import Elementos.Solicitud;
import Elementos.Trabajador;
import Equipos.Escritorio;
import Equipos.Portatil;
import Equipos.Servidor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class ManejoArchivosJson {

    private Departamento departamento;
    private final String ARCHIVO = "C:/Users/USER/Downloads/Archivo.json";

    public ManejoArchivosJson(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * Crear un objeto disco usando los datos de un objeto JSON
     *
     * @param objetoJson el objeto con los datos en formato JSON
     * @return un nuevo disco con los datos obtenidos
     */
    public Trabajador crearTrabajador(JSONObject objetoJson) {
        Trabajador trabajador = null;

        String nombre = objetoJson.getString("nombre");
        int cedula = objetoJson.getInt("cedula");
        String area = objetoJson.getString("area");
        String cargo = objetoJson.getString("cargo");

        

        JSONArray arregloJson = objetoJson.getJSONArray("solicitudes");
        Solicitud[] solicitudes = this.crearArregloSolicitudes(arregloJson);
        for (Solicitud solicitud : solicitudes) {
            departamento.adicionarSolicitud(cedula, solicitud);
        }
        
        
        try{
        JSONObject equipoJson = objetoJson.getJSONObject("equipo");
        int numero = equipoJson.getInt("numero");
        String procesador = equipoJson.getString("procesador");
        String disco = equipoJson.getString("disco");
        int memoria = equipoJson.getInt("memoria");
        double tiempoSolucion = equipoJson.getDouble("tiempoSolucion");

        try {
            String sistemaOperativo = equipoJson.getString("sistemaOperativo");
            Escritorio escritorio = new Escritorio(numero, procesador, disco, memoria, sistemaOperativo);
            trabajador = new Trabajador(nombre, cedula, area, cargo, escritorio, solicitudes);
        } catch (JSONException errorSistemaOperativo) {
            try {
                String marca = equipoJson.getString("marca");
                Portatil portatil = new Portatil(numero, procesador, disco, memoria, marca);
                trabajador = new Trabajador(nombre, cedula, area, cargo, portatil, solicitudes);
            } catch (JSONException errorMarca) {
                String sistemaArchivos = equipoJson.getString("sistemaArchivos");
                Servidor servidor = new Servidor(numero, procesador, disco, memoria, sistemaArchivos);
                trabajador = new Trabajador(nombre, cedula, area, cargo, servidor, solicitudes);
            }
        }
        }
        catch (JSONException errorEquipo){
            trabajador = new Trabajador(nombre, cedula, area, cargo, null, solicitudes);
        }
        return trabajador;
    }

    public Solicitud crearSolicitud(JSONObject objetoJson) {
        int identificador = objetoJson.getInt("identificador");
        String fecha = objetoJson.getString("fecha");
        String descripcion = objetoJson.getString("descripcion");
        boolean estado = objetoJson.getBoolean("estado");

        Solicitud solicitud = new Solicitud(identificador, fecha, descripcion, estado);
        return solicitud;
    }

    /**
     * Crea un arreglo de discos a partir de un arreglo JSON (que internamente
     * tiene objetos JSON)
     *
     * @param arregloJson el arreglo con los datos de los discos en formato JSON
     * @return un arreglo de discos
     */
    public Solicitud[] crearArregloSolicitudes(JSONArray arregloJson) {
        Solicitud[] solicitudes = new Solicitud[arregloJson.length()];
        for (int i = 0; i < arregloJson.length(); i++) {
            JSONObject objetoJson = arregloJson.getJSONObject(i);
            solicitudes[i] = crearSolicitud(objetoJson);
        }
        return solicitudes;
    }

    /**
     * Crea un arreglo de discos a partir de un arreglo JSON (que internamente
     * tiene objetos JSON)
     *
     * @param arregloJson el arreglo con los datos de los discos en formato JSON
     * @return un arreglo de discos
     */
    public Trabajador[] crearArregloTrabajador(JSONArray arregloJson) {
        Trabajador[] discos = new Trabajador[arregloJson.length()];
        for (int i = 0; i < arregloJson.length(); i++) {
            JSONObject objetoJson = arregloJson.getJSONObject(i);
            discos[i] = crearTrabajador(objetoJson);
        }
        return discos;
    }

    /**
     * Escribir la información de todos los discos en el archivo
     */
    public void escribirDatos() {
        Path rutaArchivo = Paths.get(this.ARCHIVO);
        JSONArray arregloJson = new JSONArray(departamento.getTrabajadores());

        try {
            BufferedWriter escritor = Files.newBufferedWriter(rutaArchivo, CREATE);
            // Escribe toda la cadena, que es un arreglo JSON
            escritor.write(arregloJson.toString());
            escritor.close();
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo");
        }
    }

    /**
     * Leer la información de los discos en el achivos y crear los objetos a
     * partir de esos datos
     */
    public void leerDatos() {
        // Primero valida si existe para poder continuar
        File archivo = new File(this.ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        Path rutaArchivo = Paths.get(this.ARCHIVO);
        try {
            // Lee una cadena completa del archivo
            String cadena = new String(Files.readAllBytes(rutaArchivo));

            // Se crea un arreglo JSON a partir de la cadena
            JSONArray arregloJson = new JSONArray(cadena);
            Trabajador[] trabajadores = this.crearArregloTrabajador(arregloJson);
            for (Trabajador trabajador : trabajadores) {
                departamento.adicionarTrabajador(trabajador);
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el achivo");
        }
    }

}
