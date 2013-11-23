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
 * Clase que contiene los metodos de seleccion para el algoritmo en cuestion
 *
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class ProcesoSeleccion {

    /**
     * Metodo que representa el torneo entre dos individuos
     * @param particpante1 Individuo participante nro 1
     * @param participante2 Individuo participante nro 2
     * @return Individuo ganador del torneo (Solucion)
     */
    private static Solucion torneoBinario(Solucion particpante1, 
            Solucion participante2){
        Solucion ganador;
        Random rand = new Random();
        if(particpante1.getFitness() < participante2.getFitness()){
            ganador = particpante1;
        } else if (participante2.getFitness() < particpante1.getFitness()){
            ganador = participante2;
        } else{
            int random = rand.nextInt(2);
            if(random == 0){
                ganador = particpante1;
            }else{
                ganador = participante2;
            }    
        }
        return ganador;
    }
    
    /**
     * Metodo que realiza la seleccion de los nuevos individuos de la poblacion
     * que se utilizan para la aplicacion de los Operadores Geneticos.
     * @param poblacion Conjunto de individuos factibles
     * @param conjuntoParetoExterno CPE
     * @return Poblacion Seleccionada (ArrayList de Solucion)
     */
    public static ArrayList<Solucion> seleccionarIndividuo(
            ArrayList<Solucion> poblacion,
            ArrayList<Solucion> conjuntoParetoExterno) {
        Random rand = new Random();
        ArrayList<Solucion> union = new ArrayList<>();
        ArrayList<Solucion> poolDeApareamiento = new ArrayList<>();
        /*
        * union representa la union de la poblacion con el CPE
        */
        for (Solucion individuoPoblacion:poblacion){
            union.add(individuoPoblacion);
        }
        for(Solucion individuoCPE : conjuntoParetoExterno){
            union.add(individuoCPE);
        }
        
        while(!union.isEmpty() && union.size()>2){
            int inst = rand.nextInt(union.size());
            Solucion participante1 = union.get(inst);
            union.remove(inst);
            inst = rand.nextInt(union.size());
            Solucion participante2 = union.get(inst);
            union.remove(inst);
            Solucion ganador = torneoBinario(participante1,participante2);
            poolDeApareamiento.add(ganador);
        }
        return poolDeApareamiento;
    }
}
