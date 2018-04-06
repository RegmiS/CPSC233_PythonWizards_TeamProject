import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Missles{
	private Circle missle =  new Circle(5);
	private int dmg;
	private Pane pane;
	private static ArrayList<Timeline> timelineList = new ArrayList<Timeline>();
	private Timeline animation;
	
	
	
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	
	public void setCircle()  {
		this.missle =  new Circle(5);
	
	}
	
	
	public void setPane(Pane pane) {
		this.pane = pane;	
	}
	
	
	public static ArrayList<Timeline> getTimelineList()
	{
		return Missles.timelineList;
	}
	
	public Missles(Pane pane, Enemy enemy, int x, int y, int dmg) {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("res/images/ball.png");
			Image image = new Image(inputStream);
			ImagePattern img = new ImagePattern(image);
			missle.setFill(img);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		//this.missle =  new Rectangle(1,2,Color.BLACK);
		this.pane = pane;
		
		setDmg(dmg);
		//setRectangle();
		//setPane(pane); 
//		System.out.println("X " + x);
//		System.out.println("Y " + y);
		Timeline animation = new Timeline();

    	//this.pane.getChildren().add(this.missle);
    	animation.setAutoReverse(false);
    	animation.setOnFinished(e -> this.removeMissle());
    	//Starts at tower
    	KeyFrame initial = new KeyFrame (Duration.ZERO, 
    			new KeyValue(this.missle.translateXProperty(), x*50+26),//center on the tower square
                new KeyValue(this.missle.translateYProperty(), y*50));
    	//Finishes at enemy 
    	KeyFrame Final = new KeyFrame (new Duration(200), //takes half a second to get to enemy
    			new KeyValue(this.missle.translateXProperty(), enemy.getCircle().getTranslateX()),
                new KeyValue(this.missle.translateYProperty(), enemy.getCircle().getTranslateY()));
    			
    			
    			
    			
    	
    	this.pane.getChildren().add(this.missle);
    	animation.getKeyFrames().addAll(initial, Final);
    	
    	this.animation = animation;
    	timelineList.add(animation);
    	animation.play();
	}
	
	public void removeMissle() 
	{	
		this.pane.getChildren().remove(this.missle);
		Missles.timelineList.remove(this.animation);
	}
	
	
}
