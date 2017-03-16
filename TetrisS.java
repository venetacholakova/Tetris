/**
 * TetrisS class is a subclass of TetrisPiece class. 
 *
 *@author Veneta Cholakova
 *@version 1
 */

public class TetrisS extends TetrisPiece {

	public TetrisS() {
		super();
		
		//Set all locations of all rotations to false
		for(int rot = 0; rot < filledSquares.length; rot++){
			for(int row = 0; row < filledSquares[0].length; row++){
				for(int col = 0; col < filledSquares[0][row].length; col++){
					filledSquares[rot][row][col] = false;
				}
			}
		}
			
		//Set all rotations
		setHorizontal();
		setVertical();
		
	}
	
	/**
	 * Sets the S piece to be horizontal
	 */
	private void setHorizontal(){
		//Set true where the piece's squares are - applies to 0 and 2 rotations because of symmetry
		for(int rot = 0; rot <= 2; rot+=2){
			for(int row = 0; row < (filledSquares[rot].length-2); row++){
				if(row == 0){
					filledSquares[rot][row][1] = true;
					filledSquares[rot][row][2] = true;
				}
				else{
					filledSquares[rot][row][0] = true;
					filledSquares[rot][row][1] = true;
				}	
			}
		}
	}
	
	/**
	 * Sets the S piece to be vertical
	 */
	private void setVertical(){
		//Set true where the piece's squares are - applies to 1 and 3 rotations because of symmetry
		for(int rot = 1; rot <= 3; rot+=2){
			for(int col = 0; col < (filledSquares[rot][0].length-2); col++){
				if(col == 0){
					filledSquares[rot][0][col] = true;
					filledSquares[rot][1][col] = true;
				}
				else{
					filledSquares[rot][1][col] = true;
					filledSquares[rot][2][col] = true;
				}	
			}
		}
	}
}
