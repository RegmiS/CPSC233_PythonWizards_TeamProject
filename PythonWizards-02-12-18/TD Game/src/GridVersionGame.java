import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GridVersionGame extends Application{
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int TILE_SIZE = 50;

	public static GridPane gridpane;
	public Scene scene;
	
	
	public GridVersionGame(GridPane gridpane, Scene scene1) {
		GridVersionGame.gridpane = gridpane;
		scene = scene1;
		
	}
	
	
	@Override 
	public void start(Stage stage) throws Exception {
		gridpane = new GridPane();	

		//gridpane.setPadding(new Insets(10, 10, 10, 10));
		//gridpane.setGridLinesVisible(true);

		//Grid builder - Creates a grid of Rectangles, each rectangle is a StackPane node with its own texture 	
		for (int col = 0; col < ((WIDTH)/ TILE_SIZE); col++) {
			for (int row = 0; row < ((HEIGHT)/ TILE_SIZE); row++) {
				StackPane stackpane = new StackPane();
				FileInputStream inputstream = new FileInputStream("res/images/grass.jpg"); 
				Image texture = new Image(inputstream);
				Rectangle rec = new Rectangle();
				ImagePattern texturePattern = new ImagePattern(texture);
				rec.setHeight(TILE_SIZE);
				rec.setWidth(TILE_SIZE);
				rec.setFill(texturePattern);
				rec.setStroke(Color.BLACK);
				stackpane.getChildren().addAll(rec);
				GridPane.setRowIndex(stackpane, row);
				GridPane.setColumnIndex(stackpane, col);
				gridpane.getChildren().addAll(stackpane);
			}
		}
		//
		

		
		//Side menu
		BorderPane borderpane = new BorderPane();
		VBox menubar = new VBox(10);
		menubar.getChildren().addAll(new Button("Test1"), new Button("Test2"), new Button("Test3"));
		borderpane.setRight(menubar);
		GridPane.setRowIndex(borderpane, 0);
		GridPane.setColumnIndex(borderpane, 24);
		gridpane.getChildren().addAll(borderpane);
		//
		
		
		//gridpane.getChildren().addAll(path); //Adds pathing method to grid(not working currently)
		
		scene = new Scene(gridpane, WIDTH, HEIGHT);
		
		stage.setTitle("The Python Wizards");
		stage.setScene(scene);
		stage.show();
	}
    
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
