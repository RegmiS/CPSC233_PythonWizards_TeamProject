import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.PathTransition;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GridVersionGame extends Application{
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int TILE_SIZE = 50;
	private static final int TILE_ADJ = TILE_SIZE / 2;

	public static GridPane gridpane;
	public static Scene scene;
	
	
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
		
		
		spawnEnemies(createPath()); //Adds pathing method to grid(not working currently)
		
		scene = new Scene(gridpane, WIDTH, HEIGHT);
		
		stage.setTitle("The Python Wizards");
		stage.setScene(scene);
		stage.show();
	
		
	}
	public static void spawnEnemies(Path path){
    	PathTransition transition = createTransition(path);
    	Enemy e1 = new Enemy(Color.BLUE, gridpane, path, transition );
    	//Enemy e2 = new Enemy(Color.BLUE, canvas, path, transition );
	}
	
	 // creates the PathTransition and returns it
    public static PathTransition createTransition(Path path) {
    	PathTransition transition = new PathTransition();
    	transition.setDuration(Duration.seconds(15.0));
    	transition.setPath(createPath());
		return transition;
    }
    	
	
	 public static Path createPath() {
	    	Path path = new Path();
	    	MoveTo move1 = new MoveTo();
	    	move1.setX(0);
	    	move1.setY(2 * TILE_SIZE);
	    	LineTo line1 = new LineTo();
	    	line1.setX(5 * TILE_SIZE + TILE_ADJ);
	    	line1.setY(2 * TILE_SIZE);
	    	LineTo line2 = new LineTo();
	    	line2.setX(5 * TILE_SIZE + TILE_ADJ);
	    	line2.setY(7 * TILE_SIZE);
	    	LineTo line3 = new LineTo();
	    	line3.setX(2* TILE_SIZE + TILE_ADJ);
	    	line3.setY(7 *TILE_SIZE);
	    	LineTo line4 = new LineTo();
	    	line4.setX(2 * TILE_SIZE + TILE_ADJ);
	    	line4.setY(10 *TILE_SIZE);
	    	LineTo line5 = new LineTo();
	    	line5.setX(12 *TILE_SIZE + TILE_ADJ);
	    	line5.setY(10 *TILE_SIZE);
	    	LineTo line6 = new LineTo();
	    	line6.setX(12 * TILE_SIZE + TILE_ADJ);
	    	line6.setY(5 * TILE_SIZE);
	    	LineTo line7 = new LineTo();
	    	line7.setX(24 * TILE_SIZE + TILE_ADJ);
	    	line7.setY(5 * TILE_SIZE);
	    	path.getElements().addAll(move1, line1, line2, line3, line4,
	    			line5, line6, line7);
			return path;
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
		/*
		 * Creates a horizontal/vertical path for enemies to move on
		 */
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
                        	
                            for(Node node: gridpane.getChildren()) {
                                if(node instanceof Rectangle) {
                                    if(node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                                        System.out.println( "Node: at " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex(node));
                                    	Tower t1 = new Tower(GridPane.getColumnIndex(node), GridPane.getRowIndex(node), Color.RED, gridpane);
                                    	
                                    }
                                }
                            }
                        }
                    });
            }
        });
        
        return twr;
    
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
