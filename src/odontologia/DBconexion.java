package odontologia;

import java.sql.*;

public class DBconexion {

    private Connection conexion = null;

    public Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String db = "jdbc:oracle:thin:@localhost:1521:XE";
            conexion = DriverManager.getConnection(db, "favhil", "tqd");

            /*   if (conexion != null) {
             System.out.println("Conectado");

             } else {
             System.out.println("no Conectado");
             }*/
//            conexion.close();
        } catch (Exception e) {

            e.printStackTrace();

        }
        return this.conexion;
    }

    public static void Coneccion() {
        DBconexion c = new DBconexion();
        c.getConnection();
    }
}
