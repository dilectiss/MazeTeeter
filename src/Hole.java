/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580
 *	Hilary Hunt			5018938 (m)
 *	Di Liu				5009232
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import javax.swing.ImageIcon;

public class Hole extends Body {
	
	static final ImageIcon hole = new ImageIcon(MazeTeeter.class.getResource("trap.png"));
	static final Integer width = hole.getIconWidth();
	static final Integer height = hole.getIconHeight();
	
	//Constructor
	public Hole(Double xp, Double yp) {
		xPos = xp;
		yPos = yp;
	}
	
	// methods to draw and iterate the content
	public void step(Game bw, GameComponent canvas) {}
	public void draw(Game h, GameComponent canvas) {canvas.drawImage(hole.getImage(), mapx(h, canvas, xPos), mapy(h, canvas, yPos));}
	
	// methods to return object dimensions
	public Double width() {return (double) width;}
	public Double height() {return (double) height;}
}
