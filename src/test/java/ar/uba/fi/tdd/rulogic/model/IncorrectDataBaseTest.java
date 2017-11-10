package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;

public class IncorrectDataBaseTest {

        private static List<String> database;
        
	@InjectMocks
	private static KnowledgeBase knowledgeBase;

        @Rule
        public ExpectedException thrown = ExpectedException.none();
        
        @BeforeClass
        public static void db() {

        }
        
	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}
        
        @Test
        public void test_database_incorrect() {
            database = new ArrayList<String>();
            database.add("varon(juan).");
            database.add("varon");
 
            knowledgeBase = new KnowledgeBase();
            thrown.expect(IncorrectDataBaseException.class);
            knowledgeBase.parseDB(database);        
	}
}
