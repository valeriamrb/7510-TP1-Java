/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;
import java.util.Arrays;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

public class ParserTest {

        private static Parser unParser;
        private static String linea;
        private static String consulta;
        private static String stringRegla;

        @BeforeClass
        public static void bf() {
            unParser = new Parser();
            linea = "padre(juan, pepe).";
            consulta = "padre(juan, pepe)";
            stringRegla = "hijo(X, Y) :- varon(X), padre(Y, X).";
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void test_parsear_consulta_nombre() {
                Consulta unaConsulta = unParser.parsearConsulta(consulta);
		Assert.assertTrue(unaConsulta.getNombre().equals("padre"));
	}
        
        @Test
        public void test_parsear_consulta_argumentos() {
                Consulta unaConsulta = unParser.parsearConsulta(consulta);
		Assert.assertTrue(unaConsulta.getStringArgumentos().equals("juan, pepe"));
	}
        
        @Test
        public void test_parsear_definicion_nombre() {
                Definicion unaDefinicion = unParser.parsearDefinicion(linea);
		Assert.assertTrue(unaDefinicion.getNombre().equals("padre"));
	}
        
        @Test
        public void test_parsear_definicion_argumentos() {
                Definicion unaDefinicion = unParser.parsearDefinicion(linea);
		Assert.assertTrue(unaDefinicion.getArgumentos().equals("juan, pepe"));
	}
        
        @Test
        public void test_parsear_regla_nombre() {
                Regla unaRegla = unParser.parsearRegla(stringRegla);
		Assert.assertTrue(unaRegla.getNombre().equals("hijo"));
	}
       
        @Test
        public void test_parsear_regla_argumentos_genericos() {
                Regla unaRegla = unParser.parsearRegla(stringRegla);
		Assert.assertTrue(Arrays.toString(unaRegla.getArgumentos()).equals("[X, Y]"));
	}
        
        @Test
        public void test_parsear_regla_lista_expresiones1_nombre() {
                Regla unaRegla = unParser.parsearRegla(stringRegla);
		Assert.assertTrue(unaRegla.getListaExpresiones().get(0).getNombre().equals("varon"));
	}
        
        @Test
        public void test_parsear_regla_lista_expresiones1_argumentos() {
                Regla unaRegla = unParser.parsearRegla(stringRegla);
		Assert.assertTrue(Arrays.toString(unaRegla.getListaExpresiones().get(0).getArgumentos()).equals("[X]"));
	}
        
        @Test
        public void test_parsear_regla_lista_expresiones2_nombre() {
                Regla unaRegla = unParser.parsearRegla(stringRegla);
		Assert.assertTrue(unaRegla.getListaExpresiones().get(1).getNombre().equals("padre"));
	}
        
        @Test
        public void test_parsear_regla_lista_expresiones2_argumentos() {
                Regla unaRegla = unParser.parsearRegla(stringRegla);
		Assert.assertTrue(Arrays.toString(unaRegla.getListaExpresiones().get(1).getArgumentos()).equals("[Y, X]"));
	}
        
        /*
        @Test
        public void test_parsear2() {
                Regla unaRegla = unParser.parsearRegla(stringRegla);
                System.out.println("TEST:::Recorro array expresiones:");
                for (int i = 0; i < unaRegla.getListaExpresiones().size(); i++) {
                      System.out.println("Nombre expresion:" + unaRegla.getListaExpresiones().get(i).getNombre());
                       System.out.println("Argumentos expresion:" + Arrays.toString(unaRegla.getListaExpresiones().get(i).getArgumentos()));
                }
		Assert.assertTrue(false);
	}*/
        
}