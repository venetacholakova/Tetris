import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * TetrisGameGUIController is the controller of Tetris game GUI version. 
 * It takes input from the user and changes the model and the view accordingly. 
 * The view reflects the state of the model and model is the logic of the game. 
 * It implements KeyListener interface.
 *
 * @author Veneta Cholakova
 * @version 1
 */
	
public class TetrisGameGUIController extends JPanel implements KeyListener {

	/**
	 *  Drop rate for the falling piece 
	 */  
	public static final int DEFAULT_DROP_RATE = 500;
	
	//instances of TetrisGame and TetrisBoardGUIView classes
	private TetrisGame game;
	private TetrisBoardGUIView view;
	
	//label for the scores - lines and tetrises cleared
	private JLabel linesLabel,
	               tetrisesLabel;
	
	//instructions for the game
	private JLabel instructions;
	
	//drop rate for the pieces
	private int dropRate = DEFAULT_DROP_RATE;

	/**
	 * Constructor. Calls methods to create view, model and set up timer.
	 */
	 public TetrisGameGUIController() {
		 //Set the layout to be border-style
		 super(new BorderLayout());
		 
		 // create instructions and put them at the bottom
		 instructions = new JLabel("Use arrow keys to move left, right or down.\n"
				       + "Use z to rotate counter-clockwise, x to rotate clockwise.");
		 instructions.setHorizontalAlignment(JLabel.CENTER);
		 add( instructions, BorderLayout.SOUTH );
		
		 //Initialize a tetris game
		 game = new TetrisGame();
		 
		 // Create score panel and add it to the top  
	 	 add(createScore(), BorderLayout.NORTH);
		 
		 //Create view
		 createView();
		 
		 //Set timer
		 setupTimer();
		 
		 //add key listener
		 addKeyListener(this);
		 setFocusable(true);
	 }

	/**
	 * Set up the timer for the movement of the pieces.
	 */
	 private void setupTimer() {
		 ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          //Move piece down and refresh display
		    	  game.attemptMove(TetrisGame.DOWN);
		    	  refreshDisplay();
		      }
		  };
		  // Start timer
		 new Timer(dropRate, taskPerformer).start(); 
	 }
		
	/**
	 * Create an instance of TetrisBoardGUIView and put it in the center.
	 */
	 private void createView() {
		 view = new TetrisBoardGUIView(game.getTetrisBoard());
		 add(view, BorderLayout.CENTER);
	 }
	 
	/**
	 * Initialize JLabels to reflect current scores and add them to a panel.
	 * @return panel containing two JLabels with scores
	 */
	 private JPanel createScore() {
		 //JPanel for the scores
		 JPanel scorePanel = new JPanel(new GridLayout(2, 1));

		 linesLabel = new JLabel("Number of lines cleared: " + game.getNumLines());
		 tetrisesLabel = new JLabel("Number of tetrises made: " + game.getNumTetrises());
		 
		 //Put at top
		 linesLabel.setHorizontalAlignment(JLabel.CENTER);
		 scorePanel.add( linesLabel );
		 tetrisesLabel.setHorizontalAlignment(JLabel.CENTER);
		 scorePanel.add( tetrisesLabel );
		 
		 //return the panel with the scores
		 return scorePanel;
	 }
		 
	/**
	 * Update view with changed model and new scores.
	 */
	 public void refreshDisplay() {
		 //Refresh scores
		 linesLabel.setText("Number of lines cleared: " + game.getNumLines());
		 tetrisesLabel.setText("Number of tetrises made: " + game.getNumTetrises());
		 
		 //Repaint board
		 view.repaint();
	 }

	/**
	 * Necessary for KeyListener. Listen for key input from user. Call correspondent movement methods.
	 * Determine what move was attempted and call TetrisGame method to attempt it.
	 */
	 public void keyPressed(KeyEvent e) {
	 	//Determine which key was pressed and attempt the move/rotation
		 int key = e.getKeyCode();
			
		 if (key == KeyEvent.VK_DOWN) {
			 game.attemptMove(TetrisGame.DOWN);
		 }
		 else if (key == KeyEvent.VK_LEFT) {
			 game.attemptMove(TetrisGame.LEFT);
		 }
		 else if (key == KeyEvent.VK_RIGHT) {
			 game.attemptMove(TetrisGame.RIGHT);
		 }
		 // Rotate clockwise
		 else if (key == KeyEvent.VK_X) {
			 game.attemptMove(TetrisGame.CW);
		 }
		 // Rotate counterclockwise
		 else if (key == KeyEvent.VK_Z) {
			 game.attemptMove(TetrisGame.CCW);
		 }
		 
		//Check if game is over because the top of board was reached
		if(game.getTetrisBoard().isGameOver()) {
			//TELL USER THEY LOST
			System.out.println("You lose!");
			//JLabel loseMsg = new JLabel("You lose!");
			//loseMsg.setHorizontalAlignment(JLabel.CENTER);
			//this.add(loseMsg, BorderLayout.CENTER);

			//Start new game
			this.remove(view);
			game = new TetrisGame();
			view.setBoard(game.getTetrisBoard());
			//createView();
			
		}		
			
		//Refresh display - board repainted and scores updated after a movement was made
		refreshDisplay(); 
	 }

	/**
	 * Necessary for KeyListener. Listens for key input from user.
	 */
	 public void keyTyped(KeyEvent e) {

	 }
	 
	/**
	 * Necessary for KeyListener. Listens for key input from user. 
	 */
	 public void keyReleased(KeyEvent e) {

	 }

}	


