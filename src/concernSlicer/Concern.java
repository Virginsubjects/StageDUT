package concernSlicer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Concern implements Iterable<IToken>{
	private String name;
	private String color;
	private List<IToken> tokens = new ArrayList<>();

	public Concern(String name, String color, List<IToken> tokens) {
		this.name = name;
		this.color = color;
		this.tokens = tokens;
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
