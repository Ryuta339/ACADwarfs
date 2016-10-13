package amino.parser;

import amino.*;
import java.util.HashMap;

class tParser extends AbstractParser{
	tParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AminoAcid aa;
		AbstractParser ap;
		switch(baseSequence.charAt(1)){
		  case 'T':
			ap = new ttParser(baseSequence);
			aa = ap.parse();
			break;
		  case 'C':
			aa = new Serine();
			break;
		  case 'A':
			ap = new taParser(baseSequence);
			aa = ap.parse();
			break;
		  case 'G':
			ap = new tgParser(baseSequence);
			aa = ap.parse();
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(1));
		}
		return aa;
	}
}