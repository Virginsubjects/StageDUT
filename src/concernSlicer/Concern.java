package concernSlicer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Concern implements Iterable<IToken>{
	private String name;
	private String color;
	private List<IToken> tokens = new ArrayList<>();

	public Concern(String name, List<IToken> tokens) {
		assert(!name.equals(" "));
		this.name = name;
		this.color = tokens.get(0).getString();		
		this.tokens = tokens;
		this.tokens.remove(0);
	}
	
	public String getName() { 
		return name;
	}

	@Override
	public Iterator<IToken> iterator() {
		return tokens.iterator();
	}
	
	boolean contains(IToken token) {
		return tokens.contains(token);
	}

	public String getColor() {
		return color;
	}
}
