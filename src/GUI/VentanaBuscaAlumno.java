
package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyectodavidisla.DataBase;

public class VentanaBuscaAlumno extends JFrame implements ActionListener, WindowListener {

    JPanel contenedor, contenedorTabla, contenedorBotones;
    JButton botonAlta, botonFin;
    JTextField id;
    JLabel etiquetaId;
    JTable table;
    DefaultTableModel modelo;
    DataBase db;
    ResultSet rs;
    String siguienteAccion;

    public VentanaBuscaAlumno(DataBase db, String siguienteAccion) {
        System.out.println("Creando VentanaBuscaAlumno...");
        this.db = db;
        this.siguienteAccion=siguienteAccion;
        this.setTitle("Busca Alumno");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(500, 300);
        System.out.println("Completado");
    }

    private void initComponents() {
        //Utilizo todo el fondo del JFrame
        contenedor = (JPanel) this.getContentPane();
        contenedorBotones=new JPanel();
        contenedorBotones.setLayout(new GridLayout(2,2,5,5));
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(2, 1, 5, 5));
        //Inicializo los objetos
        etiquetaId = new JLabel("Introduce el ID: ");
        id = new JTextField();
        botonAlta = new JButton("Busca");
        botonAlta.addActionListener(this);
        botonAlta.setActionCommand("busca");
        botonFin = new JButton("Fin");
        botonFin.addActionListener(this);
        botonFin.setActionCommand("fin");
        initListado();
        //los pongo en el contendor
        contenedor.add(contenedorTabla);
        contenedorBotones.add(etiquetaId);
        contenedorBotones.add(id);
        contenedorBotones.add(botonAlta);
        contenedorBotones.add(botonFin);
        contenedor.add(contenedorBotones);
    }
    
    public void initListado() {
        System.out.print("\t Creando listado de alumnos...");
        contenedorTabla= new JPanel();
        modelo = new DefaultTableModel();
        // se crea la Tabla con el modelo DefaultTableModel
        table = new JTable(modelo);
        //Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(table);
        //Agregamos el JScrollPane al contenedor
        contenedorTabla.add(scrollPane, BorderLayout.CENTER);
        // insertamos las celdas cabecera.
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        muestraFilas();
        System.out.println("Completado");
    }

    public void muestraFilas() {
        String fila[] = new String[4];
        rs=db.ejecutaConsulta("SELECT * from alumno order by ID_alumno");
        try {
            while (rs.next()) {
                //tenemos que crear un array
                fila[0] = rs.getString(1);
                fila[1] = rs.getString(2);
                fila[2] = rs.getString(3);
                fila[3] = rs.getString(5);
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    private void limpiaPantalla() {
        id.setText(null);
    }

    private void ventanaError(String cadena) {
        JOptionPane.showMessageDialog(
                this, cadena,
                "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    private void busca() {
        //le pasamos al método de la base de datos el nombre a buscar
        if (!db.buscaAlumno(id.getText())) {
            ventanaError("Registro no encontrado");
        } else {
            limpiaPantalla();
        }
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "busca":
                switch(siguienteAccion){
                    case "ver":
                        busca();
                        break;
                    case "modificar":
                        if (!db.hayResultado(id.getText())) {
                            ventanaError("Registro no encontrado");
                        } else {
                            VentanaModifica vM=new VentanaModifica(id.getText(), db);
                            limpiaPantalla();
                        }
                        break;
                    default:
                        if (!db.hayResultado(id.getText())) {
                            ventanaError("Registro no encontrado");
                        } else {
                            db.borraAlumno(id.getText());
                            JOptionPane.showMessageDialog(null, "Se ha dado de baja al alumno");
                            limpiaPantalla();
                        }
                        break;
                }
                
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
