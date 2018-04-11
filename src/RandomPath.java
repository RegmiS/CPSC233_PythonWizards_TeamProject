import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RandomPath {
	private static int baseRow = TextGame.getBaseRow();;
	private static int baseCol = TextGame.getBaseCol();;
	private static int finalCol = baseCol - 4;
	private static int startingRow;
	private static int currentCol = 0;
	private static int currentRow;
	private static String[] moveV = {"up", "down"};;
	private static String nextVmove;
	private static String right = "right";
	private static int randLength;
	private static int turn = 1;
//	private int count = 0;

	Random rand = new Random();

		public RandomPath() {
			
//			baseRow = TextGame.getBaseRow();
//			baseCol = TextGame.getBaseCol();
			
			//Draws the first part of the path
			//Always starts at (randRow, 0) and moves right 
			startingRow = rand.nextInt((11+1)-1)+1; //Pick a random starting row between 1 and 11
			currentRow = startingRow;
			moveRight(startingRow, currentCol, randomLength(right)); //First right move
			
//			count++;
//			System.out.println("Count: " + count + " Current Row: " + currentRow + " Current Col: " + currentCol);
			
			while(currentRow != baseRow || currentCol != finalCol) { //Row != 5, Column != 21
				if (turn == 1) {
				nextVmove = moveV[randomDirection()]; //Pick up|down
				randLength = randomLength(nextVmove); //Pick a random length 
				if (nextVmove.equals("up") && currentRow - (randLength) >= 1) { //OoB check
					moveUp(currentRow, currentCol, randLength);
//					count++;
//					System.out.println("Count: " + count + " Moving up" + " Current Row: " + currentRow + " Current Col: " + currentCol);
					turn = 2;
				}else if (nextVmove.equals("down") && currentRow + (randLength) <= 11) {
					moveDown(currentRow, currentCol, randLength);
//					count++;
//					System.out.println("Count: " + count + " Moving down" + " Current Row: " + currentRow + " Current Col: " + currentCol);
					turn = 2;
				}
			}else if (turn == 2) {
				randLength = randomLength(right);
				if (currentCol + randLength < finalCol) {//Move right with random length if move is not going to go past Column 21
					moveRight(currentRow, currentCol, randLength);
//					count++;
//					System.out.println("Count: " + count + " Moving right" + " Current Row: " + currentRow + " Current Col: " + currentCol);
					turn = 1;
				}else if (currentRow > baseRow) {//Move path up to baseRow and right 2 units
					moveRight(currentRow, currentCol, 2);
					moveUp(currentRow, currentCol, (currentRow - baseRow));
//					count++;
//					System.out.println("Count: " + count + " Moving right & up" + " Current Row: " + currentRow + " Current Col: " + currentCol);
					break;
				}else if (currentRow < baseRow) {//Move path down to baseRow and right 2 units
					moveRight(currentRow, currentCol, 2);
					moveDown(currentRow, currentCol, (baseRow - currentRow));
//					count++;
//					System.out.println("Count: " + count + " Moving right & down" + " Current Row: " + currentRow + " Current Col: " + currentCol);
					break;
				}else { //If currentRow = 5 move right as many units as needed to get to column 21
					moveRight(currentRow, currentCol, (finalCol - currentCol));
//					count++;
//					System.out.println("Count: " + count + " Moving right (To end)" + " Current Row: " + currentRow + " Current Col: " + currentCol);
					break;
				}
			}
		}
//			System.out.println("Count: " + count + "2nd last" + " Current Row: " + currentRow + " Current Col: " + currentCol);
			
			//Finish path from column 21 to 24(base column poition
			enemyPath(right, currentRow, currentCol, (baseCol-currentCol));

//			System.out.println("Count: " + count + " Moving right (Final)" + " Current Row: " + currentRow + " Current Col: " + currentCol);
//			System.out.println("done");
			
	
}
			
			public void moveUp(int row, int col, int length) {
				enemyPath("up", row, col, length);
				RandomPath.setCurrentRow(RandomPath.getCurrentRow() - (length));
				
			}
			public void moveDown(int row, int col, int length) {
				enemyPath("down", row, col, length);
				RandomPath.setCurrentRow(RandomPath.getCurrentRow() + (length));
			}
			public void moveLeft(int row, int col, int length) {
				enemyPath("left", row, col, length);
				RandomPath.setCurrentCol(RandomPath.getCurrentCol() - (length));
			}
			public void moveRight(int row, int col, int length) {
				enemyPath("right", row, col, length);
				RandomPath.setCurrentCol(RandomPath.getCurrentCol() + (length));
			}
		
			public int randomDirection() {
				return rand.nextInt(2);
			}
			
			public int randomLength(String direction) {
				if (direction.equals("right") || direction.equals("left") ) {
					return rand.nextInt((4+1)-2)+2;
				}
				else if (direction.equals("up") || direction.equals("down")) {
					return rand.nextInt((7+1)-2)+2;
				}
				else {
					System.out.println("Invalid direction");
					return 0;
				}
			}
			
			public static void enemyPath(String direction, int row, int col, int length) {

				if (direction == "right") {
					int[] start = {col, row};
					int[] end = {col + length, row};
					int[][] temp = new int[][] {start, end};
					Enemy.addToList(temp);
					
					for (int i = col; i < (length + col); i++) {
						Rectangle enemypath = new Rectangle(Game.getTileSize(), Game.getTileSize());
						enemypath.setFill(Color.BROWN);
						GridPane.setRowIndex(enemypath, row);
						GridPane.setColumnIndex(enemypath, i);
						Game.getGridpane().getChildren().addAll(enemypath);
						Game.getTextgame().editGridPath(row, i, " ");
					}
				}
				if (direction == "left") {
					
					int[] start = {col, row};
					int[] end = {col - length, row};
					int[][] temp = new int[][] {start, end};
					Enemy.addToList(temp);
					
					for (int i = col; i > (col - length); i--) {
						Rectangle enemypath = new Rectangle(Game.getTileSize(), Game.getTileSize());
						enemypath.setFill(Color.BROWN);
						GridPane.setRowIndex(enemypath, row);
						GridPane.setColumnIndex(enemypath, i);
						Game.getGridpane().getChildren().addAll(enemypath);
						Game.getTextgame().editGridPath(row, i, " ");
					}
				}
				if (direction == "up") {
					
					int[] start = {col, row};
					int[] end = {col, row - length};
					int[][] temp = new int[][] {start, end};
					Enemy.addToList(temp);
					
					for (int i = row; i > (row - length); i--) {
						Rectangle enemypath = new Rectangle(Game.getTileSize(), Game.getTileSize());
						enemypath.setFill(Color.BROWN);
						GridPane.setRowIndex(enemypath, i);
						GridPane.setColumnIndex(enemypath, col);
						Game.getGridpane().getChildren().addAll(enemypath);
						Game.getTextgame().editGridPath(i, col, " ");

					}
				}
				
				if (direction == "down") {
					
					int[] start = {col, row};
					int[] end = {col, row + length};
					int[][] temp = new int[][] {start, end};
					Enemy.addToList(temp);
					
					for (int i = row; i < (length + row); i++) {
						Rectangle enemypath = new Rectangle(Game.getTileSize(), Game.getTileSize());
						enemypath.setFill(Color.BROWN);
						GridPane.setRowIndex(enemypath, i);
						GridPane.setColumnIndex(enemypath, col);
						Game.getGridpane().getChildren().addAll(enemypath);
						Game.getTextgame().editGridPath(i, col, " ");
					}
				}
			}

			public static int getCurrentCol() {
				return currentCol;
			}

			public static void setCurrentCol(int currentCol) {
				RandomPath.currentCol = currentCol;
			}

			public static int getCurrentRow() {
				return currentRow;
			}

			public static void setCurrentRow(int currentRow) {
				RandomPath.currentRow = currentRow;
			}
	}


//Ignore
//enemyPath("right", 2, 0, 5, reference);
//enemyPath("down", 2, 5, 5, reference);
//enemyPath("left", 7, 5, 3, reference);
//enemyPath("down", 7, 2, 3, reference);
//enemyPath("right", 10, 2, 10, reference);
//enemyPath("up", 10, 12, 5, reference);
//enemyPath("right", 5, 12, 12, reference);
