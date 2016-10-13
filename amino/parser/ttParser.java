package amino.parser;

import amino.*;
import java.util.HashMap;

class ttParser extends AbstractParser{
	ttParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		switch(baseSequence.charAt(2)){
		  case 'T': case 'C':
			aa = new Phenylalanine();
			break;
		  case 'A': case 'G':
			aa = new Leucine();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(2));
		}
		return aa;
	}
}