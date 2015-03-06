package game;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.FlowLayout;

public class InformationPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private String text;
	private JLabel label;
	
	public InformationPanel(){
		text = "Hello! Itt a szkor.";
		label = new JLabel(text);
		setLayout(new FlowLayout());
		add(FlowLayout.LEFT,label);
	}
}
