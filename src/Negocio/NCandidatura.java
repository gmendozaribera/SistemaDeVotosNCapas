/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DCandidatura;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class NCandidatura {

    private DCandidatura dato;

    public NCandidatura() {
        dato = new DCandidatura();
    }

    public void guardar(String nombre) {
        dato.setNombre(nombre);
        dato.guardar();
    }

    public void modificar(int id, String nombre) {
        dato.setIdCandidatura(id);
        dato.setNombre(nombre);
        try {
            dato.modificar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hay un problema al actualizar");
        }
    }

    public void eliminar(int id) {
        dato.setIdCandidatura(id);
        try {
            dato.eliminar();
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hay un problema al eliminar");
        }
    }

    public DefaultComboBoxModel listarCombo() {
        return dato.getCombo();
    }

    public int getKey(String nombre) {
        dato.setNombre(nombre);
        return dato.getKey();
    }

    public DefaultTableModel listarTabla() {
        return dato.getTabla();
    }

    public static boolean isNumeric(String str) {
        if (str.equals("")) {
            return false;
        }
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }
}
