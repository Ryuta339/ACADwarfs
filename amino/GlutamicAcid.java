package amino;

public class GlutamicAcid extends AminoAcid{
	public GlutamicAcid(){
		typeOfSequenceNumber = 2;
		baseSequence = new String[typeOfSequenceNumber];
		baseSequence[0] = "GAA";
		baseSequence[1] = "GAG";
		abbreviation = "Glu";
	}
}