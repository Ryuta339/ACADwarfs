package amino;

public class Glycine extends AminoAcid{
	public Glycine(){
		//typeOfSequenceNumber = 4;
		typeOfSequenceNumber = 3;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "GGC";
		baseSequence[1] = "GGT";
		baseSequence[2] = "GGG";
		//baseSequence[3] = "GGA";		/* rare codon */
		abbreviation = "Gly";
	}
}