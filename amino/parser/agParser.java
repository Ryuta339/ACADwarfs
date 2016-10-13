package amino.parser;

import amino.*;
import java.util.HashMap;

class agParser extends AbstractParser{
	agParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		switch(baseSequence.charAt(2)){
		  case 'T': case 'C':
			aa = new Serine();
			break;
		  case 'A': case 'G':
			aa = new Arginine();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(2));
		}
		return aa;
	}
}