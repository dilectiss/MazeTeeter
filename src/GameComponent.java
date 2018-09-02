/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580 (m)
 *	Hilary Hunt			5018938
 *	Di Liu				5009232
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameComponent extends JComponent implements KeyListener {

	Integer xdim, ydim; // the size of the JComponent

	private BufferedImage background, offscreen;

	Boolean leftPressed = false;
	Boolean rightPressed = false;
	Boolean upPressed = false;
	Boolean downPressed = false;
	Boolean nPressed = false;
	
	// constructor
	public GameComponent(int xd, int yd) {
		xdim = xd;
		ydim = yd;
		this.setSize(xdim, ydim);
		this.setPreferredSize(new Dimension(xdim,ydim));
		background = new BufferedImage(xdim, ydim, BufferedImage.TYPE_INT_RGB);
		offscreen = new BufferedImage(xdim, ydim, BufferedImage.TYPE_INT_RGB);
		addKeyListener(this);
		this.setFocusable(true);
	}

	public void clearOffscreen() {
		Graphics g = offscreen.getGraphics();
		g.drawImage(background, 0, 0, null);
	}
	
	// methods for paint, background and offscreen graphics below
	public void paint(Graphics g) {g.drawImage(offscreen, 0, 0, null);}
	public Graphics getBackgroundGraphics() {return background.getGraphics();}
	public Graphics getOffscreenGraphics() {return offscreen.getGraphics();}

	public void drawImage(Image i, int x, int y) {
		Graphics g = offscreen.getGraphics();
		g.drawImage(i, x, y, null);
	}

	public void drawOffscreen() {
		Graphics g;
		g = this.getGraphics();
		g.drawImage(offscreen, 0, 0, null);
	}
	
	// resets all keys to false
	public void reset(){
		leftPressed = false;
		rightPressed = false;
		upPressed = false;
		downPressed = false;
		nPressed = false;
	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_KP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_KP_UP || e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_KP_DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_N) {
			nPressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_KP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_KP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_KP_DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_KP_UP || e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		}
	}
}
