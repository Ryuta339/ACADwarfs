package amino;

public class Proline extends AminoAcid{
	public Proline(){
		//typeOfSequenceNumber = 4
		typeOfSequenceNumber = 3;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "CCT";
		baseSequence[1] = "CCA";
		baseSequence[2] = "CCG";
		//baseSequence[3] = "CCC";				/* rare codon */
		abbreviation = "Pro";
	}
}