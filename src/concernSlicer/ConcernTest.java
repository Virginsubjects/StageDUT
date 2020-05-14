package concernSlicer;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tokens.CharToken;
import tokens.ConcernToken;
import tokens.LayoutToken;
import tokens.NumberToken;
import tokens.WordToken;

class ConcernTest {

	@Test
	void test() {
		List<IToken> liste = new ArrayList<IToken>();
		CharToken car = new CharToken('c');
		liste.add(car);
		assertTrue(liste.contains(car));
		assertFalse(!liste.contains(car));
		assertEquals(liste.size(), 1);
		
		liste.add(new ConcernToken("play", "red"));	
		liste.add(new LayoutToken('b'));
		liste.add(new NumberToken(2.00));
		liste.add(new WordToken("luv java"));	
		assertFalse(liste.isEmpty());
		assertEquals(liste.size(),5);	
		
		Concern c = new Concern("stage","red", liste);				
		assertEquals(c.getName(), "stage");
		assertEquals(c.getColor(), "red");	
		
		for(IToken t : liste)	
			assertTrue(c.contains(t));
		
		Iterator<IToken> i = c.iterator();
		while(i.hasNext() && i.next()!=null)
			assertEquals(c.getColor(),"red");
		
		
		
		

 }
}
