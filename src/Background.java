/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580 (m)
 *	Hilary Hunt			5018938
 *	Di Liu				5009232
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import javax.swing.ImageIcon;

public class Background extends Body {
	
	static final ImageIcon background = new ImageIcon(MazeTeeter.class.getResource("background.png"));
	static final Integer width = background.getIconWidth();
	static final Integer height = background.getIconHeight();
	
	//Constructor
	public Background(Double xp, Double yp) {
		xPos = xp;
		yPos = yp;
	}
	
	// methods to draw and iterate the content
	public void step(Game bw, GameComponent canvas) {}
	public void draw(Game h, GameComponent canvas) {canvas.drawImage(background.getImage(), mapx(h, canvas, xPos), mapy(h, canvas, yPos));}
	
	// methods to return object dimensions
	public Double width() {return (double) width;}
	public Double height() {return (double) height;}
}
