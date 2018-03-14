//package Application;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyValue;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.application.Application;


// Class for our enemy objects
public class Enemy extends Application {
	// Instance variables
	private Circle circle;
	private int health = 150;
	private int damage = 30;
//	private int type;
	private int radius = 5;
	private static List<int[][]> list;
	private static Enemy reference;
	private Pane pane;
	private static ArrayList<Timeline> timelineList = new ArrayList<Timeline>();
	private Timeline animation;
	
	// Getters and setters for the circle object and list
	public double getX() { return this.circle.getCenterX();	}
	public void setX(double xValue)	{ this.circle.setCenterX(xValue); }
	public void setY(double yValue)	{ this.circle.setCenterY(yValue);	}
	public double getY() { return this.circle.getCenterY(); }
	public void addList(int[][] list) { this.list.add(list); }
	
	public static ArrayList<Timeline> getTimelineList()
	{
		return Enemy.timelineList;
	}
	
	public void setReference(Enemy ref) { Enemy.reference = ref; }
	
	
	public void setPane(Pane pane) { this.pane = pane;	}
	public Circle getCircle() {return this.circle;}
	public int getHealth() {return this.health;}
	
	public void setHealth(int dmg) {
		if(health < 0 ) {
			System.out.println("TEST HEALTH 0");
		}
		
		this.health = this.health - dmg;
	}
	
	
	public void removeEnemy() 
	{	
		this.pane.getChildren().remove(this.circle);
		Enemy.timelineList.remove(this.animation);
	}
	
	// default constructor
	public Enemy()
	{
		reference = this;
		Enemy.list = new ArrayList<>();
	}
	/** Constructor creates a circle for each enemy then animates it
	 * @param type: the type of enemy (1-3)
	 * @param pane: the pane to be used where the circle is animated
	 * @param reference: enemy object with the list used to make the path for the animation
	 * @param TILE_SIZE: constant used for the tiles
	 */
	public Enemy(int type, Pane pane, double TILE_SIZE) {
		double TILE_ADJ = TILE_SIZE / 2.0;
		this.pane = pane;
		
		if (type == 1)
    		this.circle = new Circle(radius, Color.RED);
		else if (type == 2) {
    		this.circle = new Circle(radius, Color.BLUE);
    		this.health = this.health * 2;
    		this.damage = this.damage * 2;
    	} 
    	else if (type == 3) {
    		this.circle = new Circle(radius, Color.GREEN);
    		this.health = this.health * 4;
    		this.damage = this.damage * 4;
    	}
		
		Timeline animation = new Timeline();
//		this.circle.setCenterX(list.get(0)[0][0]);
//		this.circle.setCenterY(list.get(0)[0][1]);
    	pane.getChildren().add(this.circle);
    	animation.setAutoReverse(false);
    	animation.setOnFinished(e -> this.removeEnemy());
    	
    	KeyFrame initial = new KeyFrame (Duration.ZERO, 
    			new KeyValue(this.circle.translateXProperty(), list.get(0)[0][0] * TILE_SIZE),
                new KeyValue(this.circle.translateYProperty(), list.get(0)[0][1] * TILE_SIZE));
    	animation.getKeyFrames().addAll(initial);
    	int size = 0;
    	int dur = 0;
    	for (int i = 0; i < list.size(); i++) 
    	{
    		size = Math.max(Math.abs(list.get(i)[1][0] - list.get(i)[0][0]),
    				Math.abs(list.get(i)[1][1] - list.get(i)[0][1] ));
    		dur += size * 500;
    		KeyValue moveY = new KeyValue(this.circle.translateYProperty(), list.get(i)[0][1] *TILE_SIZE);
    		KeyValue moveX = new KeyValue(this.circle.translateXProperty(), list.get(i)[0][0] * TILE_SIZE + TILE_ADJ);
    		
    		if (list.get(i)[0][0] == list.get(i)[1][0])
    			moveY = new KeyValue(this.circle.translateYProperty(), list.get(i)[1][1] * TILE_SIZE);
    		if (list.get(i)[0][1] == list.get(i)[1][1])
    			moveX = new KeyValue(this.circle.translateXProperty(), list.get(i)[1][0] * TILE_SIZE + TILE_ADJ);
    		
    		KeyFrame frame = new KeyFrame(new Duration(dur), moveX, moveY);
    		animation.getKeyFrames().add(frame);
    		this.setX(list.get(i)[1][0] * TILE_SIZE + TILE_ADJ);
    		this.setY(list.get(i)[1][1] * TILE_SIZE);
    	}
    	this.animation = animation;
    	timelineList.add(animation);
    	animation.play();
	}

	
	
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
