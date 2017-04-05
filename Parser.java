import java.util.*;

public class Parser {

    /*
     * DOCUMENT -> CORPS
     * CORPS -> \begindoc SUITE_ELEMENTS \enddoc
     * SUITE_ELEMENTS -> ELEMENT SUITE_ELEMENTS | epsilon
     * ELEMENT -> MOT | \linebreak | \bf{ SUITE_ELEMENTS } | \it{ SUITE_ELEMENTS } | ENUMERATION
     
     * ENUMERATION -> \beginenum SUITE_ITEMS \endenum
     * SUITE_ITEMS -> ITEM SUITE_ITEMS | epsilon
     * ITEM -> \item SUITE_ELEMENTS
     */

    protected LookAhead1 reader;

    public Parser(LookAhead1 r){
		this.reader = r;
    }

    public void nonterm_DOCUMENT() throws Exception {
		nonterm_CORPS();
    }

    public void nonterm_CORPS() throws Exception {
		reader.eat(Sym.BEGINDOC);
		nonterm_SUITE_ELEMENTS();
		reader.eat(Sym.ENDDOC);	
    }

    public void nonterm_SUITE_ELEMENTS() throws Exception {
		nonterm_ELEMENT();
		nonterm_SUITE_ELEMENTS();
    }

    public void nonterm_ELEMENT() throws Exception {

	if(reader.check(Sym.MOT)){
	    reader.eat(Sym.MOT);
	} else if(reader.check(Sym.LINEBREAK)){
	    reader.eat(Sym.LINEBREAK);
	} else if(reader.check(Sym.BOLD)){
	    reader.eat(Sym.BOLD);
	    reader.eat(Sym.LBRACKET);
	    nonterm_SUITE_ELEMENTS();
	    reader.eat(Sym.RBRACKET);
	}  else if(this.reader.check(Sym.ITALIC)){
	    reader.eat(Sym.ITALIC);
	    reader.eat(Sym.LBRACKET);
	    nonterm_SUITE_ELEMENTS();
	    reader.eat(Sym.RBRACKET);
	} else
	    nonterm_ENUMERATION();
    }

    public void nonterm_ENUMERATION() throws Exception {
		reader.eat(Sym.BEGINENUM);
		nonterm_SUITE_ITEMS();
		reader.eat(Sym.ENDENUM);
    }

    public void nonterm_SUITE_ITEMS() throws Exception {
		nonterm_ITEM();
		nonterm_SUITE_ITEMS();
    }

    public void nonterm_ITEM() throws Exception {
		reader.eat(Sym.ITEM);
		nonterm_SUITE_ELEMENTS();
    }
    

}
