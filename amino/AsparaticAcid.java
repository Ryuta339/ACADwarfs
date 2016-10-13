package amino;

public class AsparaticAcid extends AminoAcid{
	public AsparaticAcid(){
		typeOfSequenceNumber = 2;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "GAT";
		baseSequence[1] = "GAC";
		abbreviation = "Asp";
	}
}