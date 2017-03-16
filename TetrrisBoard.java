import java.util.Random;

/**
 * This class represents the board of Tetris Game. 
 * @author Veneta Cholakova
 * @version 1
 */

public class TetrisBoard {
	
	// Constants for the dimensions of the board
	public static final int NUM_COLS = 10,
							NUM_ROWS = 18;
	
	//number of rows and columns of the board
	private int numCols,
				numRows;
	
	//2D boolean array for the board
	private boolean [][] blockMatrix;
	
	//current piece moving on the board
	private TetrisPiece currPiece;
	
	//position of current piece moving on the grid; holds top left corner
	private int [] currPieceGridPosition;
	
	/**
	 * Constructor of the class. 
	 */
	public TetrisBoard() {
		//Set rows and columns
		numCols = NUM_COLS;
		numRows = NUM_ROWS;
		
		//create the boolean board
		initBoard();
		
		//add piece
		addNewPiece();
	}
	
	/**
	 * Initialize current grid position position for current piece.
	 */
	private void initCurrentGP(){
		//Initialize the starting position 
		currPieceGridPosition = new int [] {0, numCols/2 - 2};
	}
	
	/**
	 * Initialize board.
	 */
	private void initBoard(){
		//create the boolean board
		blockMatrix = new boolean[NUM_ROWS][NUM_COLS];
		
		//set all locations to be empty
		for(int row = 0; row < NUM_ROWS; row++){
			for(int col = 0; col < NUM_COLS; col++){
				blockMatrix[row][col] = false;
			}
		}
	}
	
	/**
	 * Land piece when cannot move down anymore and add a new one
	 */
	public void landPiece(){
		//First save current piece to board by looping through its small grid 4x4
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				//get grid position
				int[] pos = getCurrPieceGridPos();
				
				//If this row exists in board, check
				if(pos[0] + row < numRows){
					//if the location is true, set the correspondent location on board to true
					if(currPiece.isFilled(currPiece.getPieceRotation(), row, col)){
						blockMatrix[pos[0] + row][pos[1] + col] = true;
					}
				}
			}
		}
			
		//Call addNewPiece to create a new moving piece
		addNewPiece();
	}
	
	/**
	 * Determine if can move left and move.
	 * @return true if moved left
	 */
	public boolean moveLeft(){
		if(currPiece!=null){
			//Call validMove
			if(validMove(currPiece, currPiece.getPieceRotation(), 
				  		currPieceGridPosition[0], (currPieceGridPosition[1]-1))){
				//Move piece
				currPieceGridPosition[1] -= 1;
				return true;
		    }
		}
		return false;
	}
	
	/**
	 * Determine if can move right and move.
	 * @return true if moved right
	 */
	public boolean moveRight(){
		if(currPiece!=null){
			//Call validMove
			if(validMove(currPiece, currPiece.getPieceRotation(), 
				  		currPieceGridPosition[0], (currPieceGridPosition[1]+1))){
				//Move piece
				currPieceGridPosition[1] += 1;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determine if can move down and move.
	 * @return true if moved down
	 */
	public boolean moveDown(){
		if(currPiece!=null){
			//Call validMove
			if(validMove(currPiece, currPiece.getPieceRotation(), 
				  		(currPieceGridPosition[0]+1), currPieceGridPosition[1])){
				//Move piece
				currPieceGridPosition[0] += 1;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determine if can rotate clockwise and rotate.
	 * @return true if rotated clockwise
	 */
	public boolean rotateCW(){
		if(currPiece!=null){
			//Call validMove
			if(validMove(currPiece, ((currPiece.getPieceRotation()+1)%4), 
						currPieceGridPosition[0], currPieceGridPosition[1])){
				//Rotate piece
				currPiece.rotateCW();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determine if can rotate counterclockwise and rotate.
	 * @return true if rotated counterclockwise
	 */
	public boolean rotateCCW(){
		if(currPiece!=null){
			//Call validMove
			if(validMove(currPiece, ((currPiece.getPieceRotation()+3)%4), 
						currPieceGridPosition[0], currPieceGridPosition[1])){
				//Rotate piece
				currPiece.rotateCCW();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determine whether there will be a collision, if a movement is attempted.
	 * @param piece piece currently moving on board
	 * @param rot rotation of piece
	 * @param gridRow location of piece on the grid/board
	 * @param gridCol location of piece on the grid/board
	 * @return true if there is collision
	 */
	private boolean detectCollision(TetrisPiece piece, int rot, int gridRow, int gridCol){
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				
				//Check only if board's row exists 
				if (row + gridRow < numRows){
				    
					// We check whether position on the piece's grid is true,
					// when the same location on board is also true (we have collision)
					if(piece.isFilled(rot, row, col) &&(blockMatrix[row + gridRow][col + gridCol])){
						return true;
					}
				}
				
			}
		}
		return false;
	}
	
	/**
	 * Determine whether piece will be out of bounds, if a movement is attempted.
	 * @param piece piece currently moving on board
	 * @param rot rotation of piece
	 * @param gridRow location of piece on the grid/board
	 * @param gridCol location of piece on the grid/board
	 * @return true if piece will be out of bounds
	 */
	private boolean detectOutOfBounds(TetrisPiece piece, int rot, int gridRow, int gridCol){
		for(int row = 0; row < 4; row++){
			for(int col = 0; col < 4; col++){
				//We check only for the positions that are true on the piece's grid
				if(piece.isFilled(rot, row, col)){
					//Check whether it is not in the bounds of the board
					if(((row + gridRow) >= getNumRows()) || ((col + gridCol) < 0)
							|| ((col + gridCol) >= getNumCols())){
						return true;
					}
				}
			}
		}
		//Return false, if it is within bounds
		return false;
	}
	
	/**
	 * Determine whether move attempted is valid.
	 * @param piece piece currently moving on board
	 * @param rot rotation of piece
	 * @param gridRow location of piece on the grid/board
	 * @param gridCol location of piece on the grid/board
	 * @return if move attempted is valid
	 */
	private boolean validMove(TetrisPiece piece, int rot, int gridRow, int gridCol){
		if(!detectOutOfBounds(piece, rot, gridRow, gridCol) && 
		   !detectCollision(piece, rot, gridRow, gridCol)){
			return true;
		}
		return false;
	}
	
	/**
	 * Determine if there is a block at location.
	 * @param row the row of the location
	 * @param col the column of the location
	 * @return true if there is a block at this location
	 */
	public boolean hasBlock(int row, int col){
		return blockMatrix[row][col];
	}
	
	/**
	 * Add new piece to move on board.
	 */
	public void addNewPiece(){
		//Create a random generator for picking a type of piece
		Random randomGenerator = new Random();
		int randomInt = (randomGenerator.nextInt(7) + 1);
		
		//Create different shape depending on the random number
		if(randomInt == 1){
			currPiece = new TetrisO();
		}
		else if(randomInt == 2){
			currPiece = new TetrisI();
		}
		else if(randomInt == 3){
			currPiece = new TetrisL();
		}
		else if(randomInt == 4){
			currPiece = new TetrisJ();
		}
		else if(randomInt == 5){
			currPiece = new TetrisS();
		}
		else if(randomInt == 6){
			currPiece = new TetrisZ();
		}
		else if(randomInt == 7){
			currPiece = new TetrisT();
		}
		
		// Initialize the grid position of this new piece
		initCurrentGP();
	}
	
	/**
	 * Return number of formed lines. For each filled line found, remove it.
	 * @return number of formed lines
	 */
	public int numberOfFormedLines(){
		//local counter for lines filled
		int linesFormed = 0;
		for(int row = 0; row < getNumRows(); row++){
			if(fullLine(row)){
				linesFormed++;
				
				//Remove this line
				removeLine(row);
			}
		}
		return linesFormed;
	}
	
	/**
	 * Determine whether a line is full with blocks.
	 * @param row the row to check if filled with blocks
	 * @return true if row checked is full
	 */
	private boolean fullLine(int row){
		for(int col = 0; col < blockMatrix[row].length; col++){
			//return false if at least one location is empty at this row
			if(!blockMatrix[row][col]){
				return false;
			}
		}
		//return true, if the if-statement was not entered and all locations are full
		return true;
	}
	
	/**
	 * Remove a line that was filled.
	 * @param row the full line to be cleared
	 */
	private void removeLine(int row){
		for(int col = 0; col < blockMatrix[row].length; col++){
			//set each location of full line to false
			blockMatrix[row][col] = false;
		}
		
		//Move all that was above it 1 row down 
		for(int myRow = row-1; myRow > 0; myRow--){
			blockMatrix [myRow+1] = blockMatrix[myRow];
		}
	}

	/**
	 * Check whether the top row of board has a true value.
	 * @return true if top is reached and game is over
	 */
	public boolean isGameOver(){
		//Check if top row has al least one true value
		for(int col = 0; col < blockMatrix[0].length; col++)
		if(blockMatrix[0][col]){
			return true;
		}
		return false;
	}
	
	/**
	 * Access the boolean array representing board
	 * @return the block matrix that holds the values for board
	 */
	public boolean [][] getBlockMatrix(){
		return blockMatrix;
	}
	
	/**
	 * Return current piece moving on board.
	 * @return current piece moving 
	 */
	public TetrisPiece getCurrentPiece(){
		return currPiece;
	}
	
	/**
	 * Return the position of current piece moving.
	 * @return grid position of current piece
	 */
	public int[] getCurrPieceGridPos(){
		return currPieceGridPosition;
	}
	
	/**
	 * Get number of columns.
	 * @return number of columns
	 */
	public int getNumCols(){
		return numCols;
	}
	
	/**
	 * Get number of rows.
	 * @return number of rows
	 */
	public int getNumRows(){
		return numRows;
	}
	

}
