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
     * Variable privada que representa a la solucionClusterizada
     */
    private ArrayList<Solucion> solucionClusterizada;

    /**
     * Seter para solucionClusterizada
     *
     * @param solucionClusterizada
     */
    public void setSolucionClusterizada(ArrayList<Solucion> 
            solucionClusterizada) {
        this.solucionClusterizada = solucionClusterizada;
    }

    /**
     * Getter de solucionClusterizada
     *
     * @return ArrayList<Solucion> solucion clusterizada
     */
    public ArrayList<Solucion> getSolucionClusterizada() {
        return solucionClusterizada;
    }

}
