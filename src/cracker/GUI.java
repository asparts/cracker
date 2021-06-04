package cracker;


import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class GUI {

	public JFrame frame;
	public JPanel panel, panel2, panel3;
	JTabbedPane tabbedPane = new JTabbedPane();
	JButton calculateButton = new JButton("Calculate");
	JButton bruteforceButton = new JButton("Bruteforce");
	JButton scanButton = new JButton("Scan");
	
	JTextField hashField = new JTextField(); // HashCracker
	JTextField wordField = new JTextField(); // HashCracker
	JTextField hostField = new JTextField(); // PortScanner
	JTextField rangeField = new JTextField(); //PS
	JTextField rangeField2 = new JTextField(); //PS
	
	JTextField hashField2 = new JTextField(); //Bruteforce
	JTextField wordField2 = new JTextField(); //bruteforce
	JTextField lenghtField = new JTextField("4"); //bruteforce
	
	JLabel hashLabel = new JLabel("Hash:"); // HashCracker
	JLabel wordLabel = new JLabel("Word:"); // HashCracker
	
	JLabel hashLabel2 = new JLabel("Hash:"); //Bruteforce
	JLabel wordLabel2 = new JLabel("Word:"); //BruteForce
	JLabel lenghtLabel = new JLabel("Min. lenght: "); //Bruteforce
	
	JLabel hostLabel = new JLabel("Host:");
	JLabel rangeLabel = new JLabel("Range:");
	
	JTextArea scannerOutput = new JTextArea();
	
	JFileChooser fileChooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt files", "txt");
	
	
	private int frameWidth = 800;
	private int frameHeight = 600;
	private int panelWidth = 600;
	private int panelHeight = 400;
	
	DatabaseConnection databaseConnection = new DatabaseConnection();
	Cracker crackerz = new Cracker();
	PortScanner portScanner = new PortScanner();
	BruteForce bruteforce = new BruteForce();
	
	public GUI() throws SQLException {
		
		//databaseConnection.establishConnection();
		
		
		fileChooser.setFileFilter(filter);
		
		setFrame();
		setButtons();
		final JMenuBar menuBar = new JMenuBar();
		populateMenuBar(menuBar);
		this.frame.setJMenuBar(menuBar);
		//panel.setVisible(true);
		frame.setVisible(true);
	}
	
	private void setFrame() {
	
		frame = new JFrame("Cracker");
		JMenuBar jMenuBar = new JMenuBar();
		populateMenuBar(jMenuBar);
		frame.setJMenuBar(jMenuBar);
		frame.setSize(frameWidth, frameHeight);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: Close db connection also while doing this
		
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				
				try {
					databaseConnection.closeConnection();
					System.exit(0);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		frame.setBounds(400, 100, frameWidth, frameHeight);
		frame.setResizable(false);
		
		setPanels();
	}
	
	
	private void setPanels() {
		
		tabbedPane.setBounds(18, 25, frameWidth-50, frameHeight-100);
		tabbedPane.setBackground(Color.BLACK);
		tabbedPane.setBorder(new CompoundBorder());
		
		panel = new JPanel();
		panel.setLayout(null); // For positioning, pretty important
		panel.setBounds(0, 0, panelWidth, panelHeight);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(new CompoundBorder());
		
		calculateButton.setBounds(350,400,100,30);
		panel.add(calculateButton);
		
		hashField.setBounds(60, 100, 300, 20);
		panel.add(hashField);
		
		hashLabel.setBounds(20, 95, 50, 30);
		hashLabel.setForeground(Color.black);
		panel.add(hashLabel);
		
		wordField.setBounds(500, 100, 150, 20);
		panel.add(wordField);
		
		wordLabel.setBounds(460, 95, 50, 30);
		wordLabel.setForeground(Color.black);
		panel.add(wordLabel);
		
		panel2 = new JPanel();
		panel2.setLayout(null); // For positioning, pretty important
		panel2.setBounds(0, 0, panelWidth, panelHeight);
		panel2.setBackground(Color.DARK_GRAY);
		panel2.setBorder(new CompoundBorder());
		
		
		hostLabel.setBounds(20, 95, 50, 30);
		hostLabel.setForeground(Color.black);
		hostField.setBounds(60, 100, 300, 20);
		scanButton.setBounds(430, 98, 100, 24);
		scannerOutput.setBounds(200, 200, 400, 200);
		
		rangeLabel.setBounds(20, 120, 50, 30);
		rangeLabel.setForeground(Color.black);
		
		
		rangeField.setBounds(80, 125, 50, 20);
		rangeField2.setBounds(150, 125, 60, 20);
		
		panel2.add(hostField);
		panel2.add(scannerOutput);
		panel2.add(hostLabel);
		panel2.add(scanButton);
		panel2.add(rangeLabel);
		panel2.add(rangeField);
		panel2.add(rangeField2);
		
		
		panel3 = new JPanel();
		panel3.setLayout(null); // For positioning, pretty important
		panel3.setBounds(0, 0, panelWidth, panelHeight);
		panel3.setBackground(Color.DARK_GRAY);
		panel3.setBorder(new CompoundBorder());
		
		hashLabel2.setBounds(20, 95, 50, 30);
		hashLabel2.setForeground(Color.black);
		
		hashField2.setBounds(60, 100, 300, 20);
		
		wordField2.setBounds(500, 100, 150, 20);
		
		wordLabel2.setBounds(460, 95, 50, 30);
		wordLabel2.setForeground(Color.black);
		
		lenghtLabel.setBounds(20, 140, 120, 20);
		lenghtLabel.setForeground(Color.black);
		lenghtField.setBounds(100, 140, 120, 20);
		setDocumentFilter(); // setting document filter for lenghtfield
		
		bruteforceButton.setBounds(350,400,100,30);
		
		
		panel3.add(lenghtField);
		panel3.add(lenghtLabel);
		panel3.add(bruteforceButton);
		panel3.add(hashLabel2);
		panel3.add(hashField2);
		panel3.add(wordField2);
		panel3.add(wordLabel2);
		
		
		tabbedPane.addTab("HashCracker", panel);
		tabbedPane.addTab("PortScanner", panel2);
		tabbedPane.addTab("BruteForce", panel3);
		
		frame.add(tabbedPane);
		
	}

	private void setButtons() {
		
		calculateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					wordField.setText(crackerz.queryDBwHash(hashField.getText().toString()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		scanButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					scannerOutput.setText(portScanner.Scan(hostField.getText().toString(), Integer.parseInt(rangeField.getText().toString()), Integer.parseInt(rangeField2.getText().toString())));
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
			
		});
		bruteforceButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					bruteforce.start(Integer.parseInt(lenghtField.getText().toString()), hashField2.getText().toString());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				wordField2.setText(bruteforce.password);
			}
			
			
		});
		
	}
	private void populateMenuBar(JMenuBar menuBar) {
		
		menuBar.add(createFileMenu());
		menuBar.add(createPreferencesMenu());
	}
	private JMenu createFileMenu()
	{
		final JMenu fileMenu = new JMenu("File");
		
		final JMenuItem addFile = new JMenuItem("Add words to database");
		
		final JMenuItem help = new JMenuItem("Help");
		
		addFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int response = fileChooser.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					try {
						crackerz.savePasswordToDBFromFile(file.toString());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println(file.toString());
					
				}
			}
			
		});
		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File("README.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
		fileMenu.add(addFile);
		fileMenu.add(help);
		
		final JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					databaseConnection.closeConnection();
					System.exit(1);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.exit(1);
				}
			}
		});
		fileMenu.add(exitMenuItem);
		return fileMenu;
	}
	private JMenu createPreferencesMenu() {
		
		final JMenu preferencesMenu = new JMenu("Preferences");
		final JMenu bruteForceMenu = new JMenu("Bruteforce");
		final JMenuItem changeCharsetMenuItem = new JMenuItem("Change charset");
		
		
		ButtonGroup radioGroup = new ButtonGroup();
		JRadioButtonMenuItem customCharSet = new JRadioButtonMenuItem("Use custom charset");
		JRadioButtonMenuItem lowerCharSet = new JRadioButtonMenuItem("Use lowercase only");
		JRadioButtonMenuItem upperCharSet = new JRadioButtonMenuItem("Use uppercse only");
		JRadioButtonMenuItem specialCharSet = new JRadioButtonMenuItem("Use special chars only");
		JRadioButtonMenuItem allPossibleCharSet = new JRadioButtonMenuItem("Use all possible chars");
		
		preferencesMenu.add(bruteForceMenu);
		bruteForceMenu.add(changeCharsetMenuItem);
		radioGroup.add(customCharSet);
		radioGroup.add(lowerCharSet);
		radioGroup.add(upperCharSet);
		radioGroup.add(specialCharSet);
		radioGroup.add(allPossibleCharSet);
		allPossibleCharSet.setSelected(true);
		bruteForceMenu.add(customCharSet);
		bruteForceMenu.add(lowerCharSet);
		bruteForceMenu.add(upperCharSet);
		bruteForceMenu.add(specialCharSet);
		bruteForceMenu.add(allPossibleCharSet);
		
		changeCharsetMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String characters = JOptionPane.showInputDialog("Enter characters separated by space");
				bruteforce.setChars(characters);
			}
			
		});
		customCharSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(customCharSet.isSelected()) {
					bruteforce.useCharSet(bruteforce.customCharSet);
				}
				
			}
			
		});
		lowerCharSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(lowerCharSet.isSelected()) {
					bruteforce.useCharSet(bruteforce.lowercaseChars);
				}
				
			}
			
		});
		upperCharSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(upperCharSet.isSelected()) {
					bruteforce.useCharSet(bruteforce.uppercaseChars);
				}
				
			}
			
		});
		specialCharSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(specialCharSet.isSelected()) {
					bruteforce.useCharSet(bruteforce.specialChars);
				}
				
			}
			
		});
		allPossibleCharSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(allPossibleCharSet.isSelected()) {
					bruteforce.useCharSet(bruteforce.allPossibleCharacters);
				}
				
			}
			
		});
		
		return preferencesMenu;
	}
	private void setDocumentFilter() { // For setting document filters. For example, in bruteforcer, we only want to take integers as lenght..
		
		((AbstractDocument)lenghtField.getDocument()).setDocumentFilter(new DocumentFilter() {
			Pattern regEx = Pattern.compile("\\d*");
			
			@Override
			public void replace(FilterBypass filterBypass, int offset, int length, String text, AttributeSet attributeSet) throws BadLocationException{
				Matcher matcher = regEx.matcher(text);
				if(!matcher.matches()) {
					return;
				}
				super.replace(filterBypass, offset, length, text, attributeSet);
			}
			
		});
		
	}
}
