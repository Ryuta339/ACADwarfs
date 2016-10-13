import java.io.*;
import java.util.ArrayList;
import amino.*;
import amino.parser.*;

public class CuiAcaOperation {
	public CuiAcaOperation(){}
	
	public static void main(String[] args){
		String inputSequence = null , originalSequence;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Which do you want to increase or decrease a number of ACA?\n"
						+  "1, increasing\t2, decreasing");
		int i=0, u=0;
		try{
			i = Integer.parseInt(br.readLine());
			while(i!=1 && i!=2){
				System.out.println("\n" + Integer.toString(i) + " is not acceptable.\n"
								 + "1, increasing\t2, decreasing");
				i = Integer.parseInt(br.readLine());
			}
		}
		catch(IOException e){
			System.err.println(e);
			System.exit(-1);
		}
		
		System.out.println("\nWhich do you use standard input or file?\n"
						+  "1, standard input\t2, file");
		try{
			u = Integer.parseInt(br.readLine());
			while(u!=1 && u!=2){
				System.out.println("\n" + Integer.toString(i) + " is not acceptable.\n"
								 + "1, standard inout\t2, file");
				u = Integer.parseInt(br.readLine());
			}
		}
		catch(IOException e){
			System.err.println(e);
			System.exit(-1);
		}
		
		FileReader fr = null;
		
		if(u == 1){
			System.out.println("\nPlease input a base sequence.");
		} else if (u ==2) {
			System.out.println("\nPlease input the file.");
			try{
				String filename = br.readLine();
				fr = new FileReader(new File(filename));
				br = new BufferedReader(fr);
			}
			catch(IOException e){
				System.err.println(e);
				System.exit(-1);
			}
		} else {
			System.err.println("A way to input have not been initialized.");
			System.exit(-1);
		}
		
		try{
			inputSequence = (br.readLine()).toUpperCase();
			if(fr != null) fr.close();
		}
		catch(IOException e){
			System.err.println(e);
			System.exit(-1);
		}
		/*
		if(inputSequence.length()%3 != 0){
			System.err.println("Sequence is not multiple of 3.");
			System.exit(-1);
		}
		*/
		AminoSequence.countPattern(inputSequence, "ACA");
		
        originalSequence = new String(inputSequence.toCharArray());

		AminoSequence al = new AminoSequence();
		
		while(inputSequence.length() >= 3){
			CodonParser cp = new CodonParser(inputSequence);
			AminoAcid aa;
			try{
				aa = cp.parse();
				al.add(aa);
			}
			catch(CodonParserException e){
				System.err.println(e);
				System.exit(-1);
			}
			inputSequence = cp.spliceTop3Sequence(inputSequence);
		}
		
		try{
			//String str1 = al.getMatchingPatternSequences("ACA");
			String str1 = null;;
			if(i == 1){
				str1 = al.getSequenceByTreeSearch("ACA");
			} else if(i == 2){
				str1 = al.getSequenceCut("ACA");
			} else {
				System.err.println("A way to input have not been initialized.");
				System.exit(-1);
			}

            StringBuffer sb = new StringBuffer();
            sb.append(str1);
            sb.append(inputSequence);
            String str2 = new String(sb);
            
            for(int j=0; j<originalSequence.length(); j++){
                if(sb.charAt(j) == originalSequence.charAt(j)){
                    char lower = (char)(sb.charAt(j) + ('a' - 'A'));
                    sb.deleteCharAt(j);
                    sb.insert(j, lower);
                }
            }
            String str3 = new String(sb);
			System.out.println(str3);
			AminoSequence.countPattern(str2, "ACA");
		}
		catch(NotBaseSequenceException e){
			System.err.println(e);;
			System.exit(-1);
		}
		
	}
}
