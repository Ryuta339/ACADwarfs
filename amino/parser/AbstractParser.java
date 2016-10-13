package amino.parser;

import amino.*;
import java.util.HashMap;

abstract class AbstractParser{
	String baseSequence;
	
	AbstractParser(String baseSequence){
		this.baseSequence = baseSequence;
	}
	
	abstract public AminoAcid parse() throws CodonParserException;
}