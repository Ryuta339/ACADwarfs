package amino;

public class Phenylalanine extends AminoAcid{
	public Phenylalanine(){
		typeOfSequenceNumber = 2;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "TTT";
		baseSequence[1] = "TTC";
		abbreviation = "Phe";
	}
}