package amino;

public class Cysteine extends AminoAcid{
	public Cysteine(){
		typeOfSequenceNumber = 2;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "TGT";
		baseSequence[1] = "TGC";
		abbreviation = "Cys";
	}
}