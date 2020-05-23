package tokenizer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class StreamTokenizerTest {

	@Test
	void test() throws IOException {
		StreamTokenizer streamTokenizer = new StreamTokenizer(
		        new StringReader("Mary had little lambs \n 1 "));
		List<String> tokens = new ArrayList<String>();
		assertEquals(streamTokenizer.TT_EOF,-1);
		assertEquals(streamTokenizer.TT_EOL,'\n');
		assertEquals(streamTokenizer.TT_NUMBER,-2);
		assertEquals(streamTokenizer.TT_WORD,-3);
		assertEquals(streamTokenizer.peek(),-2);	

		while(streamTokenizer.nextToken() != StreamTokenizer.TT_EOF){
		    if(streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
		        System.out.println(streamTokenizer.sval);
		        tokens.add(streamTokenizer.sval);
		    } else if(streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
		        System.out.println(streamTokenizer.nval);
		    	assertEquals(streamTokenizer.lineno(),2.);
		    	tokens.add(streamTokenizer.nval+"a");
		    } else if(streamTokenizer.ttype == StreamTokenizer.TT_EOL) {
		        System.out.println();
		    }
		}
		assertEquals(tokens.size(), 5);
		assertTrue(tokens.contains("Mary"));
		assertTrue(tokens.contains("had"));
		assertTrue(tokens.contains("little"));
		assertTrue(tokens.contains("lambs"));
		tokens.remove("Mary");
		assertEquals(tokens.size(), 4);
		assertFalse(tokens.contains("Mary"));
		
		String s = "*";
		//System.out.println(s.getBytes()[0]); // "*" en bytes = 42
		StreamTokenizer st= new StreamTokenizer(new StringReader("i have gotten much better at that *worth a shot!"));
		st.commentChar(42); 
		List<String> t = new ArrayList<String>();

		while(st.nextToken() != StreamTokenizer.TT_EOF){
		    if(st.ttype == StreamTokenizer.TT_WORD) 
		      t.add(streamTokenizer.sval);	    
		    }
		assertEquals(t.size(), 7);
		assertFalse(t.contains("worth"));
		assertFalse(t.contains("a"));
		assertFalse(t.contains("shot"));
		
		
		
	
	}
}