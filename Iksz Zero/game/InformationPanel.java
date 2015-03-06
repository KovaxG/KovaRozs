package game;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.FlowLayout;

public class InformationPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static String text;
	private static JLabel label;
	
	public static boolean multiPlayer = false;
	private static int scoreP1 = 0;
	private static int scoreP2 = 0;
	private static int turn = 0;
	
	public InformationPanel(){
		text = "Hello! Itt a szkor.";
		label = new JLabel(text);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(label);
	}
	
	public static void setMultiPlayer(){
		multiPlayer = true;
		scoreP1 = 0;
		scoreP2 = 0;
		text = "(Player 1): " + scoreP1 + "   Player 2: " + scoreP2;
		label.setText(text);
		turn = 0;
	}
	
	public static void playerWin(){
		turn++;
		if (turn % 2 == 1) scoreP1++;
		else scoreP2++;
		if (turn % 2 == 0) text = "(Player 1): " + scoreP1 + "   Player 2: " + scoreP2;
		else text = "Player 1: " + scoreP1 + "   (Player 2): " + scoreP2;
		label.setText(text);
	}
}
