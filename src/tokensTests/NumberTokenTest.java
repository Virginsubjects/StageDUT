package tokensTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tokens.NumberToken;
import tokens.WordToken;

class NumberTokenTest {

	@Test
	void test() {
		assertEquals("3",new NumberToken(3).toString());
		assertEquals("3",new NumberToken(3.0).toString());
		assertEquals("0.03",new NumberToken(0.03).toString());
		assertEquals("0.000365",new NumberToken(0.000365).toString());
		
		NumberToken n = new NumberToken(3.00);
		NumberToken nb= new NumberToken(4.);
		WordToken w = new WordToken("ok");
		assertFalse(n.equals(w));
		assertFalse(n.equals(nb));
		NumberToken nbr = new NumberToken(4.00000);
		assertTrue(nb.equals(nbr));
	}

}
