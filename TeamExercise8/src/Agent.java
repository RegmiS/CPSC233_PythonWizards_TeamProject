import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Agent {
	private int[] move =  new int[1];
	private int[] goal;
	private String[] grid;
	
	public Agent() {
		
	}
	
	public ArrayList<int[]> bfs(Maze start, Maze goal) {
		
		this.move = start.getLoc();
		this.goal = goal.getLoc();
		this.grid = start.getGrid();
		int[] loc =  new int[1];
		
		
		
		//Queue<Maze> q = new LinkedList<>();
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> ph = new LinkedList<>();
		
		
		ArrayList<int[]> path = new ArrayList<>();
		Queue<ArrayList<int[]>> pathQ = new LinkedList<ArrayList<int[]>>();
		//Maze maze = new Maze();
		
		
		path.add(this.move);
		pathQ.add(path);
		start.display();
		
		//q.add(start);
		q.add(this.move);
		
		
		int count = 0;
		while(!q.isEmpty() ){
			//this.move = moveq.remove(); 
			loc = q.remove();
			path = pathQ.remove();
			start.neighbor(loc);
			/*for(int i = 0; i < path.size(); i ++){
				System.out.println(Arrays.toString(path.get(i)));
			}*/
			
			//if the spot is at the end of the maze exits while loop
			if(loc[0] == this.goal[0] && loc[1] == this.goal[1]) {
				System.out.println("Path Found");
				start.display();
				
				return path;
			}
			
			ph = start.moves(loc);
			while(!ph.isEmpty()) {
				int[] moves = ph.remove();
				ArrayList<int[]> pathPH = new ArrayList<int[]>(path);
				
				
				
				pathPH.add(moves);//adds child to path
				
				pathQ.add(pathPH);// adds new path to queue
				q.add(moves);//adds new postions to queue
			}
			
			
			
			//start.display();
			//System.out.println("*******************");
			count++;
		}
		
		
		
		return path;
	}
	
	
}
