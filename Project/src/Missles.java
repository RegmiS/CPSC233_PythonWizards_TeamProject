import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Missles extends Application {
	private Rectangle missle =  new Rectangle(5,5,Color.BLACK);
	private int dmg;
	private Pane pane;
	private static ArrayList<Timeline> timelineList = new ArrayList<Timeline>();
	private Timeline animation;
	
	
	
	public void setDmg(int dmg) {
		this.dmg = dmg;
	}
	
	public void setRectangle()  {
		this.missle =  new Rectangle(5,5,Color.BLACK);
	
	}
	
	
	public void setPane(Pane pane) {
		this.pane = pane;	
	}
	
	
	public static ArrayList<Timeline> getTimelineList()
	{
		return Missles.timelineList;
	}
	
	public Missles(Pane pane, Enemy enemy, int x, int y, int dmg) {
		//this.missle =  new Rectangle(1,2,Color.BLACK);
		this.pane = pane;
		
		setDmg(dmg);
		//setRectangle();
		//setPane(pane); 
		System.out.println("X " + x);
		System.out.println("Y " + y);
		Timeline animation = new Timeline();

    	//this.pane.getChildren().add(this.missle);
    	animation.setAutoReverse(false);
    	animation.setOnFinished(e -> this.removeMissle());
    	//Starts at tower
    	KeyFrame initial = new KeyFrame (Duration.ZERO, 
    			new KeyValue(this.missle.translateXProperty(), x*50+26),//center on the tower square
                new KeyValue(this.missle.translateYProperty(), y*50));
    	//Finishes at enemy 
    	KeyFrame Final = new KeyFrame (new Duration(100), //takes half a second to get to enemy
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
	
	
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
