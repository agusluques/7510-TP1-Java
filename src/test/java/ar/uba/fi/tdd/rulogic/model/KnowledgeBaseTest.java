package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.*;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	private KnowledgeBase knowledgeBase = new KnowledgeBase();
	public static boolean dbInit = false;


	@Test
	public void testFactFalse() throws Exception{
		this.knowledgeBase.parseDb("src/main/resources/rules.db");
		Assert.assertFalse(this.knowledgeBase.answer("varon (javier)."));

	}

	@Test
	public void testFactTrue() throws Exception{
		this.knowledgeBase.parseDb("src/main/resources/rules.db");
		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)."));

	}

	@Test
	public void testRuleFalse() throws Exception{
		this.knowledgeBase.parseDb("src/main/resources/rules.db");
		Assert.assertFalse(this.knowledgeBase.answer("hijo(roberto, roberto)."));

	}

	@Test
	public void testRuleTrue() throws Exception{
		this.knowledgeBase.parseDb("src/main/resources/rules.db");
		Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas,cecilia,roberto)."));

	}

	@Test
	public void testWrongFactShouldThrowExceptionControlledAndFalse() throws Exception{
		this.knowledgeBase.parseDb("src/main/resources/rules.db");
	    Assert.assertFalse(this.knowledgeBase.answer("varon(juan"));
	}

	@Test
	public void testWrongRuleShouldThrowExceptionControlledAndFalse() throws Exception{
		this.knowledgeBase.parseDb("src/main/resources/rules.db");
	    Assert.assertFalse(this.knowledgeBase.answer("tio(nicolas,cecilia,robe"));
	}

	@Test(expected = Exception.class)
	public void testWrongDbFactShouldThrowException() throws Exception{
		this.knowledgeBase.parseDb("src/main/resources/wrongFact.db");
	}

	@Test(expected = Exception.class)
	public void testWrongDbRuleShouldThrowException() throws Exception{
		this.knowledgeBase.parseDb("src/main/resources/wrongRule.db");
	}
	
	
	

}
