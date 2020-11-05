/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author German
 */
public class DRecinto {

    private int idRecinto;
    private String nombre;
    private int idLugar;
    private Conexion conexion;

    public DRecinto() {
        this.conexion = new Conexion();
    }

    public int getIdRecinto() {
        return idRecinto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdLugar() {
        return idLugar;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setIdRecinto(int idRecinto) {
        this.idRecinto = idRecinto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
    }

    public void guardar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("INSERT INTO recinto (nombre, idLugar) VALUES (?,?)");
        ps.setString(1, getNombre());
        ps.setInt(2, getIdLugar());
        ps.execute();
    }

    public void modificar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("UPDATE recinto SET nombre=?, idLugar=? WHERE idRecinto=?");

        ps.setString(1, getNombre());
        ps.setInt(2, getIdLugar());
        ps.setInt(3, getIdRecinto());

        ps.execute();
    }

    public void eliminar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("DELETE FROM recinto WHERE idRecinto=?");
        ps.setInt(1, getIdRecinto());
        ps.execute();
    }

    public DefaultTableModel getTabla() {
        DefaultTableModel listar = new DefaultTableModel();

        listar.addColumn("Codigo");
        listar.addColumn("Nombre");
        listar.addColumn("Lugar");
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            String sql = "SELECT recinto.idRecinto, recinto.nombre, lugar.nombre FROM recinto "
                    + "INNER JOIN lugar on lugar.idLugar = recinto.idLugar "
                    + "ORDER BY recinto.idRecinto asc";
//            String sql = "SELECT idRecinto, nombre, idLugar FROM recinto";
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

    public DefaultComboBoxModel getCombo() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try {
            PreparedStatement ps;
            ResultSet rs = null;
            String sql = "select nombre from recinto;";
            String encabezado = "Recinto";
            java.sql.Connection con = conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            combo.addElement(encabezado);
            while (rs.next()) {
                combo.addElement(rs.getString(1));
            }
            ps.close();
//            conexion.closeConexion();
        } catch (SQLException ex) {
            System.out.println("no se pudo listar los datos");
        }
        return combo;
    }

    public int getKey() {
        int key = -1;
        try {
            PreparedStatement ps;
            String sql = "select idRecinto from recinto where nombre=?";
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
