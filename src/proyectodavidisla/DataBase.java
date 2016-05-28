
package proyectodavidisla;

import GUI.VentanaListado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author departamento
 */
public class DataBase {

    String bd;
    String login;
    String password;
    String servidorMysql;
    Connection conexion;

    /**
     * Metodo constructor con parametros.
     *
     * @param bd
     * @param login
     * @param password
     * @param servidorMysql
     */
    public DataBase(String bd, String login, String password, String servidorMysql) {

        this.bd = bd;
        this.login = login;
        this.password = password;
        this.servidorMysql = servidorMysql;
    }

    /**
     * Metodo que abre la conexión con la base de datos.
     *
     * @return
     */
    public boolean abrirConexion() {

        boolean estado = false;

        try {
            // Cargar el driver.
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver(new OracleDriver());
            // Crear conenection a la base de datos.
            conexion = DriverManager.getConnection(servidorMysql + bd, login, password);
            estado = true;
        } catch (SQLException e) {
            System.out.println("SQL Exception:\n" + e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Exception:\n" + e.toString());
        } catch (Exception e) {
            System.out.println("Exception:\n" + e.toString());
        }

        return estado;
    }

    /**
     * Metodo que cierra la conexion con la base de datos.
     */
    public void cerrarConexion() {

        try {
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int ejecutaUpdate(String statement) {
        int n = 0;
        try {
            Statement st = conexion.createStatement();
            System.out.println("La sentencia es: " + statement);
            n = st.executeUpdate(statement);
        } catch (SQLException ex) {
            System.out.println("SQL Exception:\n" + ex.getMessage());
        }
        return n;
    }

    public ResultSet ejecutaConsulta(String consulta) {
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conexion.createStatement();
            rs = st.executeQuery(consulta);
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        }
//        try {
//            st.close();
//        } catch (SQLException ex) {
//            System.out.println("Error sql: " + ex.getMessage());
//        }
        return rs;
    }

    public boolean buscaRegistro(String nombreBuscar) {
        ResultSet rs;
        /*
        Directamente la consulta sería así
        rs=db.ejecutaConsulta("SELECT * from alumnos where nombre='" + nombre.getText()+"';");
        Creo un String con la consulta para ver por consola la inyección de código
        probaremos poniendo en el nombre: Pepe' or 1='1
         */
        String sentencia = "SELECT * from alumnos where nombre='" + nombreBuscar + "';";
        System.out.println(sentencia);
        rs = ejecutaConsulta(sentencia);
        try {
            //VIP primero compruebo que rs no es nullo, si lo es, lo segundo no se ejecuta
            if (rs != null) {
                if (rs.isBeforeFirst()) {
                    VentanaListado vL = new VentanaListado(rs);
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
        return true; //aunque puede ser que se haya producido la excepción.  contamos conn el mensaje
    }

    public void cierraResultSet(ResultSet rs) {
        try {
            //cerramos el rs. porque garbage no puede eliminar el heap
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos: " + ex.getMessage());
        }
    }

    public void recorreResultado(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + "\t" + rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println("Error sql: " + ex.getMessage());
        }
    }

}
