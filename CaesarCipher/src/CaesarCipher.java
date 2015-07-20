
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextArea;


public class CaesarCipher extends JFrame {

	private JPanel contentPane;
	private JButton btnClear;

	
	
	//private static int index = gettextArea(spinner);
	private static int index = 0;
	//makes left box visible or hidden
	private static boolean hidden = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaesarCipher frame = new CaesarCipher();
					frame.setTitle("Ceasar Cipher Encryption");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CaesarCipher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 23, 173, 160);
		contentPane.add(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(242, 23, 182, 160);
		contentPane.add(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String outputText = encryptText(textArea.getText(), index); // INDEX GOES HERE
				textArea_1.setText(outputText);
				//textArea.getText();
				
			}
		});
		

		 
		btnEncrypt.setBounds(30, 194, 89, 23);
		contentPane.add(btnEncrypt);
	
		
		JLabel lblOutput = new JLabel("Output");
		lblOutput.setBounds(283, 7, 58, 14);
		contentPane.add(lblOutput);
		
		JLabel lblInput = new JLabel("Input Text\r\n");
		lblInput.setBounds(43, 0, 58, 28);
		contentPane.add(lblInput);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				textArea_1.setText(null);
			}
		});
		btnClear.setBounds(304, 228, 103, 23);
		contentPane.add(btnClear);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				index = (int) spinner.getValue();
			}
		});
		spinner.setBounds(193, 36, 39, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel = new JLabel("Incre/decr");
		lblNewLabel.setBounds(184, 3, 58, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnHideunhide = new JButton("Hide/Unhide");
		btnHideunhide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hidden == false){
					textArea.setVisible(false);
					hidden=true;
				}else if(hidden == true){
					textArea.setVisible(true);
					hidden=false;
				}
				
				//textArea.setVisible(false); 			
			}
		});		
		btnHideunhide.setBounds(30, 228, 103, 23);
		contentPane.add(btnHideunhide);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String outputText = encryptText(textArea_1.getText(), (index*-1)); // INDEX GOES HERE
				textArea.setText(outputText);
				//textArea.getText();
			}
		});
		btnDecrypt.setBounds(304, 194, 103, 23);
		contentPane.add(btnDecrypt);
		
		JButton btnSwap = new JButton("Swap");
		btnSwap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textArea.getText();
				String temp=str;
				String Stt = textArea_1.getText();
				String sTemp = Stt;
				textArea.setText(sTemp);
				textArea_1.setText(temp);
				
				
				
			}
		});
		btnSwap.setBounds(172, 194, 89, 23);
		contentPane.add(btnSwap);
	}
	
	
	// method that encrypts string input and returns string output
	public String encryptText(String text, int index){
		int lowerBound = 32;
		int upperBound = 126;
		String output;
		char[] charArray = text.toCharArray();
		
		// CHAR SHIFT METHOD
		
		
		
		//increments array values by 1
		for(int i = 0; i < charArray.length; ++i){
			char tempChar = charArray[i];
			boolean isValid = false;
			do{
				if(tempChar + index > upperBound){
					tempChar += - upperBound + lowerBound + index;
					charArray[i] = tempChar;
				}else if(tempChar + index < lowerBound){
					tempChar += upperBound - lowerBound + index;
					charArray[i] = tempChar;
				}
				else{
					tempChar += index;
					charArray[i] = tempChar;
				}
				if((charArray[i] >= lowerBound) && (charArray[i] <= upperBound)){
					isValid = true;
				}
			} while (!isValid);
		}

		
			/*if((tempChar >= 65 && tempChar <= 90) || (tempChar >= 97 && tempChar <= 122)){
				if(tempChar == 'Z'){
					tempChar = 'A';	
				} else if (tempChar == 'z'){
					tempChar = 'a';
				}else{
					tempChar++;
				}
			}
			charArray[i] = tempChar;
			
			
			

		}
		*/
		
		/*
		//changes Z and z to A and a
		if(tempChar == 'Z'){
			tempChar = 'A';	
			charArray[i] = tempChar;
		} else if (tempChar == 'z'){
			tempChar = 'a';
			charArray[i] = tempChar;
		}else if(tempChar >= 48 && tempChar <= 57){

		}else {
			tempChar++;
			charArray[i] = tempChar;
		}
		*/
		
		//prints out the array
		/*
		for(char c : charArray){
			System.out.print(c + " ");	
		}
		*/
		
		output = new String(charArray);
		return output;
	}
}
