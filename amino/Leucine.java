package amino;

public class Leucine extends AminoAcid{
	public Leucine(){
		//typeOfSequenceNumber = 6;
		typeOfSequenceNumber = 5;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "TTA";
		baseSequence[1] = "TTG";
		baseSequence[2] = "CTT";
		baseSequence[3] = "CTC";
		baseSequence[4] = "CTG";
		//baseSequence[5] = "CTA";			/* rare codon */
		abbreviation = "Leu";
	}
}