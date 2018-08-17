package tokens;

import java.io.BufferedWriter;
import java.io.IOException;

public class CharToken extends Token {
	private char c;
	public CharToken(char c) {
		super(""+c);
	}
	public void write(BufferedWriter writer) throws IOException {
		writer.write(c);
	}
}