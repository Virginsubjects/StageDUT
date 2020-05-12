package tokens;

import java.io.BufferedWriter;
import java.io.IOException;

public class ConcernToken extends Token {
	private String color;
	public ConcernToken(String name, String color) {
		super(name);
		this.color = color;
	}
	public static ConcernToken getGenericCT() {
		return new ConcernToken("generic", "white");
	}
	@Override
	public String getString() {
		return "@"+getWord().charAt(0);
	}
	@Override
	public void write(BufferedWriter writer) throws IOException {
		writer.write("@"+getWord().charAt(0));
	}
	public void openColorMark(BufferedWriter writer) throws IOException {
		writer.write("<span style=\"background-color: " + color +"\""+ ">");
	}
	public void closeColorMark(BufferedWriter writer) throws IOException {
		writer.write("</span>");
	}
}