package amino;

import java.util.ArrayList;

public class AminoAcid{
	protected String[] baseSequence;
	protected String abbreviation;
	protected int typeOfSequenceNumber;
	
	public String[] getBaseSequences(){
		return baseSequence;
	}
	public String getAbbreviation(){
		return abbreviation;
	}
	public int getTypeOfSequenceNumber(){
		return typeOfSequenceNumber;
	}
}