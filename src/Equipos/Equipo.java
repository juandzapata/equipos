package Equipos;

/**
 * Guarda la información de un equipo y sirve de modelo para sus clases hijas
 *
 */
public abstract class Equipo {

    private int numero;
    private String procesador;
    private String disco;
    private int memoria;
    private double tiempoSolucion;

    public Equipo(int numero, String procesador, String disco, int memoria) {
        this.numero = numero;
        this.procesador = procesador;
        this.disco = disco;
        this.memoria = memoria;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public double getTiempoSolucion() {
        return tiempoSolucion;
    }

    public void setTiempoSolucion(double tiempoSolucion) {
        this.tiempoSolucion = tiempoSolucion;
    }

    @Override
    public String toString() {
        return "\nNúmero: " + numero + "\nProcesador: " + procesador + "\nDisco: "
                + disco + "\nMemoria: " + memoria + " GB" + "\nTiempo de Solucion: " + tiempoSolucion;
    }

}
