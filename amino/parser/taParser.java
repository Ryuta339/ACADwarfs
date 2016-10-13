package amino.parser;

import amino.*;
import java.util.HashMap;

class taParser extends AbstractParser{
	taParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		switch(baseSequence.charAt(2)){
		  case 'T': case 'C':
			aa = new Tyrosine();
			break;
		  case 'A': case 'G':
			aa = new EndCodon();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(2));
		}
		return aa;
	}
}