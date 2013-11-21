/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.una.pol.ia.qap.spea.utilidades;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import py.una.pol.ia.qap.spea.solucion.Parametros;

/**
 * Clase que obtiene los parametros del archivo de configuracion
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class LecturaParametros {
    
    /**
     * Metodo que lee los parametros desde un archivo
     * @param direccionArchivo direccion del archivo que contiene los parametros
     * @return Parametros Objeto que contiene los parametros a utilizar
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public Parametros leer(String direccionArchivo){
        Parametros parametros = new Parametros();
        try {
            FileInputStream fIStream = new FileInputStream(direccionArchivo);
            DataInputStream dIStream = new DataInputStream(fIStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(dIStream));
            String linea;
            while ((linea = bReader.readLine())!=null) {
                String[]partes = linea.split("=");
                if(partes[0].equals("Cant_ciudades")){
                    parametros.setDimension(Integer.valueOf(partes[1]));
                }
                if(partes[0].equals("N_Objetivos")){
                    parametros.setNroObjetivos(Integer.valueOf(partes[1])); 
                } 
                if(partes[0].equals("N_Generaciones")){
                    parametros.setNroGeneraciones(Integer.valueOf(partes[1]));
                }
                if(partes[0].equals("Porcen_Mutacion")){
                    parametros.setPorcMutacion(Float.valueOf(partes[1]));
                }
                if(partes[0].equals("N_Poblacion")){
                    parametros.setCantidadPoblacion(Integer.valueOf(partes[1]));
                }
                if(partes[0].equals("N_ParetoSetExt")){
                    parametros.setCantidadConjuntoPareto(Integer.valueOf(partes[1]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parametros;
    }
}
