/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580 (m)
 *	Hilary Hunt			5018938 (m)
 *	Di Liu				5009232 (m)
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

public abstract class Body {

	public Double xPos, yPos;
	
	// methods to map content positions
	public static int mapx(Game h, GameComponent canvas, double x) {return (int) Math.round(x);}
	public static int mapy(Game h, GameComponent canvas, double y) {return (int) Math.round(y);}
	
	// abstract methods for drawing and iteration
	public abstract void step(Game bw, GameComponent canvas);
	public abstract void draw(Game h, GameComponent canvas);
	
	// abstract dimension methods
	public abstract Double width();
	public abstract Double height();
}
