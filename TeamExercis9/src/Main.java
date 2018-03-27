import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		char[][] grid = {{'-','X','O'}, {'O','X','-'}, {'-','O','-'}};
		ArrayList<int[]> moves = new ArrayList<>();
		
		Game game = new Game(grid);
		
		game.display();
		
		Scanner scan = new Scanner(System.in);
		
		Agent maxplayer = new Agent('O');
		Agent minplayer = new Agent('X');
		
			
			//plays the game
		int count = 0;	
		while(!game.checkWin(grid,maxplayer.getMark()) || !game.checkWin(grid,minplayer.getMark()) || !game.checkTie(grid)) {	
			
			
			//Human Player, assumes you play by the rules
			if(count %2 == 0) {
				System.out.println("Col - ");
				int col = scan.nextInt();
				System.out.println("Row - ");
				int row = scan.nextInt();
				grid[row][col]= 'X';
				
				
			}
			else {
				
			game.updateBoard(maxplayer.maxvalue(game, maxplayer, minplayer), game.getBoard(), maxplayer.getMark());
			
			
			}
			
			
			
			System.out.println();
			game.display();
			count++;	
		}
		
	scan.close();	
	
	}

}
