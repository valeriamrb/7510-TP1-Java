package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnowledgeBase {
            Parser unParser;
            boolean baseValida;
            Map<String, List<Definicion>> diccionarioDefiniciones;
            Map<String, Regla> diccionarioReglas;
            
        KnowledgeBase() {
            this.unParser = new Parser();
            this.baseValida = false;
            this.diccionarioDefiniciones = new HashMap<String, List<Definicion>>();
            this.diccionarioReglas = new HashMap<String, Regla>();
        }
        
        public boolean parseDB(List<String> listParams) {
            //Valido si la base de datos es valida.  
            if(this.unParser.validarBase(listParams) == false){
                return false;
            }
            this.baseValida = true;

            //Parseo la base de datos recibida como parametro.
            //Creo un diccionario de definiciones y otro de reglas.
            for (int i = 0; i < listParams.size(); i+=1) {
                if(this.unParser.esDefinicion(listParams.get(i))) {
                    Definicion definicion = this.unParser.parsearDefinicion(listParams.get(i));
                    
                    if( this.diccionarioDefiniciones.containsKey(definicion.getNombre()) == false ) {
                      //Si no existe la definicion en el diccionario, la agrego dentro
                      //de una lista asociada a el nombre de clave.
                      List<Definicion> listaDefiniciones = new ArrayList<Definicion>();
                      listaDefiniciones.add(definicion);
                      this.diccionarioDefiniciones.put(definicion.getNombre(), listaDefiniciones);
                    } else {
                      //Si existe la definicion en el diccionario, la agrego a la lista
                      //correspondiente.
                      List<Definicion> listaDefAux;
                      listaDefAux = this.diccionarioDefiniciones.get(definicion.getNombre());
                      listaDefAux.add(definicion);
                      this.diccionarioDefiniciones.put(definicion.getNombre(), listaDefAux);
                    }
                } else if(this.unParser.esRegla(listParams.get(i))) {
                    Regla unaRegla = this.unParser.parsearRegla(listParams.get(i));
                    this.diccionarioReglas.put(unaRegla.getNombre(), unaRegla);
                } else {
                //Base mal formada
                    return false;
                }
           }
            return true;
        }

         public boolean answer(String query) {
        //Si la base de datos no era valida devuelvo null
            if(this.baseValida == false) {
              throw new Error("Base de datos invalida");
            }

            //Verifico si la consulta es valida. Si no lo es retorno null
            if(!this.unParser.esConsultaValida(query)){
              throw new Error("Consulta mal formada");
            }
            //Parseo la consulta.
            Consulta consulta = this.unParser.parsearConsulta(query);
            String claveConsulta = consulta.getNombre();

            //Busco si la consulta existe en el diccionario de definiciones.
            if(this.diccionarioDefiniciones.containsKey(claveConsulta)){
              //Recorro la lista de definiciones asociadas a esa clave para
              //comparar si la consulta es verdadera.
              for (int i = 0; i < this.diccionarioDefiniciones.get(claveConsulta).size(); i+=1) {
                if(this.diccionarioDefiniciones.get(claveConsulta).get(i).comparar(consulta)) {
                  //Si encontre la definicion, la co  console.log("Verifico si la consulta es valida");nsulta es verdadera.
                  return true;
                }
              }
              //Si no encontre la definicion, la consulta es falsa.
              return false;
            } else {
              //Si no existe la definicion, busco en el diccionario de reglas.
              if(this.diccionarioReglas.containsKey(claveConsulta)){
                //Si existe la regla, la evaluo para ver si la consulta es verdadera
                if(this.diccionarioReglas.get(claveConsulta).comparar(consulta, this.diccionarioDefiniciones)) {
                  //Si la regla evaluada da true, la consulta es verdadera
                  return true;
                }
                //Si la regla evaluada da false, la consulta es falsa
                return false;
              }
              //Si no existe la regla, la consulta es falsa.
              return false;
            }
        }

}
