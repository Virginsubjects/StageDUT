package concernSlicer;



import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import tokenizer.StreamTokenizer;
import tokens.CharToken;
import tokens.ConcernToken;
import tokens.LayoutToken;
import tokens.NumberToken;
import tokens.WordToken;


// TODO assign lambda. correctly to SEIRS despite the point after it

public class ConcernSlicer {
	private static List<Concern> concerns = new ArrayList<>();
	private static String nl = System.lineSeparator();
	private static boolean trace = false;
	private static final String CONCERN = "concerns\\";
	private static final String COLORIZED = "colorized.html";
	
	static {
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(100);
	}
	
	private static String getHTMLHeader(String title) {
		return 
			"<!DOCTYPE html>" 	+ nl +
			"<html>" 			+ nl +
			"<head>" 			+ nl +
			"<title>" + title + " </title>" + nl +
			"</head>" 			+ nl +
			"<body>" 			+ nl +
			"<pre>";
	}
	
	private static String getHTMLEnder() {
		return 	"</pre>" + nl +
				"</body>" 		+ nl +
				"</html>" 		+ nl;
	}
	
	public static void colorize (File file, ArrayList<String> list) throws IOException {
		
		String filename = file.getAbsolutePath();
		String codeDir = file.getParentFile().getAbsolutePath()+"\\";
		String colorized = codeDir + COLORIZED;
		String concernsDir = codeDir + CONCERN;		
		int i = 0;
		for (String s : list) {			
			Path ipath = Paths.get(concernsDir + s);
			addConcern(s, ipath);
		}
		Path codePath = Paths.get(filename);
		Path colorPath = Paths.get(colorized);		
		slice(codePath, colorPath);
	}
		
	private static void slice(Path codePath, Path colorPath) throws IOException {
		List<IToken> tokens = tokenize(codePath,false);
		colorize(tokens, colorPath);
	}

	private static void addConcern(String name, Path ipath) throws IOException {
		List<IToken> tokens = tokenize(ipath, true);
		if (trace) {
			//System.out.println("Added concern "+name+ " " + color+ " "+ ipath);
			for(IToken token : tokens)
				System.out.println(token);
		}
		concerns.add(new Concern(name, tokens));
	}
	
	public static List<IToken> detectConcerns(List<IToken>  tokens, List<Concern> concerns) {
		List<IToken> tokensWithConcerns = new ArrayList<>();
		ConcernToken ct = ConcernToken.getGenericCT();
		tokensWithConcerns.add(ct);
		for (IToken token : tokens) {
			ConcernToken newct = assignConcernToToken(token, concerns);
			if (!newct.equals(ct)) {
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
			int i=1;
			writeLineNumber(i++, writer);
			for(IToken token : tokens) {
				
				ConcernToken newct = assignConcernToToken(token, concerns);
				if (!newct.equals(ct)) {
					ct.closeColorMark(writer);
					ct = newct;
					ct.openColorMark(writer);
				}
				if(token.getClass().getSimpleName().equals("LayoutToken")){
					token.write(writer);
					writeLineNumber(i++, writer);				
				}else {
				token.write(writer);	
				}
			}
			ct.closeColorMark(writer);
			writer.write(getHTMLEnder());			
		}		
	}
	
	private static ConcernToken assignConcernToToken(IToken token, List<Concern> concerns) {
		for(Concern concern : concerns) 
			if (concern.contains(token) && !token.getString().equals(" ")) 
				return new ConcernToken(concern.getName(), concern.getColor());
		return ConcernToken.getGenericCT();
	}
	
	private static void writeLineNumber(int n, BufferedWriter writer) throws IOException {
		new NumberToken(n).write(writer);
		new CharToken(' ').write(writer);	
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

// layout is typically ignored when tokenize is called
// to add a concern : only non-layout tokens matter
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

	public static int getIntertwining(List<IToken> tokensWithConcerns) {
		return 0;
	}

	public static void setTrace(boolean b) {
		trace = b;
	}
}
