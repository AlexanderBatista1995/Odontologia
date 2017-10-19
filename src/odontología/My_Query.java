/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odontología;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
                pst.setString(i, Data[i - 1]);
            }
            int n = pst.executeUpdate();
            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Datos guardados "+Table);
            } else {
                JOptionPane.showMessageDialog(null, "Datos no guardados "+Table);
            }
            pst.close();
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }

    public String SelectsMaxID(String ID, String Table) {
        MySQL_DB_Connection c = new MySQL_DB_Connection();
        Connection reg = c.getConnection();
        String SQL, Id = "";
        try {
            SQL = "SELECT max(" + ID + ") as " + ID + " FROM odontodb." + Table;
            //System.out.println(SQL);
            try (Statement ST = reg.createStatement()) {
                ResultSet rs = ST.executeQuery(SQL);
                while (rs.next()) {
                    Id = String.valueOf(rs.getInt(ID));
                }
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        if (Id.equals("0")) {
            Id = "1";
        }
        return Id;
    }

    public int SelectWhereCount(String cedula, String persona, String concat) {
        MySQL_DB_Connection c = new MySQL_DB_Connection();
        Connection reg = c.getConnection();
        String SQL;
        int Id = 0;
        try {
            SQL = "SELECT " + cedula + " as " + cedula + " FROM odontodb." + persona + " where " + concat;
            //System.out.println(SQL);
            try (Statement ST = reg.createStatement()) {
                ResultSet rs = ST.executeQuery(SQL);
                while (rs.next()) {
                    Id++;
                }
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return Id;
    }

    public static void main(String[] args) {
        My_Query MQ = new My_Query();
        int t = MQ.SelectWhereCount("Cedula", "Persona", "40224605036 = Cedula");
        System.out.println(t);
    }

    ArrayList SelectEspecialidad() {
        ArrayList Data = new ArrayList();
        String cadena = "";
        MySQL_DB_Connection c = new MySQL_DB_Connection();
        Connection reg = c.getConnection();
        String SQL;
        try {
            SQL = "Select * from Especialidades";
            
            try (Statement ST = reg.createStatement()) {
                ResultSet rs = ST.executeQuery(SQL);
                while (rs.next()) {
                    cadena=cadena.concat(String.valueOf(rs.getInt("ID_ESPECIALIDAD")));
                    cadena=cadena.concat("   ").concat(rs.getString("Nombre"));
                    cadena=cadena.concat("   ").concat(rs.getString("Descripcion"));
                    Data.add(cadena);
                }
            }
            
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return Data;
    }
}
