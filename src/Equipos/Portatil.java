package Equipos;

import Equipos.Equipo;

/**
 * Herencia de la clase equipo en la cual se modifica el tiempo de solucion y se
 * añade un atriburto
 */
public class Portatil extends Equipo {

    private String marca;

    public Portatil(int numero, String procesador, String disco, int memoria, String marca) {
        super(numero, procesador, disco, memoria);
        this.marca = marca;
        super.setTiempoSolucion(3);
  
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

   
    
    @Override
    public String toString() {
        return super.toString() + "\n Marca: " + marca;
    }

    

}
