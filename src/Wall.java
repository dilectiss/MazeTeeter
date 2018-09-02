/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580 (m)
 *	Hilary Hunt			5018938
 *	Di Liu				5009232
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import javax.swing.ImageIcon;

public class Wall extends Body {
	
	static final ImageIcon wall = new ImageIcon(MazeTeeter.class.getResource("wall.png"));
	static final Integer width = wall.getIconWidth();
	static final Integer height = wall.getIconHeight();
	
	//Constructor
	public Wall(Double xp, Double yp) {
		xPos = xp;
		yPos = yp;
	}
	
	// methods to draw and iterate the content
	public void step(Game bw, GameComponent canvas) {}
	public void draw(Game h, GameComponent canvas) {canvas.drawImage(wall.getImage(), mapx(h, canvas, xPos), mapy(h, canvas, yPos));}
	
	// methods to return object dimensions
	public Double width() {return (double) width;}
	public Double height() {return (double) height;}
}
