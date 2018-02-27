

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
	 * checks the win if the win condition has been met
	 * for larger board sizes i have decided to make the win condition a 3 in a row anywhere on the board
	 *  @return true if no one has won, false if someone has won
	 */
	public boolean checkWin() {
		
		//Checks Horizontal or Vertical win
		
		for(int i = 0; i<this.gameSize;i++) {
			for(int j = 0; j<this.gameSize - 2;j++) {
				if(this.board[i][j] == this.board[i][j+1] && this.board[i][j] == this.board[i][j+2] && this.board[i][j] != -1) {
					return false;
				}else if(this.board[j][i] == this.board[j+1][i] && this.board[j][i] == this.board[j+2][i] && this.board[j][i] != -1) {
					return false;
				}
			}
		}
		//Checks right/down diagonal win
		for(int i = 0; i < this.gameSize -2;i++) {
			for(int j= 0; j < this.gameSize - 2; j++) {
				if(this.board[i][j] == this.board[i+1][j+1] && this.board[i][j] == this.board[i+2][j+2] && this.board[i][j] != -1) {
					return false;
				}
			}
		}
		//checks left/up diagonal win
		for(int i = this.gameSize-1; i > 1;i--) {
			for(int j = 0; j < this.gameSize-2; j++) {
				if(this.board[i][j] == this.board[i-1][j+1] && this.board[i][j] == this.board[i-2][j+2] && this.board[i][j] != -1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	
}
