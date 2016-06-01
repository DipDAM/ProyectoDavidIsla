package proyectodavidisla;

import GUI.VentanaPrincipal;

public class ProyectoDavidIsla {

    public static void main(String[] args) {
        String servidor = "jdbc:oracle:thin:@localhost:";
        String bd = "1521:xe";
        String user = "david";
        String password = "case";
        DataBase db = new DataBaseSegura(bd, user, password, servidor);
        if (db.abrirConexion()) {
            VentanaPrincipal v=new VentanaPrincipal(db);
        }
    }

}
