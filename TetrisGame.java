/**
 * This class creates a board and holds methods to play the game.
 *
 * @author Veneta Cholakova
 * @version 1
 */
	
public class TetrisGame {
	
	/** Down move */
	public static final int DOWN = 0;

	/** Right move */
	public static final int RIGHT = 1;

	/** Left move */
	public static final int LEFT = 2;

	/** Clockwise rotation */
	public static final int CW = 3;

	/** Counterclockwise rotation */
	public static final int CCW = 4;
							
	//counters for lines cleared and the tetrises made
    private int numLines = 0,
    			numTetrises = 0;

	//instance of board
	private TetrisBoard tetrisBoard;

	/**
	 * Constructor of the class. 
	 */
	public TetrisGame() {
		tetrisBoard = new TetrisBoard();
	}
	
	/**
	 * Check if the move can be made and move piece. 
	 * @param moveType the type of movement that was passed as an input by user.
	 */
	public void attemptMove(int moveType){
		if(moveType == DOWN){
			//End the round if the piece cannot move down anymore
			if(!tetrisBoard.moveDown()){
				endRound();
			}
		}
		else if(moveType == RIGHT){
			tetrisBoard.moveRight();
		}
		else if(moveType == LEFT){
			tetrisBoard.moveLeft();
		}
		else if(moveType == CW){
			tetrisBoard.rotateCW();
		}
		else if(moveType == CCW){
			tetrisBoard.rotateCCW();
		}
		
	}
	
	/**
	 * End current round. Update scores
	 */
	private void endRound(){
		//Land previous piece
		tetrisBoard.landPiece();
		
		//Check for filled lines
		int numFormedLines = tetrisBoard.numberOfFormedLines();
		
		//If there are formed lines, increment according variables
		if(!(numFormedLines == 0)){
			if(numFormedLines == 4){
				//Tetris is made
				numTetrises++;
			}
			else{
				numLines += numFormedLines;
			}
		}
			
	}
	
	/**
	 * Get number of lines cleared.
	 * @return number of lines cleared
	 */
	public int getNumLines(){
		return numLines;
	}
	
	/**
	 * Get number of tetrises made.
	 * @return number of terises made
	 */
	public int getNumTetrises(){
		return numTetrises;
	}
	
	/**
	 * Access the board of the game.
	 * @return tetris board
	 */
	public TetrisBoard getTetrisBoard(){
		return tetrisBoard;
	}

}


