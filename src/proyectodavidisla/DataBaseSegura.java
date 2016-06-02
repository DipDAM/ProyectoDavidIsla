/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodavidisla;

import GUI.VentanaMatriculados;
import GUI.VentanaListado;
import GUI.VentanaNotas;
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

    public boolean buscaAlumno(String idBuscar) {
        ResultSet rs;
        PreparedStatement st;
        //sustituimos la variable por un ?
        String sentencia = "select a.nombre, a.apellido,  m.NOMBRE_MATERIA, n.CALIFICACION\n" +
            "from ALUMNO a JOIN nota n on n.ALUMNO_ID_ALUMNO=a.ID_ALUMNO join materia m on m.ID_MATERIA=n.MATERIA_ID_MATERIA\n" +
            "where a.ID_ALUMNO=?";
        System.out.println(sentencia);
        try {
            st = conexion.prepareCall(sentencia);
            //pasamos los valores a cada uno de los interrogantes
            // comenzamos numerando por el 1
            st.setString(1, idBuscar);
//            st.setInt(2, 20);
            rs = st.executeQuery();
            if(rs.isBeforeFirst()){
                VentanaNotas vl = new VentanaNotas(rs);
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error on la base de datos: " + ex.getMessage());
        }
        return true;
    }
    
    public boolean hayResultado(String idBuscar) {
        ResultSet rs;
        PreparedStatement st;
        //sustituimos la variable por un ?
        String sentencia = "select *\n" +
            "from alumno \n" +
            "where ID_ALUMNO=?";
        System.out.println(sentencia);
        try {
            st = conexion.prepareCall(sentencia);
            //pasamos los valores a cada uno de los interrogantes
            // comenzamos numerando por el 1
            st.setString(1, idBuscar);
//            st.setInt(2, 20);
            rs = st.executeQuery();
            if(rs.isBeforeFirst()){
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error on la base de datos: " + ex.getMessage());
        }
        return true;
    }
    
    public boolean buscaMateria(String idBuscar) {
        ResultSet rs;
        PreparedStatement st;
        //sustituimos la variable por un ?
        String sentencia = "select a.nombre, a.apellido\n" +
            "from MATERIA m JOIN nota n on n.materia_ID_materia=m.ID_MATERIA join alumno a on a.ID_ALUMNO=n.ALUMNO_ID_ALUMNO\n" +
            "where m.ID_materia=?";
        System.out.println(sentencia);
        try {
            st = conexion.prepareCall(sentencia);
            //pasamos los valores a cada uno de los interrogantes
            // comenzamos numerando por el 1
            st.setString(1, idBuscar);
            rs = st.executeQuery();
            if(rs.isBeforeFirst()){
                VentanaMatriculados vl = new VentanaMatriculados(rs);
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error en la base de datos: " + ex.getMessage());
        }
        return true;
    }
    
    @Override
    public boolean borraAlumno(String idBuscar) {
        ResultSet rs;
        PreparedStatement st;
        //sustituimos la variable por un ?
        String sentencia1 = "delete from nota where alumno_ID_ALUMNO=?";
        String sentencia2 = "delete from alumno where ID_ALUMNO=?";
        System.out.println(sentencia1);
        try {
            st = conexion.prepareCall(sentencia1);
            //pasamos los valores a cada uno de los interrogantes
            // comenzamos numerando por el 1
            st.setString(1, idBuscar);
//            st.setInt(2, 20);
            rs = st.executeQuery();
            if(rs.isBeforeFirst()){
                
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error on la base de datos: " + ex.getMessage());
        }
        try {
            st = conexion.prepareCall(sentencia2);
            //pasamos los valores a cada uno de los interrogantes
            // comenzamos numerando por el 1
            st.setString(1, idBuscar);
//            st.setInt(2, 20);
            rs = st.executeQuery();
            if(rs.isBeforeFirst()){
                
            }else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error on la base de datos: " + ex.getMessage());
        }
        return true;
    }

}
