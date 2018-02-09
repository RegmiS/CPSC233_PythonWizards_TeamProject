
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;

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
    public void start(Stage primaryStage) {
    	Path path = createPath();
    	spawnBase();
    	spawnEnemies(path);
    	canvas.getChildren().add(path);
        primaryStage.setScene(scene);
        primaryStage.show();
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
    	Rectangle base = new Rectangle(50, 50, Color.BLACK);
    	base.setX(1150);
    	base.setY(600);
    	canvas.getChildren().add(base);
    }
    
    // creates the PathTransition and returns it
    public static PathTransition createTransition(Path path) {
    	PathTransition transition = new PathTransition();
    	transition.setDuration(Duration.seconds(25.0));
    	transition.setPath(path);
		return transition;
    }
    
    // spawns enemies, in the future will spawn more than one
    public static void spawnEnemies(Path path){
    	PathTransition transition = createTransition(path);
    	Enemy e1 = new Enemy(Color.RED, canvas, path, transition );
    	
    

    }
    
}
