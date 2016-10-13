package amino.parser;

import amino.*;
import java.util.HashMap;

class gParser extends AbstractParser{
	gParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		AbstractParser ap;
		switch(baseSequence.charAt(1)){
		  case 'T':
			aa = new Valine();
			break;
		  case 'C':
			aa = new Alanine();
			break;
		  case 'A':
			ap = new gaParser(baseSequence);
			aa = ap.parse();
			break;
		  case 'G':
			aa = new Glycine();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(1));
		}
		return aa;
	}
}