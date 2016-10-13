package amino.parser;

import amino.*;
import java.util.HashMap;

class aaParser extends AbstractParser{
	aaParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		switch(baseSequence.charAt(2)){
		  case 'T': case 'C':
			aa = new Asparagine();
			break;
		  case 'A': case 'G':
			aa = new Lysine();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(2));
		}
		return aa;
	}
}