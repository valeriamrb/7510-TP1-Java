/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

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

    public void parsearRegla(String expresion) {
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
      String[] definiciones = definicionesRegla.split("),");

      for (int i = 0; i < definiciones.length; i+=1) {
        definiciones[i] = definiciones[i].split("\\(),");
      }
      //Creo una lista de definiciones.
      var listaDef = [];
      for (var i = 0; i < definiciones.length; i+=1) {
        var nombreRegla = definiciones[i][0];
        definiciones[i].shift()
        listaDef[i] = new Expresion(nombreRegla,definiciones[i]);
      }
      //Armo el objeto regla.
      return new Regla(aux1[0], parametros, listaDef);*/
    }
/*
    this.validarBase = function(listaDatos) {
      //Valida si la base de datos esta formada correctamente.
      var listaDefValidas = [];
      var listaReglasValidas = [];
      var elementoIncompleto = 0;
      for (var i = 0; i < listaDatos.length; i+=1) {
         if(this.esDefinicion(listaDatos[i])) {
           listaDefValidas.push(listaDatos[i]);
         } else if(this.esRegla(listaDatos[i])) {
           listaReglasValidas.push(listaDatos[i]);
         } else {
           elementoIncompleto = i;
         }
      }
      var cantidadDefValidas = listaDefValidas.length;
      var cantidadReglasValidas = listaReglasValidas.length;
      var cantidadLineasValidas = cantidadDefValidas + cantidadReglasValidas;
      var cantidadLineasTotales = listaDatos.length;
      if(cantidadLineasTotales == cantidadLineasValidas){
        return true;
      }
      console.log("Error en el elemento numero " + i + " de la base de datos: " + listaDatos[i-1]);
      return false;
    }*/
}
