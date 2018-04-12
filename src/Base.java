import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;



public class Base {

	private static int health = TextGame.getHealth(); //Health of base
	private int Xcoord = TextGame.getBaseCol();; // coordinates of base
	private int Ycoord = TextGame.getBaseRow();;
	
	public static int getHealth() {return health;}
	public int getX() {return this.Xcoord;}
	public int getY() {return this.Ycoord;}
	
	/**When an enemy hits the base, the enemies damage is subtracted from the total health
	 * 
	 * @param dmg, the dmg from an enemy, 
	 */
	public static void setHealth(int dmg) { 
		if (health - dmg >= 0) {
			health = health-dmg;
			TextGame.setHealth(TextGame.getHealth()-dmg);
			}
		}
	public void setX(int x) { this.Xcoord = x;}
	public void setY(int y) { this.Ycoord = y;}
	
	
	
	/**Initializes a base at certain coordinates
	 * 
	 * @param gridpane, grid pane the game is on
	 */
	public Base(GridPane gridpane) {
		Rectangle base = new Rectangle(50, 50);
		ImageLoader.setImage("uofclogo.png", base);
		GridPane.setConstraints(base, Xcoord, Ycoord);
		gridpane.getChildren().add(base);
		
	}

	
	
	public static Pane gameOver()
	{
		Pane endPane = new Pane();
		endPane.toFront();
		
		Label gameOver = new Label("Game Over! U of C was destroyed!");
		gameOver.setFont(new Font("Arial", 60));
		gameOver.setTextFill(Color.WHITE);
		gameOver.setLayoutX(0);
		gameOver.setLayoutY(200);
		gameOver.setAlignment(Pos.CENTER);
		
		Label round = getRound();
		Label highscore = getHighscore();
	
		endPane.setLayoutX(0);
		endPane.setLayoutY(0);
		endPane.getChildren().addAll(gameOver, round, highscore);
		return endPane;
	}
	
	public static Pane winGame()
	{
		Pane winPane = new Pane();
		winPane.toFront();
		
		Label win = new Label("Congrats! You win!");
		win.setFont(new Font("Arial", 60));
		win.setTextFill(Color.WHITE);
		win.setLayoutX(0);
		win.setLayoutY(200);
		win.setAlignment(Pos.CENTER);
		
		Leveling.increaseCurrentLevel();
		
		Label round = getRound();
		Label highscore = getHighscore();
		
		winPane.setLayoutX(0);
		winPane.setLayoutY(0);
		winPane.getChildren().addAll(win, round, highscore);
		
		return winPane;
	}
	
	public static Label getRound()
	{
		Label round = new Label("You passed " + String.valueOf(Leveling.returnCurrentLevel() - 1) + " rounds!" );
		round.setFont(new Font("Arial", 40));
		round.setTextFill(Color.WHITE);
		round.setLayoutX(0);
		round.setLayoutY(400);
		round.setAlignment(Pos.CENTER);
		return round;
	}
	
	public static Label getHighscore()
	{
		Label highscore = new Label("Highscore: "); // for future use
		highscore.setFont(new Font("Arial", 40));
		highscore.setTextFill(Color.WHITE);
		highscore.setLayoutX(0);
		highscore.setLayoutY(350);
		highscore.setAlignment(Pos.CENTER);
		return highscore;
		
	}
	
}