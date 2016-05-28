package proyectodavidisla;

import GUI.Ventana;

public class ProyectoDavidIsla {

    public static void main(String[] args) {
        String servidor = "jdbc:oracle:thin:@localhost:";
        String bd = "1521:xe";
        String user = "david";
        String password = "case";
        DataBase db = new DataBaseSegura(bd, user, password, servidor);
        if (db.abrirConexion()) {
            Ventana v=new Ventana(db);
        }
    }

}
