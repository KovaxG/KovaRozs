package game;

import javax.swing.JFrame; 
import javax.swing.JButton; 
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Board extends JFrame{
	private static final long serialVersionUID = 1L; // Ennek nincs semmi jelentosege
	private JPanel gameBoard; // Ezen vannak a gombok
	private JButton[] cell; // A gombok
	private int turn = 0;
	private String winner = "";
	
	public Board(){
		setTitle("Iksz zéró"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ha ez nincs, nem zarodik le az ablak
		setSize(300,300); 
		setLocationRelativeTo(null); // Azert, hogy az ablak a monitor kozepen jelenjen meg
		
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(3,3)); // A gombokat 3x3 matrixba helyezi
		
		cell = new JButton[9];
		for (int i = 0; i < 9; i ++){
			cell[i] = new JButton("");
			cell[i].setFont(new Font("Arial", Font.BOLD, 50));
			cell[i].addActionListener(new Handler(i)); // Ez nezi, ha megnyomtak a gombot, a handler lejjebb van
			gameBoard.add(cell[i]);
		}
		getContentPane().add(gameBoard);
		
		setVisible(true); // Megtudtam, hogy ezt a vegere jo tenni, mert mas gepeken nem jelennek meg a gombok valamiert 
	}
	
	public void check(){
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
	public void result(String s){
		winner = s;
		String panelTitle = s.equals("")? "Draw!" : "Victory!";
		String message = panelTitle.equals("Draw!")? "Draw! No one Wins." : "Victory for '" + s + "' ! Good Job!";
		
		new JOptionPane().showMessageDialog(null,message,panelTitle,JOptionPane.INFORMATION_MESSAGE); // Kiirja az uzenetet
		reset();
		
	}
	
	public void reset(){
		turn = 0;
		winner = "";
		for (int i = 0; i < 9; i ++){
			cell[i].setText("");
			cell[i].setBackground(null);
		}
	}
	
	public class Handler implements ActionListener{
		private int index; // Megjegyzi, h melyik gomb volt lenyomva
		public Handler(int a){
			index = a;
		}
		
		public void actionPerformed(ActionEvent e){
			if (cell[index].getText().equals("") && winner.equals("")){
				if (turn % 2 == 0) 
					cell[index].setText("X");
				else 
					cell[index].setText("O");
				turn++;
				check();
			}
		}
	}
}
