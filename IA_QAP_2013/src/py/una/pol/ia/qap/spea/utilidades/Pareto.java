/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.utilidades;

import java.util.ArrayList;
import py.una.pol.ia.qap.spea.solucion.Solucion;

/**
 *
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class Pareto {

    /**
     * Metodo que verifica la dominancia entre dos soluciones
     *
     * @param u solucion 1
     * @param v solucion 2
     * @return True si la solucion 1 domina a la solucion 2
     */
    public static boolean uDominaV(Solucion u, Solucion v) {
        Double[] evaluacion1 = u.getEvaluaciones();
        Double[] evaluacion2 = v.getEvaluaciones();
        boolean uDominaV = false;
        if (evaluacion1[0] <= evaluacion2[0]
                && evaluacion1[1] <= evaluacion2[1]) {
            if (evaluacion1[0] < evaluacion2[0]
                    || evaluacion1[1] < evaluacion2[1]) {
                uDominaV = true;
            }
        } else {
            uDominaV = false;
        }
        return uDominaV;
    }

    /**
     * Metodo que actualiza el conjunto Pareto externo utilizado en SPEA
     *
     * @param poblacion Conjunto de Soluciones factibles
     * @param conjuntoParetoExterno Conjunto Pareto Externo
     * @return Conjunto Pareto Externo actualizado
     */
    public static ArrayList<Solucion> actualizarConjuntoPareto(
            ArrayList<Solucion> poblacion,
            ArrayList<Solucion> conjuntoParetoExterno) {
        ArrayList<Solucion> noDominados = new ArrayList<>();
        ArrayList<Solucion> nuevoPareto = new ArrayList<>();
        int contador;
        /*
         * Esta seccion de codigo se encarga de extraer de la poblacion las 
         * soluciones no dominadas de la poblacion
         */
        for (int i = 0; i < poblacion.size(); i++) {
            contador = 0;
            for (int j = 0; j < poblacion.size(); j++) {
                if (i != j) {
                    Solucion v = poblacion.get(i);
                    Solucion u = poblacion.get(j);
                    if (uDominaV(u, v)) {
                        contador++;
                    }
                }
            }
            if (contador == 0) {
                noDominados.add(poblacion.get(i));
            }
        }
        /*
         * Aqui agrega dichas soluciones al Conjunto Pareto Externo
         */
        for (Solucion solucion : noDominados) {
            conjuntoParetoExterno.add(solucion);
        }

        /*
         * Aqui se actualiza el Conjunto Pareto Externo(CPE), mejor dicho, se 
         * eliminan las soluciones dominadas al agregar las nuevas soluciones
         * no dominadas de la poblacion al CPE
         */
        for (int i = 0; i < conjuntoParetoExterno.size(); i++) {
            contador = 0;
            for (int j = 0; j < conjuntoParetoExterno.size(); j++) {
                if (i != j) {
                    Solucion v = conjuntoParetoExterno.get(i);
                    Solucion u = conjuntoParetoExterno.get(j);
                    if (uDominaV(u, v)) {
                        contador++;
                    }
                }
            }
            if (contador == 0) {
                nuevoPareto.add(conjuntoParetoExterno.get(i));
            }
        }
        return nuevoPareto;
    }
}
