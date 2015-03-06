package game;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private JMenu gameMenu;
	private JMenu settingsMenu;
	
	private JMenuItem singlePlayerItem;
	private JMenuItem multiPlayerItem;
	
	public Menu(){
		gameMenu = new JMenu("Game");
		singlePlayerItem = new JMenuItem("Single Player");
		multiPlayerItem = new JMenuItem("Multiplayer");
		gameMenu.add(singlePlayerItem);
		gameMenu.add(multiPlayerItem);
		
		settingsMenu = new JMenu("Settings");
		
		add(gameMenu);
		add(settingsMenu);
	}
}
