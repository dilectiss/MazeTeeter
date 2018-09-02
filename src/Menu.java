/*	 COMP1110 A1 (Maze Teeter)
 *  ---------------------------
 *	Sean McKenzie		5025580
 *	Hilary Hunt			5018938 (m)
 *	Di Liu				5009232
 *	Date:				04/10/2011
 *	Date mod:			04/10/2011
 */

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.Dimension;
import java.awt.event.*;

public class Menu {
    // Create the items in the menu
    private final JMenuItem start, instructions, levelUp, levelDown, pause;	
    public int score;
    private JProgressBar scoreBar;
    public JTextField levelDisp;
    private final Dimension dim = new Dimension(40,40);
    public final JFrame frame;
    public Boolean increase = false, decrease = false;
    
    //Constructor function
    public Menu(final JFrame jframe, final Timer timer) {
	
	// Creates the menu bar
	final JMenuBar menuBar = new JMenuBar();
	jframe.setJMenuBar(menuBar);
	
	//Creates the frame for dialog boxes
	frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.pack();
	frame.setLocation(840, 500);
	frame.setVisible(false);
	
	//Create the menu items
	
	//Starts the game when pressed.
	start = new JMenuItem("Play");
	start.setMnemonic(KeyEvent.VK_S);
	start.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
	start.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.start();
				}
			}
			);
	
	//Opens a dialog box containing instructions on how to play
	instructions = new JMenuItem("Instructions");
	instructions.setMnemonic(KeyEvent.VK_I);
	instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
			ActionEvent.CTRL_MASK));
	instructions.addActionListener(
	    new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
                    timer.stop();
                    JOptionPane.showMessageDialog(frame, "Using the arrow keys, " +
                    		"tilt the board to roll the ball into the winning hole",
						  "Instructions", JOptionPane.INFORMATION_MESSAGE); 
                    timer.start();
                    jframe.toFront();
					   }
				       }
				       );
	
	//Pauses the game
	pause = new JMenuItem("Pause");
	pause.setMnemonic('P');
	pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
			ActionEvent.CTRL_MASK));

	pause.addActionListener(
				new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
					timer.stop();
				    }
				}
				);
	
	// Adds a drop down options menu
	JMenu options = new JMenu("Options");
	options.addMenuListener(new MenuListener() {
		public void menuCanceled(MenuEvent e) {}
		public void menuDeselected(MenuEvent e) {timer.start();}
		public void menuSelected(MenuEvent e) {timer.stop();}
		});
	
	// Increases the level
	levelUp = new JMenuItem("Level Up");
	levelUp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));

	levelUp.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			increase = true;
			}
		});

	options.add(levelUp);
	
	//Decreases the level
	levelDown = new JMenuItem("Level Down");
	levelDown.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
			ActionEvent.CTRL_MASK));

	levelDown.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				decrease = true;
			}
		});

	options.add(levelDown);

	//Creates a timer in the menu
	score = 2000;
	scoreBar = new JProgressBar();
	scoreBar.setMinimum(0);
	scoreBar.setMaximum(2000);
	scoreBar.setStringPainted(true);
	scoreBar.setValue(score);
	
	//Displays the current level
	levelDisp = new JTextField();
	levelDisp.setMaximumSize(dim);
	levelDisp.setText("Level: "+1+"  ");
	levelDisp.setEditable(false);

	//Add the buttons to the menu bar
	menuBar.add(levelDisp);
	menuBar.add(pause);
	menuBar.add(start);
	menuBar.add(instructions);
	menuBar.add(options);
	menuBar.add(scoreBar);
    }
    
    //Methods to change the display in the menu
    public void countDown() {
	    score--;
	    scoreBar.setValue(score);
	}
    
    //Resets the timer
    public void reset() {
    	score = scoreBar.getMaximum();
    	increase = false;
    	decrease = false;
    }
    
    public void setLevel(int level) {levelDisp.setText("Level: "+(level-7));}
}
