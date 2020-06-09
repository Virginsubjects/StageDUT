package tokensTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tokens.ConcernToken;
import tokens.WordToken;

class WordTokenTest {

	@Test
	void test() {
		assertEquals(new WordToken("aa").getString(),"aa");
		assertEquals(new WordToken("b").getString(),"b");
		assertEquals(new WordToken("c").getString(),"c");
		assertEquals(new WordToken("d").getString(),"d");
		assertEquals(new WordToken("e").getString(),"e");
		assertEquals(new WordToken("f").getString(),"f");
		assertEquals(new WordToken("g").getString(),"g");
		assertEquals(new WordToken("h").getString(),"h");
		assertEquals(new WordToken("i").getString(),"i");
		assertEquals(new WordToken("j").getString(),"j");
		assertEquals(new WordToken("k").getString(),"k");
		assertEquals(new WordToken("l").getString(),"l");
		assertEquals(new WordToken("m").getString(),"m");
		assertEquals(new WordToken("n").getString(),"n");
		assertEquals(new WordToken("o").getString(),"o");
		assertEquals(new WordToken("p").getString(),"p");
		assertEquals(new WordToken("q").getString(),"q");
		assertEquals(new WordToken("r").getString(),"r");
		assertEquals(new WordToken("s").getString(),"s");
		assertEquals(new WordToken("t").getWord(),"t");
		
		assertEquals(new WordToken("bb").toString(),"WordToken[bb]");
		assertEquals(new WordToken("c").toString(),"WordToken[c]");
		assertEquals(new WordToken("d").toString(),"WordToken[d]");
		assertEquals(new WordToken("e").toString(),"WordToken[e]");
		assertEquals(new WordToken("f").toString(),"WordToken[f]");
		assertEquals(new WordToken("g").toString(),"WordToken[g]");
		assertEquals(new WordToken("h").toString(),"WordToken[h]");
		assertEquals(new WordToken("i").toString(),"WordToken[i]");
		assertEquals(new WordToken("j").toString(),"WordToken[j]");
		assertEquals(new WordToken("k").toString(),"WordToken[k]");
		assertEquals(new WordToken("l").toString(),"WordToken[l]");
		assertEquals(new WordToken("m").toString(),"WordToken[m]");
		assertEquals(new WordToken("n").toString(),"WordToken[n]");
		assertEquals(new WordToken("o").toString(),"WordToken[o]");
		assertEquals(new WordToken("p").toString(),"WordToken[p]");
		assertEquals(new WordToken("q").toString(),"WordToken[q]");
		assertEquals(new WordToken("r").toString(),"WordToken[r]");
		assertEquals(new WordToken("s").toString(),"WordToken[s]");
		assertEquals(new WordToken("t").toString(),"WordToken[t]");
		assertEquals(new WordToken("u").toString(),"WordToken[u]");
		
		assertTrue(new WordToken("a").equals(new WordToken("a")));
		assertTrue(new WordToken("b").equals(new WordToken("b")));
		assertTrue(new WordToken("c").equals(new WordToken("c")));
		assertTrue(new WordToken("d").equals(new WordToken("d")));
		assertTrue(new WordToken("e").equals(new WordToken("e")));
		assertTrue(new WordToken("f").equals(new WordToken("f")));
		assertTrue(new WordToken("g").equals(new WordToken("g")));
		assertTrue(new WordToken("h").equals(new WordToken("h")));
		assertTrue(new WordToken("i").equals(new WordToken("i")));
		assertTrue(new WordToken("j").equals(new WordToken("j")));
		assertTrue(new WordToken("k").equals(new WordToken("k")));
		assertTrue(new WordToken("l").equals(new WordToken("l")));
		assertTrue(new WordToken("m").equals(new WordToken("m")));
		assertTrue(new WordToken("n").equals(new WordToken("n")));
		assertTrue(new WordToken("o").equals(new WordToken("o")));
		assertTrue(new WordToken("p").equals(new WordToken("p")));
		assertTrue(new WordToken("q").equals(new WordToken("q")));
		assertTrue(new WordToken("r").equals(new WordToken("r")));
		assertTrue(new WordToken("s").equals(new WordToken("s")));
		assertTrue(new WordToken("t").equals(new WordToken("t")));
		assertTrue(new WordToken("u").equals(new WordToken("u")));
		assertTrue(new WordToken("v").equals(new WordToken("v")));
		assertTrue(new WordToken("!").equals(new WordToken("!")));
		assertTrue(new WordToken("*").equals(new WordToken("*")));
		
		assertEquals(new WordToken("a").getWord(),new WordToken("a").getWord());
		assertEquals(new WordToken("b").getWord(),new WordToken("b").getWord());
		assertEquals(new WordToken("c").getWord(),new WordToken("c").getWord());
		assertEquals(new WordToken("d").getWord(),new WordToken("d").getWord());
		assertEquals(new WordToken("e").getWord(),new WordToken("e").getWord());
		assertEquals(new WordToken("f").getWord(),new WordToken("f").getWord());
		assertEquals(new WordToken("g").getWord(),new WordToken("g").getWord());
		assertEquals(new WordToken("h").getWord(),new WordToken("h").getWord());
		assertEquals(new WordToken("i").getWord(),new WordToken("i").getWord());
		assertEquals(new WordToken("j").getWord(),new WordToken("j").getWord());
		assertEquals(new WordToken("k").getWord(),new WordToken("k").getWord());
		assertEquals(new WordToken("l").getWord(),new WordToken("l").getWord());
		
		char c = 'a' ;
		for(int i = 0; i < 1000; ++i) {
			assertTrue(new WordToken(Character.toString(c)).getString().equals(Character.toString(c)));
			assertTrue(new WordToken(Character.toString(c)).toString().equals("WordToken["+Character.toString(c)+"]"));
			assertTrue(new WordToken(Character.toString(c)).getWord().equals(Character.toString(c)));
			assertTrue(new WordToken(Character.toString(c)).equals(new WordToken(Character.toString(c))));
			c++;
		}					
	}

}
