import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Agent {
	
	
	
	private char mark;				  //Either x or o
	private int[] bmove = new int[1]; //best move for computer to make
	private Game game;
	
	public Queue<int[]> moveList = new LinkedList<>();
	
	
	public void addMoveList(int[] move) {
		this.moveList.add(move);	
	}
	
	public void setBMove(int[] move) {
		this.bmove = move;
	}
	
	public int[] getBMove() {return this.bmove;}
	
	
	public char getMark() {
		return this.mark;
	}
	
	//o hi mark
	public Agent(char mark) {
	
	this.mark = mark;	
		
		
	}

	
	
public int[] maxvalue(Game game, Agent player, Agent opponent) {
		this.game = new Game(game);

		ArrayList<int[]> moves = new ArrayList<>();
		moves = this.game.moves(game.getBoard());
		Queue<int[]> moveQ = new LinkedList<>();
		Queue<Integer> score = new LinkedList<>();
		
		
		for(int i = 0; i < moves.size(); i++) {
			
			moveQ.add(moves.get(i));
		}
		
		
		Game newGame = new Game(this.game);
		int bestscore = -100;
		while(!moveQ.isEmpty()) {
			
			int[] move = moveQ.remove();
			newGame = this.game.neighbour(move,this.mark);
			if(newGame.utility(newGame.getBoard(), this.mark, opponent.getMark() ) == null) {
				minvalue(newGame,opponent, player);
			}else {
				if(newGame.utility(newGame.getBoard(), this.mark, opponent.getMark()) > bestscore){
					bestscore = newGame.utility(newGame.getBoard(), this.mark, opponent.getMark());
					setBMove(move);
				}
			}
			
			
			game.display();
			
		}
		
		
		return getBMove();
		
	}
	
	
	
	
	
	
	
	
	
	public Game minvalue(Game game, Agent player, Agent opponent) {
		

		
	this.game = new Game(game);
		
		ArrayList<int[]> moves = new ArrayList<>();
		moves = this.game.moves(game.getBoard());
		Queue<int[]> moveQ = new LinkedList<>();
		Queue<Integer> score = new LinkedList<>();
		
		
		
		
		for(int i = 0; i < moves.size(); i++) {
			
			moveQ.add(moves.get(i));
		}
		
		
		Game newGame = new Game(this.game);
		int bestscore = -100;
		while(!moveQ.isEmpty()) {
			//Game newGame = new Game(game);
			
			int[] move = moveQ.remove();
			newGame = this.game.neighbour(move,this.mark);
			
			
			if(newGame.utility(newGame.getBoard(), this.mark, opponent.getMark() ) == null) {
				minvalue(newGame,opponent, player);
			}else {
				if(newGame.utility(newGame.getBoard(), this.mark, opponent.getMark()) > bestscore){
					bestscore = newGame.utility(newGame.getBoard(), this.mark, opponent.getMark());
					setBMove(move);
				}
			}
			
			
			game.display();
			
		}
		
		this.game.updateBoard(this.bmove, this.game.getBoard(), this.mark);
		
		
		return this.game;
		
	}
	
	
	
}
