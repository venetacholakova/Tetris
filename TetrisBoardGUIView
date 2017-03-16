import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

/**
 * TetrisBoardGUIVIew is the view for the GUI version of the game.
 *
 * @author Veneta Cholakova
 * @version 1
 */

public class TetrisBoardGUIView extends JComponent{
	
	//instance of the board
	private TetrisBoard board;
	
	/** Shift the outline of board to the right */
	private static final int boardOutlineX = 5;
	
	/**
	 * Constructor of the class. 
	 * @param b the board passed 
	 */
	public TetrisBoardGUIView(TetrisBoard b) {
		this.board = b;
	}
	
	/**
	 * Override paint method from super class. Draw all pieces that are on board.
	 * @param g Graphics object passed
	 */
	public void paint(Graphics g) {
		// Draw board outline
		paintBoardOutline(g, computeBlockSize());
		
		// Draw blocks on board
		// walk through the array of booleans and determine where a block should be drawn
		for(int row = 0; row < board.getNumRows(); row++){
			for(int col = 0; col < board.getNumCols(); col++){
						
				// Get current grid position of moving piece
				int [] pieceCoords = board.getCurrPieceGridPos();
					
				//If we are within the range of moving tetris piece; the range of each piece is 4x4
				if((pieceCoords[0] <= row)&&(row < pieceCoords[0]+4)  && 
				  (pieceCoords[1]<= col)&&(col < pieceCoords[1]+4)){   
							
					//Determine the coordinates on the piece's grid (the smaller grid that is 4x4)
					int onPieceGridRow = row - pieceCoords[0],
						onPieceGridCol = col - pieceCoords[1];
							
					//Check within its grid if the values are true and display all its squares
					if((board.getCurrentPiece()).isFilled((board.getCurrentPiece()).getPieceRotation(), onPieceGridRow, onPieceGridCol) == true){
						paintBlock(g, row, col, computeBlockSize()); 
					}
					else{
						//if the location on board contains a block, draw it
						if(board.hasBlock(row, col)){
							paintBlock(g, row, col, computeBlockSize()); 
						}
					}
				}
				//If not within the range of the moving piece, check board only
				else{
					//if the location contains a block, draw it
					if(board.hasBlock(row, col)){
						paintBlock(g, row, col, computeBlockSize()); 
					}
				}
			}
		}
		
	}
	
	/**
	 * Draw the outline of the board.
	 * @param g Graphics object 
	 * @param blockSize the size of the block
	 */
	private void paintBoardOutline(Graphics g, int blockSize) {
		g.drawRect(boardOutlineX, 0, blockSize*board.getNumCols(), blockSize*board.getNumRows());
	}
	
	/**
	 * Draw block at a location.
	 * @param g 
	 * @param row the row 
	 * @param col the column
	 * @param blockSize size of the block
	 */
	private void paintBlock(Graphics g, int row, int col, int blockSize){
		// Create a color for the block
		Color myColor = new Color(51, 255, 255);

		// Set the color
		g.setColor(myColor);
		
		// Compute actual location
		int x = col*blockSize;
		int y = row*blockSize;
		        	
		// Draw the block
		g.fillRect( x + boardOutlineX, y, blockSize, blockSize);
		
		// Draw outline of the block with black
		g.setColor(Color.black);
		g.drawRect(x + boardOutlineX, y, blockSize, blockSize);
	}
	
	/**
	 * Compute the size of the block according to the  
	 * number of rows and columns, and the size of the window.
	 * @return size of a block
	 */
	private int computeBlockSize(){
		//Get size of window
		int width = this.getWidth();
		int height = this.getHeight();
		
		//Pick the smaller between the two ratios - width/columns and height/rows
		int size = Math.min(width/board.getNumCols(), height/board.getNumRows());
		
		//Return the smaller ratio to be the size of a block
		return size;	
	}
	
	/**
	 * Set board to the passed board from controller class.
	 * @param b board
	 */
	public void setBoard(TetrisBoard b){
		this.board = b;
	}
}
