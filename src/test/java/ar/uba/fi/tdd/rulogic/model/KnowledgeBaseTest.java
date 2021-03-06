package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

        private static List<String> database;
        
	@InjectMocks
	private static KnowledgeBase knowledgeBase;

        @Rule
        public ExpectedException thrown = ExpectedException.none();
        
        @BeforeClass
        public static void db() {
            database = new ArrayList<String>();
            database.add("varon(juan).");
            database.add("varon(pepe).");
            database.add("varon(hector).");
            database.add("varon(roberto).");
            database.add("varon(alejandro).");
            database.add("mujer(maria).");
            database.add("mujer(cecilia).");
            database.add("padre(juan, pepe).");
            database.add("padre(juan, pepa).");
            database.add("padre(hector, maria).");
            database.add("padre(roberto, alejandro).");
            database.add("padre(roberto, cecilia).");
            database.add("hijo(X, Y) :- varon(X), padre(Y, X).");
            database.add("hija(X, Y) :- mujer(X), padre(Y, X).");
            
            knowledgeBase = new KnowledgeBase();
            knowledgeBase.parseDB(database);
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void test_Varon_Javier_Should_be_false() {

		Assert.assertFalse(this.knowledgeBase.answer("varon(javier)."));

	}
       
        @Test
        public void test_Varon_Juan_Should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)."));

	}
        
        @Test
        public void test_Varon_Maria_Should_be_false() {

		Assert.assertFalse(this.knowledgeBase.answer("varon(maria)."));

        }
        
        @Test
        public void test_Mujer_Cecilia_Should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("mujer(cecilia)."));

	}
        
        @Test
        public void test_Padre_Juan_Pepe_Should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepe)."));

	}
  
        @Test
        public void test_Padre_Mario_Pepe_Should_be_false() {

		Assert.assertFalse(this.knowledgeBase.answer("padre(mario, pepe)."));

	}
        
        @Test
        public void test_Hijo_Pepe_Juan_Should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe, juan)."));

	}       
        
        @Test
        public void test_Hija_Maria_Roberto_Should_be_false() {

		Assert.assertFalse(this.knowledgeBase.answer("hija(maria, roberto)."));

	}
        
        @Test
        public void test_consulta_incorrect() {
                thrown.expect(IncorrectQueryException.class);
		this.knowledgeBase.answer("varon");
	}
}
