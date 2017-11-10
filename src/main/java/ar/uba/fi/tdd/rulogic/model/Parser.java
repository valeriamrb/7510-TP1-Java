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

    private int elementoIncorrecto;
    
    public boolean parsear(String expresion) {
      if(esDefinicion(expresion)) {
          parsearDefinicion(expresion);
      } else if(esRegla(expresion)){
          parsearRegla(expresion);
      }
      return false;
    }

    public Consulta parsearConsulta(String expresion) {
      String vector1 = expresion.replace(").", "");
      String[] vector2 = vector1.split("\\(");
      //Separo por coma y espacio.     
      String[] vector3 = vector2[1].split(", ");
      return new Consulta(vector2[0], vector3);
    }

    public boolean esConsultaValida(String expresion){
      String vector1 = expresion.replace(")", "");
      String[] vector2 = vector1.split("\\(");
      if(vector2.length == 2){
        return true;
      }
      return false;
    }

    public boolean esDefinicion(String expresion) {
      String[] elementos = expresion.split("\\(");
      return (elementos.length == 2);
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
      
      String[] aux1 = nombreRegla.split("\\(");
      String[] parametros = aux1[1].split(",");
      String[] definicionesAux = definicionesRegla.split("\\),");

      List<String[]> definiciones = new ArrayList<String[]>();
      String[] vectorAux = null;  
      for (int i = 0; i < definicionesAux.length; i+=1) {
        
        definicionesAux[i] = definicionesAux[i].replace("(", "@");
        definicionesAux[i] = definicionesAux[i].replace(",", "@");
        definicionesAux[i] = definicionesAux[i].replace(")", "@");
        vectorAux = definicionesAux[i].split("@");        
        
        definiciones.add(vectorAux);
      }
      
      String[] argumentosDef = null;
      //Creo una lista de expresiones.
      List<Expresion> listaDef = new ArrayList<Expresion>();
      for (int i = 0; i < definiciones.size(); i+=1) {
        String nombreDef = definiciones.get(i)[0];
        //Elimino el primer elemento (nombre) para que solo queden los argumentos.
        
        for (int j = 0; j < definiciones.get(i).length-1; j+=1) {
            argumentosDef = Arrays.copyOfRange(definiciones.get(i), 1, definiciones.get(i).length);
        }
        listaDef.add(new Expresion(nombreDef,argumentosDef));
      }
      //Armo el objeto regla
      return new Regla(aux1[0], parametros, listaDef);
    }

    public boolean validarBase(List<String> listaDatos) {
      //Valida si la base de datos esta formada correctamente.
      List<String> listaDefValidas = new ArrayList<String>();
      List<String> listaReglasValidas = new ArrayList<String>();
      int elementoIncompleto = 0;
      int i;
      for (i = 0; i < listaDatos.size(); i+=1) {
         if(this.esDefinicion(listaDatos.get(i))) { 
           listaDefValidas.add(listaDatos.get(i));
         } else if(this.esRegla(listaDatos.get(i))) {
           listaReglasValidas.add(listaDatos.get(i));
         } else {
           elementoIncompleto = i;
         }
      }
      int cantidadDefValidas = listaDefValidas.size();
      int cantidadReglasValidas = listaReglasValidas.size();
      int cantidadLineasValidas = cantidadDefValidas + cantidadReglasValidas;
      int cantidadLineasTotales = listaDatos.size();
      if(cantidadLineasTotales == cantidadLineasValidas){
        return true;
      }
      this.elementoIncorrecto = elementoIncompleto;
      return false;
    }
    
    public int getElementoIncorrecto(){
        return this.elementoIncorrecto;
    }
}
