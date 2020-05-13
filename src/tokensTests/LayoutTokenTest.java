package tokensTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import tokens.LayoutToken;

class LayoutTokenTest {

	@Test
	void test() {
		assertEquals("c", new LayoutToken('c').getString());
		assertEquals("d", new LayoutToken('d').getString());
		assertEquals("e", new LayoutToken('e').getString());
		assertEquals("f", new LayoutToken('f').getString());
		assertEquals("g", new LayoutToken('g').getString());
		
		assertTrue(new LayoutToken('c').equals(new LayoutToken('c')));
		assertTrue(new LayoutToken('d').equals(new LayoutToken('d')));
		assertTrue(new LayoutToken('e').equals(new LayoutToken('e')));
		assertTrue(new LayoutToken('f').equals(new LayoutToken('f')));
		assertTrue(new LayoutToken('g').equals(new LayoutToken('g')));
		assertTrue(new LayoutToken('h').equals(new LayoutToken('h')));
		
		assertFalse(new LayoutToken('c').equals(new LayoutToken('b')));
		assertFalse(new LayoutToken('d').equals(new LayoutToken('b')));
		assertFalse(new LayoutToken('e').equals(new LayoutToken('b')));
		assertFalse(new LayoutToken('f').equals(new LayoutToken('b')));
		assertFalse(new LayoutToken('g').equals(new LayoutToken('b')));
		assertFalse(new LayoutToken('g').equals(new LayoutToken('b')));
		
	}

}
