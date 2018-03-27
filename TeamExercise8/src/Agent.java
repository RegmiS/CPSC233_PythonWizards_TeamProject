import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Agent {
	private int[] move =  new int[1];
	private int[] goal;
	private String[] grid;
	
	
	/**Constructor, doesn't do anything
	 * 
	 */
	public Agent() {
		
	}
	
	
	/**Uses BFS to find the shortest path, and returns the path
	 * 
	 * 
	 * 
	 * @param  Maze object start where the agent starts
	 * @param Maze object goal where the agent wants to end up
	 * @return The shortest path found through BFS
	 */
	
	public ArrayList<int[]> bfs(Maze start, Maze goal) {
		
		this.move = start.getLoc(); //move updates throughout, starts at the starting position
		this.goal = goal.getLoc(); // position where the agent wants to find
		this.grid = start.getGrid(); // original grid
		int[] loc =  new int[1];     //int array, used to get coordinates when the agent moves
		
		
		
		//Queue<Maze> q = new LinkedList<>();
		Queue<int[]> q = new LinkedList<>(); //a queue of moves that the agent can make
		Queue<int[]> ph = new LinkedList<>(); // placeholder queue, used to take a list of moves and transfer them to a new queue
		
		
		ArrayList<int[]> path = new ArrayList<>();                         //a list of coordinates the agent has moved from the start
		Queue<ArrayList<int[]>> pathQ = new LinkedList<ArrayList<int[]>>(); // a queue of the list of moves the agent has moved
		//Maze maze = new Maze();
		
		
		path.add(this.move);
		pathQ.add(path);
		start.display();
		
		//q.add(start);
		q.add(this.move);
		
		
		
		while(!q.isEmpty() ){
			 
			loc = q.remove();
			path = pathQ.remove();
			start.neighbor(loc);
	
			
			//if the spot is at the end of the maze exits while loop
			if(loc[0] == this.goal[0] && loc[1] == this.goal[1]) {
				System.out.println("Path Found");
				start.display();
				
				return path;
			}
			
			//gets a list of moves the agent can move from his current position
			ph = start.moves(loc);
			while(!ph.isEmpty()) {
				int[] moves = ph.remove();
				ArrayList<int[]> pathPH = new ArrayList<int[]>(path);
				
				
				
				pathPH.add(moves);//adds child to path
				
				pathQ.add(pathPH);// adds new path to queue
				q.add(moves);//adds new positions to queue
			}
			
			
		}
		
		
		
		return path;
	}
	
	
}
