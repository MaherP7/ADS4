%%
%public
%class Lexer
%unicode
%type Token
%line
%column
%yylexthrow Exception


mot = "^[a-zA-Z0-9]*$"
linebreak = "\r" | "\n" | "\r\n"
begindoc = "<!DOCTYPE html><html><body>"
enddoc = "</body></html>"
beginenum = "<ol>"
endenum = "</ol>"
blank = [ \t]

%%

{begindoc}	{return new ValuedToken(Sym.BEGINDOC, yytext());}
{enddoc}	{return new ValuedToken(Sym.ENDDOC, yytext());}
{mot}		{return new ValuedToken(Sym.MOT, yytext());}
{linebreak}	{return new ValuedToken(Sym.LINEBREAK, yytext());}
\bf		{return new Token(Sym.BOLD);}
\it		{return new Token(Sym.ITALIC);}
{beginenum}	{return new ValuedToken(Sym.BEGINENUM, yytext());}
{endenum}	{return new ValuedToken(Sym.ENDENUM, yytext());}
\item		{return new Token(Sym.ITEM);}
"{"		{return new Token(Sym.LBRACKET);}
"}"		{return new Token(Sym.RBRACKET);}
{blank}		{}
[^]		{throw new Exception("Lexer error at line "+ yyline + " column " + yycolumn);}
<<EOF>>		{return new Token(Sym.EOF);}