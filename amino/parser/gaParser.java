package amino.parser;

import amino.*;
import java.util.HashMap;

class gaParser extends AbstractParser{
	gaParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		switch(baseSequence.charAt(2)){
		  case 'T': case 'C':
			aa = new AsparaticAcid();
			break;
		  case 'A': case 'G':
			aa = new GlutamicAcid();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(2));
		}
		return aa;
	}
}