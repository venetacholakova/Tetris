/**
 * TetrisPiece class is abstract super class of all seven shapes. It has methods for rotation.
 *
 * @author Veneta Cholakova
 * @version 1
 */
	
public abstract class TetrisPiece {

	//3D array of the shape; holds rotation and dimensions of price's grid
	protected boolean [][][] filledSquares;

    //rotation of piece
	protected int pieceRotation;

	/**
	 * Constructor of the class.
	 */
	public TetrisPiece(){
		//Set initial rotation to 0 
		pieceRotation = 0;
				
		//initialize the boolean array representing rotation, rows and columns of piece's grid
		filledSquares = new boolean [4][4][4];
	}

	/**
	 * Method that rotates the object counterclockwise.
	 */
	public void rotateCCW(){
		pieceRotation = (pieceRotation + 3) % 4;
	}

	/**
	 * Method that rotates the object clockwise.
	 */
	public void rotateCW(){
		pieceRotation = (pieceRotation + 1) % 4;
	}

	/**
	 * Get rotation of piece.
	 * @return piece rotation
	 */
	public int getPieceRotation(){
		return pieceRotation;
	}

	/**
	 * Determine if square is filled.
	 * @param rot rotation of piece
	 * @param row row for the location
	 * @param col column for the location
	 * @return true if the location is filled
	 */
	public boolean isFilled(int rot, int row, int col){
		return filledSquares[rot][row][col];
	}
}


		






