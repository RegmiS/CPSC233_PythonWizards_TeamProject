

/**
 * @author Matt
 *
 */
public class TicTacToe {

	private int[][] board;   //empty int double arry
	private int turn = 0; 	//Turn counter 0 for x, 1 for O
	private int gameSize = 6; // change the size of the board
	
	
	/**TicTacToe
	 * constructor that initializes an empty(-1) array of size gameSize
	 */
	public TicTacToe() {
		
		this.board = new int[gameSize][gameSize];
		
		for(int i = 0;i<gameSize;i++) {
			for(int j=0; j<gameSize; j++) {
				this.board[i][j] = -1;
			}
		}
	}
	
	public int getGameSize() {return gameSize;}
	public int getFlag(int col, int row) {
		return this.board[col][row];
	}
	
	public int getTurn() {return this.turn;}
	
	
	public void setTurn(int count) {
		
		this.turn = count%2;
	}
	
	
	/**setBoard
	 * sets a position in the 
	 * 
	 * @param turn
	 * @param col
	 * @param row
	 */
	public void setBoard(int turn, int col, int row) {

		this.board[col][row]= turn;
		
	}
	
	
	
	/**printBoard
	 * 
	 * prints a text version of the board, -1 empty space, 0 for x, 1 for O
	 */
	public void printBoard() {
		System.out.println("*****************");
		
		for(int i=0; i<gameSize; i++) {
			for(int j=0; j<gameSize; j++) {
				System.out.print(this.board[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * checks the win if the win condition has been met, only works for 3x3!!
	 *  @return true if no one has won, false if some has won
	 */
	public boolean checkWin() {
		for(int i = 0; i<3;i++) {
			if(this.board[i][0] == this.board[i][1] && this.board[i][0] == this.board[i][2] && this.board[i][0] != -1) {
				return false;
			}else if(this.board[0][i] == this.board[1][i] && this.board[0][i] == this.board[2][i] && this.board[0][i] != -1) {
				return false;
			}
		}
		
		if(this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2] && this.board[0][0] != -1) {
			return false;
		}
		if(this.board[2][0] == this.board[1][1] && this.board[2][0] == this.board[0][2] && this.board[2][0] != -1) {
			return false;
		}
		
		return true;
	}
	
	
	
}
