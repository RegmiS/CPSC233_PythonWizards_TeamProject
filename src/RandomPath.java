import java.util.Random;

public class RandomPath {
	private static int baseRow = 5;
	private static int baseCol = 24;
	private static int finalCol = baseCol - 4;
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
			
			//Draws the first part of the path
			//Always starts at (randRow, 0) and moves right 
			currentRow = rand.nextInt((11+1)-1)+1; //Pick a random starting row between 1 and 11
			moveRight(currentRow, currentCol, randomLength(right)); //First right move
			
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
			GridVersionGame.enemyPath(right, currentRow, currentCol, (baseCol-currentCol), GridVersionGame.getReference());

//			System.out.println("Count: " + count + " Moving right (Final)" + " Current Row: " + currentRow + " Current Col: " + currentCol);
//			System.out.println("done");
			
	
}
			
			public void moveUp(int row, int col, int length) {
				GridVersionGame.enemyPath("up", row, col, length, GridVersionGame.getReference());
				RandomPath.setCurrentRow(RandomPath.getCurrentRow() - (length));
				
			}
			public void moveDown(int row, int col, int length) {
				GridVersionGame.enemyPath("down", row, col, length, GridVersionGame.getReference());
				RandomPath.setCurrentRow(RandomPath.getCurrentRow() + (length));
			}
			public void moveLeft(int row, int col, int length) {
				GridVersionGame.enemyPath("left", row, col, length, GridVersionGame.getReference());
				RandomPath.setCurrentCol(RandomPath.getCurrentCol() - (length));
			}
			public void moveRight(int row, int col, int length) {
				GridVersionGame.enemyPath("right", row, col, length, GridVersionGame.getReference());
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
					return rand.nextInt((3+1)-2)+2;
				}
				else {
					System.out.println("Invalid direction");
					return 0;
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
