import Control.Departamento;
import Control.ManejoArchivosJson;
import Elementos.Solicitud;
import Elementos.Trabajador;
import Equipos.Equipo;

import javax.swing.*;
import java.awt.event.*;

public class Menu {
    private JTabbedPane tabPrincipal;
    private JPanel panelPrincipal;
    private JPanel tabRegistrarTrabajador;
    private JPanel tabConsultarTrabajadores;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblCedula;
    private JTextField txtCedula;
    private JTextField txtAreaDeTrabajo;
    private JLabel lblAreaDeTrabajo;
    private JTextField txtCargo;
    private JLabel lblCargo;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JTextArea textConsultarTrabajador;
    private JPanel tabAsignarEquipo;
    private JTextField txtNombreEquipo;
    private JTextField txtNumeroEquipo;
    private JTextField txtProcesadorEquipo;
    private JTextField txtDiscoDuro;
    private JTextField txtCantidadMemoria;
    private JTextField txtTipoEquipo;
    private JButton bntGuardarEquipo;
    private JButton btnLimpiarEquipo;
    private JLabel lblNombreEquipo;
    private JLabel lblNumeroEquipo;
    private JLabel lblProcesadorEquipo;
    private JLabel lblDiscoDuro;
    private JLabel lblCantidadMemoria;
    private JLabel lblTipoEquipo;
    private JPanel tabConsultarEquipo;
    private JTextArea textConsultarEquipo;
    private JLabel lblNumeroEquipoConsultar;
    private JTextField txtNumeroEquipoConsultar;
    private JButton btnGuardarConsultarEquipo;
    private JButton btnLimpiarConsultarEquipo;
    private JPanel tabRegistrarSolicitud;
    private JTextField txtIdentificadorSolicitud;
    private JTextField txtFechaSolicitud;
    private JTextField txtDescripcionSolicitud;
    private JButton btnGuardarRegistrarSolicitud;
    private JButton btnLimpiarRegistrarSolicitud;
    private JLabel lblNombreTrabajadorSolicitud;
    private JLabel lblIdentificadorSolicitud;
    private JLabel lblFechaSolicitud;
    private JLabel lblDescripcionSolicitud;
    private JTextField txtNombreTrabajadorSolicitud;
    private JPanel tabConsultarSolicitud;
    private JTextArea textConsultarSolicitud;
    private JTextField txtNombreConsultarSolicitud;
    private JButton btnConsultarSolicitud;
    private JButton btnLimpiarConsultarSolicitud;
    private JLabel lblNombreConsultarSolicitud;
    private JPanel tabCerrarSolicitud;
    private JTextField txtIdentificadorSolicitudCerrar;
    private JLabel lblIdentificadorSolicitudCerrar;
    private JButton btnAceptarCerrarSolicitud;
    private JButton btnLimpiarCerrarSolicitud;
    private JButton bntConsultarTrabajadores;

    public Departamento departamento;
    private ManejoArchivosJson archivador;

    public Menu(){
        departamento = new Departamento("Departamento de Tecnologia", 1);
        this.archivador = new ManejoArchivosJson(departamento);

        archivador.leerDatos();

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarTrabajador();
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposRegistrarTrabajador();
            }
        });

        bntGuardarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarEquipo();
            }
        });
        btnLimpiarEquipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCamposAsignarEquipo();
            }
        });
        btnGuardarConsultarEquipo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                consultarEquipo();
            }
        });
        btnLimpiarConsultarEquipo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                limpiarCamposConsultarEquipo();
            }
        });
        btnGuardarRegistrarSolicitud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                registrarSolicitud();
            }
        });
        btnLimpiarRegistrarSolicitud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                limpiarCamposRegistrarSolicitud();
            }
        });
        btnLimpiarConsultarSolicitud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                limpiarCamposConsultarSolicitud();
            }
        });
        btnConsultarSolicitud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                consultarSolicitud();
            }
        });
        btnLimpiarCerrarSolicitud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                limpiarCamposCerrarSolicitud();
            }
        });
        bntConsultarTrabajadores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mostrarTrabajadores();
            }
        });
        btnAceptarCerrarSolicitud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cerrarSolicitud();
            }
        });
    }

    /**
     * Se registra un nuevo Elementos.Trabajador
     */
    public void registrarTrabajador() {
        String nombre = txtNombre.getText().trim().toLowerCase();
        int cedula = Integer.parseInt(txtCedula.getText());
        String area = txtAreaDeTrabajo.getText();
        String cargo = txtCargo.getText();
        departamento.registrarTrabajador(nombre, cedula, area, cargo);
        JOptionPane.showMessageDialog(panelPrincipal, "Trabajador Registrado");
    }

    public void mostrarTrabajadores(){
        Trabajador[] trabajadores = departamento.getTrabajadores();
        if(trabajadores.length == 0){
            textConsultarTrabajador.setText("No hay Trabajadores registrados");
        }else{
            String datosTrabajadores = "";
            for(Trabajador trabajador: trabajadores){
                datosTrabajadores += "{"+trabajador+"}\n";
            }
            textConsultarTrabajador.setText(datosTrabajadores);
        }
    }

    /**
     * Se asigna un equipo a un trabajador existente
     */
    public void asignarEquipo() {
        String nombre = txtNombreEquipo.getText().trim().toLowerCase();
        int posicion = departamento.verificarTrabajador(nombre);

        if (posicion != -1) {
            int numero = Integer.parseInt(txtNumeroEquipo.getText());
            String procesador = txtProcesadorEquipo.getText();
            String disco = txtDiscoDuro.getText();
            int memoria = Integer.parseInt(txtCantidadMemoria.getText());
            int menu = Integer.parseInt(txtTipoEquipo.getText());
            departamento.asignarEquipo(numero, procesador, disco, memoria, menu, posicion);
        } else {
            JOptionPane.showMessageDialog(panelPrincipal, "El trabajador no se encuentra");
        }
    }

    public void consultarEquipo() {
        boolean bandera = false;
        int numero = Integer.parseInt(txtNumeroEquipoConsultar.getText());
        Trabajador[] trabajadores = departamento.getTrabajadores();

        for (int i = 0; i < trabajadores.length; i++) {
            Equipo equipo = trabajadores[i].getEquipo();
            if(equipo.getNumero() == numero){
                bandera = true;
                textConsultarEquipo.setText(equipo.toString()+"\nNombre: "+trabajadores[i].getNombre()+"\nCargo: "+trabajadores[i].getCargo());
            }
        }
        if(bandera == false){
            textConsultarTrabajador.setText("No hay Trabajadores registrados");
        }
    }

    public void registrarSolicitud() {
        boolean banderaTrabajador = false;
        boolean banderaEquipo = false;

        String nombre = txtNombreTrabajadorSolicitud.getText().trim().toLowerCase();;
        Trabajador[] trabajadores = departamento.getTrabajadores();
        int identificador = Integer.parseInt(txtIdentificadorSolicitud.getText());
        String fecha = txtFechaSolicitud.getText();
        String descripcion = txtDescripcionSolicitud.getText();
        Solicitud solicitud = new Solicitud(identificador,fecha,descripcion);

        for (int i = 0; i < trabajadores.length; i++) {
            if(trabajadores[i].getNombre().equals(nombre)){
                banderaTrabajador = true;
                if(trabajadores[i].getEquipo() != null){
                    banderaEquipo = true;
                    trabajadores[i].adicionarSolicitud(solicitud);
                    JOptionPane.showMessageDialog(panelPrincipal, "Solicitud Registrada");
                }
            }
        }
        if(banderaTrabajador == false){
            JOptionPane.showMessageDialog(panelPrincipal, "El trabajador no se encuentra");
            }
        if(banderaEquipo == false){
                JOptionPane.showMessageDialog(panelPrincipal, "El trabajador no tiene un equipo asignado");
            }
        }

    public void consultarSolicitud(){
        boolean banderaTrabajador = false;
        String nombre = txtNombreConsultarSolicitud.getText().trim().toLowerCase();;
        Trabajador[] trabajadores = departamento.getTrabajadores();

        for (int i = 0; i < trabajadores.length; i++) {
            if(trabajadores[i].getNombre().equals(nombre)){
                banderaTrabajador = true;

                Solicitud[] solicitudes = trabajadores[i].getSolicitudes();
                if(solicitudes.length == 0){
                    textConsultarSolicitud.setText("No hay solicitudes");
                }else{
                    String datosSolicitudes = "";
                    for(Solicitud solicitud: solicitudes){
                        datosSolicitudes += "{"+solicitud+"}\n";
                    }
                    textConsultarSolicitud.setText(datosSolicitudes);
                }


            }
        }
        if(!banderaTrabajador){
            JOptionPane.showMessageDialog(panelPrincipal, "El trabajador no se encuentra");
        }

    }

    public void cerrarSolicitud(){
        int identificardo = Integer.parseInt(txtIdentificadorSolicitudCerrar.getText());
        Trabajador[] trabajadores = departamento.getTrabajadores();
        for (int i = 0; i < trabajadores.length; i++) {
            Solicitud[] solicitudes = trabajadores[i].getSolicitudes();
            for (int j = 0; j < solicitudes.length;j++){
                if(solicitudes[j].getIdentificador() == identificardo){
                    JOptionPane.showMessageDialog(panelPrincipal, trabajadores[i].cerrarSolicitud(identificardo));

                }
            }
        }
    }


















    public void limpiarCamposRegistrarTrabajador(){
        txtNombre.setText("");
        txtCedula.setText("");
        txtAreaDeTrabajo.setText("");
        txtCargo.setText("");
    }

    public void limpiarCamposAsignarEquipo(){
        txtNombreEquipo.setText("");
        txtNumeroEquipo.setText("");
        txtProcesadorEquipo.setText("");
        txtDiscoDuro.setText("");
        txtCantidadMemoria.setText("");
        txtTipoEquipo.setText("");
    }
    public void limpiarCamposConsultarEquipo(){
        txtNumeroEquipoConsultar.setText("");
        textConsultarEquipo.setText("");
    }
    public  void limpiarCamposRegistrarSolicitud(){
        txtNombreTrabajadorSolicitud.setText("");
        txtIdentificadorSolicitud.setText("");
        txtFechaSolicitud.setText("");
        txtDescripcionSolicitud.setText("");
    }
    public void limpiarCamposConsultarSolicitud(){
        txtNombreConsultarSolicitud.setText("");
        textConsultarSolicitud.setText("");
    }
    public  void limpiarCamposCerrarSolicitud(){
        txtIdentificadorSolicitudCerrar.setText("");
    }

    public static void main(String[] args) {
        Menu principal = new Menu();
        JFrame frame = new JFrame("Departamento de Tecnologia J.J");
        frame.setContentPane(principal.panelPrincipal);
        frame.setSize(600,800);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                principal.archivador.escribirDatos();
                System.exit(0);
            }
        });
        frame.pack();
        frame.setVisible(true);
    }


}
