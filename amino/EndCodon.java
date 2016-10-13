package amino;

public class EndCodon extends AminoAcid{
	public EndCodon(){
		typeOfSequenceNumber = 3;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "TAA";
		baseSequence[1] = "TAG";
		baseSequence[2] = "TGA";
		abbreviation = "End";
	}
}