package amino.parser;

import amino.*;
import java.util.HashMap;

class cParser extends AbstractParser{
	cParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		AbstractParser ap;
		switch(baseSequence.charAt(1)){
		  case 'T':
			aa = new Leucine();
			break;
		  case 'C':
			aa = new Proline();
			break;
		  case 'A':
			ap = new caParser(baseSequence);
			aa = ap.parse();
			break;
		  case 'G':
			aa = new Arginine();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(1));
		}
		return aa;
	}
}