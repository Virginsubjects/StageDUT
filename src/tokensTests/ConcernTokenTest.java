



package tokensTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import tokens.ConcernToken;

class ConcernTokenTest {

	@Test
	void test() throws IOException {
		assertEquals(new ConcernToken("js","vanilla").getWord(),"js");
		assertEquals(new ConcernToken("POO","vanilla").getWord(),"POO");
		assertEquals(new ConcernToken("python","vanilla").getWord(),"python");
		assertEquals(new ConcernToken("assemblage","vanilla").getWord(),"assemblage");
		assertEquals(new ConcernToken("SQL","vanilla").getWord(),"SQL");
		
		assertTrue(new ConcernToken("Pascal", "red").getString().equals("@P"));
		assertTrue(new ConcernToken("a", "red").getString().equals("@a"));
		assertTrue(new ConcernToken("b", "red").getString().equals("@b"));
		assertTrue(new ConcernToken("c", "red").getString().equals("@c"));
		assertTrue(new ConcernToken("d", "red").getString().equals("@d"));
		assertTrue(new ConcernToken("e", "red").getString().equals("@e"));
		assertTrue(new ConcernToken("f", "red").getString().equals("@f"));
		assertTrue(new ConcernToken("g", "red").getString().equals("@g"));
		assertTrue(new ConcernToken("h", "red").getString().equals("@h"));
		assertTrue(new ConcernToken("i", "red").getString().equals("@i"));
		assertTrue(new ConcernToken("j", "red").getString().equals("@j"));
		assertTrue(new ConcernToken("k", "red").getString().equals("@k"));
		
		assertFalse(new ConcernToken("a", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("b", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("c", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("d", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("e", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("f", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("g", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("h", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("i", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("j", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("k", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("l", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("m", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("n", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("o", "red").getString().equals("@q"));
		assertFalse(new ConcernToken("p", "red").getString().equals("@q"));
		
		char c = 'a' ;
		for(int i = 0; i < 1000; ++i) {
			assertTrue(new ConcernToken(Character.toString(c) ,"red").getString().equals("@"+Character.toString(c)));
			c++;
		}	
		
		for(int i = 0; i < 1000; ++i) 		
		assertEquals(ConcernToken.getGenericCT().getWord(),"generic");
	

}
}
