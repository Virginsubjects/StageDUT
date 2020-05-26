package tokensTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tokens.WordToken;

class WordTokenTest {

	@Test
	void test() {
		assertEquals(new WordToken("aa").getString(),"aa");
		assertEquals(new WordToken("aaa").getWord(),"aaa");
		assertEquals(new WordToken("bb").toString(),"WordToken[bb]");
		assertEquals(new WordToken("c").toString(),"WordToken[c]");
		assertEquals(new WordToken("d").toString(),"WordToken[d]");
		assertEquals(new WordToken("e").toString(),"WordToken[e]");
		assertEquals(new WordToken("f").toString(),"WordToken[f]");
		assertEquals(new WordToken("g").toString(),"WordToken[g]");
	
		assertTrue(new WordToken("mot").equals(new WordToken("mot")));
		assertFalse(new WordToken("mot").equals(new WordToken("maux")));
		assertEquals(new WordToken("mot").getWord(),new WordToken("mot").getWord());
				
	}

}
