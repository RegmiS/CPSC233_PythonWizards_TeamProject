import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
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
	public static Main textgame;
	public static Timeline timeline;
	public static int round = 1;
	public static Enemy reference;
	
	
	public GridVersionGame(GridPane gridpane, Scene scene1) {
		GridVersionGame.gridpane = gridpane;
		scene = scene1;
		textgame = new Main(HEIGHT, WIDTH, TILE_SIZE);
		reference = new Enemy();
		
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
		enemyPath("right", 2, 0, 5, reference);
		enemyPath("down", 2, 5, 5, reference);
		enemyPath("left", 7, 5, 3, reference);
		enemyPath("down", 7, 2, 3, reference);
		enemyPath("right", 10, 2, 10, reference);
		enemyPath("up", 10, 12, 5, reference);
		enemyPath("right", 5, 12, 12, reference);
		
		//
		textgame.drawGame();
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
		
		
		spawnEnemies(reference, 1); 
		
		scene = new Scene(gridpane, WIDTH, HEIGHT);
		
		stage.setTitle("The Python Wizards");
		stage.setScene(scene);
		stage.show();
	
		
	}
	public static void spawnEnemies(Enemy reference, int round){
    	Enemy e1 = new Enemy(1, gridpane, reference, TILE_SIZE);
    	//Enemy e2 = new Enemy(Color.BLUE, canvas, path, transition );
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
	
	/*
	 * Creates a horizontal/vertical path for enemies to move on
	 */
	public void enemyPath(String direction, int row, int col, int length, Enemy e1) {

		if (direction == "right") {
			int[] start = {col, row};
			int[] end = {col + length, row};
			int[][] temp = new int[][] {start, end};
			e1.addList(temp);
			
			for (int i = col; i < (length + col); i++) {
				Rectangle enemypath = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypath.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypath, row);
				GridPane.setColumnIndex(enemypath, i);
				gridpane.getChildren().addAll(enemypath);
				textgame.editGridPath(row, i, " ");
			}
		}
		if (direction == "left") {
			
			int[] start = {col, row};
			int[] end = {col - length, row};
			int[][] temp = new int[][] {start, end};
			e1.addList(temp);
			
			for (int i = col; i > (col - length); i--) {
				Rectangle enemypath = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypath.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypath, row);
				GridPane.setColumnIndex(enemypath, i);
				gridpane.getChildren().addAll(enemypath);
				textgame.editGridPath(row, i, " ");
			}
		}
		if (direction == "up") {
			
			int[] start = {col, row};
			int[] end = {col, row - length};
			int[][] temp = new int[][] {start, end};
			e1.addList(temp);
			
			for (int i = row; i > (row - length); i--) {
				Rectangle enemypath = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypath.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypath, i);
				GridPane.setColumnIndex(enemypath, col);
				gridpane.getChildren().addAll(enemypath);
				textgame.editGridPath(i, col, " ");

			}
		}
		
		if (direction == "down") {
			
			int[] start = {col, row};
			int[] end = {col, row + length};
			int[][] temp = new int[][] {start, end};
			e1.addList(temp);
			
			for (int i = row; i < (length + row); i++) {
				Rectangle enemypath = new Rectangle(TILE_SIZE, TILE_SIZE);
				enemypath.setFill(Color.BROWN);
				GridPane.setRowIndex(enemypath, i);
				GridPane.setColumnIndex(enemypath, col);
				gridpane.getChildren().addAll(enemypath);
				textgame.editGridPath(i, col, " ");
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
    	textgame.setBase(5, 24, "B");
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
                                    	textgame.editGridTower(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), "X");
                                    	textgame.drawGame();
                                    	
                                    }
                                }
                            }
                        }
                    });
            }
        });
        
        return twr;
    }
    
    public static Button startButton()
{
    	Button start = new Button("Start Round");
    	start.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
//        	spawnEnemies(timeline, round);
        	round ++;
//        	gridpane.getChildren().remove(start);  // for when the game is playable
        }
	});
	return start;
}

	public static void main(String[] args) {
		launch(args);
	}
	
}
