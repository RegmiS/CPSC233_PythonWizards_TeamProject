//package Application;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyValue;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;

import javafx.application.Application;


// Class for our enemy objects
public class Enemy extends Application {
	// Instance variables
	private Circle circle;
	private int health = 50;
	private int damage = 30;
	private int type;
	private int radius = 5;
	private List<int[][]> list;
	private Enemy reference;
	
	
	public double getX()
	{
		return this.circle.getCenterX();
	}
	
	public void setX(double xValue)
	{
		this.circle.setCenterX(xValue);
	}
	
	public void setY(double yValue)
	{
		this.circle.setCenterY(yValue);
	}

	public double getY()
	{
		return this.circle.getCenterY();
	}
	
	
	public void addList(int[][] list)
	{
		
		this.list.add(list);
	}
	
	public Enemy()
	{
		reference = this;
		this.list = new ArrayList<>();
	}
	
    // Constructor creates a circle for each enemy created and begins to animate it
	public Enemy(int type, Pane pane, Enemy reference, double TILE_SIZE) {
		double TILE_ADJ = TILE_SIZE / 2.0;
	
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
		
		
//    	this.circle.setCenterX(reference.list.get(0)[0][0] * TILE_SIZE);
//    	this.circle.setCenterY(reference.list.get(0)[0][1] * TILE_SIZE);
    	pane.getChildren().add(this.circle);
    	Timeline timeline = new Timeline();
    	timeline.setCycleCount(Timeline.INDEFINITE);
    	timeline.setOnFinished(e -> timeline.stop());
    	timeline.setAutoReverse(false);
    	int dur = 3000;
    	list = reference.list;
    	
    	KeyFrame initial = new KeyFrame (Duration.ZERO, 
    			new KeyValue(this.circle.translateXProperty(), reference.list.get(0)[0][0] * TILE_SIZE),
                new KeyValue(this.circle.translateYProperty(), reference.list.get(0)[0][1] * TILE_SIZE));
    	timeline.getKeyFrames().add(initial);
    	for (int i = 0; i < reference.list.size(); i++) 
    	{
    		KeyValue moveY = new KeyValue(this.circle.translateYProperty(), reference.list.get(i)[0][1] *TILE_SIZE);
    		KeyValue moveX = new KeyValue(this.circle.translateXProperty(), reference.list.get(i)[0][0] * TILE_SIZE + TILE_ADJ);
    		if (list.get(i)[0][0] == list.get(i)[1][0])
    			moveY = new KeyValue(this.circle.translateYProperty(), list.get(i)[1][1] * TILE_SIZE);
    		if (list.get(i)[0][1] == list.get(i)[1][1])
    			moveX = new KeyValue(this.circle.translateXProperty(), list.get(i)[1][0] * TILE_SIZE + TILE_ADJ);
    		KeyFrame frame = new KeyFrame(new Duration(dur), moveX, moveY);
//    		System.out.println(moveX + " " + moveY);
    		timeline.getKeyFrames().add(frame);
    		this.setX(list.get(i)[1][0] * TILE_SIZE + TILE_ADJ);
    		this.setY(list.get(i)[1][1] * TILE_SIZE);
    		dur += 3000;
    	}
    	timeline.play();
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	// when we add the towers this method will run to damage the enemies
//	public void hit(Object bullet, Pane canvas) {
//		this.health -= bullet.damage;  
//		if (this.health <= 0) 
//			canvas.getChildren().remove(this.circle);
//	}
	
	// if the towers hit home base this will damage the base and then cause the circle to disappear
//	public void homeBase(Object base, Pane canvas) {
//		base.health -= this.damage;
//		canvas.getChildren().remove(this.circle);
//	}
	


	
}
