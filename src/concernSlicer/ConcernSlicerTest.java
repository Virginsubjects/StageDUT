package concernSlicer;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tokens.ConcernToken;
import tokens.NumberToken;
import tokens.WordToken;

class ConcernSlicerTest {
	@Test
	void test() {
		List<IToken> tokensWithConcerns = new ArrayList<IToken>(); // assign !
		assertEquals(0, ConcernSlicer.getIntertwining(tokensWithConcerns));
		List<IToken> tokens =  new ArrayList<>();
		List<Concern> concerns = new ArrayList<>();
		tokens.add(new NumberToken(2.));
		tokens.add(new WordToken("java"));
		assertEquals(tokens.size(), 2);
		tokensWithConcerns.add(new ConcernToken("java", "jaune"));
		assertEquals(tokensWithConcerns.size(), 1);
		concerns.add(new Concern("coder", "jaune", tokensWithConcerns));
		assertEquals(concerns.size(), 1);	
		for( Concern c : concerns) {
			assertEquals(c.getColor(), "jaune");
			assertEquals(c.getName(), "coder");
			java.util.Iterator<IToken> i = c.iterator();
			while(i.hasNext())
			  assertEquals(i.next().getString(),"@j");		
		}
		
		List<IToken> res = ConcernSlicer.detectConcerns(tokens, concerns);
		assertEquals(res.size(), 3);
	}
}