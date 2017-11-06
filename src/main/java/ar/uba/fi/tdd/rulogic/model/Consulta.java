/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import static java.lang.String.join;
import java.util.List;

/**
 *
 * @author val
 */
public class Consulta {
    private String nombre;
    private String[] argumentos;

    Consulta(String nombre, String[] argumentos) {
        this.nombre = nombre;
        this.argumentos = argumentos;
    }
    
    public String getNombre() {
      return this.nombre;
    }

    public String getStringArgumentos() {
      //Devuelve los argumentos en un string separados por coma.
      return join(", ", this.argumentos);
    }

    public String[] getArgumentos() {
      return this.argumentos;
    }
}
