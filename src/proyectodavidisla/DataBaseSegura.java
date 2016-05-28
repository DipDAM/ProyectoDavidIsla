/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodavidisla;

import GUI.VentanaListado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DavidIsla
 */
public class DataBaseSegura extends DataBase {

    public DataBaseSegura(String bd, String login, String password, String servidorMysql) {
        super(bd, login, password, servidorMysql);
    }

    public boolean buscaRegistro(String nombreBuscar) {
        ResultSet rs;
        PreparedStatement st;
        //sustituimos la variable por un ?
        String sentencia = "SELECT * from alumno where nombre= ?";// and edad= ?
        System.out.println(sentencia);
        try {
            st = conexion.prepareCall(sentencia);
            //pasamos los valores a cada uno de los interrogantes
            // comenzamos numerando por el 1
            st.setString(1, nombreBuscar);
//            st.setInt(2, 20);
            rs = st.executeQuery();
            if(rs.isBeforeFirst()){
                VentanaListado vl = new VentanaListado(rs);
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error on la base de datos: " + ex.getMessage());
        }
        return true;
    }

}
