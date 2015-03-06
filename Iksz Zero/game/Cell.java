package game;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Cell extends JButton{
	private static final long serialVersionUID = 1L;

	public Cell(){
		setFont(new Font("Arial", Font.BOLD, 50));
		addActionListener(new Handler());
	}
	
	public class Handler implements ActionListener{	
		public void actionPerformed(ActionEvent e){
			if (getText().equals("") && Board.winner.equals("")){
				if (Board.turn % 2 == 0) 
					setText("X");
				else 
					setText("O");
				Board.turn++;
				Board.check();
			}
		}
	}
}
