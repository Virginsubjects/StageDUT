package tokens;

import java.io.BufferedWriter;
import java.io.IOException;

import textWriter.IToken;

public abstract class Token implements IToken {
	private String word;
	
	protected void setWord(String word) {
		this.word = word;
	}

	public Token(String word) {
		this.word = word;
	}
	
	@Override
	public String getText() {
		return word;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+"["+word+"]";
	}

	@Override
	public void write(BufferedWriter writer) throws IOException {
		writer.write(word);
	}
}
