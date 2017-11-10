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

    Definicion(String nombre, String argumentos) {
        this.nombre = nombre;
        this.argumentos = argumentos;
    }
    
    public boolean comparar(Consulta consulta) {
      return (nombre.equals(consulta.getNombre()) && argumentos.equals(consulta.getStringArgumentos()));
    }
    
    public boolean comparar(Definicion definicion) {
        return (nombre.equals(definicion.getNombre()) && argumentos.equals(definicion.getArgumentos()));
    }
    
    public String getNombre() {
      return this.nombre;
    }

    public String getArgumentos() {
      return this.argumentos;
    }
}
