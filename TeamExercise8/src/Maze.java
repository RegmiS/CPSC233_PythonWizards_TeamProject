import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class Maze {

	private String[] grid; 
	                          
	private Queue<int[]> q = new LinkedList<int[]>();
	private int[] Loc;
	Stack<int[]> parents = new Stack<>();
	
	public void setLoc(int[] loc) {this.Loc = loc;}
	public void setGrid(String[] grid) {this.grid = grid.clone();}
	public void addQ(int[] a) {this.q.add(a);}
	
	public void addParents(int[] p) {this.parents.add(p);}
	
	public String[] getGrid(){return this.grid;}
	public int[] getLoc() {return this.Loc;}
	public Queue<int[]> getQ(){return this.q;}
	public Stack<int[]> getParents(){return this.parents;}
	
	
	
	
	
	
	/**Initializes a constructor, 
	 * 
	 * @param grid - a maze
	 * @param loc - location of the agent
	 */
	
	public Maze(String[] grid, int[] loc) {
		setGrid(grid);
		setLoc(loc);
	}

	
	
	
	
	
	/**Finds a list(Queue) of moves that the agent can move, and returns the list.
	 * 
	 * 
	 * @param loc-the location of the agent 
	 * @return returns a list of possible moves the agent can make, no back tracking
	 */
	
	public Queue<int[]> moves(int[] loc){
	
		setLoc(loc);
		//Maze agent can only move to adjacent squares
		//above spot
		if(this.grid[this.Loc[0]].charAt(this.Loc[1] + 1)!='X'&&this.grid[this.Loc[0]].charAt(this.Loc[1] + 1)!='*') {
			int[] p = {this.Loc[0], this.Loc[1] +1};
			addQ(p);
		}
		//below Spot
		if(this.grid[this.Loc[0]].charAt(this.Loc[1] - 1)!='X'&&this.grid[this.Loc[0] ].charAt(this.Loc[1] - 1)!='*') {
			int[] p = {this.Loc[0] , this.Loc[1] -1};
			addQ(p);
		}
		//right of spot
		if(this.grid[this.Loc[0] +1].charAt(this.Loc[1] )!='X'&&this.grid[this.Loc[0] +1].charAt(this.Loc[1] )!='*') {
			int[] p = {this.Loc[0] +1, this.Loc[1] };
			addQ(p);
		}
		//left of spot
		if(this.grid[this.Loc[0] -1].charAt(this.Loc[1])!='X'&&this.grid[this.Loc[0] -1].charAt(this.Loc[1] )!='*') {
			int[] p = {this.Loc[0] -1, this.Loc[1] };
			addQ(p);
		}
		
		
		return getQ();
	}
	
	
	//Displays the maze
	
	public void display() {
		for(int i = 0; i < this.grid.length;i++) {
			System.out.println(this.grid[i]);
		}
	}
	
	
	
	
	
	/**Changes the agents position to a '*', basically a boolean for vistied or not
	 * 
	 * 
	 * @param loc - agents current position
	 */
	public void neighbor(int[] loc) {
		setLoc(loc);
		
		String line = this.grid[loc[0]];
		String newline = line.substring(0,loc[1]) + '*'+line.substring(loc[1]+1); //* means visited
		this.grid[loc[0]] = newline;
		
	}
	
	
	
}
