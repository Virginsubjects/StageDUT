package concernSlicer;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import tokens.ConcernToken;
import tokens.NumberToken;
import tokens.WordToken;

class ConcernSlicerTest {
	@Test
	void test() {
		
		List<IToken> tokensWithConcerns = new ArrayList<IToken>(); //pour concern "coder"
		List<IToken> tokensWithConcernsFact = new ArrayList<IToken>();// pour concern "factoriser"
		List<IToken> tokensWithConcernsGit = new ArrayList<IToken>();// pour Concern "git"
		assertEquals(0, ConcernSlicer.getIntertwining(tokensWithConcerns));
		assertEquals(0, ConcernSlicer.getIntertwining(tokensWithConcernsFact));
		assertEquals(0, ConcernSlicer.getIntertwining(tokensWithConcernsGit));
		
		List<IToken> tokens =  new ArrayList<>();
		List<Concern> concerns = new ArrayList<>();
		
		tokens.add(new NumberToken(2.));
		tokens.add(new NumberToken(5.));
		tokens.add(new NumberToken(7.));
		tokens.add(new WordToken("java"));
		tokens.add(new WordToken("4ever"));
		assertEquals(tokens.size(), 5);
		
		tokensWithConcerns.add(new ConcernToken("java", "jaune"));
		tokensWithConcerns.add(new ConcernToken("html", "jaune"));
		tokensWithConcerns.add(new ConcernToken("css", "jaune"));		
		assertEquals(tokensWithConcerns.size(), 3);
		
		tokensWithConcernsFact.add(new ConcernToken("DRY", "rouge"));
		tokensWithConcernsFact.add(new ConcernToken("SRP", "rouge"));
		tokensWithConcernsFact.add(new ConcernToken("ISP", "rouge"));
		tokensWithConcernsFact.add(new ConcernToken("SOLID", "rouge"));
		assertEquals(tokensWithConcernsFact.size(), 4);
		
		tokensWithConcernsGit.add(new ConcernToken("init", "vert"));
		tokensWithConcernsGit.add(new ConcernToken("status", "vert"));
		tokensWithConcernsGit.add(new ConcernToken("checkout", "vert"));
		tokensWithConcernsGit.add(new ConcernToken("commit", "vert"));
		tokensWithConcernsGit.add(new ConcernToken("push", "vert"));
		assertEquals(tokensWithConcernsGit.size(), 5);
		
		
		
		concerns.add(new Concern("coder", "jaune", tokensWithConcerns));
		concerns.add(new Concern("factoriser", "rouge", tokensWithConcernsFact));
		concerns.add(new Concern("git", "vert", tokensWithConcernsGit));
		assertEquals(concerns.size(), 3);	
		
		for( Concern c : concerns) {
			if(c.getName().equals("coder")) 
					assertEquals(c.getColor(),"jaune");
			else if(c.getName().contentEquals("factoriser"))
					assertEquals(c.getColor(),"rouge");
			else  assertEquals(c.getColor(),"vert");
		}	
		
		List<IToken> res = ConcernSlicer.detectConcerns(tokens, concerns);
		assertEquals(res.size(), 6);// 5 + 1 (voir methode detectConcerns)
		
	}
}
