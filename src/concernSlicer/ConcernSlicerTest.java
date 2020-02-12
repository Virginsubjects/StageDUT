package concernSlicer;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ConcernSlicerTest {
	@Test
	void test() {
		List<IToken> tokensWithConcerns = null; // assign !
		ConcernSlicer.getIntertwining(tokensWithConcerns);
		fail();
	}
}
