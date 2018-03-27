import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Game {

	private char[][] board = new char[2][2]; //tictactoe is 2 by 2
	
	
	public void setBoard(char[][] board) {this.board = board.clone();}
	public char[][] getBoard() {return this.board;}
	
	public Game(char[][] board) {
		setBoard(board);
		
	}

	public Game(Game g) {
		this.board = g.getBoard().clone();
	}
	
	
	public void display() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length;j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Finds the score for the move
	 * 
	 * 
	 * @param b board
	 * @param player MaxPlayer
	 * @param oppenent MinPlayer
	 * @return 10 for a win, 0 for a tie, -10 for a loss, and null if no of those
	 */
	
	
	public Integer utility(char[][] b, char player, char oppenent) {
		
		
		if(checkWin(b,player)) {
			return 10;
		} else if(checkWin(b,oppenent)) {
			return -10;
		} else if(checkTie(b)) {
			return 0;
		}
		
		return null;
	}

	
	
	/**takes the board state and return thes possible moves
	 * 
	 * @param b board
	 * @return a list of possible moves
	 */
	
	
	
	
	public ArrayList<int[]> moves(char[][] b){
		ArrayList<int[]> movs = new ArrayList<>();
		
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				if(b[i][j] == '-') {
					int[] move = {i,j};
					movs.add(move);
				}
				
			}
		}
		
		
		return movs;
		
	}
	
	
	/**
	 * checks if all the spaces on the board are full, 
	 * 
	 * @param b board 
	 * @return a boolean, true if it is a tie, flase if not
	 */
	
	public boolean checkTie(char[][] b) {
		boolean tie = true;
		
		for(int i =0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(b[i][j] == '-') {
					tie = false;
				}
				
			}
		}
		
		
		return tie;
	}
	
	
	/**Checks all possible win conditions in tictactoe, returns if someone won
	 * 
	 * 
	 * 
	 * @param b board
	 * @param player - char either x or o
	 * @return boolean, false if no one has won, true if player has one
	 */
	
	public boolean checkWin(char[][] b, char player ) {
		boolean winner = false;

		//horizontal and vertical wins
		for(int i = 0; i < 3; i++) {	
			if(b[i][0] == player && b[i][1] == player && b[i][2] == player ) {
				winner = true;
			}
			
		}
		for(int i = 0; i < 3; i++) {	
			if(b[0][i] == player && b[1][i] == player && b[2][i] == player ) {
				winner = true;
			}
			
		}
		
		//diagonal wins
		if(b[0][0] == player && b[1][1] == player && b[2][2] == player ) {
			winner = true;
		}
		if(b[0][2] == player && b[1][1] == player && b[2][0] == player)  {
			winner = true;
		}
		
		
		return winner;
	}
	/**
	 * 
	 * This doesnt work, fix it
	 * 
	 * @param move - where the player is moving
	 * @param mark - x or o
	 * @return a new game board
	 */
	
	
	public Game neighbour(int[] move, char mark) {
		char[][] board = updateBoard(move, this.board, mark);
		
		Game game =  new Game(board);
		
		return game;

	}
	
	public char[][] updateBoard(int[] move, char[][] board, char mark){
		char[][] nboard = board.clone();
		nboard[move[0]][move[1]] = mark;
		return nboard;
		
	}
	
	
	
}
