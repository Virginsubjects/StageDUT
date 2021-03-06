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
		CharToken cc = new CharToken('d');
		liste.add(car);
		assertTrue(liste.contains(car));
		assertFalse(liste.contains(cc));
		assertEquals(liste.size(), 1);
		liste.remove(car);		
		assertEquals(liste.size(), 0);
		assertFalse(liste.contains(car));
		liste.add(car);
		assertEquals(liste.size(), 1);
		liste.add(new ConcernToken("play", "red"));	
		liste.add(new LayoutToken('b'));
		liste.add(new NumberToken(2.00));
		liste.add(new WordToken("luv java"));	
		assertFalse(liste.isEmpty());
		assertEquals(liste.size(),5);	
		
		Concern c = new Concern("stage", liste);				
		assertEquals(c.getName(), "stage");
		
		
		for(IToken t : liste)	
			assertTrue(c.contains(t));
		
		Iterator<IToken> i = c.iterator();
		
		
		Concern conc = new Concern("dut", liste);				
		assertEquals(conc.getName(), "dut");
		
		
		
		
		
		
		

 }
}
