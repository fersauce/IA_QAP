/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import py.una.pol.ia.qap.spea.solucion.Instancia;
import py.una.pol.ia.qap.spea.solucion.Parametros;
import py.una.pol.ia.qap.spea.solucion.Solucion;
import py.una.pol.ia.qap.spea.utilidades.Evaluacion;
import py.una.pol.ia.qap.spea.utilidades.Generador;
import py.una.pol.ia.qap.spea.utilidades.LecturaParametros;
import py.una.pol.ia.qap.spea.utilidades.OperadoresGeneticos;
import py.una.pol.ia.qap.spea.utilidades.Pareto;
import py.una.pol.ia.qap.spea.utilidades.ProcesoSeleccion;
import py.una.pol.ia.qap.spea.utilidades.ReductorPareto;

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
     * Constructor de la clase SPEA. Inicializa todos los atributos de la clase
     */
    public SPEA() {
        this.poblacion = new ArrayList<>();
        this.conjuntoParetoExterno = new ArrayList<>();
        this.instancia = new Instancia();
        this.parametros = new Parametros();
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
    public void setConjuntoParetoExterno(
            ArrayList<Solucion> conjuntoParetoExterno) {
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
        /*
        * Obtiene los parametros del archivo de parametros.
        */
        problema.setParametros(LecturaParametros.
                leer((new File("src/py/una/pol/ia/qap/spea"
                                + "/archivos/parametros.txt")).
                        getAbsolutePath()));
        /*
        * Obtiene las matrices a utilizar en el problema
        * del archivo proveido.
        */
        problema.getInstancia().
                leerDatos((new File("src/py/una/pol/ia/qap/spea/archivos"
                                + "/qapUni.75.p75.1.qap.txt")).
                        getAbsolutePath());
        /*
        * Crea el generador y setea la poblacion inicial 
        * y calcula las evaluaciones iniciales
        */
        Generador generador = new Generador();
        problema.setPoblacion(generador.generarPoblacionInicial(
                problema.parametros.getCantidadPoblacion(),
                problema.parametros.getDimension(),
                problema.parametros.getNroObjetivos()));
        Evaluacion.calcularEvaluaciones(problema.getPoblacion(),
                problema.getInstancia().getDistancias(),
                problema.getInstancia().getFlujo1(),
                problema.getInstancia().getFlujo2());
        /*
        * Aqui es la parte donde se realiza lo principal del algoritmo
        * hasta que llegue al criterio de parada.
        */
        while (generaciones < problema.parametros.getNroGeneraciones()) {
            /*
            * Actualiza el Conjunto Pareto Externo (CPE)
            */
            problema.setConjuntoParetoExterno(Pareto.
                    actualizarConjuntoPareto(problema.getPoblacion(),
                            problema.getConjuntoParetoExterno()));
            /*
            * Aqui ingresa si el tamanho del CPE es mayor al 
            * tamanho de la poblacion, entonces se realiza una tecnica
            * de clustering al CPE.
            */
            if (problema.getConjuntoParetoExterno().size()
                    > problema.parametros.getCantidadConjuntoPareto()) {
                problema.setConjuntoParetoExterno(ReductorPareto.
                        reducirConjuntoPareto(problema.parametros.
                                getCantidadConjuntoPareto(),
                                problema.getConjuntoParetoExterno()));
            }
            /*
            * Calcula el strength correspondiente para cada 
            * individuo del CPE.
            */
            Evaluacion.calcularStrength(problema.getPoblacion(),
                    problema.getConjuntoParetoExterno());
            /*
            * Calcula el fitness para cada individuo de la poblacion
            * utilizando el strength del CPE.
            */
            Evaluacion.calcularFitness(problema.getPoblacion(),
                    problema.getConjuntoParetoExterno());
            /*
            * Aqui realiza la seleccion entre los individuos de la union de la 
            * poblacion y el CPE utilizando torneo binario.
            */
            ArrayList<Solucion> poolDeApareamiento = ProcesoSeleccion.
                    seleccionarIndividuo(problema.getPoblacion(),
                            problema.getConjuntoParetoExterno());
            /*
            *
            */
            ArrayList<Solucion> nuevaGeneracion = OperadoresGeneticos.
                    crossover(poolDeApareamiento);
            /*
            *
            */
            ArrayList<Solucion> nuevaPoblacionMutada = OperadoresGeneticos.
                    mutacion(nuevaGeneracion, problema.getParametros().
                            getPorcMutacion(),
                            problema.getParametros().getDimension());
            /*
            *
            */
            problema.setPoblacion(nuevaPoblacionMutada);
            Evaluacion.calcularEvaluaciones(problema.getPoblacion(),
                    problema.getInstancia().getDistancias(),
                    problema.getInstancia().getFlujo1(),
                    problema.getInstancia().getFlujo2());
            /*
            *
            */
            generaciones = generaciones + 1;
            System.out.println("Generacion: " + generaciones);
        }

        /*
        *
        */
        try {
            try (FileWriter fichero = new FileWriter((new File("src/py/una/pol"
                    + "/ia/qap/spea/archivos"
                    + "/pareto-qapUni.75.p75.1.qap.txt")).
                    getAbsolutePath())) {
                PrintWriter pWriter = new PrintWriter(fichero);
                for (Solucion solucion : problema.
                        getConjuntoParetoExterno()) {
                    pWriter.println(solucion.getEvaluaciones()[0]
                            + " " + solucion.getEvaluaciones()[1]);
                    for (Integer cromosoma : solucion.getCromosoma()) {
                        System.out.print(" | " + cromosoma);
                    }
                    System.out.println();
                }
            }
        } catch (IOException e) {
        }
    }
}
