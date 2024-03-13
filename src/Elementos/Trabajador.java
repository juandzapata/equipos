package Elementos;

import Elementos.Solicitud;
import Equipos.Equipo;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * Guarda la información de un trabajador además de su equipo y solicitudes
 * correspondientes
 */
public class Trabajador {

    private String nombre;
    private int cedula;
    private String area;
    private String cargo;
    private Equipo equipo;
    Solicitud[] solicitudes;

    public Trabajador(String nombre, int cedula, String area, String cargo, Equipo equipo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.area = area;
        this.cargo = cargo;
        this.equipo = equipo;
        this.solicitudes = new Solicitud[0];
    }

    public Trabajador(String nombre, int cedula, String area, String cargo, Equipo equipo, Solicitud[] solicitudes) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.area = area;
        this.cargo = cargo;
        this.equipo = equipo;
        this.solicitudes = solicitudes;
    }

    public Trabajador(String nombre, int cedula, String area, String cargo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.area = area;
        this.cargo = cargo;
        this.equipo = null;
        this.solicitudes = new Solicitud[0];

    }

    public Solicitud[] getSolicitudes() {
        return this.solicitudes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        String cadenaEquipo = equipo == null ? "No tiene Equipo" : equipo.toString();
        return "Nombre: " + nombre + "\nCédula: " + cedula + "\nÁrea: " + area
                + "\nCargo: " + cargo + "\n- - - - - - - - - - - - - - -\nEquipo:" + cadenaEquipo;
    }


    public void adicionarSolicitud(Solicitud solicitud) {
        int nuevoTamano = solicitudes.length + 1;
        Solicitud[] nuevoArreglo = new Solicitud[nuevoTamano];
        for (int i = 0; i < solicitudes.length; i++) {
            nuevoArreglo[i] = solicitudes[i];
        }
        nuevoArreglo[nuevoTamano - 1] = solicitud;
        solicitudes = nuevoArreglo;
    }


    /**
     * SE cambia el valor booleano de una solicitud por false
     *
     * @param identificador
     */
    public String cerrarSolicitud(int identificador) {
        String cadena = "";
        boolean bandera = false;

        for (int i = 0; i < solicitudes.length; i++) {
            if (solicitudes[i].getIdentificador() == identificador) {
                solicitudes[i].setEstado(false);
                bandera = true;
                cadena = "Solicitud cerrada";
                break;
            }
        }
        if (bandera == false) {
            cadena = "No existe la solicitud";
        }
        return cadena;
    }
}

