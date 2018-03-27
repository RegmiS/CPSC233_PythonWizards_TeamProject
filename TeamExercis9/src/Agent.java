import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Agent {
	
	
	
	private char mark;
	
	
	
	public char getMark() {
		return this.mark;
	}
	
	//o hi mark
	public Agent(char mark) {
	
	this.mark = mark;	
		
		
	}

	
	
	public void maxvalue(Game game, Agent player, Agent opponent) {
		
		ArrayList<int[]> moves = new ArrayList<>();
		moves = game.moves(game.getBoard());
		Queue<int[]> moveQ = new LinkedList<>();
		Queue<Integer> score = new LinkedList<>();
		
		
		
		
		for(int i = 0; i < moves.size(); i++) {
			
			System.out.println(moves.get(i));
			moveQ.add(moves.get(i));
		}
		
		
		
		while(!moveQ.isEmpty()) {
			Game newGame = new Game(game);
			System.out.println("test");
			int[] move = moveQ.remove();
			newGame.neighbour(move,this.mark);
			if(newGame.utility(newGame.getBoard(), this.mark, opponent.getMark() ) == 100) {
				minvalue(newGame,opponent, player);
			}else {
				score.add(newGame.utility(newGame.getBoard(), this.mark, opponent.getMark()));  //will be 10 for win 0 for tie, -10 for loss
			}
			
			
			game.display();
			
		}
		
		
		
	}
	
	
	public void minvalue(Game game, Agent player, Agent opponent) {
		
		ArrayList<int[]> moves = new ArrayList<>();
		moves = game.moves(game.getBoard());
		Queue<int[]> moveQ = new LinkedList<>();
		Queue<Integer> score = new LinkedList<>();
		
		
		
		
		for(int i = 0; i < moves.size(); i++) {
			
			System.out.println(moves.get(i));
			moveQ.add(moves.get(i));
		}
		
		
		
		while(!moveQ.isEmpty()) {
			Game newGame = new Game(game);
			int[] move = moveQ.remove();
			newGame.neighbour(move,this.mark);
			if(newGame.utility(newGame.getBoard(), this.mark, opponent.getMark() ) == 100) {
				maxvalue(newGame,opponent, player);
			}else {
				score.add(newGame.utility(newGame.getBoard(), this.mark, opponent.getMark()));  //will be 10 for win 0 for tie, -10 for loss
			}
			
			
			game.display();
			
		}
		
		
	}
	
	
	
}
