/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DPartido;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author German
 */
public class NPartido {

    DPartido dato;

    public NPartido() {
        dato = new DPartido();
    }

    public boolean existePartido(String partido) {
        dato.setNombre(partido);
        if (dato.existePartido() == 0) {
            return false;
        }
        return true;
    }

    public void guardar(String nombre, String sigla){
        dato.setNombre(nombre);
        dato.setSigla(sigla);

        try {
            dato.guardar();
        } catch (SQLException ex) {
            Logger.getLogger(NPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar(int id, String nombre, String sigla) {
        dato.setIdPartido(id);
        dato.setNombre(nombre);
        dato.setSigla(sigla);
        try {
            dato.modificar();
        } catch (SQLException ex) {
            Logger.getLogger(NPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(int id) {
        dato.setIdPartido(id);
        try {
            dato.eliminar();
        } catch (SQLException ex) {
            Logger.getLogger(NPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel listarTabla() {
        return dato.getTabla();
    }

    public DefaultComboBoxModel listarCombo() {
        return dato.getCombo();
    }

    public int getKey(String nombre){
        dato.setNombre(nombre);
        return dato.getKey();
    }
}
