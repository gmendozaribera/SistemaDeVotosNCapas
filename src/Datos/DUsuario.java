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
public class DUsuario {

    private int idUsuario;
    private String nombre;
    private String apellidoPat;
    private String apellidoMat;
    private String correo;
    private String usuario;
    private String password;
    private int telefono;
    private int ci;
    private int idCargo;
    private int idRecinto;
    private Conexion conexion;

    public DUsuario() {
        conexion = new Conexion();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public int getCi() {
        return ci;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public int getIdRecinto() {
        return idRecinto;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public void setIdRecinto(int idRecinto) {
        this.idRecinto = idRecinto;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void guardar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("INSERT INTO usuario (nombre, apellidopat, apellidomat, correo, user, password, telefono, ci, idCargo, idRecinto)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, getNombre());
        ps.setString(2, getApellidoPat());
        ps.setString(3, getApellidoMat());
        ps.setString(4, getCorreo());
        ps.setString(5, getUsuario());
        ps.setString(6, getPassword());
        ps.setInt(7, getTelefono());
        ps.setInt(8, getCi());
        ps.setInt(9, getIdCargo());
        if (getIdRecinto() == -1) {
            ps.setNull(10, getIdRecinto());
        } else {
            ps.setInt(10, getIdRecinto());
        }

        ps.execute();
    }

    public void modificar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("UPDATE usuario SET nombre=?, apellidopat=?, apellidomat=?, correo=?, user=?, password=?, telefono=?, ci=?, idCargo=?, idRecinto=? WHERE idUsuario=?");

        ps.setString(1, getNombre());
        ps.setString(2, getApellidoPat());
        ps.setString(3, getApellidoMat());
        ps.setString(4, getCorreo());
        ps.setString(5, getUsuario());
        ps.setString(6, getPassword());
        ps.setInt(7, getTelefono());
        ps.setInt(8, getCi());
        ps.setInt(9, getIdCargo());
        if (getIdRecinto() == -1) {
            ps.setNull(10, getIdRecinto());
        } else {
            ps.setInt(10, getIdRecinto());
        }
        ps.setInt(11, getIdUsuario());

        ps.execute();
    }

    public void eliminar() throws SQLException {
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("DELETE FROM usuario WHERE idUsuario=?");
        ps.setInt(1, getIdUsuario());
        ps.execute();
    }

    public DefaultTableModel getTabla(int cargo) {
        DefaultTableModel listar = new DefaultTableModel();

        listar.addColumn("Codigo");
        listar.addColumn("Nombre");
        listar.addColumn("Apellido Pat");
        listar.addColumn("Apellido Mat");
        listar.addColumn("Correo");
        listar.addColumn("Usuario");
        listar.addColumn("Telefono");
        listar.addColumn("Carnet Identidad");
        listar.addColumn("Cargo");
        listar.addColumn("Recinto");
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            String sql;
            if (cargo == 1) {
                sql = "SELECT u.idUsuario, u.nombre, u.apellidopat ,u.apellidomat, "
                        + "u.correo, u.user, u.telefono, u.ci, ca.nombre, "
                        + "(SELECT re.nombre FROM recinto as re WHERE u.idRecinto = re.idRecinto) as recinto "
                        + "FROM usuario as u "
                        + "INNER JOIN cargo as ca on ca.idCargo = u.idCargo ORDER BY u.idUsuario asc";
            } else {
                sql = "SELECT u.idUsuario, u.nombre, u.apellidopat ,u.apellidomat, "
                        + "u.correo, u.user, u.telefono, u.ci, ca.nombre, "
                        + "(SELECT re.nombre FROM recinto as re WHERE u.idRecinto = re.idRecinto) as recinto "
                        + "FROM usuario as u "
                        + "INNER JOIN cargo as ca on ca.idCargo = u.idCargo WHERE u.idCargo>2 ORDER BY u.idUsuario asc";
            }
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

    public int existeUsuario() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conexion.getConexion();

        String sql = "SELECT count(idUsuario) FROM usuario WHERE user = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, getUsuario());
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

    public boolean login() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conexion.getConexion();
        String sql = "SELECT idUsuario, user, password, nombre, idCargo, idRecinto FROM usuario WHERE user=? LIMIT 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (getPassword().equals(rs.getString(3))) {
                    setIdUsuario(rs.getInt(1));
                    setNombre(rs.getString(4));
                    setIdCargo(rs.getInt(5));
                    setIdRecinto(rs.getInt(6));
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
}
