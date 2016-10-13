package amino.parser;

import amino.*;
import java.util.HashMap;

class atParser extends AbstractParser{
	atParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		switch(baseSequence.charAt(2)){
		  case 'T': case 'C': case 'A':
			aa = new Isoleucine();
			break;
		  case 'G':
			aa = new Methionine();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(2));
		}
		return aa;
	}
}