package tokensTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tokens.ConcernToken;

class ConcernTokenTest {

	@Test
	void test() {
		assertEquals(new ConcernToken("js","vanilla").getWord(),"js");
		assertEquals(new ConcernToken("POO","vanilla").getWord(),"POO");
		assertEquals(new ConcernToken("python","vanilla").getWord(),"python");
		assertEquals(new ConcernToken("assemblage","vanilla").getWord(),"assemblage");
		assertEquals(new ConcernToken("SQL","vanilla").getWord(),"SQL");
		
		assertTrue(new ConcernToken("Pascal", "red").getString().equals("@P"));
		assertFalse(new ConcernToken("Blaise", "red").getString().equals("@q"));
		assertEquals(ConcernToken.getGenericCT().getWord(),"generic");		
		
	}

}
