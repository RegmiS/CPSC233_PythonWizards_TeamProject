import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


// Class for our enemy objects
public class Enemy {
	// Instance variables
	private Circle circle;
	private int health;
	private int damage;
	private int points;
	private int radius;
	private static List<int[][]> list;
	private static Pane pane;
	private static ArrayList<Timeline> timelineList = new ArrayList<Timeline>();
	private Timeline animation;
	
	// Getters and setters for the circle object and list
	public double getX() { return this.circle.getCenterX();	}
	public void setX(double xValue)	{ this.circle.setCenterX(xValue); }
	public void setY(double yValue)	{ this.circle.setCenterY(yValue);	}
	public double getY() { return this.circle.getCenterY(); }
	public void addList(int[][] list) { Enemy.list.add(list); }
	
	

	
	public static ArrayList<Timeline> getTimelineList()
	{
		return Enemy.timelineList;
	}
	
	
	
	public static void setPane(Pane pane) { Enemy.pane = pane;	}
	public Circle getCircle() {return this.circle;}
	public int getHealth() {return this.health;}
	
	public void setHealth(int dmg) {
		if(health < 0 ) {
			System.out.println("TEST HEALTH 0");
		}
		
		this.health = this.health - dmg;
	}
	
	public void removeEnemy(boolean hit) 
	{	
		if (hit)
		{
			Base.setHealth(this.damage);
		}
		
		else
			Main.setMoney(Main.getMoney() + this.points);
		Enemy.pane.getChildren().remove(this.circle);
		Enemy.timelineList.remove(this.animation);
		this.animation.stop();
		Game.getEnemyList().remove(this);
	}
	
	// default constructor
	public Enemy()
	{
		Enemy.list = new ArrayList<>();
	}
	/** Constructor creates a circle for each enemy then animates it
	 * @param type: the type of enemy
	 * @param TILE_SIZE: constant used for the tiles
	 */
	public Enemy(int TILE_SIZE, int health, Color tier_color, int damage, int radius) {
		this.circle = new Circle(radius, tier_color);
		this.health = health;
		this.damage = damage;
		this.points = damage;
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("res/images/alien1.png");
			Image image = new Image(inputStream);
			ImagePattern img = new ImagePattern(image);
			this.circle.setFill(img);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		

		
		Timeline animation = enemyAnimation(TILE_SIZE);
		this.circle.setCenterX(list.get(0)[0][0]);
		this.circle.setCenterY(list.get(0)[0][1]);
    	
    	animation.setAutoReverse(false);
    	animation.setOnFinished(e -> this.removeEnemy(true));
    	
    	
    	
    	this.animation = animation;
    	timelineList.add(animation);
	}

	public void displayEnemy()
	{
    	pane.getChildren().add(this.circle);
    	
    	animation.play();
    	Game.getEnemyList().add(this);
    	Main.addEnemies();
	}
	
	public Timeline enemyAnimation(double TILE_SIZE)
	{
		double TILE_ADJ = TILE_SIZE / 2.0 - this.circle.getRadius();
		Timeline animation = new Timeline();
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
			dur += size * 1000;
			KeyValue moveY = new KeyValue(this.circle.translateYProperty(), list.get(i)[0][1] *TILE_SIZE);
			KeyValue moveX = new KeyValue(this.circle.translateXProperty(), list.get(i)[0][0] * TILE_SIZE + TILE_ADJ);
			
			if (list.get(i)[0][0] == list.get(i)[1][0])
			{
				moveY = new KeyValue(this.circle.translateYProperty(), list.get(i)[1][1] * TILE_SIZE);
	//			if (list.get(i) == list.get(list.size() - 1))
	//				moveY = new KeyValue(this.circle.translateXProperty(), list.get(i)[1][0] * TILE_SIZE);
			}
			if (list.get(i)[0][1] == list.get(i)[1][1])
			{
				moveX = new KeyValue(this.circle.translateXProperty(), list.get(i)[1][0] * TILE_SIZE + TILE_ADJ);
				if (list.get(i) == list.get(list.size() - 1))
					moveX = new KeyValue(this.circle.translateXProperty(), list.get(i)[1][0] * TILE_SIZE);
			}
			
			KeyFrame frame = new KeyFrame(new Duration(dur), moveX, moveY);
			animation.getKeyFrames().add(frame);
	//		this.setX(list.get(i)[1][0] * TILE_SIZE + TILE_ADJ);
	//		this.setY(list.get(i)[1][1] * TILE_SIZE);
		}
		return animation;

	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	

	
}
