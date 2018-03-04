
import javafx.application.Application;
import javafx.application.Platform;
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
	//
	
	public Tower(int xc, int yc,Color color, Pane canvas)  {
		Platform.runLater(new Runnable() {
			@Override 
			public void run() { //From https://stackoverflow.com/a/17395191/8645685
				rectangle = new Rectangle(25, 25, color);
				GridPane.setConstraints(rectangle, xc, yc);
				GridPane.setHalignment(rectangle, HPos.CENTER);
				canvas.getChildren().add(rectangle);
			}
		});
				
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
