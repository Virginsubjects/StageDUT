package tokens;

import java.io.BufferedWriter;
import java.io.IOException;

import concernSlicer.IToken;

public class LayoutToken implements IToken {
	private char c;
	public LayoutToken(char c) {
		this.c = c;
	}

	@Override
	public void write(BufferedWriter writer) throws IOException {
		String s;
		if (c == '\n')
			s = System.lineSeparator();
		else s = ""+c;
		writer.write(s);
	}
	@Override
	public boolean equals(Object o) {
	    if (o.getClass() != this.getClass()) {
	      return false;
	    }
	    LayoutToken t = (LayoutToken)o;
	    return c == t.c;
	}
}
