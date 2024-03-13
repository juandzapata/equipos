package Equipos;

import Equipos.Equipo;

/**
 * Herencia de la clase equipo en la cual se modifica el tiempo de solucion y se
 * añade un atriburto
 */
public class Escritorio extends Equipo {

    private String sistemaOperativo;

    public Escritorio(int numero, String procesador, String disco, int memoria, String sistemaOperativo) {
        super(numero, procesador, disco, memoria);
        this.sistemaOperativo = sistemaOperativo;
        super.setTiempoSolucion(1);
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSistema Operativo: " + sistemaOperativo;
    }

}
