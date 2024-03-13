package Equipos;

import Equipos.Equipo;

/**
 * Herencia de la clase equipo en la cual se modifica el tiempo de solucion y se
 * añade un atriburto
 */
public class Servidor extends Equipo {

    private String sistemaArchivos;

    public Servidor(int numero, String procesador, String disco, int memoria, String sistemaArchivos) {
        super(numero, procesador, disco, memoria);
        this.sistemaArchivos = sistemaArchivos;
        super.setTiempoSolucion(6);
    }

    public String getSistemaArchivos() {
        return sistemaArchivos;
    }

    public void setSistemaArchivos(String sistemaArchivos) {
        this.sistemaArchivos = sistemaArchivos;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSistema de Archivos: " + sistemaArchivos;
    }

}
