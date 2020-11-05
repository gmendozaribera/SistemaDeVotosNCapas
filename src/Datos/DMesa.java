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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author German
 */
public class DMesa {

    private int idMesa;
    private int numero;
    private int idRecinto;
    private Conexion conexion;

    public DMesa() {
        conexion = new Conexion();
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getIdRecinto() {
        return idRecinto;
    }

    public void setIdRecinto(int idRecinto) {
        this.idRecinto = idRecinto;
    }

    public void guardar() {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        try {
            ps = conn.prepareStatement("INSERT INTO mesa (numero, idRecinto) VALUES (?,?)");
            ps.setInt(1, getNumero());
            ps.setInt(2, getIdRecinto());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DMesa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminar() {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        try {
            ps = conn.prepareStatement("DELETE FROM mesa WHERE idRecinto=? and numero>?");
            ps.setInt(1, getIdRecinto());
            ps.setInt(2, getNumero());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DMesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int cantidadActual() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = conexion.getConexion();
        try {
            ps = con.prepareStatement("SELECT max(numero) FROM mesa WHERE idRecinto=?");
            ps.setInt(1, getIdRecinto());
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("cantidad del sql " + rs.getInt(1) + " y el idRecinto= " + getIdRecinto());
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            System.err.println("Error al proceso cantidadActual" + e.toString());
            return 0;
        }
    }

    public int existeRecinto() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Connection con = conexion.getConexion();
        try {
            ps = con.prepareStatement("SELECT count(idRecinto) FROM mesa WHERE idRecinto= ?");
            ps.setInt(1, getIdRecinto());
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException ex) {
            System.err.println("Error al proceso existeRecinto" + ex.toString());
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("No se puede cerrar conexion en el proceso existeRecinto" + e.toString());
            }
        }
    }

    public DefaultTableModel getTabla() {
        DefaultTableModel listar = new DefaultTableModel();

        listar.addColumn("Recinto");
        listar.addColumn("Cantidad de Mesas");
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            String sql = "SELECT recinto.nombre, (SELECT COUNT(mesa.numero) FROM mesa WHERE recinto.idRecinto = mesa.idRecinto) FROM recinto ";
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

    public DefaultComboBoxModel getCombo(int idUsuario) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try {
            PreparedStatement ps;
            ResultSet rs = null;
            String sql = "SELECT me.numero FROM mesa as me\n"
                    + "INNER JOIN recinto as re ON re.idRecinto = me.idRecinto\n"
                    + "INNER JOIN usuario as us ON us.idRecinto=re.idRecinto\n"
                    + "WHERE us.idUsuario =?";
            String encabezado = "Mesa";
            java.sql.Connection con = conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
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
            String sql = "select idMesa from mesa where numero=? and idRecinto=?";
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, getNumero());
            ps.setInt(2, getIdRecinto());
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
