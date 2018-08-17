package textWriter;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IToken {
	String getText();
	void write(BufferedWriter writer) throws IOException;
}
