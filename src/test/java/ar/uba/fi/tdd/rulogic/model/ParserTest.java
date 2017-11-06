/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

public class ParserTest {

        private static Parser unParser;
        private static String linea;
        private static String stringRegla = "hijo(X, Y) :- varon(X), padre(Y, X).";

        @BeforeClass
        public static void bf() {
            unParser = new Parser();
            linea = "padre(juan, pepe)";
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void test_parsear_consulta_nombre() {
                Consulta unaConsulta = unParser.parsearConsulta(linea);
		Assert.assertTrue(unaConsulta.getNombre().equals("padre"));
	}
        
        public void test_parsear_consulta_argumentos() {
                Consulta unaConsulta = unParser.parsearConsulta(linea);
		Assert.assertTrue(unaConsulta.getStringArgumentos().equals("juan, pepe"));
	}
        
        public void test_parsear_definicion_nombre() {
                Definicion unaDefinicion = unParser.parsearDefinicion(linea);
		Assert.assertTrue(unaDefinicion.getNombre().equals("padre"));
	}
        
        public void test_parsear_definicion_argumentos() {
                Definicion unaDefinicion = unParser.parsearDefinicion(linea);
		Assert.assertTrue(unaDefinicion.getArgumentos().equals("juan, pepe"));
	}
        
        public void test_parsear_regla_nombre() {
                unParser.parsearRegla(stringRegla);
                //Consulta unaConsulta = unParser.parsearConsulta(linea);
		//Assert.assertTrue(unaConsulta.getNombre().equals("padre"));
	}
        
        public void test_parsear_regla_argumentos() {
                //Consulta unaConsulta = unParser.parsearConsulta(linea);
		//Assert.assertTrue(unaConsulta.getStringArgumentos().equals("juan, pepe"));
	}
}