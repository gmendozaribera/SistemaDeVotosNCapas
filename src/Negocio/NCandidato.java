/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DCandidato;
import java.text.NumberFormat;
import java.text.ParsePosition;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class NCandidato {

    private DCandidato dato;

    public NCandidato() {
        dato = new DCandidato();
    }

    public void guardar(String nombre, String apellidoPat, String apellidoMat, int ci, int idCandidatura, int idPartido) {
        dato.setNombre(nombre);
        dato.setApellidoPat(apellidoPat);
        dato.setApellidoMat(apellidoMat);
        dato.setCi(ci);
        dato.setIdCandidatura(idCandidatura);
        dato.setIdPartido(idPartido);
        dato.guardar();
    }

    public void modificar(int id, String nombre, String apellidoPat, String apellidoMat, int ci, int idCandidatura, int idPartido) {
        dato.setIdCandidato(id);
        dato.setNombre(nombre);
        dato.setApellidoPat(apellidoPat);
        dato.setApellidoMat(apellidoMat);
        dato.setCi(ci);
        dato.setIdCandidatura(idCandidatura);
        dato.setIdPartido(idPartido);
        dato.modificar();
    }

    public void eliminar(int id) {
        dato.setIdCandidato(id);
        dato.eliminar();

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
