import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
				//StackPane stackpane = new StackPane();
				FileInputStream inputstream = new FileInputStream("res/images/grass.jpg"); 
				Image texture = new Image(inputstream);
				Rectangle rec = new Rectangle();
				ImagePattern texturePattern = new ImagePattern(texture);
				rec.setId(Integer.toString(col) + ", " + Integer.toString(row)); //Assign Id to each node
				rec.setHeight(TILE_SIZE);
				rec.setWidth(TILE_SIZE);
				rec.setFill(texturePattern);
				rec.setStroke(Color.BLACK);
				System.out.println(rec.getId());
				//stackpane.getChildren().addAll(rec);
				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				gridpane.getChildren().addAll(rec);
			}
		}
		//
		
		Button twr = placeTower();
        GridPane.setConstraints(twr, 0, 1);
        GridPane.setColumnSpan(twr, 2);
    	gridpane.getChildren().addAll(twr);
		
		spawnBase();
		
		//Enemy path
		enemyPathH("right", 2, 0, 5);
		enemyPathV("down", 2, 5, 5);
		enemyPathH("left", 7, 5, 3);
		enemyPathV("down", 7, 2, 3);
		enemyPathH("right", 10, 2, 10);
		enemyPathV("up", 10, 12, 5);
		enemyPathH("right", 5, 12, 12);
		//
		
		//Side menu - WIP
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
	
	public void enemyPathH(String direction, int row, int col, int Hlength) {
		if (direction == "right") {
		for (int i = col; i < (Hlength + col); i++) {
				Rectangle enemypathH = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypathH.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypathH, row);
				GridPane.setColumnIndex(enemypathH, i);
				gridpane.getChildren().addAll(enemypathH);
			}
		}
		if (direction == "left") {
			for (int i = col; i > (col - Hlength); i--) {
				Rectangle enemypathH = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypathH.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypathH, row);
				GridPane.setColumnIndex(enemypathH, i);
				gridpane.getChildren().addAll(enemypathH);
			}
		}
	}
	
	public void enemyPathV(String direction, int row, int col, int Vlength) {
		if (direction == "up") {
			for (int i = row; i > (row - Vlength); i--) {
				Rectangle enemypathV = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypathV.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypathV, i);
				GridPane.setColumnIndex(enemypathV, col);
				gridpane.getChildren().addAll(enemypathV);

			}
		}
		if (direction == "down") {
			for (int i = row; i < (Vlength + row); i++) {
				Rectangle enemypathV = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypathV.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypathV, i);
				GridPane.setColumnIndex(enemypathV, col);
				gridpane.getChildren().addAll(enemypathV);

			}
		}
	}
	
    public static void spawnBase() {
    	Rectangle base = new Rectangle(50, 50, Color.BLUE);
    	GridPane.setConstraints(base, 24, 5);
    	gridpane.getChildren().add(base);
    }
    
    public static Button placeTower() 
    {
    	Button twr = new Button("Place Tower");
        twr.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	gridpane.setOnMousePressed(new EventHandler<MouseEvent>()
        		{
        		    @Override
        		    public void handle(MouseEvent event) 
        		    {
        		    	
        		    	double xc = event.getSceneX();
        		    	double yc = event.getSceneY();
        		    	Tower t1 = new Tower(xc,yc,Color.ROYALBLUE, gridpane);
        		    	
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
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
