/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580 (m)
 *	Hilary Hunt			5018938
 *	Di Liu				5009232
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Game {

	// random var for positions
	static Random ran = new Random();

	Boolean won = false;
	
	private int numWalls = 6;		// number of walls in the game
	private int numTraps;			// number of traps in the game
	private WinHole winhole;		// single winning hole (green)
	private Gem gem;				// single ball (remove)
	
	private ArrayList<Wall> walls = new ArrayList<Wall>();		// holds the walls
	private ArrayList<Hole> traps = new ArrayList<Hole>();		// holds the traps
	
	private double gx;
	private double gy;
	
	// constructor
	public Game(int x, int y, int z) {
		
		numTraps = z;
		
		gy = y - WinHole.width;
		gx = x - WinHole.width;
		
		gem = new Gem(-gx, gy);				// create an instance of the ball
		winhole = new WinHole(gx, gy - 600);		// create an instance of the winhole
		
		
		// generates the specified number of walls
		double xCounter = 0.0; double yCounter = 0.0;
		for (int i = 0; i < numWalls; i++) {	
				if (i % 2 == 0) {yCounter = 80;} else {yCounter = 0;}
				xCounter += 190;
				walls.add(new Wall(xCounter, yCounter));
			}
		
		// generates the specified number of traps
		double trapPosX, trapPosY;
		for (int j = 0; j < numTraps; j++) {
			trapPosX = ran.nextInt((int) (x - WinHole.width));
			trapPosY = ran.nextInt((int) (y - WinHole.width));
			traps.add(new Hole (trapPosX, trapPosY));
		}
	}
	
	// method to draw everything
	public void draw(GameComponent canvas) {  
		
		winhole.draw(this, canvas);

		Iterator<Hole> itH = traps.iterator();
		while (itH.hasNext()) {Hole hole = itH.next(); hole.draw(this, canvas);}

		Iterator<Wall> itW = walls.iterator();
		while (itW.hasNext()) {Wall wall = itW.next(); wall.draw(this, canvas);}

		gem.draw(this, canvas);
	}

	// method to move everything
	public void step(GameComponent canvas) {
		
		gem.step(this, canvas);
		
		if ((gem.xPos > (winhole.xPos - 35)) && (gem.xPos < (winhole.xPos + 35)) && 
				(gem.yPos > (winhole.yPos - 35)) && (gem.yPos < (winhole.yPos + 35))) {
			won = true;
		}
		
		Iterator<Wall> itW = walls.iterator();
		while (itW.hasNext()) { Wall wall = itW.next();
			gem.wallCollision(wall.xPos, wall.yPos, wall.width(), wall.height());
		}
		
		Iterator<Hole> itH = traps.iterator();
		while (itH.hasNext()) {
			Hole hole = itH.next();
			if ((gem.xPos > (hole.xPos - 35)) && (gem.xPos < (hole.xPos + 35)) && 
					(gem.yPos > (hole.yPos - 35)) && (gem.yPos < (hole.yPos + 35))) {
				
				gem.xPos = -gx; gem.yPos = gy;		// position reset to the start
				gem.xVel = 0.0; gem.yVel = 0.0;		// velocity reset to zero
			}
		}
	}
}
