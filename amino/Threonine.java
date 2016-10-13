package amino;

public class Threonine extends AminoAcid{
	public Threonine(){
		typeOfSequenceNumber = 4;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "ACT";
		baseSequence[1] = "ACC";
		baseSequence[2] = "ACA";
		baseSequence[3] = "ACG";
		abbreviation = "Thr";
	}
}