package game;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Color;

public class Board extends JPanel{
	private static final long serialVersionUID = 1L; // Ennek nincs semmi jelentosege
	public static Cell[] cell; // A gombok
	public static int turn = 0;
	public static String winner = "";
	
	public Board(){
		setLayout(new GridLayout(3,3)); // A gombokat 3x3 matrixba helyezi
		
		cell = new Cell[9];
		for (int i = 0; i < 9; i ++){
			cell[i] = new Cell();
			add(cell[i]);
		}
	}
	
	public static void check(){
		for (int i = 0; i < 3; i++){
			// Rows
			if (cell[3*i+0].getText() == cell[3*i+1].getText() && cell[3*i+1].getText() == cell[3*i+2].getText() && cell[3*i+2].getText() != ""){
				cell[3*i+0].setBackground(Color.PINK);
				cell[3*i+1].setBackground(Color.PINK);
				cell[3*i+2].setBackground(Color.PINK);
				result(cell[3*i+0].getText());
			}
			// Columns
			if (cell[3*0+i].getText() == cell[3*1+i].getText() && cell[3*1+i].getText() == cell[3*2+i].getText() && cell[3*2+i].getText() != ""){
				cell[3*0+i].setBackground(Color.PINK);
				cell[3*1+i].setBackground(Color.PINK);
				cell[3*2+i].setBackground(Color.PINK);
				result(cell[3*0+i].getText());
			}
		}
		// Main Diagonal
		if (cell[0].getText() == cell[4].getText() && cell[4].getText() == cell[8].getText() && cell[8].getText() != ""){
			cell[0].setBackground(Color.PINK);
			cell[4].setBackground(Color.PINK);
			cell[8].setBackground(Color.PINK);
			result(cell[8].getText());
		}
		// Other Diagonal
		if (cell[6].getText() == cell[4].getText() && cell[4].getText() == cell[2].getText() && cell[2].getText() != ""){
			cell[6].setBackground(Color.PINK);
			cell[4].setBackground(Color.PINK);
			cell[2].setBackground(Color.PINK);
			result(cell[2].getText());
		}
		if (turn == 9){
			result(""); // Megtelt a palya s egyenlo
		}
	}
	
	@SuppressWarnings("static-access") // Az eclipse mind cseszi a fejem emiatt rajottem h lehet ilyent csinalni s elhalgat
	public static void result(String s){
		winner = s;
		String panelTitle = s.equals("")? "Draw!" : "Victory!";
		String message = panelTitle.equals("Draw!")? "Draw! No one Wins." : "Victory for '" + s + "' ! Good Job!";
		
		new JOptionPane().showMessageDialog(null,message,panelTitle,JOptionPane.INFORMATION_MESSAGE); // Kiirja az uzenetet
		reset();
		
	}
	
	public static void reset(){
		turn = 0;
		winner = "";
		for (int i = 0; i < 9; i ++){
			cell[i].setText("");
			cell[i].setBackground(null);
		}
	}
}
