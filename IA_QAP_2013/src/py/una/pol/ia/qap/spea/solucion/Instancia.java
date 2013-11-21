/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.ia.qap.spea.solucion;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que instancia los datos de las matrices a utilizar para la resolucion
 * del problema
 *
 * @author sauce <carlifer.fernando@gmail.com>
 */
public class Instancia {

    /**
     * Atributo privado que representa a la matriz de flujos 1
     */
    private Double[][] flujo1;
    /**
     * Atributo privado que representa a la matriz de flujos 2
     */
    private Double[][] flujo2;
    /**
     * Atributo privado que representa a la matriz de distancias
     */
    private Double[][] distancias;
    /**
     *
     */
    private Integer tamanho;

    /**
     * Setter de tamanho
     *
     * @param tamanho
     */
    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Getter de flujo1
     *
     * @return Double[][]
     */
    public Double[][] getFlujo1() {
        return flujo1;
    }

    /**
     * Getter de flujo2
     *
     * @return Double[][]
     */
    public Double[][] getFlujo2() {
        return flujo2;
    }

    /**
     * Getter de distancias
     *
     * @return Double[][]
     */
    public Double[][] getDistancias() {
        return distancias;
    }

    /**
     * Getter de tamanho
     *
     * @return Integer
     */
    public Integer getTamanho() {
        return tamanho;
    }

    /**
     * Metodo que lee los datos de los flujos 1 y 2 y de las distancias
     * proveìdos desde un archivo
     *
     * @param direccion ruta del archivo de donde provienen los datos
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public void leerDatos(String direccion) {

        try {
            /*
             Aqui se obtiene el archivo que se lee mediante la direccion
             pasada como parametro
             */
            FileInputStream fIStream = new FileInputStream(direccion);
            DataInputStream dIStream = new DataInputStream(fIStream);
            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(dIStream));
            String strLinea;

            /*
             Aqui obtenemos la dimension de las matrices de flujos y distancias
             */
            this.tamanho = Integer.valueOf(bReader.readLine());

            /*
             Dimensionamos las respectivas matrices
             */
            this.flujo1 = new Double[this.getTamanho()][this.getTamanho()];
            this.flujo2 = new Double[this.getTamanho()][this.getTamanho()];
            this.distancias = new Double[this.getTamanho()][this.getTamanho()];
            
            /*
            Inicializamos los contadores para las filas, columnas y 
            matriz (este ultimo para saber que matriz inicializar)
            */
            Integer fila = 0;
            Integer columna = 0;
            Integer contadorMatriz = 1;
            Integer valor = 0;
            
            /*
            Leemos hasta el final del archivo
            */
            while ((strLinea = bReader.readLine()) != null){
                /*
                Si se llega a la ultima columna o fila se inicializan los
                contadores a cero
                */
                if(fila == this.tamanho-1){
                    fila = 0;
                    columna = 0;
                }
                if(columna == this.tamanho){
                    fila++;
                    columna = 0;
                }
                /*
                Separamos cada lineas mediante los espacios en blanco 
                entre los numeros
                */
                String partes[] = strLinea.split("\\s+");
                
                /*
                Utilizamos un valor de ajuste debido a que algunas lineas
                tienen un elemento mas que otras debido a la forma en que como
                se separan los elementos
                */
                if(partes.length == this.tamanho + 1){
                    valor = 1;
                } else if (partes.length == this.tamanho){
                    valor = 0;
                }
                
                /*
                Si se encuentra una linea en blanco se aumenta el contador
                de matrices; asi indicamos que se empezara a inicializar
                otra matriz
                */
                if(partes.length == 1){
                    contadorMatriz++;
                }
                
                /*
                Aqui agregamos los datos obtenidos del archivo a las respectivas
                matrices
                */
                if(partes.length>1){
                    for(int i = valor;i < partes.length;i++){
                        if(contadorMatriz == 1){
                            /*
                            Objetivo 1
                            */
                            this.flujo1[fila][columna] = 
                                    Double.valueOf(partes[i]);
                        } else if(contadorMatriz == 2){
                            /*
                            Objetivo 2
                            */
                            this.flujo2[fila][columna] = 
                                    Double.valueOf(partes[i]);
                        } else{
                            /*
                            Distancias
                            */
                            this.distancias[fila][columna] = 
                                    Double.valueOf(partes[i]);
                        }
                        columna++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(
                    Instancia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
