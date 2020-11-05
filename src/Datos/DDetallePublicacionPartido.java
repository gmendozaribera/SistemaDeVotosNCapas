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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class DDetallePublicacionPartido {

    private int idPublicacion;
    private int idPartido;
    private int cantidad;
    private Conexion conexion;

    public DDetallePublicacionPartido() {
        this.conexion = new Conexion();
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void guardar()  {
        try {
            PreparedStatement ps = null;
            java.sql.Connection conn = conexion.getConexion();
            
            ps = conn.prepareStatement("INSERT INTO detallepublicacionpartido (idPublicacion, idPartido, cantidad) VALUES (?,?,?)");
            ps.setInt(1, getIdPublicacion());
            ps.setInt(2, getIdPartido());
            ps.setInt(3, getCantidad());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DDetallePublicacionPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel getTabla(int idPublicacion) {
        DefaultTableModel listar = new DefaultTableModel();
        listar.addColumn("Mesa");
        listar.addColumn("Partido");
        listar.addColumn("Cantidad");
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            String sql = "SELECT me.numero, pa.nombre, de.cantidad FROM detallevoto as de\n"
                    + "INNER JOIN mesa as me ON me.idMesa=de.idMesa\n"
                    + "INNER JOIN partido as pa ON pa.idPartido=de.idPartido";
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

}
