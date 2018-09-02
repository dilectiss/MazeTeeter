/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580
 *	Hilary Hunt			5018938
 *	Di Liu				5009232 (m)
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import javax.swing.ImageIcon;

public class Gem extends Body {
 
	static final ImageIcon gem = new ImageIcon(MazeTeeter.class.getResource("ball.png"));
	static final Integer width = gem.getIconWidth();
	static final Integer height = gem.getIconHeight();
	
	double xVel = 0.00;
	double yVel = 0.00;
	boolean xCol;
	boolean yCol;

	public Gem(Double x, Double y) {
		xPos = x;
		yPos = y;
	}

	public void draw(Game h, GameComponent canvas) {
		canvas.drawImage(gem.getImage(), mapx(h, canvas, xPos), mapy(h, canvas, yPos));
	}

	//Updates the ball position and velocity
	public void step(Game bw, GameComponent canvas) {
		
		if (canvas.leftPressed) { xVel--; }
		if (canvas.rightPressed) { xVel++; }
		if (canvas.upPressed) { yVel--; }
		if (canvas.downPressed) { yVel++; }
		
		xPos += xVel*0.2;
		yPos += yVel*0.2;
		
		//Prevents the ball from leaving the screen
		if ((xPos < 0) && (xVel < 0) || (xPos > (canvas.xdim - width))) xVel = -xVel*0.1;
		if ((yPos < 0) && (yVel < 0) || (yPos > (canvas.ydim - width))) yVel = -yVel*0.1;
		
		if (xPos < 0) xPos = 0.0; // left wall check
		if (yPos < 0) yPos = 0.0; // top wall check
		if (yPos > canvas.ydim - width) yPos = (double) canvas.ydim - width; // right wall check
		if (xPos > canvas.xdim - width) xPos = (double) canvas.xdim - width; // bottom wall check	
	}
      
      //Checks whether the ball has collided with any walls
      boolean wallCollision(double xwPos, double ywPos, double widthWall, double heightWall) {	
          if (((xPos+width) > xwPos) && (xPos < (xwPos+widthWall)) &&
                  ((yPos+width) > ywPos) && (yPos < (ywPos+heightWall))) { 
        		if (yPos + width > ywPos+heightWall) yPos = ywPos+heightWall;
        		if (((xPos+(width/2)) < xwPos) && ((xPos + width) > xwPos))
        			xPos = (xwPos-width);
        		if (((xPos+(width/2))>(xwPos+widthWall)) && (xPos < (xwPos+widthWall)))
        			xPos = (xwPos+widthWall);
              if ((xPos > xwPos) && (xPos < (xwPos+widthWall))) {         	  
            	  xCol = true;      	  
                  yVel = -yVel*0.2;
                  yPos += -yVel*0.3;   
              } else {           	  
            	  xCol = true;  	  
                  xVel = -xVel*0.2;
                  yVel += -xVel*0.3; 
              }             
              return true;
          }
          return false;
      }
      
    // methods to return object dimensions
	public Double width() {return (double) width;}
	public Double height() {return (double) height;}
}
