package game;

import javax.swing.JFrame; 
import javax.swing.JButton; 
import javax.swing.JPanel;

import com.sun.prism.paint.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame{
	private static final long serialVersionUID = 1L; 
	private JPanel gameBoard;
	private JButton[] cell;
	private int turn = 0;
	
	public Board(){
		setTitle("Iksz zéró"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300); 
		setVisible(true);
		
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(3,3));
		
		cell = new JButton[9];
		for (int i = 0; i < 9; i ++){
			cell[i] = new JButton("");
			cell[i].addActionListener(new Handler(i));
			gameBoard.add(cell[i]);
		}
		getContentPane().add(gameBoard);
	}
	
	public void check(){
		// TODO this part will check if someone won
	}
	
	public void result(int s){
		// TODO if s == 0 its a draw, if s == 1 'X' won, else 'O' won
	}
	
	public class Handler implements ActionListener{
		private int index;
		public Handler(int a){
			index = a;
		}
		
		public void actionPerformed(ActionEvent e){
			if (cell[index].getText() == ""){
				if (turn % 2 == 0) 
					cell[index].setText("X");
				else 
					cell[index].setText("O");
				turn++;
				check();
				if (turn == 9) result(0);
			}
		}
	}
}

//cell[index].setBackground(java.awt.Color.PINK);
