package tokens;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberTokenTest {

	@Test
	void test() {
		assertEquals("3",new NumberToken(3).toString());
		assertEquals("3",new NumberToken(3.0).toString());
		assertEquals("0.03",new NumberToken(0.03).toString());
		assertEquals("0.000365",new NumberToken(0.000365).toString());
	}

}
