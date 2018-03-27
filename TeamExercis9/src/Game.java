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
		setBoard(g.getBoard());
	}
	
	
	public void display() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length;j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public Integer utility(char[][] b, char player, char oppenent) {
		
		
		//Return the minimax utility value of this game:
        //1 = X win, -1 = O win, 0 = tie, None = not over yet."""
		
		if(checkWin(b,player)) {
			return 10;
		} else if(checkWin(b,oppenent)) {
			return -10;
		} else if(checkTie(b)) {
			return 0;
		}
		
		return 100;
	}

	
	
	/**
	 * 
	 * @param b
	 * @return
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
	
	
	
	public char[][] neighbour(int[] move, char mark) {
		
		char[][] nGrid = getBoard();
		nGrid[move[0]][move[1]] = mark;
		
		return nGrid;

	}
	
	
	
	
	
}
