
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Triangle;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.animation.PathTransition;
import javafx.application.Application;


public class Tower extends Application {
	
	private Rectangle rectangle;
	private int rateOfFire = 1;
	private int damage = 5;
	private int health = 10;
	
	public Tower(Color color, Pane canvas) {
		this.rectangle = new Rectangle(5,5,color);
	}
	
	
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
