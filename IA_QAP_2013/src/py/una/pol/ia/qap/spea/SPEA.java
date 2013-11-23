/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea;

import java.io.File;
import java.util.ArrayList;
import py.una.pol.ia.qap.spea.solucion.Instancia;
import py.una.pol.ia.qap.spea.solucion.Parametros;
import py.una.pol.ia.qap.spea.solucion.Solucion;
import py.una.pol.ia.qap.spea.utilidades.LecturaParametros;

/**
 * Clase que ejecuta el algoritmo de resolucion de QAP aplicando SPEA
 *
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class SPEA {

    /**
     * Atributo privado de la clase SPEA. Objeto que representa a la poblacion
     * con la que trabaja SPEA.
     */
    private ArrayList<Solucion> poblacion;
    /**
     * Atributo privado de la clase SPEA. Objeto que representa al Conjunto
     * Pareto Externo con el que trabaja SPEA.
     */
    private ArrayList<Solucion> conjuntoParetoExterno;
    /**
     * Atributo privado de la clase SPEA. Objeto que representa a la instancia
     * de los archivos de donde SPEA obtiene los datos de las matrices.
     */
    private Instancia instancia;
    /**
     * Atributo privado de la clase SPEA. Objeto que representa a los parametros
     * a utilizar para llevar a cabo SPEA
     */
    private Parametros parametros;

    /**
     * Constructor de la clase SPEA.
     *
     * @param poblacion conjunto de soluciones factibles
     * @param conjuntoParetoExterno CPE
     * @param parametros parametros a utilizar en el algoritmo
     */
    public SPEA(ArrayList<Solucion> poblacion,
            ArrayList<Solucion> conjuntoParetoExterno,
            Parametros parametros) {
        this.poblacion = poblacion;
        this.conjuntoParetoExterno = conjuntoParetoExterno;
        this.parametros = parametros;
    }

    /**
     * Constructor de la clase SPEA.
     */
    public SPEA() {
    }

    /**
     * Getter de poblacion.
     *
     * @return poblacion (ArrayList de Solucion)
     */
    public ArrayList<Solucion> getPoblacion() {
        return poblacion;
    }

    /**
     * Setter de poblacion.
     *
     * @param poblacion conjunto de soluciones factibles
     */
    public void setPoblacion(ArrayList<Solucion> poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * Getter de conjuntoParetoExterno
     *
     * @return CPE (ArrayList de Solucion)
     */
    public ArrayList<Solucion> getConjuntoParetoExterno() {
        return conjuntoParetoExterno;
    }

    /**
     * Setter de conjuntoParetoExterno.
     *
     * @param conjuntoParetoExterno CPE
     */
    public void setConjuntoParetoExterno(ArrayList<Solucion> conjuntoParetoExterno) {
        this.conjuntoParetoExterno = conjuntoParetoExterno;
    }

    /**
     * Getter de instancia.
     *
     * @return instancia (Instancia)
     */
    public Instancia getInstancia() {
        return instancia;
    }

    /**
     * Setter de instancia.
     *
     * @param instancia objeto a setear
     */
    public void setInstancia(Instancia instancia) {
        this.instancia = instancia;
    }

    /**
     * Getter de parametros
     *
     * @return parametros (Parametros)
     */
    public Parametros getParametros() {
        return parametros;
    }

    /**
     * Setter de parametros.
     *
     * @param parametros objeto a setear
     */
    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Integer generaciones = 0;
        SPEA problema = new SPEA();
        problema.setParametros(LecturaParametros.
                leer((new File("src/py/una/pol/ia/qap/spea"
                + "/archivos/parametros.txt")).
                getAbsolutePath()));
        problema.getInstancia().
                leerDatos((new File("src/py/una/pol/ia/qap/spea"
                + "/archivos/qapUni.75.p75.1.qap.txt")).
                getAbsolutePath());
        
    }
}
