package amino;

public class Valine extends AminoAcid{
	public Valine(){
		typeOfSequenceNumber = 4;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "GTT";
		baseSequence[1] = "GTC";
		baseSequence[2] = "GTA";
		baseSequence[3] = "GTG";
		abbreviation = "Val";
	}
}