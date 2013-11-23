/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.utilidades;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;
import py.una.pol.ia.qap.spea.solucion.Solucion;

/**
 *
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class OperadoresGeneticos {

    /**
     * Metodo que realiza el cruce entre dos individuos
     *
     * @param padre1 primer padre
     * @param padre2 segundo padre
     * @return descendiente de ambos
     */
    private static Integer[] ordenCrossover(Integer[] padre1,
            Integer[] padre2) {
        Random rand = new Random();
        Integer[] descendiente = new Integer[padre1.length];
        TreeSet<Integer> orden = new TreeSet<>();
        int punto1 = rand.nextInt(padre1.length);
        while (punto1 == 0 || punto1 == padre1.length) {
            punto1 = rand.nextInt(padre1.length);
        }
        int punto2 = rand.nextInt(padre1.length);
        while ((punto2 == punto1) || punto2 == 0
                || punto2 == padre1.length) {
            punto2 = rand.nextInt(padre1.length);
        }
        if (punto1 > punto2) {
            int tmp = punto1;
            punto1 = punto2;
            punto2 = tmp;
        }
        for (int i = 0; i < padre1.length; i++) {
            if (i < punto1 || i > punto2) {
                descendiente[i] = padre1[i];
            } else {
                for (int j = 0; j < padre1.length; j++) {
                    if (padre1[i] == padre2[j]) {
                        orden.add(j);
                    }
                }
            }
        }

        Integer contador = punto1;
        for (Integer numero : orden) {
            descendiente[contador] = padre2[numero];
            contador++;
        }
        return descendiente;
    }

    /**
     * Metodo que reproduce a dos individuos.
     *
     * @param individuo1 primer individuo para la reproduccion
     * @param individuo2 segundo individuo para la reproduccion
     * @return dos descendientes (ArrayList de Solucion)
     */
    private static ArrayList<Solucion> reproducirse(Solucion individuo1,
            Solucion individuo2) {
        Random ran = new Random();
        Integer[] cromosoma1 = individuo1.getCromosoma();
        Integer[] cromosoma2 = individuo2.getCromosoma();
        Integer[] cromosDesc1;
        Integer[] cromosDesc2;
        int punto1 = ran.nextInt(cromosoma1.length);
        while (!(punto1 > 0 && punto1 < cromosoma1.length)) {
            punto1 = ran.nextInt(cromosoma1.length);
        }
        cromosDesc1 = ordenCrossover(cromosoma1, cromosoma2);
        cromosDesc2 = ordenCrossover(cromosoma2, cromosoma1);
        Solucion descendiente1 = new Solucion(cromosoma1.length,
                individuo1.getEvaluaciones().length);
        descendiente1.setCromosoma(cromosDesc1);
        Solucion descendiente2 = new Solucion(cromosoma1.length,
                individuo1.getEvaluaciones().length);
        descendiente2.setCromosoma(cromosDesc2);
        ArrayList<Solucion> descendientes = new ArrayList<>();
        descendientes.add(descendiente1);
        descendientes.add(descendiente2);
        return descendientes;
    }

    /**
     * Metodo que realiza el crossover a toda la poblacion.
     *
     * @param poolDeApareamiento Poblacion seleccionada
     * @return Descendientes de la poblacion (ArrayList de Solucion)
     */
    public static ArrayList<Solucion> crossover(
            ArrayList<Solucion> poolDeApareamiento) {
        Random rand = new Random();
        ArrayList<Solucion> poblacionNueva = new ArrayList<>();
        while (!poolDeApareamiento.isEmpty() && poolDeApareamiento.size() >= 2) {
            ArrayList<Solucion> descendientes;
            int inst = rand.nextInt(poolDeApareamiento.size());
            Solucion individuo1 = poolDeApareamiento.get(inst);
            poolDeApareamiento.remove(inst);
            inst = rand.nextInt(poolDeApareamiento.size());
            Solucion individuo2 = poolDeApareamiento.get(inst);
            poolDeApareamiento.remove(inst);
            descendientes = reproducirse(individuo1, individuo2);
            for (Solucion descendiente : descendientes) {
                poblacionNueva.add(descendiente);
            }
        }
        return poblacionNueva;
    }

    /**
     * Metodo que realiza la mutacion sobre determinados miembros de la
     * poblacion
     *
     * @param poblacion Conjunto de soluciones factibles
     * @param porcentajeMutacion porcentaje de mutacion a aplicar a la poblacion
     * @param dimension
     * @return Descendientes con mutacion aplicada (ArrayList de Solucion)
     */
    public static ArrayList<Solucion> mutacion(ArrayList<Solucion> poblacion,
            Float porcentajeMutacion, int dimension) {
        ArrayList<Solucion> poblacionMutada = new ArrayList<>();
        for (Solucion individuo : poblacion) {
            poblacionMutada.add(individuo);
        }
        Random rand = new Random();
        int cantidad = Math.round((poblacion.size() * porcentajeMutacion) / 100);
        int aleatorio;

        ArrayList<Integer> seleccionados = new ArrayList<>();
        Integer[] listaSeleccionados = new Integer[cantidad];
        for (int i = 0; i < poblacion.size(); i++) {
            seleccionados.add(i);
        }
        for (int i = 0; i < cantidad; i++) {
            aleatorio = rand.nextInt(seleccionados.size());
            int seleccionado = seleccionados.get(aleatorio);
            seleccionados.remove(aleatorio);
            listaSeleccionados[i] = seleccionado;
        }
        for (int i = 0; i < cantidad; i++) {
            int pos1 = rand.nextInt(dimension);
            int pos2 = rand.nextInt(dimension);
            while (pos1 == pos2) {
                pos2 = rand.nextInt(dimension);
            }
            int tmp = poblacionMutada.get(listaSeleccionados[i]).
                    getCromosoma()[pos1];
            poblacionMutada.get(listaSeleccionados[i]).
                    getCromosoma()[pos1] = poblacionMutada.
                    get(listaSeleccionados[i]).getCromosoma()[pos2];
            poblacionMutada.get(listaSeleccionados[i]).
                    getCromosoma()[pos2] = tmp;
        }
        return poblacionMutada;
    }
}
