/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontología;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexander Batista
 */
public class My_Query {

    public void inserts(String[] Orden, String[] Data, String Table) {
        MySQL_DB_Connection c = new MySQL_DB_Connection();
        Connection reg = c.getConnection();
        int len = Orden.length;
        String SQL;
        try {
            SQL = "INSERT INTO `odontodb`." + Table + " ( ";
            for (int i = 0; i < len; i++) {
                SQL = SQL.concat("`").concat(Orden[i]).concat("`, ");
            }
            SQL = SQL.substring(0, SQL.length() - 2);
            SQL = SQL.concat(") VALUES (");
            len = Data.length;
            for (int i = 0; i < len; i++) {
                SQL = SQL.concat("?").concat(", ");
            }
            SQL = SQL.substring(0, SQL.length() - 2);
            SQL = SQL.concat(")");
            PreparedStatement pst = reg.prepareStatement(SQL);
            for (int i = 1; i <= Data.length; i++) {
                pst.setString(i, Data[i-1]);
            }
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Datos guardados");
            } else {
                JOptionPane.showMessageDialog(null, "Datos no guardados");
            }

        } catch (SQLException sQLException) {
        }
    }

    public static void main(String[] args) {
        String[] Data = {"3", "Alexander", "Batista", "8095739340", "8296677372", "sssdfdhgfhmfg", "dfsdsasdf@hghjjlk.com", "1995-08-30", "2017-10-17", "40224605036", "252122"};
        String Tabla = "Persona";
        My_Query mq = new My_Query();
        //mq.inserts(Orden, Data, Tabla);
    }
}
