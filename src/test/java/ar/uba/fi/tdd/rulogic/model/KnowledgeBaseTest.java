package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

        private static List<String> db;
        
	@InjectMocks
	private KnowledgeBase knowledgeBase;

        @BeforeClass
        public static void db() {
            db = new ArrayList<String>();
            db.add("varon(juan).");
            db.add("varon(pepe).");
            db.add("varon(hector).");
            db.add("varon(roberto).");
            db.add("varon(alejandro).");
            db.add("mujer(maria).");
            db.add("mujer(cecilia).");
            db.add("padre(juan, pepe).");
            db.add("padre(juan, pepa).");
            db.add("padre(hector, maria).");
            db.add("padre(roberto, alejandro).");
            db.add("padre(roberto, cecilia).");
            db.add("hijo(X, Y) :- varon(X), padre(Y, X).");
            db.add("hija(X, Y) :- mujer(X), padre(Y, X).");
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void test() {

		Assert.assertTrue(this.knowledgeBase.answer("varon(javier)."));

	}
        
        public void test_Varon_Juan_Should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)."));

	}
        public void test_Varon_Maria_Should_be_false() {

		Assert.assertFalse(this.knowledgeBase.answer("varon(maria)."));

        }
        
        public void test_Mujer_Cecilia_Should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("mujer(cecilia)."));

	}
        
        public void test_Padre_Juan_Pepe_Should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepe)."));

	}
        
        public void test_Padre_Mario_Pepe_Should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("padre(mario, pepe)."));

	}
                
}
