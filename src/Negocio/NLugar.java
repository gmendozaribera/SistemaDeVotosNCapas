/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DLugar;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class NLugar {

    private DLugar dato;

    public NLugar() {
        dato = new DLugar();
    }

    public void guardar(String nombre, String tipo) throws SQLException {

        dato.setNombre(nombre);
        dato.setTipo(tipo);
        dato.guardar();
    }

    public void modificar(int id, String nombre, String tipo) throws SQLException {
        dato.setIdLugar(id);
        dato.setNombre(nombre);
        dato.setTipo(tipo);
        dato.modificar();
    }

    public void eliminar(int id) throws SQLException {
        dato.setIdLugar(id);
        dato.eliminar();
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
