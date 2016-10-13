package amino.parser;

import amino.*;
import java.util.HashMap;

class tgParser extends AbstractParser{
	tgParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		switch(baseSequence.charAt(2)){
		  case 'T': case 'C':
			aa = new Cysteine();
			break;
		  case 'A':
			aa = new EndCodon();
			break;
		  case 'G':
			aa = new Tryptophan();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(2));
		}
		return aa;
	}
}