public class LexerException extends Exception 
{
    public LexerException(String s,int l,int c)
    {
		super(s+" at "+l+" "+c);
    }
}