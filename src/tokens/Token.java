//Token.java

package tokens;

import java.io.BufferedWriter;
import java.io.IOException;

import concernSlicer.IToken;

public abstract class Token implements IToken {
	private String word;
	
	protected String getWord() {
		return word;
	}

	public Token(String word) {
		this.word = word;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+"["+word+"]";
	}

	@Override
	public void write(BufferedWriter writer) throws IOException {
		writer.write(word);
	}
	@Override
	public boolean equals(Object o) {
	    if (o.getClass() != this.getClass())
	      return false;
	    Token t = (Token)o;
	    return word.equals(t.word);
	}
}
