/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.solucion;

/**
 * Clase que mantiene la solucion de SPEA
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class Solucion {

    /**
     * Variable privada que representa el cromosoma.
     */
    private Integer[] cromosoma;
    /**
     * Variable privada que representa las evaluaciones.
     */
    private Double[] evaluaciones;
    /**
     * Variable privada que representa el fitness.
     */
    private Double fitness;

    /**
     * Constructor de la clase Solucion
     *
     * @param dimension dimension del cromosoma.
     * @param nroEvaluaciones dimension de las evaluaciones.
     */
    public Solucion(Integer dimension, Integer nroEvaluaciones) {
        this.cromosoma = new Integer[dimension];
        this.evaluaciones = new Double[nroEvaluaciones];
    }

    /**
     * Setea el cromosoma
     *
     * @param cromosoma
     */
    public void setCromosoma(Integer[] cromosoma) {
        this.cromosoma = cromosoma;
    }

    /**
     * Setea las evaluaciones
     *
     * @param evaluaciones
     */
    public void setEvaluaciones(Double[] evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    /**
     * Setea el fitness
     *
     * @param fitness
     */
    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    /**
     * Retorna el cromosoma
     *
     * @return cromosoma (Array de Integer)
     */
    public Integer[] getCromosoma() {
        return cromosoma;
    }

    /**
     * Retorna las evaluaciones obtenidas
     *
     * @return evaluaciones (Array de Double)
     */
    public Double[] getEvaluaciones() {
        return evaluaciones;
    }

    /**
     * Retorna el fitness de esta solucion
     *
     * @return fitness (Double)
     */
    public Double getFitness() {
        return fitness;
    }
}
