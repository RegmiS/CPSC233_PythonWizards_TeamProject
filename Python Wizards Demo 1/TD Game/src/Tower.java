
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.event.*;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Tower extends Application {
	
	private Rectangle rectangle;
	//private int rateOfFire = 1;
	//private int damage = 5;
	//private int health = 10;
	private double xcoord;
	private double ycoord;
	
	

	
	public Tower(double xc, double yc,Color color, Pane canvas) {
		//getCoord(canvas);
		
		this.rectangle = new Rectangle(25, 25, color);
		this.rectangle.setLayoutX(xc);
		this.rectangle.setLayoutY(yc);
		
		canvas.getChildren().add(this.rectangle);
    	
		
		
	}
	
	
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
