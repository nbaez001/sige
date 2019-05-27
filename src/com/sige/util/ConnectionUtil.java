package com.sige.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionUtil {

    private Connection conexion = null;
    private String url = "";

    public ConnectionUtil() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            url = "jdbc:sqlserver://localhost:1433;databaseName=sige";
            conexion = DriverManager.getConnection(url, "sa", "1234");
            System.out.println("Conexion a Base de Datos " + url + " . . . . .Ok");

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public Connection cerrarConexion() {
        try {
            conexion.close();
            System.out.println("Cerrando conexion a " + url + " . . . . . Ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        conexion = null;
        return conexion;
    }
}