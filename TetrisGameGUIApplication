import javax.swing.JFrame;

/**
 * This class starts the game by calling the controller of Tetris GUI version.
 *
 * @author Veneta Cholakova
 * @version 1
 */

public class TetrisGameGUIApplication
{
	/**
	 * Sets the width of the frame to 1200 pixels.
	 */
	public static int FRAME_WIDTH = 600;
	
	/**
	 * Sets the height of the frame to 800 pixels.
	 */
	public static int FRAME_HEIGHT = 1200;
	
	/**
	 * Creates a JFrame and adds the TeterisGameGUIController panel to it. 
	 * Starts the GUI version of the game.
	 * @param args the user input (not used)
	 */
	public static void main(String[] args) {
		// Create a frame to put the game panel to
		JFrame gameFrame = new JFrame("(: Tetris Game by Veneta D Cholakova :)");
		
		// Add tetris game panel 
		gameFrame.add(new TetrisGameGUIController());
		
		// Set frame size
		gameFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		// Set default close operation and set visible
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

}
