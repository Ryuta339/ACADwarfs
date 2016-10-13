import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import java.io.*;
import amino.*;
import amino.parser.*;

public class GuiAcaOperation{
	JFrame frame;
	ButtonGroup  operations;
	JRadioButton increase, decrease;
	JTextArea textarea, outputarea, inputNumber, outputNumber;
	Color background;
	JButton fileSelectButton;
	
	String inputSequence, originalSequence ;
	
	public GuiAcaOperation(){
		frame = new JFrame("ACA operation");
		
		operations = new ButtonGroup();
		
		increase = new JRadioButton("increase ACA", true);
		decrease = new JRadioButton("decrease ACA");
		
		operations.add(increase);
		operations.add(decrease);
		
		textarea = new JTextArea(10, 80);
		outputarea = new JTextArea(10, 80);
		inputNumber = new JTextArea(1, 4);
		outputNumber = new JTextArea(1, 4);
		
		background = new Color(0xEF, 0xEF, 0xEF);
		
		fileSelectButton = new JButton("file select");
		
		inputSequence = null;
	}
	
	public void initWindow(){
		JPanel allPanel = new JPanel();
		allPanel.setLayout(new BoxLayout(allPanel, BoxLayout.Y_AXIS));
		allPanel.setSize(1000, 600);
		allPanel.setBackground(background);
		
		/** For Setting **/
		JPanel buttonPanel = new JPanel();
		GridLayout layout = new GridLayout(1,3);
		buttonPanel.setLayout(layout);
		buttonPanel.setBackground(background);
		
		
		JLabel l1 = new JLabel("choose operation");
		l1.setHorizontalAlignment(JLabel.CENTER);
		l1.setSize(320, 30);
		l1.setBackground(background);
		buttonPanel.add(l1);
		
		increase.setSize(160, 30);
		increase.setBackground(background);
		buttonPanel.add(increase);
		
		decrease.setSize(160, 30);
		decrease.setBackground(background);
		buttonPanel.add(decrease);
		
		allPanel.add(buttonPanel);
		
		
		/** For input **/
		fileSelectButton.addActionListener(new FileSelectedActionListener());
		allPanel.add(fileSelectButton);
		
		
		textarea.setBorder(new LineBorder(background, 10, false));
		textarea.setFont(new Font("Arial", Font.PLAIN, 20));
		
		allPanel.add(textarea);
		
		JButton button = new JButton("start");
		button.addActionListener(new startButtonActionListener());
		allPanel.add(button);
		
		/** For output **/
		outputarea.setBorder(new LineBorder(background, 10, false));
		outputarea.setFont(new Font("Arial", Font.PLAIN, 20));
		allPanel.add(outputarea);
		
		
		/** Number **/
		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new GridLayout(1,4));
		numberPanel.setBackground(background);
		numberPanel.setBorder(new LineBorder(background, 10, false));
		
		JLabel prevl = new JLabel("previous number");
		prevl.setHorizontalAlignment(JLabel.RIGHT);
		numberPanel.add(prevl);
		
		numberPanel.add(inputNumber);
		
		JLabel newl = new JLabel("new number");
		newl.setHorizontalAlignment(JLabel.RIGHT);
		numberPanel.add(newl);
		
		numberPanel.add(outputNumber);
		
		allPanel.add(numberPanel);
		
		frame.add(allPanel);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){   
				frame.setVisible (true);
			}
		});
	}
	
	private static boolean checkBeforeReadfile(File file){
		if (file.exists()){
			if (file.isFile() && file.canRead()){
				return true;
			}
		}
		
		return false;
	}
	
	private class startButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			String getSequence = textarea.getText().toUpperCase();
			inputSequence = "";
			for(int i=0; i<getSequence.length(); i++){
				char c = getSequence.charAt(i);
				if(c != '\n') inputSequence += c;
			}
			
			originalSequence = new String(inputSequence.toCharArray());
			/*
			if(inputSequence.length()%3 != 0){
				ErrorFrame f = new ErrorFrame("Base Sequence is not multiple of 3");
				f.initWindow();
			}
			*/
			
			StringWriter sw = new StringWriter();
			AminoSequence.countPattern(inputSequence, "ACA", new PrintWriter(sw));
			inputNumber.setText(sw.toString());
			
			AminoSequence al = new AminoSequence();
			
			while(inputSequence.length() >= 3){
				CodonParser cp = new CodonParser(inputSequence);
				AminoAcid aa;
				try{
					aa = cp.parse();
					al.add(aa);
				}
				catch(CodonParserException e){
					ErrorFrame f = new ErrorFrame(e);
					f.initWindow();
					
				}
				inputSequence = cp.spliceTop3Sequence(inputSequence);
			}
			
			try{
				//String str1 = al.getMatchingPatternSequences("ACA");
				String str1 = null;
				if(increase.isSelected()){
					str1 = al.getSequenceByTreeSearch("ACA");
				} else if(decrease.isSelected()){
					str1 = al.getSequenceCut("ACA");
				} else {
					ErrorFrame f = new ErrorFrame("Something error occured.\n");
				}
				
				StringBuilder sb = new StringBuilder();
				int n = str1.length();
				
				sb.append(str1);
				sb.append(inputSequence);
                String str3 = new String(sb);
				// Change same base to lower case
                for(int i=0; i<originalSequence.length(); i++){
                    if(sb.charAt(i) == originalSequence.charAt(i)){
                        char lower = (char)(sb.charAt(i) + (char)('a' - 'A'));
                        sb.deleteCharAt(i);
                        sb.insert(i, lower);
                    }
                }

                for(int i=1; 101*i<n; i++){
					sb.insert(101*i-1, '\n');
				}
				String str2 = new String(sb);
				outputarea.setText(str2);
				sw = new StringWriter();
				AminoSequence.countPattern(str3, "ACA", new PrintWriter(sw));
				outputNumber.setText(sw.toString());
			}
			catch(NotBaseSequenceException e){
				ErrorFrame f = new ErrorFrame(e);
				f.initWindow();
			}
		}
	}
	
	private class FileSelectedActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFileChooser filechooser = new JFileChooser();
			JFrame frame = new JFrame("file select");
			
			int selected = filechooser.showOpenDialog(frame);
			if (selected == JFileChooser.APPROVE_OPTION){
				File file = filechooser.getSelectedFile();
				
				textarea.setText("");
				
				try{
					if (checkBeforeReadfile(file)){
						BufferedReader br = new BufferedReader(new FileReader(file));
						
						String str;
						while((str = br.readLine()) != null){
							textarea.append(str);
							textarea.append("\n");
						}
						
						br.close();
					}else{
						ErrorFrame f = new ErrorFrame("Cannot open the file or not exist the file/");
						f.initWindow();
					}
				}catch(FileNotFoundException err){
					ErrorFrame f = new ErrorFrame(err);
					f.initWindow();
				}catch(IOException err){
					ErrorFrame f = new ErrorFrame(err);
					f.initWindow();
				}
			}
		}
	}
	
	
	public static void main(String[] args){
		GuiAcaOperation gao = new GuiAcaOperation();
		gao.initWindow();
	}
}

class ErrorFrame extends JFrame{
	JLabel errorLabel;
	public ErrorFrame(Exception exception){
		super("alert");
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		
		String errorMessage = changeHtml("<html>" + sw.toString() + "\n\nFinish this program.</html>");
		
		errorLabel = new JLabel(errorMessage);
		
	}
	public ErrorFrame(String message){
		super("laert");
		String errorMessage = changeHtml(message);
		errorLabel = new JLabel(errorMessage);
	}
	
	public void initWindow(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 300, 200);
		int ans = JOptionPane.showOptionDialog(this, errorLabel, "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);
		if(ans==0 || ans==1 || ans==2) System.exit(-1);
	}
	
	private static String changeHtml(String str){
		ArrayList<Character> messageList = new ArrayList<Character>();
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			if(c == '\n'){
				messageList.add('<');
				messageList.add('b');
				messageList.add('r');
				messageList.add('>');
			} else {
				messageList.add(str.charAt(i));
			}
		}
		char[] charArray = new char[messageList.size()];
		for(int i=0; i<messageList.size(); i++){
			charArray[i] = messageList.get(i);
		}
			
		return String.valueOf(charArray);
	}
}
