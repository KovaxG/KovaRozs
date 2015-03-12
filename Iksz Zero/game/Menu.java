package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import ai.AI;
import utils.MenuHandlerEnum;

public class Menu extends JMenuBar{
	private static final long serialVersionUID = 1L;
	private JMenu gameMenu;
	private JMenu settingsMenu;
	
	private JMenuItem singlePlayerItem;
	private JMenuItem multiPlayerItem;
	
	private JMenuItem clearScoreItem;
	private JMenuItem setPlayersItem;
	private JMenuItem setRoundsItem;
	
	public Menu(){
		gameMenu = new JMenu("Game");
		singlePlayerItem = new JMenuItem("Single Player");
		singlePlayerItem.addActionListener(new MenuHandler(MenuHandlerEnum.SINGLEPLAYER));
		multiPlayerItem = new JMenuItem("Multiplayer");
		multiPlayerItem.addActionListener(new MenuHandler(MenuHandlerEnum.MULTIPLAYER));
		gameMenu.add(singlePlayerItem);
		gameMenu.add(multiPlayerItem);
		
		settingsMenu = new JMenu("Settings");
		clearScoreItem = new JMenuItem("Reset Score");
		clearScoreItem.addActionListener(new MenuHandler(MenuHandlerEnum.RESETSCORE));
		setPlayersItem = new JMenuItem("Set Player Names");
		setPlayersItem.addActionListener(new MenuHandler(MenuHandlerEnum.SETPLAYERNAMES));
		setRoundsItem = new JMenuItem("Play Round");
		setRoundsItem.addActionListener(new MenuHandler(MenuHandlerEnum.SETROUNDS));
		settingsMenu.add(clearScoreItem);
		settingsMenu.add(setPlayersItem);
		settingsMenu.add(setRoundsItem);
		
		add(gameMenu);
		add(settingsMenu);
	}
	
	public class MenuHandler implements ActionListener{
		private MenuHandlerEnum index = MenuHandlerEnum.BLANK;
		
		public MenuHandler(MenuHandlerEnum i){
			index = i;
		}
		
		public void actionPerformed(ActionEvent e){
			switch(index){
			case SINGLEPLAYER: 
				InformationPanel.setSinglePlayer();
				InformationPanel.noRounds();
				Board.reset();
				AI.clearBoard();
				break;
			case MULTIPLAYER: 
				InformationPanel.setMultiPlayer();
				InformationPanel.noRounds();
				Board.reset();
				break;
			case RESETSCORE: 
				InformationPanel.clearScore();
				Board.reset();
				break;
			case SETPLAYERNAMES: 
				String name1 = JOptionPane.showInputDialog("Name of First Player:",InformationPanel.getPlayer1Name());
				String name2 = JOptionPane.showInputDialog("Name of second Player:",InformationPanel.getPlayer2Name());
				InformationPanel.setPlayerNames(name1,name2);
				break;
			case SETROUNDS: 
				String roundNumber= JOptionPane.showInputDialog("How many rounds do you want to play?",3);
				try{
					int temp = Integer.parseInt(roundNumber);
					if (temp < 0) throw new Exception(); // TODO nulla
					InformationPanel.playRounds(temp);
					Board.reset();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null,"Incorrect Input!","Error",JOptionPane.ERROR_MESSAGE);
				}
				
				break;
			default: break;
			}
		}
	}
}
