
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Tower extends Application {
	
	private Rectangle rectangle;
	//private int rateOfFire = 1;
	//private int damage = 5;
	//private int health = 10;
	
	public Tower(int xc, int yc,Color color, Pane canvas) {
		//getCoord(canvas);
		
		this.rectangle = new Rectangle(25, 25, color);
		GridPane.setConstraints(this.rectangle, xc, yc);
		GridPane.setHalignment(this.rectangle, HPos.CENTER);
		canvas.getChildren().add(this.rectangle);		
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
