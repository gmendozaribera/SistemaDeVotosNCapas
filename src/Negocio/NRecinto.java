/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DRecinto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class NRecinto {

    private DRecinto dato;

    public NRecinto() {
        dato = new DRecinto();
    }

    public void guardar(String nombre, int idLugar) throws SQLException {

        dato.setNombre(nombre);
        dato.setIdLugar(idLugar);

        dato.guardar();

    }

    public void modificar(int id, String nombre, int idLugar) throws SQLException {
        dato.setIdRecinto(id);
        dato.setNombre(nombre);
        dato.setIdLugar(idLugar);
        dato.modificar();

    }

    public void eliminar(int id) throws SQLException {
        dato.setIdRecinto(id);
        dato.eliminar();

    }

    public DefaultTableModel listarTabla() {
        return dato.getTabla();
    }

    public DefaultComboBoxModel listarCombo() {
        return dato.getCombo();
    }

    public int getKey(String nombre) {
        dato.setNombre(nombre);
        return dato.getKey();
    }
}
