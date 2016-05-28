/*
 * Cambia tipo de moneda
 */
package GUI;

import Excepcion.MiError;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import proyectodavidisla.Alumno;
import proyectodavidisla.DataBase;

public class VentanaAlumno extends JFrame implements ActionListener, WindowListener {

    JPanel contenedor;
    JButton botonAlta, botonFin;
    JTextField nombre, apellido, direccion;
    JLabel etiquetaNombre, etiquetaApellido, etiquetaDireccion;
    DataBase db;

    public VentanaAlumno(DataBase db) {
        this.db = db;
        this.setTitle("Alta Alumno");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300, 300);
    }

    private void initComponents() {
        //Utilizo todo el fondo del JFrame
        contenedor = (JPanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(4, 2, 5, 5));
        //Inicializo los objetos
        etiquetaNombre = new JLabel("Nombre: ");
        nombre = new JTextField();
        etiquetaApellido = new JLabel("Apellido(s): ");
        apellido = new JTextField();
        etiquetaDireccion = new JLabel("Direccion ");
        direccion = new JTextField();
        botonAlta = new JButton("Alta");
        botonAlta.addActionListener(this);
        botonAlta.setActionCommand("alta");
        botonFin = new JButton("Fin");
        botonFin.addActionListener(this);
        botonFin.setActionCommand("fin");
        //los pongo en el contendor
        contenedor.add(etiquetaNombre);
        contenedor.add(nombre);
        contenedor.add(etiquetaApellido);
        contenedor.add(apellido);
        contenedor.add(etiquetaDireccion);
        contenedor.add(direccion);
        contenedor.add(botonAlta);
        contenedor.add(botonFin);
    }

    private void limpiaPantalla() {
        nombre.setText(null);
        apellido.setText(null);
        direccion.setText(null);
    }

    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    private void alta() {
        Alumno al;
        try {
            al = new Alumno(nombre(), apellido(), direccion());

            String cadena = "INSERT INTO alumno (ID_alumno, nombre, apellido, direccion, fecha_nacimiento) VALUES (ID_ALUMNO_SQ.NEXTVAL, '"
                    + al.getNombre() + "',  '" + al.getApellido() + "',  '" + al.getDireccion() + "', '01/01/2015')";
            db.ejecutaUpdate(cadena);
            JOptionPane.showMessageDialog(null, "Se ha dado de alta a " + al.getNombre() + " " + al.getApellido());
        } catch (MiError me) {
            System.out.println("Error: No se ha dado de alta");
        }
        limpiaPantalla();
    }

    private boolean compruebaCadena20(String cadena) {
        return cadena.length() > 0 && cadena.length() <= 20;
    }

    private String nombre() throws MiError {
        if (compruebaCadena20(nombre.getText())) {
            return nombre.getText();
        } else {
            ventanaError("El nombre tiene que tener entre 1 y 20 car.");
            throw new MiError("Nombre incorrecto");
        }
    }

    private String apellido() throws MiError {
        if (compruebaCadena20(apellido.getText())) {
            return apellido.getText();
        } else {
            ventanaError("El apellido tiene que tener entre 1 y 20 car.");
            throw new MiError("Apellido incorrecto");
        }
    }

    private String direccion() throws MiError {
        if (compruebaCadena20(direccion.getText())) {
            return direccion.getText();
        } else {
            ventanaError("Hay que introducir una direccion");
            throw new MiError("Direccion incorrecta");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "alta":
                alta();
                break;
            default:
                this.dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
