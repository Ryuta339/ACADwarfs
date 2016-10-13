package amino;

public class NotBaseSequenceException extends AminoAcidException{
	public NotBaseSequenceException(String pattern){
		super(pattern +" is not a base sequence pattern.");
	}
}