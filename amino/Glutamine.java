package amino;

public class Glutamine extends AminoAcid{
	public Glutamine(){
		typeOfSequenceNumber = 2;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "CAA";
		baseSequence[1] = "CAG";
		abbreviation = "Gln";
	}
}