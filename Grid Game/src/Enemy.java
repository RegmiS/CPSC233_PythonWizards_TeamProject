//package Application;



import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.animation.PathTransition;
import javafx.application.Application;


// Class for our enemy objects
public class Enemy extends Application {
	// Instance variables
	private Circle circle;
	private int health = 50;
	private int damage = 30;
	private int type;
	
	
    // Constructor creates a circle for each enemy created and will later use startCords and path to enter the screen
	public Enemy(int type, Pane pane, Path path, PathTransition transition ) {
		if (type == 1)
    		this.circle = new Circle(5, Color.RED);
		else if (type == 2) {
    		this.circle = new Circle(5, Color.BLUE);
    		this.health = this.health * 2;
    		this.damage = this.damage * 2;
    	} 
    	else if (type == 3) {
    		this.circle = new Circle(5, Color.GREEN);
    		this.health = this.health * 4;
    		this.damage = this.damage * 4;
    	}
    	
    	pane.getChildren().add(this.circle);
    	transition.setNode(this.circle);
//    	transition.setOnFinished(this.homeBase(base, canvas)); to be used when we have a base class setup
    	transition.play();
     
		
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
