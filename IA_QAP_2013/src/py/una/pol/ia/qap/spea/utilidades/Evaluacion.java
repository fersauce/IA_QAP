/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.utilidades;

import java.util.ArrayList;
import py.una.pol.ia.qap.spea.solucion.Solucion;

/**
 * Clase que contiene metodos de actualizacion de fitness, strength y
 * evaluaciones de los objetivos.
 *
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class Evaluacion {

    /**
     * Metodo que calcula el strength de los individuos del CPE
     *
     * @param poblacion Conjunto de Soluciones factibles
     * @param conjuntoParetoExterno CPE
     */
    public static void calcularStrength(ArrayList<Solucion> poblacion,
            ArrayList<Solucion> conjuntoParetoExterno) {
        Integer contadorCubierto;
        Double strength;
        for (Solucion paretoIndependiente : conjuntoParetoExterno) {
            contadorCubierto = 0;
            for (Solucion pobInd : poblacion) {
                if (Pareto.uDominaV(paretoIndependiente, pobInd)) {
                    contadorCubierto++;
                }
            }
            strength = (double) contadorCubierto / (poblacion.size() + 1);
            paretoIndependiente.setFitness(strength);
        }
    }

    /**
     * Metodo que actualiza el fitness de cada individuo de la poblacion
     *
     * @param poblacion Conjunto de Soluciones factibles
     * @param conjuntoParetoExterno CPE
     */
    public static void calcularFitness(ArrayList<Solucion> poblacion,
            ArrayList<Solucion> conjuntoParetoExterno) {
        Double suma;
        for (Solucion pobInd : poblacion) {
            suma = 0.0;
            for (Solucion paretoInd : conjuntoParetoExterno) {
                if (Pareto.uDominaV(paretoInd, pobInd)) {
                    suma += paretoInd.getFitness();
                }
            }
            pobInd.setFitness(suma + 1.0);
        }
    }

    /**
     * Metodo que calcula las evaluaciones de los individuos de la poblacion
     *
     * @param poblacion Conjunto de Soluciones Factibles
     * @param flujo1 Matriz de flujo 1
     * @param flujo2 Matriz de flujo 2
     * @param distancias Matriz de distancias
     */
    public static void calcularEvaluaciones(ArrayList<Solucion> poblacion,
            Double[][] flujo1, Double[][] flujo2, Double[][] distancias) {
        Double sumaObjetivo1;
        Double sumaObjetivo2;
        for (Solucion sol : poblacion) {
            Integer[] solucion = sol.getCromosoma();
            sumaObjetivo1 = 0.0;
            sumaObjetivo2 = 0.0;
            for (int ciudadI = 0; ciudadI < solucion.length; ciudadI++) {
                for (int ciudadJ = 0; ciudadJ < solucion.length; ciudadJ++) {
                    sumaObjetivo1 = sumaObjetivo1 + ((distancias[ciudadI][ciudadJ]) *
                            (flujo1[solucion[ciudadI]][solucion[ciudadJ]]));
                    sumaObjetivo2 = sumaObjetivo2 + ((distancias[ciudadI][ciudadJ]) *
                            (flujo2[solucion[ciudadI]][solucion[ciudadJ]]));
                }
            }
            sol.setEvaluaciones(new Double[]{sumaObjetivo1, sumaObjetivo2});
        }
    }
}
