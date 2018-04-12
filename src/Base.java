import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



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
	
	
	
	/**Intializes a base at certain coordinates
	 * 
	 * @param gridpane, grid pane the game is on
	 */
	public Base(GridPane gridpane) {
		Rectangle base = new Rectangle(50, 50);
		ImageLoader.setImage("uofclogo.png", base);
		GridPane.setConstraints(base, Xcoord, Ycoord);
		gridpane.getChildren().add(base);
		
	}

	
	
	
}