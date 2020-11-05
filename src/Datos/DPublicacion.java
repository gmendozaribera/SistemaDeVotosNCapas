/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author German
 */
public class DPublicacion {

    private Conexion conexion;
    private int idPublicacion;
    private int idMesa;
    private int cantidadTotal;

    public DPublicacion() {
        conexion = new Conexion();
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public void guardar(){
        try {
            PreparedStatement ps = null;
            java.sql.Connection conn = conexion.getConexion();
            ps = conn.prepareStatement("INSERT INTO publicacion (idMesa, cantidadTotal) VALUES (?,?)");
            ps.setInt(1, getIdMesa());
            ps.setInt(2, getCantidadTotal());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DPublicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdUltimaPublicacion() {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            String sql = "SELECT MAX(idPublicacion) FROM publicacion";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            rs.next();
            int ultimaColumna = rs.getInt(1);
            return ultimaColumna;
        } catch (SQLException ex) {
            System.err.println("Error al obtener el ultimo ID de publicacion");
        }
        return 0;
    }

    public DefaultTableModel getTabla() {
        DefaultTableModel listar = new DefaultTableModel();
        listar.addColumn("cod. Publicacion");
        listar.addColumn("N° de Mesa");
        listar.addColumn("Cod. de Mesa");
        listar.addColumn("Cantidad Total");
        listar.addColumn("Fecha de Publicacion");
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            String sql = "SELECT pu.idPublicacion, me.numero, me.idMesa , pu.cantidadTotal, pu.fecha FROM publicacion as pu "
                    + "INNER JOIN mesa as me on me.idMesa = pu.idMesa "
                    + "ORDER BY pu.idPublicacion ASC";
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
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return listar;
    }
}
