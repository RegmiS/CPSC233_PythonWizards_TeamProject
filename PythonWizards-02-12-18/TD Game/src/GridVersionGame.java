import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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

		drawGrid();
		spawnBase();
		
		Button twr = placeTower();
        GridPane.setConstraints(twr, 0, 1);
        GridPane.setColumnSpan(twr, 2);
    	gridpane.getChildren().addAll(twr);
		
		
		//Enemy path
		enemyPath("right", 2, 0, 5);
		enemyPath("down", 2, 5, 5);
		enemyPath("left", 7, 5, 3);
		enemyPath("down", 7, 2, 3);
		enemyPath("right", 10, 2, 10);
		enemyPath("up", 10, 12, 5);
		enemyPath("right", 5, 12, 12);
		//
		
		//Side menu - WIP
		/*
		BorderPane borderpane = new BorderPane();
		VBox menubar = new VBox(10);
		menubar.getChildren().addAll(new Button("Test1"), new Button("Test2"), new Button("Test3"));
		borderpane.setRight(menubar);
		GridPane.setRowIndex(borderpane, 0);
		GridPane.setColumnIndex(borderpane, 24);
		gridpane.getChildren().addAll(borderpane);
		*/
		
		
		//gridpane.getChildren().addAll(path); //Adds pathing method to grid(not working currently)
		
		scene = new Scene(gridpane, WIDTH, HEIGHT);
		
		stage.setTitle("The Python Wizards");
		stage.setScene(scene);
		stage.show();
	
		
	}
	
	public void drawGrid() throws FileNotFoundException {
		//Grid builder - Creates a grid of Rectangles, each rectangle is a StackPane node with its own texture 	
		for (int col = 0; col < ((WIDTH)/ TILE_SIZE); col++) {
			for (int row = 0; row < ((HEIGHT)/ TILE_SIZE); row++) {
				Rectangle rec = new Rectangle(TILE_SIZE, TILE_SIZE);
				ImagePattern texturePattern = new ImagePattern(backgroundImage("grass.jpg"));
				rec.setId(Integer.toString(col) + ", " + Integer.toString(row)); //Assign Id to each node
				rec.setFill(texturePattern);
				rec.setStroke(Color.BLACK);
				System.out.println(rec.getId());
				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				gridpane.getChildren().addAll(rec);
			}
		}
	}
	
	public void enemyPath(String direction, int row, int col, int length) {
		if (direction == "right") {
		for (int i = col; i < (length + col); i++) {
				Rectangle enemypath = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypath.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypath, row);
				GridPane.setColumnIndex(enemypath, i);
				gridpane.getChildren().addAll(enemypath);
			}
		}
		if (direction == "left") {
			for (int i = col; i > (col - length); i--) {
				Rectangle enemypath = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypath.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypath, row);
				GridPane.setColumnIndex(enemypath, i);
				gridpane.getChildren().addAll(enemypath);
			}
		}
		if (direction == "up") {
			for (int i = row; i > (row - length); i--) {
				Rectangle enemypath = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypath.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypath, i);
				GridPane.setColumnIndex(enemypath, col);
				gridpane.getChildren().addAll(enemypath);

			}
		}
		
		if (direction == "down") {
			for (int i = row; i < (length + row); i++) {
				Rectangle enemypath = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypath.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypath, i);
				GridPane.setColumnIndex(enemypath, col);
				gridpane.getChildren().addAll(enemypath);

			}
		}
	}
	
	public Image backgroundImage(String filename) throws FileNotFoundException {
		/*
		 * Takes image filename from "res/images/" directory and sets it as window background 
		 */
		FileInputStream inputStream = new FileInputStream("res/images/" + filename);
		Image image = new Image(inputStream);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(TILE_SIZE); 
		imageView.setFitHeight(TILE_SIZE);
		
		return image;
		
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
//            	gridpane.setOnMousePressed(new EventHandler<MouseEvent>()
  //      		{
            		
            		gridpane.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {  //From https://stackoverflow.com/questions/28320110/javafx-how-to-get-column-and-row-index-in-gridpane
                        @Override
                        public void handle(MouseEvent e) {
                        	
                            for( Node node: gridpane.getChildren()) {
                                if( node instanceof Rectangle) {
                                    if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                                        System.out.println( "Node: at " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
                                    	Tower t1 = new Tower(GridPane.getColumnIndex(node), GridPane.getRowIndex(node), Color.RED, gridpane);
                                    	
                                    }
                                }
                            }
                        }
                    });
        		    /*@Override
        		    public void handle(MouseEvent event) 
        		    {
        		    	
        		    	double xc = event.getSceneX();
        		    	double yc = event.getSceneY();
        		    
        		    	
        		    	//Tower(event.getSceneX(), event.getSceneY());
        		    	System.out.println(event.getSceneX());
        		        System.out.println(event.getSceneY());
        		        //double xcoord = event.getSceneX();
        				//double ycoord = event.getSceneY();
        		    }  
        		});*/
            	
            	//Tower t1 = new Tower(Color.ROYALBLUE, canvas);
            }
        });
        
        return twr;
    
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
