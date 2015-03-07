package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private JMenu gameMenu;
	private JMenu settingsMenu;
	
	private JMenuItem singlePlayerItem;
	private JMenuItem multiPlayerItem;
	
	private JMenuItem clearScoreItem;
	private JMenuItem clearTurnItem;
	private JMenuItem setPlayersItem;
	
	public Menu(){
		gameMenu = new JMenu("Game");
		singlePlayerItem = new JMenuItem("Single Player");
		singlePlayerItem.addActionListener(new MenuHandler(1));
		multiPlayerItem = new JMenuItem("Multiplayer");
		multiPlayerItem.addActionListener(new MenuHandler(2));
		gameMenu.add(singlePlayerItem);
		gameMenu.add(multiPlayerItem);
		
		settingsMenu = new JMenu("Settings");
		clearScoreItem = new JMenuItem("Reset Score");
		clearScoreItem.addActionListener(new MenuHandler(3));
		clearTurnItem = new JMenuItem("Reset Game");
		clearTurnItem.addActionListener(new MenuHandler(4));
		setPlayersItem = new JMenuItem("Set Player Names");
		setPlayersItem.addActionListener(new MenuHandler(5));
		settingsMenu.add(clearScoreItem);
		settingsMenu.add(setPlayersItem);
		
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
				// TODO itt kell egy AIt irni, tehat meg nem tudok mit ide irni
			}
			else if (index == 2){
				InformationPanel.setMultiPlayer();
			}
			else if (index == 3){
				InformationPanel.clearScore();
			}
			else if (index == 4){
				// TODO informationPanel.resetGame();
			}
			else if (index == 5){
				String name1 = JOptionPane.showInputDialog("Name of First Player:",InformationPanel.getPlayer1Name());
				String name2 = JOptionPane.showInputDialog("Name of second Player:",InformationPanel.getPlayer2Name());
				InformationPanel.setPlayerNames(name1,name2);
			}
		}
	}
}
