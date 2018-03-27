import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		
		String[] grid  = {"XXXXXXXXXXXXXXXXXXXX",
                		  "X     X    X       X",
                		  "X XXXXX XXXX XXX XXX",
                		  "X       X      X X X",
                		  "X X XXX XXXXXX X X X",
                		  "X X   X        X X X",
                		  "X XXX XXXXXX XXXXX X",
                		  "X XXX    X X X     X",
                		  "X    XXX       XXXXX",
                		  "XXXXX   XXXXXX     X",
                		  "X   XXX X X    X X X",
                		  "XXX XXX X X XXXX X X",
                		  "X     X X   XX X X X",
                		  "XXXXX     XXXX X XXX",
                		  "X     X XXX    X   X",
                		  "X XXXXX X XXXX XXX X",
                		  "X X     X  X X     X",
                		  "X X XXXXXX X XXXXX X",
                		  "X X                X",
						  "XXXXXXXXXXXXXXXXXX X"};
		
		
		
		int[] end = {19,18};
		int[]start = {1,1};
		Maze nmaze = new Maze(grid, start);
		Maze maze = new Maze(grid, start);	//1,1 is start location
		Maze goal = new Maze(grid, end);	//19,18 exit
		int[] move = new int[1];
		
		Agent agent = new Agent();
		
		//agent.bfs(maze, goal);
		//Maze nmaze = new Maze(grid, start);
		ArrayList<int[]> path = agent.bfs(maze, goal);
		//System.out.println(path.toString());
		System.out.println(path.size());
		for(int i = 0; i < path.size(); i ++){
			System.out.println(Arrays.toString(path.get(i)));
			nmaze.neighbor(path.get(i));
			nmaze.display();
			Thread.sleep(500);
		}
}
	
	
	
	
	
	

}
