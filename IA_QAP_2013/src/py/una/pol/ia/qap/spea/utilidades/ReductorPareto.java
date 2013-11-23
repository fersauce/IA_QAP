/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.utilidades;

import java.util.ArrayList;
import py.una.pol.ia.qap.spea.solucion.Cluster;
import py.una.pol.ia.qap.spea.solucion.Solucion;

/**
 * Clase que aplica clustering al conjunto pareto si fuese necesario
 *
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class ReductorPareto {

    /**
     * Metodo que crea un cluster
     *
     * @param conjParetoExterno
     * @return Lista de miembros del cluster (ArrayList de Cluster)
     */
    private static ArrayList<Cluster> crearCluster(ArrayList<Solucion> conjParetoExterno) {
        ArrayList<Cluster> listaCluster = new ArrayList<>();
        for (Solucion individuoPareto : conjParetoExterno) {
            Cluster cluster = new Cluster();
            ArrayList<Solucion> vectorDeSoluciones = new ArrayList<>();
            vectorDeSoluciones.add(individuoPareto);
            cluster.setSolucionClusterizada(vectorDeSoluciones);
            listaCluster.add(cluster);
        }
        return listaCluster;
    }

    /**
     * Metodo que calcula la distancia entre dos clusteres
     *
     * @param cluster1 Cluster uno
     * @param cluster2 Cluster dos
     * @return Distancia entre ambos clusteres (Double)
     */
    private static Double calcularDistancia(Cluster cluster1, Cluster cluster2) {
        Double distancia;
        Double sum = 0.0;
        Double dist;
        for (Solucion solCluster1 : cluster1.getSolucionClusterizada()) {
            for (Solucion solCluster2 : cluster2.getSolucionClusterizada()) {
                dist = 0.0;
                for (int i = 0; i < solCluster1.getEvaluaciones().length; i++) {
                    dist += Math.pow((solCluster1.getEvaluaciones()[i]
                            - solCluster2.getEvaluaciones()[i]), 2);
                }
                sum += Math.sqrt(dist);
            }
        }
        distancia = (1 / (cluster1.getSolucionClusterizada().size()
                * cluster2.getSolucionClusterizada().size()) * sum);
        return distancia;
    }

    /**
     * Metodo que calcula el centroide de un cluster
     *
     * @param cluster cluster que se desea saber su centroide
     * @return Centroide del cluster (Solucion)
     */
    private static Solucion getCentroide(Cluster cluster) {
        int tamanho = cluster.getSolucionClusterizada().size();
        Double distancia;
        Double suma;
        Double minDistance = Double.POSITIVE_INFINITY;
        Double promedioDistancias;
        int representante = 0;
        ArrayList<Solucion> listaSoluciones = cluster.getSolucionClusterizada();
        for (int i = 0; i < tamanho; i++) {
            Double[] evaluaciones1 = listaSoluciones.get(i).getEvaluaciones();
            suma = 0.0;
            for (int j = 0; j < tamanho; j++) {
                if (i != j) {
                    distancia = 0.0;
                    Double[] evaluaciones2 = listaSoluciones.get(j).getEvaluaciones();
                    for (int k = 0; k < evaluaciones2.length; k++) {
                        distancia = distancia + Math.pow((evaluaciones2[k] - evaluaciones1[k]), 2);
                    }
                    distancia = Math.sqrt(distancia);
                    suma += distancia;
                }
            }
            promedioDistancias = suma / (listaSoluciones.size() - 1);
            if (promedioDistancias < minDistance) {
                minDistance = promedioDistancias;
                representante = i;
            }
        }
        Solucion centroide = listaSoluciones.get(representante);
        return centroide;
    }

    /**
     * Metodo que reduce el conjunto pareto externo si es 
     * que rebasa el tamanho maximo
     * @param tamanhoMaximo tamanho maximo del conjunto Pareto externo
     * @param conjuntoPareto Conjunto Pareto a reducir
     * @return Conjunto Pareto Clusterizado
     */
    public static ArrayList<Solucion> reducirConjuntoPareto(
            Integer tamanhoMaximo, ArrayList<Solucion> conjuntoPareto){
        int contador = conjuntoPareto.size();
        int cluster1 = 0;
        int cluster2 = 0;
        Double distanciaMinima;
        ArrayList<Cluster> listaClusters = crearCluster(conjuntoPareto);
        while (contador > tamanhoMaximo){
            distanciaMinima = Double.POSITIVE_INFINITY;
            for(int i = 0;i<listaClusters.size();i++){
                Cluster clusterX = listaClusters.get(i);
                for(int j = 0;j<listaClusters.size();j++){
                    if(i!=j){
                        Cluster clusterY = listaClusters.get(j);
                        Double distancia = calcularDistancia(clusterX, clusterY);
                        if(distancia<distanciaMinima){
                            distanciaMinima = distancia;
                            cluster1 = i;
                            cluster2 = j;
                        }
                    }
                }
            }
            
            Cluster nuevoCluster = new Cluster();
            ArrayList<Solucion> nuevaLista = new ArrayList<>();
            for (Solucion solucion : listaClusters.get(cluster1).getSolucionClusterizada()){
                nuevaLista.add(solucion);
            }
            for (Solucion solucion : listaClusters.get(cluster2).getSolucionClusterizada()){
                nuevaLista.add(solucion);
            }
            nuevoCluster.setSolucionClusterizada(nuevaLista);
            listaClusters.add(nuevoCluster);
            listaClusters.remove(cluster1);
            listaClusters.remove(cluster2);
            contador = listaClusters.size();
        }
        ArrayList<Solucion> nuevoConjuntoPareto = new ArrayList<>();
        for(Cluster cluster : listaClusters){
            Solucion individuoPareto = getCentroide(cluster);
            nuevoConjuntoPareto.add(individuoPareto);
        }
        return nuevoConjuntoPareto;
    }
}