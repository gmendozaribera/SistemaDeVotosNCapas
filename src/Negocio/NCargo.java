/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DCargo;
import javax.swing.*;

/**
 *
 * @author German
 */
public class NCargo {

    private DCargo dato;

    public NCargo() {
        dato = new DCargo();
    }
    
    public DefaultComboBoxModel listarCombo() {
        return dato.getCombo();
    }

    public int getKey(String nombre){
        dato.setNombre(nombre);
        return dato.getKey();
    }
}
