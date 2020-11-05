/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DMesa;
import java.text.NumberFormat;
import java.text.ParsePosition;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class NMesa {

    private DMesa dato;

    public NMesa() {
        this.dato = new DMesa();
    }

    public void asignarCantidad(int cantidad, int idRecinto) {
        dato.setNumero(cantidad);
        dato.setIdRecinto(idRecinto);
        int cantidadActual = dato.cantidadActual();
        if (cantidadActual >= cantidad) {
            dato.eliminar();
        } else {
            for (int i = cantidadActual; i < cantidad; i++) {
                dato.setNumero(i + 1);
                dato.guardar();
            }
        }
    }

    public boolean existeRecinto(int idRecinto) {
        dato.setIdRecinto(idRecinto);
        if (dato.existeRecinto() == 0) {
            return false;
        }
        return true;
    }

    public DefaultTableModel listarTabla() {
        return dato.getTabla();
    }

    public DefaultComboBoxModel listarCombo(int idUsuario) {
        return dato.getCombo(idUsuario);
    }

    public int getKey(String numero, int recinto) {
        int num = Integer.valueOf(numero);
        dato.setNumero(num);
        dato.setIdRecinto(recinto);
        return dato.getKey();
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
