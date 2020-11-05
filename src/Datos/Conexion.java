/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author German
 */
public class Conexion {
    public String db = "bdvotos";
    public String url = "jdbc:mysql://127.0.0.1/"+ db;
    public String user = "root";
    public String password = "";
    
    public static Conexion instance;
    private static Connection con = null;
    private static String driver = "org.gjt.mm.mysql.Driver";
    
    public Conexion() {
    }
     
    public Connection getConexion()
    {
        Conexion instance;
        Connection con = null;
        String driver = "org.gjt.mm.mysql.Driver";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);
            
        } catch(SQLException e)
        {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
      return con;  
    }
    
    public Connection closeConexion() {
        try {
            con.close();
            System.out.println("Cerrrando Conexion");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return con = null;
    }
}
