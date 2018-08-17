package tokens;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberToken extends Token {
	private double number;
	public NumberToken(double number) {
		super("");
		this.number = number;
		
	}
	@Override
	public void write(BufferedWriter writer) throws IOException {
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(100);
		nf.setGroupingUsed(false);
		writer.write(nf.format(number));
	}
}
