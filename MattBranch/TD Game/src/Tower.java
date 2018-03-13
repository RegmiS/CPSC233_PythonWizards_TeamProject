
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
	
	private Rectangle tower;
	private Circle aimRadius;
	//private int rateOfFire = 1;
	//private int damage = 5;
	//private int health = 10;
	private double xcoord;
	private double ycoord;
	private int range = 50;
	
	
	public int getRange(){return range;}
	public void setXcoord(double xc) {
		this.xcoord = xc;
	}
	
	public void setYcoord(double yc) {
		this.ycoord = yc;
	}
	
	public Tower(double xc, double yc,Color color, Pane canvas) {
		//getCoord(canvas);
		this.tower = new Rectangle(20, 20, color);
		this.tower.setLayoutX(xc);
		this.tower.setLayoutY(yc);
		
		setXcoord(xc);
		setYcoord(yc);
		
		this.aimRadius = new Circle(range, Color.CRIMSON);
		this.aimRadius.setLayoutX(xc+10);
		this.aimRadius.setLayoutY(yc+10);
		
		/*Rectangle mis = new Rectangle(1,1,color.BLACK);
		mis.setLayoutX(xc);
		mis.setLayoutY(yc);*/
		Missle mis = new Missle(xc,yc,canvas);
		
		canvas.getChildren().addAll(this.aimRadius,this.tower);
    	
		
	}
	
	
	public void inRange() {
		Canvas hit = new Canvas();
		//hit.addEventFilter(EventType <>, arg1);
		
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
