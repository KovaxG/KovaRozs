package game;

import java.awt.BorderLayout;

import javax.swing.JFrame; 

public class Window extends JFrame{
	private static final long serialVersionUID = 1L;
	public Board board;
	public Menu menu;
	public InformationPanel infoPanel;

	public static void main(String args[]){
		new Window();
	}
	
	public Window(){
		setTitle("X-O");
		setSize(300,340);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ha ez nincs, nem zarodik le az ablak
		setLocationRelativeTo(null); // Azert, hogy az ablak a monitor kozepen jelenjen meg
		setLayout(new BorderLayout());
		
		menu = new Menu();
		infoPanel = new InformationPanel();
		board = new Board();
		getContentPane().add(BorderLayout.CENTER, board);
		getContentPane().add(BorderLayout.NORTH, menu);
		getContentPane().add(BorderLayout.SOUTH, infoPanel);
		setVisible(true);
	}
}
