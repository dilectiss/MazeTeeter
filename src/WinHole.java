/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580
 *	Hilary Hunt			5018938 (m)
 *	Di Liu				5009232
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import javax.swing.ImageIcon;

public class WinHole extends Body {
	
	//Constructor
	public WinHole(Double x, Double y) {
		xPos = x;
		yPos = y;
	}
	
	static final ImageIcon winhole = new ImageIcon(MazeTeeter.class.getResource("winHole.png"));
	static final Integer width = winhole.getIconWidth();
	static final Integer height = winhole.getIconHeight();
	
	// methods to draw and iterate the content
	public void step(Game bw, GameComponent canvas) {}
	public void draw(Game h, GameComponent canvas) {canvas.drawImage(winhole.getImage(), mapx(h, canvas, xPos), mapy(h, canvas, yPos));}

	// methods to return object dimensions
	public Double width() {return (double) width;}
	public Double height() {return (double) height;}
}
