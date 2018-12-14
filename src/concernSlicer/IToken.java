package concernSlicer;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IToken {
	void write(BufferedWriter writer) throws IOException;
}
