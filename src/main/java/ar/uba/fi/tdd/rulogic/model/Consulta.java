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
public class Consulta {
    private String nombre;
    private List<String> argumentos;
    
    public String getNombre() {
      return nombre;
    }

    public String getStringArgumentos() {
      //Devuelve los argumentos en un string separados por coma.
      return argumentos.join(", ");
    }

    public List<String> getArgumentos() {
      return argumentos;
    }
}
