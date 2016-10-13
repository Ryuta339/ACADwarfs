package amino.parser;

import amino.*;

public class CodonParserException extends AminoAcidException{
	public CodonParserException(){
		super("Unexpected sequence input.");
	}
	public CodonParserException(char c){
		super((Character.valueOf(c)).toString() + " is not unexpected sequence.");
	}
}