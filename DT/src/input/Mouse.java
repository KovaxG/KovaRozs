package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	
	public int x;
	public int y;
	public boolean pressed = false;
	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		pressed = true;
	}

	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}

	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
}
