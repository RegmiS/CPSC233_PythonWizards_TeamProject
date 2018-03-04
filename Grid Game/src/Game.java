
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

// game runs after the menu
public class Game extends Application {
	public static Pane canvas;
	public Scene scene;
	
	// constructor that takes the pane and scene from the menu class
	public Game(Pane pane, Scene scene1) {
		canvas = pane;
		scene = scene1;
		
	}
	
	
	
	
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
    	Path path = createPath();
    	spawnBase();
    	
    	Button twr = placeTower();
        GridPane.setConstraints(twr, 0, 0);
    	GridPane.setHalignment(twr, HPos.CENTER);
    	canvas.getChildren().addAll(
    			path,
    			twr);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
		
    			
    	spawnEnemies(path);
    	
    	primaryStage.show();
    	Thread.sleep(50);
    	
    		
    }
    
    // creates the path to be followed by the enemies
    public static Path createPath() {
    	Path path = new Path();;
    	MoveTo move1 = new MoveTo();
    	move1.setX(0);
    	move1.setY(100);
    	LineTo line1 = new LineTo();
    	line1.setX(800);
    	line1.setY(100);
    	LineTo line2 = new LineTo();
    	line2.setX(800);
    	line2.setY(300);
    	LineTo line3 = new LineTo();
    	line3.setX(50);
    	line3.setY(300);
    	LineTo line4 = new LineTo();
    	line4.setX(1150);
    	line4.setY(625);
    	path.getElements().addAll(move1, line1, line2, line3, line4);
		return path;
    }
    
    // spawns the base for now, in the future base might be its own class
    public static void spawnBase() {
    	Rectangle base = new Rectangle(50, 50, Color.BLUE);
    	base.setX(1150);
    	base.setY(600);
    	canvas.getChildren().add(base);
    }
    
    // creates the PathTransition and returns it
    public static PathTransition createTransition(Path path) {
    	PathTransition transition = new PathTransition();
    	transition.setDuration(Duration.seconds(5.0));
    	transition.setPath(path);
		return transition;
    }
    
    // spawns enemies, in the future will spawn more than one
    public static void spawnEnemies(Path path){
    	PathTransition transition = createTransition(path);
    	Enemy e1 = new Enemy(Color.RED, canvas, path, transition );
    	//Enemy e2 = new Enemy(Color.BLUE, canvas, path, transition );
    	
    }
    
    
    public static Button placeTower() 
    {
    	Button twr = new Button("Place Tower");
        twr.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	canvas.setOnMousePressed(new EventHandler<MouseEvent>()
        		{
        		    @Override
        		    public void handle(MouseEvent event) 
        		    {
        		    	
        		    	double xc = event.getSceneX();
        		    	double yc = event.getSceneY();
        		    	Tower t1 = new Tower(xc,yc,Color.ROYALBLUE, canvas);
        		    	
        		    	//Tower(event.getSceneX(), event.getSceneY());
        		    	System.out.println(event.getSceneX());
        		        System.out.println(event.getSceneY());
        		        //double xcoord = event.getSceneX();
        				//double ycoord = event.getSceneY();
        		    }  
        		});
            	
            	//Tower t1 = new Tower(Color.ROYALBLUE, canvas);
            }
        });
        
        return twr;
    
    }
    
    
}
