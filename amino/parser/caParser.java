package amino.parser;

import amino.*;
import java.util.HashMap;

class caParser extends AbstractParser{
	caParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		switch(baseSequence.charAt(2)){
		  case 'T': case 'C':
			aa = new Histidine();
			break;
		  case 'A': case 'G':
			aa = new Glutamine();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(2));
		}
		return aa;
	}
}