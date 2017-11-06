/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author val
 */
public class Parser {
    
    public boolean parsear(String expresion) {
      if(esDefinicion(expresion)) {
          parsearDefinicion(expresion);
      } else if(esRegla(expresion)){
          //parsearRegla(expresion);
      }
      return false;
    }

    public Consulta parsearConsulta(String expresion) {
      String vector1 = expresion.replace(")", "");
      String[] vector2 = vector1.split("\\(");
      //Separo por coma y espacio.     
      String[] vector3 = vector2[1].split(", ");
      return new Consulta(vector2[0], vector3);
    }

    public boolean esConsultaValida(String expresion){
      String vector1 = expresion.replace(")", "");
      String[] vector2 = vector1.split("(");
      if(vector2.length == 2){
        return true;
      }
      return false;
    }

    public boolean esDefinicion(String expresion) {
      String[] elementos = expresion.split("(");
      if(elementos.length == 2){
          return true;
      }
      return false;
    }

    public boolean esRegla(String expresion) {
      String[] elementos = expresion.split(":-");
      if(elementos.length == 2){
          return true;
      }
      return false;
    }

    public Definicion parsearDefinicion(String expresion) {
      //Filtra un string y devuelve un objeto Definicion con los valores correspondientes a la expresion.
      //Obtengo un vector con dos posiciones, la primera es el nombre de la definicion y la segunda los argumentos.
      String vector1 = expresion.replace(").", "");
      String[] vector2 = vector1.split("\\(");
      //Creo una definicion
      return new Definicion(vector2[0], vector2[1]);
    }

    public Regla parsearRegla(String expresion) {
      //Filtra un string de regla y devuelve un objeto Regla con los valores correspondientes a la expresion.
      String vector1 = expresion.replace(").", "");
      //Quito los espacios.
      String vector2 = vector1.replace(" ", "");
      String[] vector3 = vector2.split(":-");
      String nombreRegla = vector3[0].replace(")", "");
      String definicionesRegla = vector3[1];
      
      System.out.println("@@@@@@@@@@");
      System.out.println(definicionesRegla);
      String[] aux1 = nombreRegla.split("\\(");
      String[] parametros = aux1[1].split(",");
      String[] definicionesAux = definicionesRegla.split("\\),");
      System.out.println("Los parametros de la regla son: ");
      System.out.println(Arrays.toString(parametros));
      System.out.println(Arrays.toString(definicionesAux));
      //System.out.println(definicionesAux[0]);
      //String[][] definiciones = null;
      List<String[]> definiciones = new ArrayList<String[]>();
      String[] vectorAux = null;  
      for (int i = 0; i < definicionesAux.length; i+=1) {
        
        definicionesAux[i] = definicionesAux[i].replace("(", "@");
        definicionesAux[i] = definicionesAux[i].replace(",", "@");
        definicionesAux[i] = definicionesAux[i].replace(")", "@");
        System.out.println(definicionesAux[i]);
        //System.out.println(Arrays.toString(definicionesAux[i].split("@")));
        //System.out.println(Arrays.toString(definicionesAux[i].split("\\(\\),"))); 
        System.out.println("fin");
        vectorAux = definicionesAux[i].split("@");        
        System.out.println(Arrays.toString(vectorAux));
        
        definiciones.add(vectorAux);
        //System.out.println(definiciones[i][i]);
        //System.out.println(Arrays.toString(definiciones[i]));
      }
      
      System.out.println("Recorro array:");
      for (int f = 0; f < definiciones.size(); f++) {
            System.out.println(Arrays.toString(definiciones.get(f)));
       }
      
      String[] argumentosDef = null;
      //Creo una lista de expresiones.
      List<Expresion> listaDef = new ArrayList<Expresion>();
      for (int i = 0; i < definiciones.size(); i+=1) {
        String nombreDef = definiciones.get(i)[0];
        System.out.println(nombreDef);
        //Elimino el primer elemento (nombre) para que solo queden los argumentos.
        
        for (int j = 0; j < definiciones.get(i).length-1; j+=1) {
            System.out.println(definiciones.get(i)[j+1]);
            argumentosDef = Arrays.copyOfRange(definiciones.get(i), 1, definiciones.get(i).length);
        }
        System.out.println(Arrays.toString(argumentosDef));
        listaDef.add(new Expresion(nombreDef,argumentosDef));
      }
      //Armo el objeto regla
      return new Regla(aux1[0], parametros, listaDef);
    }

    public boolean validarBase(List<String> listaDatos) {
      //Valida si la base de datos esta formada correctamente.
      var listaDefValidas = [];
      var listaReglasValidas = [];
      int elementoIncompleto = 0;
      int i;
      for (i = 0; i < listaDatos.length; i+=1) {
         if(this.esDefinicion(listaDatos[i])) {
           listaDefValidas.push(listaDatos[i]);
         } else if(this.esRegla(listaDatos[i])) {
           listaReglasValidas.push(listaDatos[i]);
         } else {
           elementoIncompleto = i;
         }
      }
      int cantidadDefValidas = listaDefValidas.length;
      int cantidadReglasValidas = listaReglasValidas.length;
      int cantidadLineasValidas = cantidadDefValidas + cantidadReglasValidas;
      int cantidadLineasTotales = listaDatos.length;
      if(cantidadLineasTotales == cantidadLineasValidas){
        return true;
      }
      System.out.println("Error en el elemento numero " + i + " de la base de datos: " + listaDatos[i-1]);
      return false;
    }
}
