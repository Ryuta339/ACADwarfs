package amino;

public class Serine extends AminoAcid{
	public Serine(){
		typeOfSequenceNumber = 6;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "TCT";
		baseSequence[1] = "TCC";
		baseSequence[2] = "TCA";
		baseSequence[3] = "TCG";
		baseSequence[4] = "AGT";
		baseSequence[5] = "AGC";
		abbreviation = "Ser";
	}
}