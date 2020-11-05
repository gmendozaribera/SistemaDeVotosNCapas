/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

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
public class DCandidato {

    private int idCandidato;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private int ci;
    private int idCandidatura;
    private int idPartido;
    private Conexion conexion;

    public DCandidato() {
        conexion = new Conexion();
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public int getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(int idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public void guardar() {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        try {
            ps = conn.prepareStatement("INSERT INTO candidato (nombre, apellidoPat, apellidoMat, ci, idCandidatura, idPartido) VALUES (?,?,?,?,?,?)");
            ps.setString(1, getNombre());
            ps.setString(2, getApellidoPat());
            ps.setString(3, getApellidoMat());
            ps.setInt(4, getCi());
            ps.setInt(5, getIdCandidatura());
            ps.setInt(6, getIdPartido());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DCandidato.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modificar() {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        try {
            ps = conn.prepareStatement("UPDATE candidato SET nombre=?, apellidoPat=?, apellidoMat=?, ci=?, idCandidatura=?, idPartido=? WHERE idCandidato=?");
            ps.setString(1, getNombre());
            ps.setString(2, getApellidoPat());
            ps.setString(3, getApellidoMat());
            ps.setInt(4, getCi());
            ps.setInt(5, getIdCandidatura());
            ps.setInt(6, getIdPartido());
            ps.setInt(7, getIdCandidato());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DCandidato.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminar() {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        try {
            ps = conn.prepareStatement("DELETE FROM candidato WHERE idCandidato=?");
            ps.setInt(1, getIdCandidatura());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DCandidato.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DefaultTableModel getTabla() {
        DefaultTableModel listar = new DefaultTableModel();
        listar.addColumn("Codigo");
        listar.addColumn("Nombre");
        listar.addColumn("Apellido Pat.");
        listar.addColumn("Apellido Mat.");
        listar.addColumn("CI.");
        listar.addColumn("Candidatura");
        listar.addColumn("Partido");
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            String sql = "SELECT ca.idCandidato, ca.nombre, ca.apellidoPat, ca.apellidoMat, ca.ci, can.nombre, pa.nombre "
                    + "FROM candidato as ca "
                    + "INNER JOIN candidatura as can on can.idCandidatura=ca.idCandidatura "
                    + "INNER JOIN partido as pa on pa.idPartido=ca.idPartido "
                    + "ORDER BY pa.nombre ASC";
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
