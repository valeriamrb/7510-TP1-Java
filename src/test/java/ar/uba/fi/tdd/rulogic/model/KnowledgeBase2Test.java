package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

/**
 *
 * @author val
 */

public class KnowledgeBase2Test {

        private static List<String> database;
        
	@InjectMocks
	private static KnowledgeBase knowledgeBase;

        @BeforeClass
        public static void db() {
            database = new ArrayList<String>();
            database.add("add(zero, zero, zero).");
            database.add("add(zero, one, one).");
            database.add("add(zero, two, two).");
            database.add("add(one, zero, one).");
            database.add("add(one, one, two).");
            database.add("add(one, two, zero).");
            database.add("add(two, zero, two).");
            database.add("add(two, one, zero).");
            database.add("add(two, two, one).");
            database.add("subtract(X, Y, Z) :- add(Y, Z, X).");
            
            knowledgeBase = new KnowledgeBase();
            knowledgeBase.parseDB(database);
        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void test_add_one_one_two_should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("add(one, one, two)."));

	}

        @Test
        public void test_add_two_one_one_should_be_false() {

		Assert.assertFalse(this.knowledgeBase.answer("add(two, one, one)."));

        }
        
        @Test
        public void test_add_two_zero_two_should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("add(two, zero, two)."));

	}
        
        @Test
        public void test_add_one_zero_one_should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("add(one, zero, one)."));

        }
       
        @Test
        public void test_subtract_two_one_one_should_be_true() {

		Assert.assertTrue(this.knowledgeBase.answer("subtract(two, one, one)."));

	}
}

