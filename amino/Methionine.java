package amino;

public class Methionine extends AminoAcid{
	public Methionine(){
		typeOfSequenceNumber = 1;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "ATG";
		abbreviation = "Met";
	}
}