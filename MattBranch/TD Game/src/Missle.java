import javafx.animation.PathTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javafx.application.Application;

public class Missle{
	private int dmg;
	private int speed;
	
	private Rectangle mis;
	private Path path;
	
	
	
	public Missle(double xc, double yc,Pane canvas) {
		this.mis = new Rectangle(100,100,Color.BLACK); 
		setPath(xc, yc);
		this.mis.setLayoutX(xc);
		this.mis.setLayoutY(yc);
		canvas.getChildren().addAll(this.mis);
		PathTransition transition = createTransition(this.path);
		transition.setNode(this.mis);
		transition.play();
	}
	
	public void setPath(double xc,double yc) {
		
    	MoveTo move1 = new MoveTo();
    	move1.setX(xc);
    	move1.setY(yc);
    	LineTo line1 = new LineTo();
    	line1.setX(100);
    	line1.setY(100);
    	
    	this.path.getElements().addAll(move1, line1);
			
	}
	
	 public static PathTransition createTransition(Path path2) {
	    	PathTransition transition = new PathTransition();
	    	transition.setDuration(Duration.seconds(5.0));
	    	transition.setPath(path2);
			return transition;
	    }
	
}
