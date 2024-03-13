package Control;


import Equipos.Portatil;
import Equipos.Servidor;
import Equipos.Escritorio;
import Elementos.Trabajador;
import Elementos.Solicitud;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * Clase de control del departamento tecnológico. Guarda la informacion de los
 * trabajadores y nos permite trabajar con ellos
 */
public class Departamento {
    
    private String nombre;
    private int codigo;
    Trabajador[] trabajadores;
    
    public Departamento(String nombre, int codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.trabajadores = new Trabajador[0];
    }
    
    public Trabajador[] getTrabajadores() {
        return this.trabajadores;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Se reciben los atriubtos necesarios para crear un trabajador y se
     * verifica que no haya otro trabajador con el mismo nombre. Además de que
     * se utiliza la funcion de "Arrays.copyOf" la cual sirve para darle una
     * posicion más a un areglo y copiar la informacion con la que ya venia.
     *
     * @param nombreTrabajador
     * @param cedula
     * @param area
     * @param cargo
     */
    public void registrarTrabajador(String nombreTrabajador, int cedula, String area, String cargo) {
        
        Trabajador trabajador = new Trabajador(nombreTrabajador.toLowerCase(), cedula, area, cargo);
        
        Trabajador[] trabajadorAumentado = Arrays.copyOf(this.trabajadores, this.trabajadores.length + 1);
        boolean bandera = false;
        
        if (trabajadorAumentado.length == 1) {
            trabajadorAumentado[0] = trabajador;
        } else {
            for (int i = 0; i < trabajadorAumentado.length - 1; i++) {
                
                if (trabajadorAumentado[i].getNombre().equals(nombreTrabajador)) {
                    bandera = true;
                    JOptionPane.showMessageDialog(null, "El trabajador ya se encuentra");
                    break;
                }
            }
            if (bandera == false) {
                trabajadorAumentado[trabajadorAumentado.length - 1] = trabajador;
            }
        }
        
        this.trabajadores = trabajadorAumentado;
    }
    
    public void adicionarTrabajador(Trabajador trabajador) {
        int nuevoTamano = trabajadores.length + 1;
        Trabajador[] nuevoArreglo = new Trabajador[nuevoTamano];
        for (int i = 0; i < trabajadores.length; i++) {
            nuevoArreglo[i] = trabajadores[i];
        }
        nuevoArreglo[nuevoTamano - 1] = trabajador;
        trabajadores = nuevoArreglo;
    }

    /**
     * Se verifica que el trabajador exista y se devuelve su posición
     *
     * @param nombreTrabajador
     * @return int Si se encuentra la posicion en la que se encuentra este
     * trabajador en el arrelgo si no, se retorna -1 (Número necesario para
     * usarse en otro método, así se verifican 2 cosas al mismo tiempo - 1: Que
     * si exista un trabajador con ese nombre 2: La posicion en la que se
     * encuentra. )
     */
    public int verificarTrabajador(String nombreTrabajador) {
        int posicion = -1;
        for (int i = 0; i < trabajadores.length; i++) {
            if (trabajadores[i].getNombre().equals(nombreTrabajador.toLowerCase())) {
                posicion = i;
            }
        }
        return posicion;
        
    }

    /**
     * Verificar si un trabajador tiene un equipo asignado
     *
     * @param posicion
     * @return boolean
     */
    public boolean verificarEquipo(int posicion) {
        boolean respuesta = false;
        if (trabajadores[posicion].getEquipo() != null) {
            respuesta = true;
        }
        return respuesta;
    }

    /**
     * Se le asigna un equipo a un trabajador existente Dependiendo del tipo del
     * equipo se pide un atributo especifico de la subclase
     *
     * @param numero
     * @param procesador
     * @param disco
     * @param memoria
     * @param menu
     * @param posicion
     */
    public void asignarEquipo(int numero, String procesador, String disco,
            int memoria, int menu, int posicion) {
        
        String tipo;
        switch (menu) {
            
            case 1:
                tipo = JOptionPane.showInputDialog("Sistema Operativo: ");
                Escritorio escritorio = new Escritorio(numero, procesador, disco, memoria, tipo);
                trabajadores[posicion].setEquipo(escritorio);
                break;
            case 2:
                tipo = JOptionPane.showInputDialog("Marca: ");
                Portatil portatil = new Portatil(numero, procesador, disco, memoria, tipo);
                trabajadores[posicion].setEquipo(portatil);
                break;
            case 3:
                tipo = JOptionPane.showInputDialog("Sistema de Archivos: ");
                Servidor servidor = new Servidor(numero, procesador, disco, memoria, tipo);
                trabajadores[posicion].setEquipo(servidor);
                break;
        }
        
    }




    public void adicionarSolicitud(int cedula, Solicitud solicitud) {
        for (int i = 0; i < trabajadores.length; i++) {
            if (trabajadores[i].getCedula() == cedula) {
                trabajadores[i].adicionarSolicitud(solicitud);
            }
        }
    }

}
