/**
 * TetrisT class is a subclass of TetrisPiece class. 
 *
 *@author Veneta Cholakova
 *@version 1
 */

public class TetrisT extends TetrisPiece {

	public TetrisT() {
		super();
		
		//Set all locations of all rotations to false
		for(int rot = 0; rot < filledSquares.length; rot++){
			for(int row = 0; row < filledSquares[0].length; row++){
				for(int col = 0; col < filledSquares[0][row].length; col++){
					filledSquares[rot][row][col] = false;
				}
			}
		}
		
		// Set all rotations
		setUp();
		setLeft();
		setDown();
		setRight();
	}
	
	/**
	 * Sets the T piece to be vertical
	 */
	private void setUp(){
		//Set true where the piece's squares are - applies to 0 rotation
		for(int col = 0; col < (filledSquares[0][0].length-1); col++){
				filledSquares[0][0][col] = true;
		}
		filledSquares[0][1][1] = true;
	}
	
	/**
	 * Sets the T piece to be upside down
	 */
	private void setDown(){
		//Set true where the piece's squares are - applies to 2 rotation
		for(int col = 0; col < (filledSquares[2][1].length-1); col++){
				filledSquares[2][1][col] = true;
		}
		filledSquares[2][0][1] = true;
	}
	
	/**
	 * Sets the T piece to be turned to the right
	 */
	private void setRight(){
		//Set true where the piece's squares are - applies to 1 rotation
		for(int row = 0; row < (filledSquares[1].length-1); row++){
			filledSquares[1][row][1] = true;
		}
		filledSquares[1][1][0] = true;
	}
	
	/**
	 * Sets the T piece to be turned to the left
	 */
	private void setLeft(){
		//Set true where the piece's squares are - applies to 3 rotation
		for(int row = 0; row < (filledSquares[3].length-1); row++){
			filledSquares[3][row][0] = true;
		}
		filledSquares[3][1][1] = true;
	}

}
