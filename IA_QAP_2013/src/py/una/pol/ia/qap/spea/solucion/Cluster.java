/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.solucion;

import java.util.ArrayList;

/**
 * Clase que realiza el clustering en caso que sea necesario
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class Cluster {

    /**
     * Variable privada que representa a la solucion clusterizada
     */
    private ArrayList<Solucion> solucion;

    /**
     * Seter para solucion
     *
     * @param solucion
     */
    public void setSolucion(ArrayList<Solucion> solucion) {
        this.solucion = solucion;
    }

    /**
     * Retorna solucion
     *
     * @return
     */
    public ArrayList<Solucion> getSolucion() {
        return solucion;
    }

}
