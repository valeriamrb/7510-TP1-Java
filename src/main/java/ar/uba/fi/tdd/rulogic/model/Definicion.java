/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.List;

/**
 *
 * @author val
 */
public class Definicion {
    private String nombre;
    private String argumentos;

    Definicion(String nombre, List<String> argumentos) {
        nombre = nombre;
        argumentos = argumentos;
    }
    
    public Boolean comparar(Consulta consulta) {
      if(nombre == consulta.getNombre() && argumentos == consulta.getStringArgumentos()) {
          return true;
      }
      return false;
    }
    
    public String getNombre() {
      return nombre;
    }

    public String getArgumentos() {
      return argumentos;
    }
}
