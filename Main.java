import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
    	if (args.length < 1) {
    	    System.out.println("java Main <namefile>");
    	    System.exit(1);
        }

    	File input = new File(args[0]);
    	Reader reader = new BufferedReader(new FileReader(args[0]));
    	Lexer lexer = new Lexer(reader);
        LookAhead1 look = new LookAhead1(lexer);
        Parser parser = new Parser(look);
	
        try {
	       parser.nonterm_DOCUMENT();
        }catch (Exception e){
	       System.out.println("The file is not correct");
	       System.out.println(e);
        }
    }	
}
