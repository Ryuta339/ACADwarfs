package amino.parser;

import amino.*;
import java.util.HashMap;

class aParser extends AbstractParser{
	aParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		AbstractParser ap;
		switch(baseSequence.charAt(1)){
		  case 'T':
			ap = new atParser(baseSequence);
			aa = ap.parse();
			break;
		  case 'C':
			aa = new Threonine();
			break;
		  case 'A':
			ap = new aaParser(baseSequence);
			aa = ap.parse();
			break;
		  case 'G':
			ap = new agParser(baseSequence);
			aa = ap.parse();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(1));
		}
		return aa;
	}
}