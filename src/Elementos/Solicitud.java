package Elementos;

import java.util.Date;
import Equipos.Equipo;

/**
 * Guarda la informacion de una solicitud
 */
public class Solicitud {

    private int identificador;
    private String fecha;
    private String descripcion;
    private boolean estado;

    public Solicitud(int identificador, String fecha, String descripcion) {
        this.identificador = identificador;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = true;
    }

    public Solicitud(int identificador, String fecha, String descripcion, Boolean estado) {
        this.identificador = identificador;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        String estado1 = estado ? "Abierto" : "Cerrado";
        return "Identificador:" + identificador + "\nFecha: "
                + fecha + "\nDescripción: " + descripcion + "\nEstado: " + estado1;
    }

}
