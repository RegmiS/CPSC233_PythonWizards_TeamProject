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
	private static ArrayList<int[][]> list = new ArrayList<int[][]>();
	private static Pane pane;
	private static ArrayList<Timeline> timelineList = new ArrayList<Timeline>();
	private Timeline animation;
	
	// Getters and setters for the circle object and list
	public double getX() { return this.circle.getCenterX();	}
	public void setX(double xValue)	{ this.circle.setCenterX(xValue); }
	public void setY(double yValue)	{ this.circle.setCenterY(yValue);	}
	public double getY() { return this.circle.getCenterY(); }
	public void addList(int[][] list) { Enemy.list.add(list); }
	
	public static ArrayList<int[][]> getList()
	{ 
		return list;
	}
	
	public static void setList(ArrayList<int[][]> save) 
	{
		list = save;
	}

	
	public static ArrayList<Timeline> getTimelineList()
	{
		return Enemy.timelineList;
	}
	
	
	
	public static void setPane(Pane pane) { Enemy.pane = pane;	}
	public Circle getCircle() {return this.circle;}
	public int getHealth() {return this.health;}
	
	public void setHealth(int dmg) {
		this.health = this.health - dmg;
	}
	
	public void removeEnemy(boolean hit) 
	{	
		if (hit)
		{
			Base.setHealth(this.damage);
		}
		
		else
			TextGame.setMoney(TextGame.getMoney() + this.points);
		Enemy.pane.getChildren().remove(this.circle);
		Enemy.timelineList.remove(this.animation);
		this.animation.stop();
		Game.getEnemyList().remove(this);
	}
	
	public static void addToList(int[][] a)
	{
		list.add(a);
	}
	
	
	/** Constructor creates a circle for each enemy then animates it
	 * @param type: the type of enemy
	 * @param TILE_SIZE: constant used for the tiles
	 */
	public Enemy(int TILE_SIZE, int health, String filename, int damage, int radius) {
		this.circle = new Circle(radius);
		this.health = health;
		this.damage = damage;
		this.points = damage;
		ImageLoader.setImage(filename, this.circle);
		

		
		Timeline animation = enemyAnimation(TILE_SIZE);
		this.circle.setTranslateX(list.get(0)[0][0] * TILE_SIZE);
		this.circle.setTranslateY(list.get(0)[0][1] * TILE_SIZE);
    	
    	animation.setAutoReverse(false);
    	animation.setOnFinished(e -> this.removeEnemy(true));
    	
    	
    	
    	this.animation = animation;
    	
	}

	public void displayEnemy()
	{
    	pane.getChildren().add(this.circle);
    	timelineList.add(animation);
    	
    	animation.play();
    	Game.getEnemyList().add(this);
    	TextGame.addEnemies();
	}
	
	
	public Timeline enemyAnimation(double TILE_SIZE)
	{
		double TILE_ADJ = TILE_SIZE / 2.0 - this.circle.getRadius();
		Timeline animation = new Timeline();
		KeyFrame initial = new KeyFrame (Duration.ZERO, 
				new KeyValue(this.circle.translateXProperty(), list.get(0)[0][0] * TILE_SIZE ),
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

			}
			if (list.get(i)[0][1] == list.get(i)[1][1])
			{
				moveX = new KeyValue(this.circle.translateXProperty(), list.get(i)[1][0] * TILE_SIZE + TILE_ADJ);
				if (list.get(i) == list.get(list.size() - 1))
					moveX = new KeyValue(this.circle.translateXProperty(), list.get(i)[1][0] * TILE_SIZE - this.circle.getRadius());
			}
			
			KeyFrame frame = new KeyFrame(new Duration(dur), moveX, moveY);
			animation.getKeyFrames().add(frame);
		}
		return animation;

	}


	

	
}
