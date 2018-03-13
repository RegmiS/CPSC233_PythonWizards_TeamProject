import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Tower extends Application {
	
	private Rectangle rectangle;
	//private int rateOfFire = 1;
	private int damage = 1;
	//private int health = 10;
	private int xCoord;
	private int yCoord;
	private int range = 50;
	
	
	public int getDmg() {return this.damage;}
	public int getX() {return this.xCoord;}
	public int getY() {return this.yCoord;}
	
	
	
	private void setXCoord(int x) {
		this.xCoord = x;
	}
	
	private void setYCoord(int y) {
		this.yCoord = y;
	}
	
	public Tower(int xc, int yc,Color color, Pane canvas)  {
		
		setXCoord(xc);
		setYCoord(yc);

		
		Platform.runLater(new Runnable() {
			@Override 
			public void run() { //From https://stackoverflow.com/a/17395191/8645685
				rectangle = new Rectangle(25, 25, color);
				GridPane.setConstraints(rectangle, xc, yc);
				GridPane.setHalignment(rectangle, HPos.CENTER);
				canvas.getChildren().add(rectangle);
			}
		});
				
	}
	
	
	/**Check in range
	 * checks the list until the first enemy is in range, the does dmg to that enemy
	 * @param enemyList list of all the enemies on the board
	 */
	public void checkInRange(ArrayList<Enemy> enemyList) {

		Iterator<Enemy> iter = enemyList.iterator();
		while(iter.hasNext()) {
			Enemy enemy = (Enemy)iter.next();
			
			if(distanceFrom(enemy) < 100) {
				enemy.setHealth(this.damage);
				return;
			}
			
		}
		
	}
	
	
	
	/**
	 * @param enemy, takes in a single enemy and finds the distance from a tower
	 * @return the distance from a tower
	 */
	public double distanceFrom(Enemy enemy) {
		double distance;
		double tx = this.xCoord * 50;
		
		double ty = this.yCoord * 50;
		double ex = enemy.getCircle().getTranslateX();
	
		double ey =  enemy.getCircle().getTranslateY();
		
		double x  = Math.abs(tx -ex);
		
		double y = Math.abs(ey - ty);
		
		distance = Math.sqrt(x*x + y*y);
	
		return distance;
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
