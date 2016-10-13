package amino;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;


public class AminoSequence extends ArrayList<AminoAcid>{
	// Constructors
	public AminoSequence(){
		super();
	}
	public AminoSequence(Collection<AminoAcid> c){
		super(c);
	}
	public AminoSequence(int initialCapacity){
		super(initialCapacity);
	}
	
	/* Count pattern method */
	public static void countPattern(String str, String pattern){
		char[] strArray = str.toCharArray();
		char[] patternArray = pattern.toCharArray();
		int num=0;
		Boolean b;
		for(int i=0; i<strArray.length - patternArray.length; i++){
			b = true;
			for(int j=0; j<patternArray.length; j++){
				if(strArray[i+j] != patternArray[j]) b = false;
			}
			
			if(b) num++;
		}
		System.out.println(Integer.toString(num));
	}
	
	public static void countPattern(String str, String pattern, PrintWriter pw){
		char[] strArray = str.toCharArray();
		char[] patternArray = pattern.toCharArray();
		int num=0;
		Boolean b;
		for(int i=0; i<strArray.length - patternArray.length; i++){
			b = true;
			for(int j=0; j<patternArray.length; j++){
				if(strArray[i+j] != patternArray[j]) b = false;
			}
			
			if(b) num++;
		}
		pw.print(Integer.toString(num));
	}
	
	/* Check sequence is bases */
	public Boolean isBaseSequence(String basePattern) throws NotBaseSequenceException{
		Pattern pattern = Pattern.compile("^[TCAG]*$");
		Matcher matcher = pattern.matcher(basePattern);
		Boolean ret = matcher.matches();
		if(! ret){
			throw new NotBaseSequenceException(basePattern);
		}
		return ret;
	}
	
	/* Getting a number of base sequences from this amino sequence */
	public int getBaseSequencesNumber(){
		int ret = 1;
		for(AminoAcid aa: this){
			ret *= aa.typeOfSequenceNumber;
		}
		return ret;
	}
	
	/* Getting base sequences list from this amino sequences */
	public LinkedList<String> getBaseSequencesFromAminoSequence(){
		LinkedList<String> sequencesList = new LinkedList<String>();
		int baseSequenceLength = getBaseSequencesNumber();
		
		sequencesList.add("");
		for(AminoAcid aa: this){
			int n = sequencesList.size();
			
			for(int i=0; i<n; i++){
				String seq = sequencesList.poll();
				for(String bases: aa.baseSequence){
					sequencesList.add(seq + bases);
				}
			}
		}
		return sequencesList;
	}
	
	/* Getting string vector of base sequences list from this amino sequences */
	public String[] getVectorBaseSequencesFromAminoSequence(){
		return (String[])getBaseSequencesFromAminoSequence().toArray(new String[0]);
	}
	
	/* Getting base sequence with the largest number of matching pattern 
	 * @param  basePattern
	 * @return matched string
	 */
	public String getMatchingPatternSequences(String basePattern) throws NotBaseSequenceException{
		String[] sequences = getVectorBaseSequencesFromAminoSequence();
		int max=-1, maxIndex=-1, number;
		Boolean b;
		char[] charPattern = basePattern.toCharArray();
		
		// Find the largest number of matching pattern
		for(int i=0; i<sequences.length; i++){
			number = 0;
			char[] charSequences = sequences[i].toCharArray();
			// Find matching pattern
			for(int j=0; j < charSequences.length-charPattern.length; j++){
				b = true;
				// Check matching pattern
				for(int k=0; k<charPattern.length; k++){
					if(charSequences[j+k] != charPattern[k]){
						b = false;
						break;
					}
				}
				if(b) number++;
			}
			
			if(number > max){
				max = number;
				maxIndex = i;
			}
		}
		return sequences[maxIndex];
	}
	
	/* Getting base sequence with the largest number of matching pattern 
	 * This method use tree search
	 * @param  basePattern
	 * @return matched string
	 */
	public String getSequenceByTreeSearch(String basePattern) throws NotBaseSequenceException {
		// Check sequence is bases
		isBaseSequence(basePattern);
		
		int matched = -1, number, index;
		Boolean b;
		String returnSequence = "", maxMatched;
		char[] charPattern = basePattern.toCharArray();
		
		int s = size();
		
		for(AminoAcid aa: this){
			index = indexOf(aa);
			if(index < s-1){
				AminoAcid aaNext = get(index+1);
				
				maxMatched = returnSequence + ((aa.getBaseSequences())[0]);
				for(String seq: aa.getBaseSequences()){
					for(String seq2: aaNext.getBaseSequences()){
						number = 0;
						char[] charSequence = (returnSequence + seq + seq2).toCharArray();
						for(int i=0; i<charSequence.length-charPattern.length; i++){
							b = true;
							for(int j=0; j<charPattern.length; j++){
								if(charSequence[i+j] != charPattern[j]) b = false;
							}
							if(b) number++;
						}
						
						if(number > matched){
							maxMatched = returnSequence + seq;
							matched = number;
						}
					}
				}
				returnSequence = maxMatched;
			} else {
				maxMatched = returnSequence + ((aa.getBaseSequences())[0]);
				for(String seq: aa.getBaseSequences()){
					number = 0;
					char[] charSequence = (returnSequence + seq).toCharArray();
					for(int i=0; i<charSequence.length-charPattern.length; i++){
						b = true;
						for(int j=0; j<charPattern.length; j++){
							if(charSequence[i+j] != charPattern[j]) b = false;
						}
						if(b) number++;
					}
					
					if(number > matched){
						maxMatched = returnSequence + seq;
						matched = number;
					}
				}
				returnSequence = maxMatched;
			}
		}
		
		return returnSequence;
	}
	
	/* Getting base sequence with the smallest number of matching pattern 
	 * This method use tree search
	 * @param  basePattern
	 * @return matched string
	 */
	public String getSequenceCut(String basePattern) throws NotBaseSequenceException{
		// Check sequence is bases
		isBaseSequence(basePattern);
		
		int matched = Integer.MAX_VALUE, number, index;
		Boolean b;
		String returnSequence = "", maxMatched;
		char[] charPattern = basePattern.toCharArray();
		
		int s = size();
		
		for(AminoAcid aa: this){
			index = indexOf(aa);
			if(index < s-1){
				AminoAcid aaNext = get(index+1);
				
				maxMatched = returnSequence + ((aa.getBaseSequences())[0]);
				for(String seq: aa.getBaseSequences()){
					for(String seq2: aaNext.getBaseSequences()){
						number = 0;
						char[] charSequence = (returnSequence + seq + seq2).toCharArray();
						for(int i=0; i<charSequence.length-charPattern.length; i++){
							b = true;
							for(int j=0; j<charPattern.length; j++){
								if(charSequence[i+j] != charPattern[j]) b = false;
							}
							if(b) number++;
						}
						
						if(number < matched){
							maxMatched = returnSequence + seq;
							matched = number;
						}
					}
				}
				returnSequence = maxMatched;
			} else {
				maxMatched = returnSequence + ((aa.getBaseSequences())[0]);
				for(String seq: aa.getBaseSequences()){
					number = 0;
					char[] charSequence = (returnSequence + seq).toCharArray();
					for(int i=0; i<charSequence.length-charPattern.length; i++){
						b = true;
						for(int j=0; j<charPattern.length; j++){
							if(charSequence[i+j] != charPattern[j]) b = false;
						}
						if(b) number++;
					}
					
					if(number < matched){
						maxMatched = returnSequence + seq;
						matched = number;
					}
				}
				returnSequence = maxMatched;
			}
		}
		
		return returnSequence;
	}
}