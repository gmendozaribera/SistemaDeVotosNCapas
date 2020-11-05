/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DPublicacion;
import Datos.DDetallePublicacionPartido;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class NPublicacion {

    DPublicacion datoPublicacion;
    DDetallePublicacionPartido datoDetalle;

    public NPublicacion() {
        datoPublicacion = new DPublicacion();
        datoDetalle = new DDetallePublicacionPartido();
    }

    public void guardar(int idMesa, int cantidadTotal)  {
        datoPublicacion.setIdMesa(idMesa);
        datoPublicacion.setCantidadTotal(cantidadTotal);
        datoPublicacion.guardar();
    }

    public void guardarDetalle(ArrayList<Integer> listaIdPartido, ArrayList<Integer> listaCantidad) {
        int idPublicacion = datoPublicacion.getIdUltimaPublicacion();
        datoDetalle.setIdPublicacion(idPublicacion);
        for (int i = 0; i < listaIdPartido.size(); i++) {
            datoDetalle.setIdPartido(listaIdPartido.get(i));
            datoDetalle.setCantidad(listaCantidad.get(i));
            datoDetalle.guardar();
        }
    }

    public DefaultTableModel listarTabla() {
        return datoPublicacion.getTabla();
    }
}
