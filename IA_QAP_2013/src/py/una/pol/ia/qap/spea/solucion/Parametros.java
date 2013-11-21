/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.solucion;

/**
 * Clase que mantiene los parametros a utilizar en SPEA
 * @author sauce
 */
public class Parametros {

    private Integer dimension;
    private Integer nroObjetivos;
    private Integer nroGeneraciones;
    private Float porcMutacion;
    private Integer cantidadPoblacion;
    private Integer cantidadConjuntoPareto;

    /**
     * Seter para dimension
     *
     * @param dimension
     */
    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    /**
     * Seter de nroObjetivos
     *
     * @param nroObjetivos
     */
    public void setNroObjetivos(Integer nroObjetivos) {
        this.nroObjetivos = nroObjetivos;
    }

    /**
     * Seter de nroGeneraciones
     *
     * @param nroGeneraciones
     */
    public void setNroGeneraciones(Integer nroGeneraciones) {
        this.nroGeneraciones = nroGeneraciones;
    }

    /**
     * Seter de porcMutacion
     *
     * @param porcMutacion
     */
    public void setPorcMutacion(Float porcMutacion) {
        this.porcMutacion = porcMutacion;
    }

    /**
     * Seter de cantidadPoblacion
     *
     * @param cantidadPoblacion
     */
    public void setCantidadPoblacion(Integer cantidadPoblacion) {
        this.cantidadPoblacion = cantidadPoblacion;
    }

    /**
     * Seter de cantidadConjuntoPareto
     *
     * @param cantidadConjuntoPareto
     */
    public void setCantidadConjuntoPareto(Integer cantidadConjuntoPareto) {
        this.cantidadConjuntoPareto = cantidadConjuntoPareto;
    }

    /**
     * Getter de dimension
     *
     * @return Integer
     */
    public Integer getDimension() {
        return dimension;
    }

    /**
     * Getter de nroObjetivos
     *
     * @return Integer
     */
    public Integer getNroObjetivos() {
        return nroObjetivos;
    }

    /**
     * Getter de nroGeneraciones
     *
     * @return Integer
     */
    public Integer getNroGeneraciones() {
        return nroGeneraciones;
    }

    /**
     * Getter de porcMutacion
     *
     * @return Float
     */
    public Float getPorcMutacion() {
        return porcMutacion;
    }

    /**
     * Getter de cantidadPoblacion
     *
     * @return Integer
     */
    public Integer getCantidadPoblacion() {
        return cantidadPoblacion;
    }

    /**
     * Getter de cantidadConjuntoPareto
     *
     * @return Integer
     */
    public Integer getCantidadConjuntoPareto() {
        return cantidadConjuntoPareto;
    }

}
