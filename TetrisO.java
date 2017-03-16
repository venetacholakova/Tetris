/**
 * TetrisO class is a subclass of TetrisPiece class. 
 * It has the form of a square and because of its symmetry it does not rotate.
 *
 *@author Veneta Cholakova
 *@version 1
 */

public class TetrisO extends TetrisPiece {

	public TetrisO() {
		super();
		
		//Set all locations of all rotations to false
		for(int rot = 0; rot < filledSquares.length; rot++){
			for(int row = 0; row < filledSquares[0].length; row++){
				for(int col = 0; col < filledSquares[0][row].length; col++){
					filledSquares[rot][row][col] = false;
				}
			}
		}
		
		
		//Set true where the piece's squares are - applies to all rotations because of symmetry
		for(int rot = 0; rot < filledSquares.length; rot++){
			for(int row = 0; row < 2; row++){
				for(int col = 0; col < 2; col++){
					filledSquares[0][row][col] = true;
				}
			}
		}
		
	}

}
