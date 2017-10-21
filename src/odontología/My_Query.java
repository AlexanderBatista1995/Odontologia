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
            try (PreparedStatement pst = reg.prepareStatement(SQL)) {
                for (int i = 1; i <= Data.length; i++) {
                    pst.setString(i, Data[i - 1]);
                }
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Datos guardados " + Table);
                } else {
                    JOptionPane.showMessageDialog(null, "Datos no guardados " + Table);
                }
            }
        } catch (SQLException sQLException) {
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
        }
        return Id;
    }

    public static void main(String[] args) {

    }

    ArrayList SelectEspecialidad(String[] Campos, String Tabla, String Condicion) {
        ArrayList Data = new ArrayList();
        String cadena = "";
        MySQL_DB_Connection c = new MySQL_DB_Connection();
        Connection reg = c.getConnection();
        String SQL;
        try {
            SQL = "SELECT ";
            for (String Campo : Campos) {
                SQL = SQL.concat(Campo).concat(", ");
            }
            SQL = SQL.substring(0, SQL.length() - 2);
            if (!Condicion.isEmpty()) {
                SQL = SQL + " FROM odontodb." + Tabla + " where " + Condicion;
            } else {
                SQL = SQL + " FROM odontodb." + Tabla;
            }
            System.out.println(SQL);
            try (Statement ST = reg.createStatement()) {
                ResultSet rs = ST.executeQuery(SQL);
                while (rs.next()) {
                    for (String Campo : Campos) {
                        if (Campo.toUpperCase().substring(0, 3).equals("ID_") || Campo.toUpperCase().equals("TELEFONO") || Campo.toUpperCase().equals("CELULAR")) {
                            cadena = cadena.concat("   ").concat(String.valueOf(rs.getLong(Campo)));
                        } else if (Campo.toUpperCase().substring(0, 5).equals("FECHA")) {
                            cadena = cadena.concat("   ").concat(String.valueOf(rs.getDate(Campo)));
                        } else {
                            cadena = cadena.concat("   ").concat(rs.getString(Campo));
                        }

                    }
                    Data.add(cadena);
                    cadena = "";

                }
            }
            Data.stream().forEach((Data1) -> {
                System.err.println(Data1);
            });
        } catch (SQLException sQLException) {
        }
        return Data;
    }

    public String[] SelectWhereData1(String[] Campos, String persona, String concat) {
        MySQL_DB_Connection c = new MySQL_DB_Connection();
        Connection reg = c.getConnection();
        String[] datos = new String[Campos.length];
        int te = 1;
        String SQL;
        int Id = 0;
        try {
            SQL = "SELECT ";
            for (String Campo : Campos) {
                SQL = SQL.concat(Campo).concat(", ");
            }
            SQL = SQL.substring(0, SQL.length() - 2);
            SQL = SQL + " FROM odontodb." + persona + " where " + concat;

            System.out.println(SQL);
            try (Statement ST = reg.createStatement()) {
                ResultSet rs = ST.executeQuery(SQL);
                while (rs.next()) {
                    for (int i = 0; i < Campos.length; i++) {
                        if (Campos[i].toUpperCase().substring(0, 3).equals("ID_")
                                || Campos[i].toUpperCase().equals("TELEFONO")
                                || Campos[i].toUpperCase().equals("CELULAR")) {
                            datos[i] = String.valueOf(rs.getLong(Campos[i]));
                        } else if (Campos[i].toUpperCase().substring(0, 5).equals("FECHA")) {
                            datos[i] = String.valueOf(rs.getDate(Campos[i]));
                        } else {
                            datos[i] = rs.getString(Campos[i]);
                        }
                    }
                }
            }
            for (String dato : datos) {
                System.out.println(dato);
            }
        } catch (SQLException sQLException) {
        }
        return datos;
    }

    public void Updates(String[] Orden, String[] Data, String Table, String Condicion) {
        MySQL_DB_Connection c = new MySQL_DB_Connection();
        Connection reg = c.getConnection();
        int len = Orden.length;
        String SQL;
        try {
           
            SQL = "update `odontodb`." + Table + " set ";
            for (int i = 0; i < len; i++) {
                SQL = SQL.concat("`").concat(Orden[i]).concat("` = ").concat("?").concat(", ");
            }
            SQL = SQL.substring(0, SQL.length() - 2);
            SQL = SQL.concat(" where " + Condicion);
            try (PreparedStatement pst = reg.prepareStatement(SQL)) {
                for (int i = 1; i <= Data.length; i++) {
                    pst.setString(i, Data[i - 1]);
                }
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Datos guardados " + Table);
                } else {
                    JOptionPane.showMessageDialog(null, "Datos no guardados " + Table);
                }
            }
                        
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
    }

}
