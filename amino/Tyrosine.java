package amino;

public class Tyrosine extends AminoAcid{
	public Tyrosine(){
		typeOfSequenceNumber = 2;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "TAT";
		baseSequence[1] = "TAC";
		abbreviation = "Tyr";
	}
}