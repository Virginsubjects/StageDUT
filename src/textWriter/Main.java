package textWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tokens.CharToken;
import tokens.LayoutToken;
import tokens.NumberToken;
import tokens.WordToken;

public class Main {
	private List<Concern> concerns = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(100);
		System.out.println("Text Writer");
		Path path = Paths.get("D:\\Dropbox\\temporaire\\essais highlighting\\concerns\\SEIRS.txt");
		addConcern(path,"SEIRS");
		path = Paths.get("D:\\Dropbox\\temporaire\\essais highlighting\\concerns\\spatial.txt");
		addConcern(path,"spatial");
		path = Paths.get("D:\\Dropbox\\temporaire\\essais highlighting\\concerns\\multispecies.txt");
		addConcern(path,"multispecies");

		Path codePath = Paths.get("D:\\Dropbox\\temporaire\\essais highlighting\\code.txt");
		Path destPath = Paths.get("D:\\Dropbox\\temporaire\\essais highlighting\\dest.txt");
		List<IToken> tokens = rewrite(codePath, destPath);
		write(tokens, destPath);

	}
	
	private static void write(List<IToken> tokens, Path outPath) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(outPath)) {
			for(IToken token : tokens) {
				token.write(writer);
			}
		}
	}
	
	private static void addConcern(Path concernPath, String name) throws IOException {
		Path rwPath = Paths.get("D:\\Dropbox\\temporaire\\essais highlighting\\concerns\\tokensRewritten.txt");
		List<IToken> tokens = rewrite(concernPath, rwPath);
		Path rebuiltPath = Paths.get("D:\\Dropbox\\temporaire\\essais highlighting\\concerns\\tokensRebuilt.txt");
		try (BufferedWriter writer = Files.newBufferedWriter(rebuiltPath)) {
			for(IToken token : tokens) {
				token.write(writer);
			}
		}
	}

	public static List<IToken> rewrite(Path ipath, Path opath)  throws IOException{
	   List<IToken> tokens = new ArrayList<>();
	   StreamTokenizer st = new StreamTokenizer(Files.newBufferedReader(ipath));
	   st.ordinaryChar('/');
	   st.eolIsSignificant(true);
	   NumberFormat nf = NumberFormat.getInstance(Locale.US);
	   nf.setMaximumFractionDigits(100);
	   nf.setGroupingUsed(false);
	   try (BufferedWriter writer = Files.newBufferedWriter(opath)) {
		   boolean eof = false;
		   do {
			   int token = st.nextToken();
			   switch (token) {
			   		case StreamTokenizer.TT_EOF:
			   			eof = true;
			   			break;
			   		case StreamTokenizer.TT_EOL:
			   			//writer.newLine();
		                tokens.add(new LayoutToken(System.lineSeparator()));
			   			break;
                 
			   		case StreamTokenizer.TT_WORD:
			   			//writer.write(st.sval);
		                tokens.add(new WordToken(st.sval));
			   			break;
                 
			   		case StreamTokenizer.TT_NUMBER:
			   			//writer.write(nf.format(st.nval));
		                tokens.add(new NumberToken(st.nval));
			   			break;
			   		default:
			   			//writer.write((char)token);
			   			System.out.println("Adding token for %"+(char)token+"%("+token+")");
			   			tokens.add(new CharToken((char)token));
			   }
		   } while (!eof);
		   return tokens;
	   }
   }
}
