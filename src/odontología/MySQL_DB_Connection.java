/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontología;
import java.sql.*;
/**
 *
 * @author Alexander Batista
 */
public class MySQL_DB_Connection {
    private Connection dbConnection;
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/OdontoDB","root", "AJBA1995***");
            Statement St = dbConnection.createStatement();
            if (dbConnection != null) {
             System.out.println("Conectado");
             } else {
             System.out.println("no Conectado");
             }
        } catch (ClassNotFoundException | SQLException e) {
            
        }
        return dbConnection;
    }
}
