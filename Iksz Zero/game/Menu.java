package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		singlePlayerItem.addActionListener(new MenuHandler(1));
		multiPlayerItem = new JMenuItem("Multiplayer");
		multiPlayerItem.addActionListener(new MenuHandler(2));
		gameMenu.add(singlePlayerItem);
		gameMenu.add(multiPlayerItem);
		
		settingsMenu = new JMenu("Settings");
		
		add(gameMenu);
		add(settingsMenu);
	}
	
	public class MenuHandler implements ActionListener{
		private int index = 0;
		
		public MenuHandler(int i){
			index = i;
		}
		
		public void actionPerformed(ActionEvent e){
			if (index == 1){
				
			}
			if (index == 2){
				InformationPanel.setMultiPlayer();
			}
		}
	}
}
