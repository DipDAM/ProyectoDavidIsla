package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import proyectodavidisla.DataBase;

public class VentanaPrincipal extends JFrame implements ActionListener, WindowListener {

    JPanel contenedor;
    JButton botones[];
    DataBase db;


    public VentanaPrincipal(DataBase db) {
        //Creo el ArrayList nuevo
        System.out.print("Creando ventana principal...");
        this.db=db;
        this.setTitle("Ventana principal");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300, 300);
        System.out.println("Completado");
    }

    private void initComponents() {
        String textoBotones[] = {"Alta", "Listado Alfabético", "Notas de un alumno", "Alumnos de una materia", "Modificar alumno", "Fin"};
        botones = new JButton[textoBotones.length];
        //Utilizo todo el fondo del JFrame
        contenedor = (JPanel) this.getContentPane();
        //Inicializo un layout
        contenedor.setLayout(new GridLayout(textoBotones.length, 1, 5, 5));
        //Inicializo los objetos
        for (int x = 0; x < textoBotones.length; x++) {
            botones[x] = new JButton();
            botones[x].setText(textoBotones[x]);
            botones[x].setActionCommand(Integer.toString(x));
            botones[x].addActionListener(this);
            contenedor.add(botones[x]);
        }
        //Atiendo a la ventana
        this.addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "0":
                VentanaAlta vA=new VentanaAlta(db);
                break;
            case "1":
//                db.recorreResultado(db.ejecutaConsulta("SELECT * from alumnos"));
                VentanaListado vL=new VentanaListado(db.ejecutaConsulta("SELECT * from alumno order by nombre"));
                break;
            case "2":
                VentanaBuscaAlumno vBA1=new VentanaBuscaAlumno(db, "ver");
                break;
            case "3":
                VentanaBuscaMateria vBM=new VentanaBuscaMateria(db);//PEDIR MATERIA, MOSTRAR MATRICULADOS
                break;
            case "4":
                VentanaBuscaAlumno vBA2=new VentanaBuscaAlumno(db, "modificar");
                //MODIFICA
                break;
            default:
                fin();
        }
    }

    private void fin() {
        db.cerrarConexion();
        System.exit(0);
    }

    @Override
    public void windowOpened(WindowEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Cerrando programa...");
        fin();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.out.println("Cerrado");
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
