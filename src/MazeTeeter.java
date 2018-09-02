/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580 (m)
 *	Hilary Hunt			5018938 (m)
 *	Di Liu				5009232
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class MazeTeeter implements ActionListener {
	
	// window dimensions
	final static Integer xDim = 1280, yDim = 800;

	private Game game;
	private Timer timer;
	private GameComponent canvas;
	private static JFrame jframe, frame;
	protected int counter = 8;
	private Menu menu;

	//Constructor
	public MazeTeeter() {
		// frame for dialog boxes
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocation(640, 400);
		frame.setVisible(false);
		
		// main frame for the game
		jframe = new JFrame("Maze Teeter Game");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLocation(100, 20);
		canvas = new GameComponent(xDim, yDim-80);
		(jframe.getContentPane()).add(canvas);
		canvas.setLocation(0,0);
		timer = new Timer(0,this);
		menu = new Menu(jframe, timer);
		jframe.pack();
		canvas.requestFocusInWindow();
		jframe.setVisible(true);
		jframe.setResizable(false);
	}

	//Creates a new game
	public void newGame(int num) {
		canvas.reset();
		menu.setLevel(counter);
		setbackground();
		game = new Game(xDim, yDim-80, num);
		timer.start();
		menu.reset();
		jframe.toFront();
	}

	//Main game loop
	public void actionPerformed(ActionEvent event) {
		menu.countDown();
		if (event.getSource() == timer) {
			if (!game.won) {
				canvas.clearOffscreen();
				game.draw(canvas);
				canvas.drawOffscreen();
				game.step(canvas);
				canvas.requestFocusInWindow();
				// thread sleep to slow the ball down
				try {Thread.sleep(20);} catch (InterruptedException e) {e.printStackTrace();}
				
				//Cap the game at level 50 and allows players to increase the level
				if (menu.increase == true && !(counter == 57)) {
					counter++;
					menu.levelDisp.setText("Level: "+(counter-7));
					newGame(counter);
				}
				
				//Sets minimum level and allows players to decrease the level
				if (menu.decrease == true && !(counter == 8)) {
					counter--;
					menu.levelDisp.setText("Level: "+(counter-7));
					newGame(counter);
				}
				
				//Tells the player they've won
				if (game.won) {
					Object[] winbox = {"Next Level", "Replay", "Quit"};
					int n = JOptionPane.showOptionDialog(frame, "Congratulations!", "You Win!",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
							null, winbox, winbox[2]);
					if (n == 2) {
						timer.stop();
						jframe.setVisible(false);					
						jframe.dispose();
					}
					else { 
						if (n == 1) {newGame(counter);}
						else {
							if (counter != 58) {counter++;}
							newGame(counter);
							}

					}
				}
				
				if (menu.score == 0) {
					timer.stop();
					Object[] losebox = {"Replay", "Quit"};
					int choice = JOptionPane.showOptionDialog(frame, "Game Over", "You Lose!",
										  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
										  null, losebox, losebox[1]); 
					if (choice == 1) {
						timer.stop();
						jframe.setVisible(false);					
						jframe.dispose();
					}
					else {
						newGame(counter);
					}
				}
			}
		}
	}

	private void setbackground() {
		Graphics g = canvas.getBackgroundGraphics();
		Color myColor = new Color(17, 11, 26);
		g.setColor(myColor);
		g.fillRect(0, 0, xDim, yDim);
	}

	public static void main(String[] args) {
		MazeTeeter app = new MazeTeeter();
		app.newGame(8);
	}
}

