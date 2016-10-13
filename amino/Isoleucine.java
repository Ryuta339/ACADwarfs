package amino;

public class Isoleucine extends AminoAcid{
	public Isoleucine(){
		//typeOfSequenceNumber = 3;
		typeOfSequenceNumber = 2;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "ATT";
		baseSequence[1] = "ATC";
		//baseSequence[2] = "ATA";		/* rare codon */
		abbreviation = "Ile";
	}
}