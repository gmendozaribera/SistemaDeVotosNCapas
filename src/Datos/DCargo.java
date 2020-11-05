/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author German
 */
public class DCargo {
    
    private int idCargo;
    private String nombre;
    private static Conexion conexion;

    public DCargo(){
        conexion = new Conexion();
    }
    
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Conexion getConexion() {
        return conexion;
    }

    public DefaultComboBoxModel getCombo() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try{
            PreparedStatement ps;
            ResultSet rs = null;
            String sql = "select nombre from cargo;";
            String encabezado = "Cargo";
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
            String sql = "select idCargo from cargo where nombre=?";
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
