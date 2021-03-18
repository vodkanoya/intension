import javax.swing.*;
import java.util.*;
import java.awt.*;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.JScrollPane;  
import javax.swing.JTextPane;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.Document;  
import javax.swing.text.SimpleAttributeSet;  
import javax.swing.text.StyleConstants;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Game {
	
	Container con;
	JFrame window;
	JPanel titlePanel, gameDescriptionPanel, startButtonPanel, hardButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
	JLabel title, gameDescription, securityLabel, securityLabelNumber;
	JButton startButton, hardButton, c1, c2, c3, c4, creditsButton;
	JTextArea mainTextArea;
	Font titleFont = new Font("Consolas", Font.PLAIN, 50);
	Font startButtonFont = new Font("Consolas", Font.ITALIC, 26);
	Font normalFont = new Font("Consolas", Font.PLAIN, 20);
	Font smallnormalFont = new Font("Consolas", Font.PLAIN, 15);
	int securityHP;
	String position;
	
	ArrayList<Integer> choices = new ArrayList<>();
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	ChoiceHandler choiceHandler = new ChoiceHandler();
	
	public static void main(String[] args) {
		
		new Game();
	}
	
	public Game() {
		
		
		window = new JFrame();
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.darkGray);
		window.setLayout(null);
		window.setVisible(true);
		
		// formatting
		con = window.getContentPane();
		titlePanel = new JPanel();
		gameDescriptionPanel = new JPanel();
		startButtonPanel = new JPanel();
		
		titlePanel.setBounds(100, 100, 600, 150);
		gameDescriptionPanel.setBounds(100, 300, 600, 150);
		startButtonPanel.setBounds(250, 400, 300, 70);
		startButtonPanel.setBorder(new RoundedBorder(15));
		
		titlePanel.setBackground(Color.darkGray);
		gameDescriptionPanel.setBackground(Color.darkGray);
		startButtonPanel.setForeground(Color.white);
		startButtonPanel.setBackground(Color.darkGray);
		
		title = new JLabel("I N T E N S I O N"); // the second mode would be called RETENSION
		title.setForeground(Color.lightGray);
		title.setFont(titleFont);
		
		gameDescription = new JLabel("How well can you secure yourself against data breaches?");
		gameDescription.setForeground(Color.gray);
		gameDescription.setFont(smallnormalFont);
		
		startButton = new JButton("CLICK HERE TO FIND OUT");
		startButton.setBackground(Color.darkGray);
		startButton.setForeground(Color.white);
		startButton.setFont(smallnormalFont);
		startButton.setBorderPainted(false);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);
		
		creditsButton = new JButton("@vodkanoya Github | 2021");
		creditsButton.setBackground(Color.darkGray);
		creditsButton.setForeground(Color.gray);
		creditsButton.setFont(smallnormalFont);
		creditsButton.setBounds(250, 480, 300, 60);
		creditsButton.setBorderPainted(false);
		creditsButton.setFocusPainted(false);
		
		titlePanel.add(title);
		startButtonPanel.add(startButton);
		gameDescriptionPanel.add(gameDescription);
		
		con.add(titlePanel);
		con.add(startButtonPanel);
		con.add(gameDescriptionPanel);
		con.add(creditsButton);
		
	}
	
	public void createGameScreen() {
		
		titlePanel.setVisible(false);
		startButtonPanel.setVisible(false);
		gameDescriptionPanel.setVisible(false);
		creditsButton.setVisible(false);
		
		mainTextPanel = new JPanel();
		mainTextPanel.setBounds(100, 100, 600, 250);
		mainTextPanel.setBackground(Color.darkGray);
		con.add(mainTextPanel);
		
		mainTextArea = new JTextArea("This is the main text area.");
		mainTextArea.setBounds(100, 100, 480, 350);
		mainTextArea.setBackground(Color.darkGray);
		mainTextArea.setForeground(Color.white);
		mainTextArea.setFont(normalFont);
		mainTextArea.setLineWrap(true);
		mainTextPanel.add(mainTextArea);
		
		choiceButtonPanel = new JPanel();
		choiceButtonPanel.setBounds(250, 370, 300, 150);
		choiceButtonPanel.setBackground(Color.darkGray);
		choiceButtonPanel.setLayout(new GridLayout(4,1));
		con.add(choiceButtonPanel);
		
		c1 = new JButton("Text 1");
		c1.setBackground(Color.darkGray);
		c1.setForeground(Color.white);
		c1.setFont(smallnormalFont);
		c1.setFocusPainted(false);
		c1.addActionListener(choiceHandler);
		c1.setActionCommand("c1");
		
		c2 = new JButton("Text 2");
		c2.setBackground(Color.darkGray);
		c2.setForeground(Color.white);
		c2.setFont(smallnormalFont);
		c2.setFocusPainted(false);
		c2.addActionListener(choiceHandler);
		c2.setActionCommand("c2");
		
		c3 = new JButton("Text 3");
		c3.setBackground(Color.darkGray);
		c3.setForeground(Color.white);
		c3.setFont(smallnormalFont);
		c3.setFocusPainted(false);
		c3.addActionListener(choiceHandler);
		c3.setActionCommand("c3");
		
		c4 = new JButton("Text 4");
		c4.setBackground(Color.darkGray);
		c4.setForeground(Color.white);
		c4.setFont(smallnormalFont);
		c4.setFocusPainted(false);
		c4.addActionListener(choiceHandler);
		c4.setActionCommand("c4");
		
		choiceButtonPanel.add(c1);
		choiceButtonPanel.add(c2);
		choiceButtonPanel.add(c3);
		choiceButtonPanel.add(c4);
		
		playerPanel = new JPanel();
		playerPanel.setBounds(100, 15, 600, 80);
		playerPanel.setBackground(Color.darkGray);
		playerPanel.setLayout(new GridLayout(1,4));
		con.add(playerPanel);
		
		securityLabel = new JLabel("Security Level: ");
		securityLabelNumber = new JLabel();
		securityLabel.setFont(normalFont);
		securityLabelNumber.setFont(normalFont);
		securityLabel.setForeground(Color.gray);
		securityLabelNumber.setForeground(Color.gray);
		playerPanel.add(securityLabel);
		playerPanel.add(securityLabelNumber);
		
		playerSetup();
		
	}
	
	public void playerSetup() {
		
		securityHP = 85;
		securityLabelNumber.setText("" + securityHP);
		
		clearance();
		
	}
	
	public void clearance() {
		
		position = "clearance";
		mainTextArea.setText("You've received a suspicious email in \nyour inbox. "
				+ "\n\nIn the preview window it appears to contain"
				+ "several attachments and links."
				+ "\n\nWhat do you do next?");
		
		c1.setText("Reply to it");
		c2.setText("Open the attachments");
		c3.setText("Click the links");
		c4.setText("Delete it");
		
	}
	
	public void replyEmail() {
		
		position = "replyEmail";
		mainTextArea.setText("The sender responds. \n\n'You're the lucky winner of $500! "
				+ "\nPlease give us the details of your \nbank account to claim the money.'");

		c1.setText("Give bank account details");
		c2.setText("Demand THEIR bank account details");
		c3.setText("'I don't have a bank account'");
		c4.setText("Delete it");
		
	}
	
	public void giveDetails() {
		
		position = "giveDetails";
		mainTextArea.setText("Your bank calls you to inform you that \nyour account has"
				+ " been compromised. \n\nNever disclose sensitive \ninformation online." + choices);
		securityHP = securityHP - 20;
		securityLabelNumber.setText(""+securityHP);
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
	}
	
	public void deleteEmail() {
		
		position = "deleteEmail";
		mainTextArea.setText("You open a new tab. \nIn the search bar, you type: ");
		
		c1.setText("https://google.com"); //correct
		c2.setVisible(true);
		c4.setVisible(true);
		c3.setVisible(true);
		c2.setText("https://gogle.com");
		c3.setText("https://goggles.com");
		c4.setText("http://google.com");
		
	}
	
	public void incorrectURL() {
		
		position = "incorrectURL";
		mainTextArea.setText("You've reached a page with a 404 error.");
		
		c1.setText("Go to https://google.com"); //correct
		c2.setText("Go to http://google.com");
		c3.setVisible(false);
		c4.setVisible(false);
	}
	
	public void correctURL() {
		
		position = "correctURL";
		mainTextArea.setText("The homepage of Google opens up. \nYou notice a padlock in the browser bar."
				+ "\n\nWhat does it mean?");
		
		c1.setText("It doesn't have any significance.");
		c2.setText("You're safely connected."); // correct
		c2.setVisible(true);
		c3.setVisible(true);
		c4.setVisible(true);
		c3.setText("The website is locked.");
		c4.setText("This is a safe website.");
	}
	
	public void wrongGoogle() {
		
		position = "wrongGoogle";
		mainTextArea.setText("You've arrived at what looks like the \nhomepage of Google."
				+ "\n\nIt's full of ads and pop-ups you don't \nrecognize.");
		
		c1.setText("Go to https://google.com");
		c2.setText("Click a pop-up");
		c3.setVisible(false);
		c4.setVisible(false);
		
	}
	
	public void popUp() {
		
		position = "popUp";
		mainTextArea.setText("The pop-up reads: 'HTTP and HTTPS are \nbrowser protocols used to send and receive"
				+ " data packets over the web. "
				+ "Data is \necnrypted with HTTP but not with HTTPS.'"
				+ "\n\nYou shrug. \nSeems like meaningless information.");
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
		
	}
	
	public void demand() {
		
		position = "demandBank";
		mainTextArea.setText("They send back an email with a button, \nsaying it's for claiming your prize."
				+ "\n\nAt the corner of your eye, you spot \nsomething in the distance away from your \nscreen. "
				+ "But a notification at the bottom \ncorner of the screen demands your \nattention too."
				+ "\n\nWhat do you do?");
		
		c1.setText("Buttons exist to be pressed!");
		c2.setText("Investigate what caught your eye.");
		c3.setText("Check the notification.");
		c4.setText("Close popup. Delete email.");
	}
	
	public void openAttachment() {
		
		position = "openAttachment";
		mainTextArea.setText("Uh oh! \n\nA notification informs you that your \nsystem has been infected by"
				+ " malware." +
				"\n\nNever open emails with attachments or"
				+ "\nlinks from suspicious senders for it could be a trap "
				+ "devised to infect you or to get \nyou to disclose personal data.");
		
		securityHP = securityHP - 20;
		securityLabelNumber.setText(""+securityHP);
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
		
	}
	
	public void padlockOver() {
		
		position = "padlockOver";
		mainTextArea.setText("Over the corner of your eye, you spot a \nUSB on the floor. \n\nYou proceed to:");
		
		c2.setVisible(true);
		c4.setVisible(true);
		c3.setVisible(true);
		c1.setText("Give it to someone you know");
		c2.setText("Leave it on the floor");
		c3.setText("Give it to IT security personnel"); // correct
		c4.setText("Check its contents");
		
	}
	
	public void padlockCorrect() {
		
		position = "padlockCorrect";
		
		securityHP = securityHP + 5;
		securityLabelNumber.setText(""+securityHP);
		
		mainTextArea.setText("Seems you know a bit about cybersecurity...Nice."
				+ "\n\nOver the corner of your eye, you spot a \nUSB on the floor. \n\nYou proceed to:");
		
		c2.setVisible(true);
		c4.setVisible(true);
		c3.setVisible(true);
		c1.setText("Give it to someone you know");
		c2.setText("Leave it on the floor");
		c3.setText("Give it to IT security personnel"); // correct
		c4.setText("Check its contents");
		
	}
	
	public void padlockIncorrect() {
		
		position = "padlockIncorrect";
		mainTextArea.setText("The padlock represents the digital proof \nthat the site was registered"
				+ " with a \ncertificate authority to ensure that they \nare who they say they are."
				+ "\n\nYou may be safely connected to the site \nbut the site may not be legitimate.");
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
		
	}
	
	public void usbBetterCorrect() {
		
		position = "usbBetterCorrect";
		mainTextArea.setText("So it seems you're aware of the risks of \nleaving a stray USB lying around."
				+ "\n\nThen tell me, what would be the best course"
				+ "of action when a Windows notification \ninforms "
				+ "you to update your system?");
		
		securityHP = securityHP + 5;
		securityLabelNumber.setText(""+securityHP);
		
		c2.setVisible(true);
		c4.setVisible(true); 
		c3.setVisible(true);
		c1.setText("Update it later."); // correct
		c2.setText("Update it now."); // correct
		c3.setText("Back up data and update it now."); // even better
		c4.setText("Ignore and don't update.");
		
	}
	
	public void usbCorrect() {
		
		position = "usbCorrect";
		mainTextArea.setText("Ah, look - it's a Windows notification \ninforming you to"
				+ " update your system.");
		
		c2.setVisible(true);
		c4.setVisible(true);
		c3.setVisible(true);
		c1.setText("Update it later."); // correct
		c2.setText("Update it now."); // correct
		c3.setText("Back up data and update it now."); // even better
		c4.setText("Ignore and don't update.");
		
	}
	
	public void webcamAccessed() {
		
		position = "webcamAccessed";
		mainTextArea.setText("Your mouse begins to move on its own and a bunch "
				+ "of programs open up without you doing"
				+ "anything. You are unable to regain control of your computer."
				+ "\n\nUpdates should be done as soon as possible as there may be vulnerabilities "
				+ "attackers \nwould rush to exploit. They could take \ncontrol of your system "
				+ "if these \nvulnerabilities aren't fixed.");
		
		securityHP = securityHP - 25;
		securityLabelNumber.setText(""+securityHP);
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
	}
	
	public void updateCorrect() {
		
		position = "updateCorrect";
		mainTextArea.setText("A friend calls and asks if it's okay to \nreuse their password.");
		
		c2.setVisible(true);
		c4.setVisible(true);
		c3.setVisible(true);
		c1.setText("'No. Never reuse a password.'"); // correct
		c2.setText("'Yes, it's okay.'"); 
		c3.setText("'What's your password?"); 
		c4.setText("'Yes, just make it more complex.'");
		
	}
	
	public void updateBetter() {
		
		position = "updateBetter";
		mainTextArea.setText("Well done! While updates should be done \nas soon as possible,"
				+ " it would be best to \nback-up one's data beforehand."
				+ "\n\nIn addition, back-ups should be done \nregularly, to save important"
				+ " files from \nsystem crashes and malware infections.");
		
		securityHP = securityHP + 20;
		securityLabelNumber.setText(""+securityHP);
		
		if (securityHP > 100) {
			securityHP = 100;
			securityLabelNumber.setText(""+securityHP);
		}
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
	}
	
	public void passwordQuestion() {
		
		position = "passwordQuestion";
		mainTextArea.setText("'Oh, my password is p4sT3w0rld$24.'");
		
		securityHP = securityHP - 30;
		securityLabelNumber.setText(""+securityHP);
		
		c1.setText("'Hey, that's a good password.'"); 
		c2.setText("'You need a better password.'"); 
		c3.setText("'Change your password now.'"); // would recover a bit of HP
		c4.setVisible(false);
	}
	
	public void p_neverReveal() {
		
		position = "p_neverReveal";
		mainTextArea.setText("Never reveal or ask anyone else to \nreveal their password."
				+ "\n\nThis could lead to their accounts and data \nbeing compromised.");
		
		securityHP = securityHP - 10;
		securityLabelNumber.setText(""+securityHP);
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
	}
	
	public void p_choice3() {
		
		position = "p_choice3";
		mainTextArea.setText("Never reveal or ask anyone else to \nreveal their password."
				+ "\n\nThis could lead to their accounts and data \nbeing compromised.");
		
		securityHP = securityHP + 10;
		securityLabelNumber.setText(""+securityHP);
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
	}
	
	
	public void usbIncorrect() {
		
		position = "usbIncorrect";
		mainTextArea.setText("Attackers usually leave malware-infected \nUSB sticks lying around."
				+ "\n\nIt's important that they are turned over \nto the IT department who can safely test \nthe device issue a"
				+ "warning to collect any \nother stray USBs.");
		
		securityHP = securityHP - 20;
		securityLabelNumber.setText(""+securityHP);
		
		c1.setText("Next");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
	}
	
	public void extra1() {
		
		position = "extra1";
		mainTextArea.setText("Alright, let's get more technical."
				+ "\n\nRaise your hand if you want to know why a \nfirewall is called a firewall.");
		
		c2.setVisible(true);
		c4.setVisible(true);
		c3.setVisible(true);
		c1.setText("Why?");
		c2.setText("You can tell if my hand's raised?"); 
		c3.setText("I don't need to know this."); 
		c4.setText("It's not an IT term, is it?");
	}
	
	public void firewalltrivia() {
		
		position = "firewalltrivia";
		mainTextArea.setText("I don't have to tell you this, but the term"
				+ "WAS stolen from the construction industry."
				+ "\nA firewall is software installed between a \nnetwork and the rest of the Internet, "
				+ "\nprotecting one from malicious traffic."
				+ "\n\nFeel free to buy one. We're selling at a \nbargain price of 15 security points.");
		
		c1.setText("Buy");
		c2.setText("How did you know..?");
		c4.setVisible(false);
		c3.setVisible(false);

	}
	
	public void serverKnowing() {
		
		position = "serverKnowing";
		mainTextArea.setText("I can tell you everything you've done in \nthis game so far. Curious?"
				+ "\n\nThen select a key to access the information"
				+ "stored on a site." + choices);
		
		c2.setVisible(true);
		c4.setVisible(true);
		c3.setVisible(true);
		c1.setText("422121222");
		c2.setText("2341434"); 
		c3.setText("32123212"); 
		c4.setText("Correct key"); // actual order of choices
		
	}
	
	public void selectSite() {
		
		position = "selectSite";
		mainTextArea.setText("Got your key? Good."
				+ "\n\nWoops. Looks like I've lost the actual link"
				+ "so I'm not too sure..."
				+ "\n\nLet's try going to:");
		
		c2.setVisible(true);
		c4.setVisible(true);
		c3.setVisible(true);
		c1.setText("https://intensionsdata.net"); // correct
		c2.setText("https://inttensions.net"); 
		c3.setText("http://intensionsdata.net");
		c4.setText("https://intensionsbeta.nett"); 
	}
	
	public void enterKey() {
		
		position = "enterKey";
		mainTextArea.setText("A white screen appears with text reading \n'Enter Password: '"
				+ "\n\nRight! It's this site."
				+ "\n\nLet's enter the key, shall we?");
	}
	
	public void correctKeyEntered() {
		
		position = "correctKeyEntered";
		
	}
	
	public void insecureHTTP() {
		
		position = "insecureHTTP";
		mainTextArea.setText("Did you really mean to come here?"
				+ "\n\nWell...it's certainly not this site."
				+ "\n\nLet's try again.");
		
		c1.setText("Back to site selection");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
	}
	
	public void incorrectSiteEnter() {
		
		position = "incorrectSiteEnter";
		mainTextArea.setText("Some confusing and obscene images appear \nout of the blue."
				+ "\n\nWell...it's certainly not this site."
				+ "\n\nLet's try again.");
		
		c1.setText("Back to site selection");
		c2.setVisible(false);
		c4.setVisible(false);
		c3.setVisible(false);
	}
	
	public void haveNotLearned() {
		
		position = "haveNotLearned";
		mainTextArea.setText("");
	}
	
	public void keySelect() {
		
		position = "keySelect";
		mainTextArea.setText("");
	}
	
	
	public void gameOver() {
		
		position = "gameOver";
		mainTextArea.setText("You've failed to secure your system.");
		
	}
	
	public void startOver() {
		
		createGameScreen();
		playerSetup();
		
	}
	
	private static class RoundedBorder implements Border {
		
		private int radius;
		
		RoundedBorder(int radius) {
			this.radius = radius;
			
		}
		
		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	    }
	}
	
	public class TitleScreenHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			createGameScreen();
		}
	}
	
	public class ChoiceHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			
			String yourChoice = event.getActionCommand();
			
			switch(position) {
			case "clearance":
				switch(yourChoice) {
				case "c1": choices.add(1); replyEmail() ; break;
				case "c2": choices.add(2); openAttachment(); break;
				case "c3": choices.add(3); openAttachment(); break;
				case "c4": choices.add(4); deleteEmail(); break;
				
				}
				
				break;
				
			case "replyEmail" :
				switch(yourChoice) {
				case "c1": choices.add(1); giveDetails(); break;
				case "c2": choices.add(2); demand(); break;
				case "c3": choices.add(3); demand(); break;
				case "c4": choices.add(4); deleteEmail(); break;
				}
				
				break;
				
			case "demandBank":
				switch(yourChoice) {
				case "c1": choices.add(1); popUp(); break;
				case "c2": choices.add(2); padlockOver(); break;
				case "c3": choices.add(3); usbCorrect(); break;
				case "c4": choices.add(4); deleteEmail(); break;
				}
				
				break;
				
			case "giveDetails":
				switch(yourChoice) {
				case "c1": deleteEmail(); break;
				}
				
				break;
				
			case "openAttachment" :
				switch(yourChoice) {
				case "c1": deleteEmail(); break;
				}
				
				break;
				
			case "deleteEmail":
				switch(yourChoice) {
				case "c1": choices.add(1); correctURL(); break;
				case "c2": choices.add(2); incorrectURL(); break;
				case "c3": choices.add(3); incorrectURL(); break;
				case "c4": choices.add(4); wrongGoogle(); break;
				}
				
				break;
				
			case "incorrectURL":
				switch(yourChoice) {
				case "c1": choices.add(1); correctURL(); break;
				case "c2": choices.add(2); wrongGoogle(); break;
				}
				
				break;
				
			case "popUp":
				switch(yourChoice) {
				case "c1": correctURL(); break;
				}
				
				break;
				
			case "wrongGoogle":
				switch(yourChoice) {
				case "c1": choices.add(1); correctURL(); break;
				case "c2": choices.add(2); popUp(); break;
				}
				
				break;
				
			case "correctURL":
				switch(yourChoice) {
				case"c1": choices.add(1); padlockIncorrect(); break;
				case"c2": choices.add(2); padlockCorrect(); break;
				case"c3": choices.add(3); padlockIncorrect(); break;
				case"c4": choices.add(4); padlockIncorrect(); break;
				}
				
				break;
				
			case "padlockIncorrect":
				switch(yourChoice) {
				case "c1": padlockOver(); break;
				}
				
				break;
				
			case "padlockCorrect":
				switch(yourChoice) {
				case"c1": choices.add(1); usbIncorrect(); break;
				case"c2": choices.add(2); usbIncorrect(); break;
				case"c3": choices.add(3); usbBetterCorrect(); break;
				case"c4": choices.add(4); usbIncorrect(); break;
				
				}
				
				break;
				
			case "padlockOver":
				switch(yourChoice) {
				case"c1": choices.add(1); usbIncorrect(); break;
				case"c2": choices.add(2); usbIncorrect(); break;
				case"c3": choices.add(3); usbBetterCorrect(); break;
				case"c4": choices.add(4); usbIncorrect(); break;
				
				}
				
				break;
				
			case "usbBetterCorrect":
				switch(yourChoice) {
				case"c1": choices.add(1); updateCorrect(); break;
				case"c2": choices.add(2); updateCorrect(); break;
				case"c3": choices.add(3); updateBetter(); break;
				case"c4": choices.add(4); webcamAccessed(); break;
				} 
				
				break;
				
			case "usbIncorrect":
				switch(yourChoice) {
				case"c1": usbCorrect(); break;
				}
				
				break;
				
			case "usbCorrect":
				switch(yourChoice) {
				case"c1": choices.add(1); updateCorrect(); break;
				case"c2": choices.add(2); updateCorrect(); break;
				case"c3": choices.add(3); updateBetter(); break;
				case"c4": choices.add(4); webcamAccessed(); break;
				
				}
				
				break;
				
			case "webcamAccessed":
				switch(yourChoice) {
				case "c1": updateCorrect(); break;
				
				}
				
			case "updateBetter":
				switch(yourChoice) {
				case "c1": updateCorrect(); break;
				
				}
				
				break;
				
			case "updateCorrect":
				switch(yourChoice) {
				case"c1": choices.add(1); extra1(); break;
				case"c2": choices.add(2); p_neverReveal(); break;
				case"c3": choices.add(3); passwordQuestion(); break;
				case"c4": choices.add(4); p_neverReveal(); break;
				}
				
				break;
				
			case "p_neverReveal":
				switch(yourChoice) {
				case "c1": extra1(); break;
				}
				
				break;
				
			case "passwordQuestion":
				switch(yourChoice) {
				case"c1": p_neverReveal(); break;
				case"c2": p_neverReveal(); break;
				case"c3": p_choice3(); break;
				}
				
				break;
				
			case "p_choice3":
				switch(yourChoice) {
				case "c1": extra1(); break;
				}
				
				break;
				
			case "extra1":
				switch(yourChoice) {
				case"c1": choices.add(1); firewalltrivia(); break;
				case"c2": choices.add(2); serverKnowing(); break;
				case"c3": choices.add(3); firewalltrivia(); break;
				case"c4": choices.add(4); firewalltrivia(); break;
				}
				
				break;
				
			case "firewalltrivia":
				switch(yourChoice) {
				case "c1": choices.add(1); firewalltrivia(); break;
				case"c2": choices.add(2); serverKnowing(); break;
				}
				
				break;
				
			case "serverKnowing":
				switch(yourChoice) {
				case"c1": choices.add(1); selectSite(); break;
				case"c2": choices.add(2); selectSite(); break;
				case"c3": choices.add(3); selectSite(); break;
				case"c4": choices.add(4); firewalltrivia(); break;
				}
				
				break;
				
			case "selectSite":
				switch(yourChoice) {
				case "c1": choices.add(1); enterKey(); break;
				case"c2": choices.add(2); incorrectSiteEnter(); break;
				case"c3": choices.add(3); insecureHTTP(); break;
				case"c4": choices.add(4); incorrectSiteEnter(); break;
				}
				
				break;
				
			case "incorrectSiteEnter":
				switch(yourChoice) {
				case "c1": selectSite(); break;
				}
				
				break;
				
			case "enterKey":
				switch(yourChoice) {
				case "c1": selectSite(); break;
				}
				
				break;
				
			case "insecureHTTP":
				switch(yourChoice) {
				case "c1": selectSite(); break;
				}
				
				break;
				
			}
		}
	}
}
