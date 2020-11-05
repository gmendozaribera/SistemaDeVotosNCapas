/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DUsuario;
import Datos.DCargo;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author German
 */
public class NUsuario {

    private DUsuario datoUsuario;
    private DCargo datoCargo;

    public NUsuario() {
        datoUsuario = new DUsuario();
        datoCargo = new DCargo();
    }

    public DUsuario datosUsuario() {
        return datoUsuario;
    }

    public boolean login(String usuario, String password) {
        datoUsuario.setUsuario(usuario);
        datoUsuario.setPassword(password);
        return datoUsuario.login();
    }

    public boolean existeUsuario(String usuario) {
        datoUsuario.setUsuario(usuario);
        if (datoUsuario.existeUsuario() == 0) {
            return false;
        }
        return true;
    }

    public void guardar(String nombre, String apellidoPat, String apellidoMat, String correo, String user, String password, int telefono, int ci, int idCargo, int idRecinto) throws SQLException {
        datoUsuario.setNombre(nombre);
        datoUsuario.setApellidoMat(apellidoMat);
        datoUsuario.setApellidoPat(apellidoPat);
        datoUsuario.setCi(ci);
        datoUsuario.setCorreo(correo);
        datoUsuario.setIdCargo(idCargo);
        datoUsuario.setIdRecinto(idRecinto);
        datoUsuario.setTelefono(telefono);
        datoUsuario.setPassword(password);
        datoUsuario.setUsuario(user);
        datoUsuario.guardar();
    }

    public void modificar(int id, String nombre, String apellidoPat, String apellidoMat, String correo, String user, String password, int telefono, int ci, int idCargo, int idRecinto) throws SQLException {
        datoUsuario.setIdUsuario(id);
        datoUsuario.setNombre(nombre);
        datoUsuario.setApellidoMat(apellidoMat);
        datoUsuario.setApellidoPat(apellidoPat);
        datoUsuario.setCi(ci);
        datoUsuario.setCorreo(correo);
        datoUsuario.setIdCargo(idCargo);
        datoUsuario.setIdRecinto(idRecinto);
        datoUsuario.setTelefono(telefono);
        datoUsuario.setPassword(password);
        datoUsuario.setUsuario(user);
        datoUsuario.modificar();
    }

    public void eliminar(int id) throws SQLException {
        datoUsuario.setIdUsuario(id);
        datoUsuario.eliminar();
    }

    public DefaultTableModel listarTabla(int cargo){
        return datoUsuario.getTabla(cargo);
    }

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        return mather.find();

    }

    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* Retorna un hash SHA1 a partir de un texto */
    public String sha1(String txt) {
        return getHash(txt, "SHA1");
    }

    public boolean isNumeric(String str) {
        if (str.equals("")) {
            return false;
        }
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }
}
