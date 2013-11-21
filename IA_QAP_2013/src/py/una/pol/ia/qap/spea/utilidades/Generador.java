/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.utilidades;

import java.util.ArrayList;
import java.util.Random;
import py.una.pol.ia.qap.spea.solucion.Solucion;

/**
 * Clase que genera la poblacion inicial para trabajar con SPEA
 *
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class Generador {

    /**
     * Atributo privado que representa a la poblacion inicial
     */
    private ArrayList<Solucion> poblacionInicial;

    public ArrayList<Integer> iniciarPosiciones(Integer dimension) {
        ArrayList<Integer> posicion = new ArrayList<Integer>();
        for (int i = 0; i < dimension; i++) {
            posicion.add(i);
        }
        return posicion;
    }

    /**
     * Metodo que genera la poblacion inicial del problema en cuestion
     *
     * @param cantidadPoblacion
     * @param dimension
     * @param nroObjetivos
     * @return ArrayList<Solucion> poblacion inicial del problema
     */
    public ArrayList<Solucion> generarPoblacionInicial(int cantidadPoblacion,
            int dimension, int nroObjetivos) {
        /*
         * 
         */
        Random rand = new Random();
        int aleatorio;
        Integer[] solucion = new Integer[dimension];
        ArrayList<Integer> posibilidades;
        this.poblacionInicial = new ArrayList<>();
        for (int i = 0; i < cantidadPoblacion; i++) {
            posibilidades = iniciarPosiciones(dimension);
            for (int j = 0; j < dimension; j++) {
                aleatorio = rand.nextInt(posibilidades.size());
                solucion[j] = posibilidades.get(aleatorio);
                posibilidades.remove(aleatorio);
            }
            Solucion solucionProblema = new Solucion(dimension, nroObjetivos);
            solucionProblema.setCromosoma(solucion);
            poblacionInicial.add(solucionProblema);
            solucion = new Integer[dimension];
        }
        return this.poblacionInicial;
    }

}
