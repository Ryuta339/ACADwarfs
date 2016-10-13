package amino;

public class Alanine extends AminoAcid{
	public Alanine(){
		typeOfSequenceNumber = 4;;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "GCT";
		baseSequence[1] = "GCC";
		baseSequence[2] = "GCA";
		baseSequence[3] = "GCG";
		abbreviation = "Ala";
	}
}