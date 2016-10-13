package amino;

public class Lysine extends AminoAcid{
	public Lysine(){
		typeOfSequenceNumber = 2;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "AAA";
		baseSequence[1] = "AAG";
		abbreviation = "Lys";
	}
}