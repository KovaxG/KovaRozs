package game;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.FlowLayout;

public class InformationPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static String p1 = "Player 1"; // Name of player 1
	private static String p2 = "Player 2"; // Name of player 2
	private static JLabel label;
	
	public static boolean multiPlayer = false;
	private static int scoreP1 = 0;
	private static int scoreP2 = 0;
	private static int turn = 0;
	
	public InformationPanel(){
		label = new JLabel("I'm a potato.");
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(label);
	}
	
	public static void setMultiPlayer(){
		multiPlayer = true;
		turn = 0;
		scoreP1 = 0;
		scoreP2 = 0;
		label.setText(displayText());
	}
	
	public static void clearScore(){
		scoreP1 = 0;
		scoreP2 = 0;
		label.setText(displayText());
	}
	
	public static String getWinnerName(String s){
		if (turn % 2 == 1){
			if (s.equals("X")) return p2;
			else if (s.equals("O")) return p1;
		}
		else{
			if (s.equals("X")) return p1;
			else if (s.equals("O")) return p2;
		}
		return "Potato";
	}
	
	public static void playerWin(String s){
		turn++;
		if (turn % 2 == 1){
			if (s.equals("X")) scoreP1++;
			else if (s.equals("O")) scoreP2++;
		}
		else{
			if (s.equals("X")) scoreP2++;
			else if (s.equals("O")) scoreP1++;
		}
		label.setText(displayText());
	}
	
	public static void setPlayerNames(String n1, String n2){
		p1 = n1;
		p2 = n2;
		label.setText(displayText());
	}
	
	public static String getPlayer1Name(){
		return p1;
	}
	
	public static String getPlayer2Name(){
		return p2;
	}
	
	private static String displayText(){
		if (turn % 2 == 0) return "[" + p1 + ": " + scoreP1 + "]   " + p2 + ": " + scoreP2;
		else return p1 + ": " + scoreP1 + "   [" + p2 + ": " + scoreP2 + "]";
	}
}
