package amino;

public class Arginine extends AminoAcid{
	public Arginine(){
		//typeOfSequenceNumber = 6;
		typeOfSequenceNumber = 4;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "CGT";
		baseSequence[1] = "CGC";
		baseSequence[2] = "CGA";
		baseSequence[3] = "CGG";
		//baseSequence[4] = "AGA";				/* rare codon */
		//baseSequence[5] = "AGG";				/* rare codon */
		abbreviation = "Arg";
	}
}