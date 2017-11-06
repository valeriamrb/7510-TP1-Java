/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.Arrays;
import java.util.Map;

/**
 *
 * @author val
 */
public class Expresion {
    
    private String nombre;
    private String[] argumentos;
    
    Expresion(String nombreRegla, String[] definiciones) {
        this.nombre = nombreRegla;
        this.argumentos = definiciones;
    }
    
    private boolean comparar(Consulta consulta) {
      if(this.nombre.equals(consulta.getNombre()) && this.argumentos == consulta.getArgumentos()) {
          return true;
      }
      return false;
    }

    public Definicion evaluar(Map<String, String> diccionarioArgumentos){
      //Asocia argumentos concretos a los argumentos genericos de la expresion.
      for (int i = 0; i < this.argumentos.length; i+=1) {
          String valor = diccionarioArgumentos.get(this.argumentos[i]);
          this.argumentos[i] = valor;
      }
      //Convierto los argumentos en un string.
      String string1 = Arrays.toString(this.argumentos);
      String string2 = string1.replace("[", "");
      String string3 = string2.replace("]", "");
      return new Definicion(this.nombre, string3);
    }

    public String getNombre() {
      return this.nombre;
    }

    public String[] getArgumentos() {
      return this.argumentos;
    }

}
