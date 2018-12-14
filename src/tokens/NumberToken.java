package tokens;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import concernSlicer.IToken;

public class NumberToken implements IToken {
	private double number;
	public NumberToken(double number) {
		this.number = number;
	}
	@Override
	public void write(BufferedWriter writer) throws IOException {
		writer.write(toString());
	}
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(100);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	@Override
	public boolean equals(Object o) {
	    if (o == this) {
	      return true;
	    }
	    if (o.getClass() != this.getClass())
	      return false;
	    NumberToken t = (NumberToken)o;
	    return number == t.number;
	 }
}
