/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Datos.Alumno;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        String  textoEtiquetas[]= {"Nombre","Apellido","Direccion"};
        etiquetaDatos = new JLabel[textoEtiquetas.length];
        datos= new JTextField[textoEtiquetas.length];
        
        for(int x=0;x<textoEtiquetas.length;x++){
            etiquetaDatos[x]=new JLabel(textoEtiquetas[x]);
            contenedor.add(etiquetaDatos[x]);
            datos[x]=new JTextField();
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
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Aceptar":
                //Alumno al=new Alumno(id, datos[0].getText(), datos[1].getText(), datos[2].getText());
                System.out.println("Aceptando...");
                break;
            default:
                System.out.println("Cancelando...");
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
