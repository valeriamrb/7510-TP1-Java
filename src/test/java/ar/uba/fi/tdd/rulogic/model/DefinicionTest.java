/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 *
 * @author val
 */
public class DefinicionTest {

        @BeforeClass
        public static void bf() {
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}
        
	@Test
	public void test_comparar_consulta_true() {
                Consulta unaConsulta = new Consulta("varon", new String[]{"juan"});
                Definicion unaDefinicion = new Definicion("varon", "juan");
		Assert.assertTrue(unaDefinicion.comparar(unaConsulta));
	}
        
        @Test
	public void test_comparar_consulta_false() {
                Consulta unaConsulta = new Consulta("varon", new String[]{"juan"});
                Definicion unaDefinicion = new Definicion("varon", "pepe");
		Assert.assertFalse(unaDefinicion.comparar(unaConsulta));
	}
        
        @Test
	public void test_comparar_definicion_true() {
                Definicion unaDefinicion = new Definicion("varon", "juan");
                Definicion unaDefinicion2 = new Definicion("varon", "juan");
		Assert.assertTrue(unaDefinicion.comparar(unaDefinicion2));
	}
        
        @Test
	public void test_comparar_definicion_false() {
                Definicion unaDefinicion = new Definicion("varon", "juan");
                Definicion unaDefinicion2 = new Definicion("varon", "juan");
		Assert.assertTrue(unaDefinicion.comparar(unaDefinicion2));
	}
}
