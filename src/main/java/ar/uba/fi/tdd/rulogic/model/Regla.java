/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author val
 */
public class Regla {
    private String nombre;
    private String[] argumentosGenericos;
    private List<Expresion> listaDefiniciones;
    

    Regla(String nombre, String[] argumentos, List<Expresion> listaDefiniciones) {
        this.nombre = nombre;
        this.argumentosGenericos = argumentos;
        this.listaDefiniciones = listaDefiniciones;
    }

    public boolean comparar(Consulta consulta, Map<String, List<Definicion>> diccionarioDef) {
        if(this.nombre.equals(consulta.getNombre()) == false) {
          return false;
        }
        //Si el nombre es igual evaluo la regla, reemplazando las definiciones
        //de la misma por los argumentos de la consulta.
        List<Definicion> definicionesFormadas = this.formarRegla(consulta);
        //Busco si las definiciones que componen la regla existen y son verdaderas
        for (int i = 0; i < definicionesFormadas.size(); i+=1) {
            if( diccionarioDef.containsKey(definicionesFormadas.get(i).getNombre()) == false) {
              //Si no existe la definicion en el diccionario, la regla no es verdadera
              return false;
            } else {
              //Si existe el nombre de la definicion, busco si se corresponden
              //los parametros.
              List<Definicion> definiciones = diccionarioDef.get(definicionesFormadas.get(i).getNombre());
              boolean existeDefinicion = false;
              for (int j = 0; j < definiciones.size(); j+=1) {
                if(definiciones.get(j).comparar(definicionesFormadas.get(i))) {
                  existeDefinicion = true;
                  break;
                }
              }
              //Si una de las definiciones no era verdadera, la regla no se cumple.
              if(existeDefinicion == false) {
                return false;
              }
            }
        }
        //Si todas las definiciones eran verdaderas, la regla se cumple.
        return true;
    }

    public List<Definicion> formarRegla(Consulta consulta) {
      //Asocio los argumentos genericos a los argumentos concretos de la consulta.
      Map<String, String> mapaArgumentos = new HashMap<String, String>();
      List<Definicion> definiciones = new ArrayList<Definicion>();
      String[] argumentosConsulta = consulta.getArgumentos();

      for (int i = 0; i < this.argumentosGenericos.length; i+=1) {
          mapaArgumentos.put(this.argumentosGenericos[i], argumentosConsulta[i]);
      }

      //Evaluo las definiciones.
      for (int i = 0; i < this.listaDefiniciones.size(); i+=1) {
          definiciones.add(this.listaDefiniciones.get(i).evaluar(mapaArgumentos));
      }
      return definiciones;
    }

    public String getNombre() {
      return this.nombre;
    }

    public String[] getArgumentos() {
      return this.argumentosGenericos;
    }
    
    public List<Expresion> getListaExpresiones(){
        return this.listaDefiniciones;
    }
}
