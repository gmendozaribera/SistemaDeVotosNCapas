/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author German
 */
public class DPartido {

    private int idPartido;
    private String Sigla;
    private String Nombre;
    private Conexion conexion;

    public DPartido() {
        conexion = new Conexion();
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getSigla() {
        return Sigla;
    }

    public void setSigla(String Sigla) {
        this.Sigla = Sigla;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void guardar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("INSERT INTO partido (sigla, nombre) VALUES (?,?)");
        ps.setString(1, getSigla());
        ps.setString(2, getNombre());
        ps.execute();
    }

    public void modificar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("UPDATE partido SET sigla=?, nombre=? WHERE idPartido=?");

        ps.setString(1, getSigla());
        ps.setString(2, getNombre());
        ps.setInt(3, getIdPartido());

        ps.execute();
    }

    public void eliminar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("DELETE FROM partido WHERE idPartido=?");
        ps.setInt(1, getIdPartido());
        ps.execute();
    }

    public DefaultTableModel getTabla() {
        DefaultTableModel listar = new DefaultTableModel();

        listar.addColumn("Codigo");
        listar.addColumn("Sigla");
        listar.addColumn("Nombre");
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            String sql = "SELECT idPartido, sigla, nombre from partido";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                listar.addRow(filas);
            }
            ps.close();
//            conexion.closeConexion();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return listar;
    }

    public int existePartido() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conexion.getConexion();

        String sql = "SELECT count(idPartido) FROM partido WHERE nombre = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, getNombre());
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public DefaultComboBoxModel getCombo() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try{
            PreparedStatement ps;
            ResultSet rs = null;
            String sql = "select nombre from partido;";
            String encabezado = "Partido";
            java.sql.Connection con = conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            combo.addElement(encabezado);
            while (rs.next()) {
                combo.addElement(rs.getString(1));
            }
            ps.close();
//            conexion.closeConexion();
        }catch (SQLException ex){
            System.out.println("no se pudo listar los datos");
        }
        return combo;
    }

    public int getKey() {
        int key=-1;       
        try {
            PreparedStatement ps;
            String sql = "select idPartido from partido where nombre=?";
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            ps = con.prepareStatement(sql);
            //nuevo
            ps.setString(1, getNombre());
//            ps.execute();
            //final
            rs = ps.executeQuery();
            while (rs.next()) {
                key = Integer.valueOf(rs.getObject(1).toString());
            }
            ps.close();
//            conexion.closeConexion();
            
        } catch (Exception e) {
            System.out.println("no encontro la llave");
        }
        return key;
    }
}
