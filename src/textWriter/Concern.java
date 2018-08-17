package textWriter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Concern implements Iterable<IToken>{
	private String name;
	private List<IToken> tokens = new ArrayList<>();
	

	public Concern(String name, List<IToken> tokens) {
		this.name = name;
		this.tokens = tokens;
	}
	
	public String getName() { 
		return name;
	}
	
	public boolean includes(IToken token) {
		return tokens.contains(token);
	}

	@Override
	public Iterator<IToken> iterator() {
		return tokens.iterator();
	}

}
