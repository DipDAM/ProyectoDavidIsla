/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Datos.Alumno;
import Excepcion.MiError;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyectodavidisla.DataBase;

/**
 *
 * @author David
 */
public class VentanaModifica extends JFrame implements WindowListener, ActionListener {

    String id;
    JPanel contenedor;
    JButton botonAceptar, botonCancelar;
    JTextField[] datos;
    JLabel[] etiquetaDatos;
    DataBase db;
    ResultSet rs;

    VentanaModifica(String id, DataBase db) {
        this.id = id;
        this.db = db;
        //Creo el ArrayList nuevo
        System.out.print("Creando ventana principal...");
        this.db = db;
        this.setTitle("Modificar datos del alumno");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300, 300);
        System.out.println("Completado");
    }

    private void initComponents() {
        //Utilizo todo el fondo del JFrame
        contenedor = (JPanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(4, 2, 5, 5));
        //Inicializo los objetos
        String textoEtiquetas[] = {"Nombre", "Apellido", "Direccion"};
        etiquetaDatos = new JLabel[textoEtiquetas.length];
        datos = new JTextField[textoEtiquetas.length];

        for (int x = 0; x < textoEtiquetas.length; x++) {
            etiquetaDatos[x] = new JLabel(textoEtiquetas[x]);
            contenedor.add(etiquetaDatos[x]);
            datos[x] = new JTextField();
            contenedor.add(datos[x]);
        }

        botonAceptar = new JButton();
        botonAceptar.setText("Aceptar");
        botonAceptar.setActionCommand(botonAceptar.getText());
        botonAceptar.addActionListener(this);
        contenedor.add(botonAceptar);
        botonCancelar = new JButton();
        botonCancelar.setText("Cancelar");
        botonCancelar.setActionCommand(botonCancelar.getText());
        botonCancelar.addActionListener(this);
        contenedor.add(botonCancelar);
        //Atiendo a la ventana
        this.addWindowListener(this);
    }

    private void modifica() {
        System.out.print("Intentando modificar el alumno...");
        Alumno al;
        try {
            al = new Alumno(nombre(), apellido(), direccion());

            String cadena = "update alumno set NOMBRE='" + al.getNombre()
                    + "', apellido='" + al.getApellido() + "', direccion='" + al.getDireccion()
                    + "'where id_alumno='" + Integer.parseInt(id) + "'";
            db.ejecutaUpdate(cadena);
            JOptionPane.showMessageDialog(null, "Ahora los datos del alumno son: " + al.getNombre() + " " + al.getApellido());
            System.out.println("Completado");
        } catch (MiError me) {
            System.out.println("Error: No se han podido modificar los datos");
        }
        System.out.print("Vaciando campos de texto...");
        limpiaPantalla();
        System.out.println("Completado");
    }

    private void limpiaPantalla() {
        for (int x = 0; x < datos.length; x++) {
            datos[x].setText(null);
        }

    }

    private boolean compruebaCadena20(String cadena) {
        return cadena.length() > 0 && cadena.length() <= 20;
    }

    private String nombre() throws MiError {
        if (compruebaCadena20(datos[0].getText())) {
            return datos[0].getText();
        } else {
            ventanaError("El nombre tiene que tener entre 1 y 20 car.");
            throw new MiError("Nombre incorrecto");
        }
    }

    private String apellido() throws MiError {
        if (compruebaCadena20(datos[1].getText())) {
            return datos[1].getText();
        } else {
            ventanaError("El apellido tiene que tener entre 1 y 20 car.");
            throw new MiError("Apellido incorrecto");
        }
    }

    private String direccion() throws MiError {
        if (compruebaCadena20(datos[2].getText())) {
            return datos[2].getText();
        } else {
            ventanaError("Hay que introducir una direccion");
            throw new MiError("Direccion incorrecta");
        }
    }

    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Aceptar":
                modifica();
                System.out.println("Aceptando...");
                break;
            default:
                System.out.print("Cerrando VentanaModifica...");
                this.dispose();
                System.out.println("Completado");
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
