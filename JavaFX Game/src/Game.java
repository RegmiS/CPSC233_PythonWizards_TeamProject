
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.layout.Pane;



public class Game extends Application {
	public Pane canvas;
	public Scene scene;

	public Game(Pane pane, Scene scene1) {
		canvas = pane;
		scene = scene1;
		
	}
	
    @Override
    public void start(Stage primaryStage) {

//        StackPane root = new StackPane();

//    	Scene scene = new Scene(canvas, 800, 700);
        Button btnHello = new Button();
        btnHello.setText("Spawn enemies");
        btnHello.setOnAction(evt -> Circle(canvas));

        Button moveenemies = new Button();
        moveenemies.setText("MoveEnemiesTest");
        moveenemies.setOnAction(evt -> moveEnemires());

        canvas.getChildren().add(btnHello);
       


        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void spawnEnemies(){

    }

//    public static void main(String[] args, Pane canvas) {
//        launch(args);
//    }

    public static void Circle(Pane canvas2){
        for(int a = 0; a<50;a++){
        	Circle circle = new Circle(5,Color.RED);
        	circle.setCenterX(0);
        	circle.setCenterY(0);

        	canvas2.getChildren().add(circle);
        }

    	
        
//            
//        circles.add(circle);

        

    }
    public void moveEnemires(){
        //would get the object and then change its values as seen fit to make it move

    }
}
