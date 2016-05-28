package GUI;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristina
 */
public class VentanaListado extends JFrame {

    JPanel contenedor;
    JTable table;
    DefaultTableModel modelo;
    ResultSet rs;

    public VentanaListado(ResultSet rs) {
        this.rs = rs;
        this.setTitle("Listado");
        this.setVisible(true);
        initComponents();
        this.pack();
        this.setSize(300, 300);
    }

    public void initComponents() {
        contenedor = (JPanel) this.getContentPane();
        modelo = new DefaultTableModel();
        // se crea la Tabla con el modelo DefaultTableModel
        table = new JTable(modelo);
        //Creamos un JscrollPane y le agregamos la JTable
        JScrollPane scrollPane = new JScrollPane(table);
        //Agregamos el JScrollPane al contenedor
        contenedor.add(scrollPane, BorderLayout.CENTER);
        // insertamos los nombres de las celdas cabecera.
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Direccion");
        muestraFilas();
    }

    public void muestraFilas() {
        String fila[] = new String[3];
        try {
            while (rs.next()) {
                //tenemos que crear un array
                fila[0] = rs.getString(2);
                fila[1] = rs.getString(3);
                fila[2] = rs.getString(5);
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}


