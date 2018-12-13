package textWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tokens.CharToken;
import tokens.ConcernToken;
import tokens.LayoutToken;
import tokens.NumberToken;
import tokens.WordToken;


// TODO assign lambda. correctly to SEIRS despite the point after it

public class Main {
	private static List<Concern> concerns = new ArrayList<>();
	private static String nl = System.lineSeparator();
	
	private static String getHTMLHeader(String title) {
		return 
			"<!DOCTYPE html>" 	+ nl +
			"<html>" 			+ nl +
			"<head>" 			+ nl +
			"<title>" + title + " </title>" + nl +
			"</head>" 			+ nl +
			"<body>" 			+ nl +
			"<pre><code>";
	}
	
	private static String getHTMLEnder() {
		return 	"</code></pre>" + nl +
				"</body>" 		+ nl +
				"</html>" 		+ nl;
	}

	public static void main(String[] args) throws IOException {
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(100);
		System.out.println("Text Writer");
		String codeDir = "D:\\Dropbox\\EnCours\\Recherche\\essais highlighting\\";
		String concernsDir = codeDir + "concerns\\";
		String names[] = {"SEIRS", "spatial", "multispecies", "matlab",
				"seirs_spatial", "seirs_species", "species_spatial" };
		String colors[] = {"YELLOW", "CYAN", "MAGENTA", "LIGHTGRAY",
				"BLUE", "PALEGREEN", "ORANGE"};
		for (int i=0; i< names.length; ++i) {
			Path ipath = Paths.get(concernsDir + names[i] + ".txt");
			addConcern(names[i], colors[i], ipath);
		}
		
		Path codePath = Paths.get(codeDir +  "script3.m");
		// Path codePath = Paths.get(codeDir +  "essai.txt");
		Path wcPath = Paths.get(codeDir + "withConcerns.txt");
		Path colorPath = Paths.get(codeDir + "colorized.html");
		List<IToken> tokens = tokenize(codePath,false);
		List<IToken> tokensWithConcerns = detectConcerns(tokens, concerns);
		write(tokensWithConcerns, wcPath, false);
		colorize(tokens, colorPath);

	}
	
	public static List<IToken> detectConcerns(List<IToken>  tokens, List<Concern> concerns) {
		List<IToken> tokensWithConcerns = new ArrayList<>();
		ConcernToken ct = ConcernToken.getGenericCT();
		tokensWithConcerns.add(ct);
		for (IToken token : tokens) {
			ConcernToken newct = assignConcernToToken(token, concerns);
			if (! newct.getText().equals(ct.getText())) {
				tokensWithConcerns.add(newct);
				ct = newct;
			}
			tokensWithConcerns.add(token);
		}
		return tokensWithConcerns;
	}
	
	public static void colorize (List<IToken> tokens, Path outPath) throws IOException  {
		try (BufferedWriter writer = Files.newBufferedWriter(outPath, StandardCharsets.UTF_8)) {
			writer.write(getHTMLHeader(""+outPath.getFileName()));
			ConcernToken ct = ConcernToken.getGenericCT();
			ct.openColorMark(writer);
			for(IToken token : tokens) {
				ConcernToken newct = assignConcernToToken(token, concerns);
				if (! newct.getText().equals(ct.getText())) {
					ct.closeColorMark(writer);
					ct = newct;
					ct.openColorMark(writer);
				}	
				token.write(writer);		
			}
			ct.closeColorMark(writer);
			writer.write(getHTMLEnder());
		}
	}
	
	private static ConcernToken assignConcernToToken(IToken token, List<Concern> concerns) {
		for(Concern concern : concerns) 
			if (concern.contains(token))
				return new ConcernToken(concern.getName(), concern.getColor());
		return ConcernToken.getGenericCT();
	}

	private static void write(List<IToken> tokens, Path outPath, boolean insertEOLs) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(outPath, StandardCharsets.UTF_8)) {
			String separator= "";
			for(IToken token : tokens) {
				if (insertEOLs)  {
					writer.write(separator);
					separator = nl;
				}
				token.write(writer);		
			}
		}
	}
	
	private static void addConcern(String name, String color, Path ipath) throws IOException {
		List<IToken> tokens = tokenize(ipath, true);
		concerns.add(new Concern(name, color, tokens));
	}

	public static List<IToken> tokenize(Path ipath, boolean ignoreLayout)  throws IOException{
	   List<IToken> tokens = new ArrayList<>();
	   StreamTokenizer st = new StreamTokenizer(Files.newBufferedReader(ipath));
	   st.ordinaryChar('/');
	   st.eolIsSignificant(true);
	   boolean eof = false;
	   do {
			   int token = st.nextToken();
			   switch (token) {
			   		case StreamTokenizer.TT_EOF:
			   			eof = true;
			   			break;
			   		case StreamTokenizer.TT_EOL:
			   			if (! ignoreLayout)
			   				tokens.add(new LayoutToken('\n'));
			   			break;
                 
			   		case StreamTokenizer.TT_WORD:
		                tokens.add(new WordToken(st.sval));
			   			break;
                 
			   		case StreamTokenizer.TT_NUMBER:
		                tokens.add(new NumberToken(st.nval));
			   			break;
			   		default:
			   			tokens.add(new CharToken((char)token));
		        }
		 } while (!eof);
		 return tokens;
   }
}
