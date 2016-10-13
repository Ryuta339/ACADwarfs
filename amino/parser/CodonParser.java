package amino.parser;

import amino.*;
import java.util.HashMap;

public class CodonParser extends AbstractParser{
	public CodonParser(String baseSequence){
		super(baseSequence);
	}
	
	@Override
	public AminoAcid parse() throws CodonParserException{
		AbstractParser ap = null;
		switch(baseSequence.charAt(0)){
		  case 'T':
			ap = new tParser(baseSequence);
			break;
		  case 'C':
			ap = new cParser(baseSequence);
			break;
		  case 'A':
			ap = new aParser(baseSequence);
			break;
		  case 'G':
			ap = new gParser(baseSequence);
			break;
		  default:
			throw new CodonParserException(baseSequence.charAt(0));
		}
		AminoAcid aa = ap.parse();
		return aa;
	}
	
	public String spliceTopNSequence(String sequence, int n){
		return sequence.substring(n);
	}
	public String spliceTop3Sequence(String sequence){
		return spliceTopNSequence(sequence, 3);
	}
}