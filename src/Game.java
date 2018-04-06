import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Game extends Application{
	
	private static final int WIDTH = 1480;
	private static final int HEIGHT = 720;
	private static final int TILE_SIZE = 50;

	private static GridPane gridpane;
	private static GridPane storegrid;
	private static Scene scene;
	private static Main textgame;
	private static Timeline timeline;
	private static Enemy reference;
	private static AnimationTimer timer;
	private static int framecount = 0;

	
	
	private static ArrayList<Enemy> enemyList;
	private static ArrayList<Tower> towerList;
	private static ArrayList<Enemy> queueList;
	
	private static Leveling scalingAlgo = new Leveling("normal", 20);
	
	
	public Game(GridPane gridpane, GridPane storegrid, Scene scene1) {
		Game.gridpane = gridpane;
		Game.storegrid = storegrid;
		scene = scene1;
		textgame = new Main(HEIGHT-50, WIDTH-200, TILE_SIZE);
		reference = new Enemy();
		
	}
	
	
	@Override 
	public void start(Stage stage) throws Exception {
		gridpane = new GridPane();
		storegrid = new GridPane();
		storegrid.setVgap(10);
		storegrid.setHgap(10);
		storegrid.setPadding(new Insets(10, 10, 10, 10));
		
		Game.enemyList = new ArrayList<Enemy>();
		Game.timeline = new Timeline();
		towerList = new ArrayList<Tower>();
				

		Enemy.setPane(gridpane);
		drawGrid();
		spawnBase(); // does text base
		Base b1 = new Base(gridpane);
		
		

        Button start = startButton();
        GridPane.setConstraints(start, 21, 0);
        GridPane.setColumnSpan(start, 2);
        Button pause = pauseButton(timeline);
        GridPane.setConstraints(pause, 24, 0);
        GridPane.setColumnSpan(pause, 1);
        Button play = playButton(timeline);
        GridPane.setConstraints(play, 23, 0);
        GridPane.setColumnSpan(play, 1);
        
        
        gridpane.getChildren().addAll(start, pause, play);
		
		
		//Enemy path
        new RandomPath();
//		enemyPath("right", 2, 0, 5, reference);
//		enemyPath("down", 2, 5, 5, reference);
//		enemyPath("left", 7, 5, 3, reference);
//		enemyPath("down", 7, 2, 3, reference);
//		enemyPath("right", 10, 2, 10, reference);
//		enemyPath("up", 10, 12, 5, reference);
//		enemyPath("right", 5, 12, 12, reference);
		
		//
		Main.drawGame();
		//
		
		
		
		timer = new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				
				if (framecount % 10 == 0) {
					towerList.forEach(Tower -> Tower.checkInRange(enemyList));
				}
				if (framecount % 40 == 0 && !queueList.isEmpty() ) {
					
					queueList.get(0).displayEnemy();
					queueList.remove(queueList.get(0));
				}
				else if (framecount % 40 == 0 && enemyList.isEmpty())
				{
					start.setVisible(true);
					timer.stop();
				}
				if (framecount % 100 == 0) 
					Main.drawGame();
			
				
				if(Base.getHealth() <= 0) {
				
				timer.stop();
    			ArrayList<Timeline> enemyList = Enemy.getTimelineList();
    			for (int i = 0; i < enemyList.size(); i++  )
    				enemyList.get(i).pause();
    			ArrayList<Timeline> missleList = Missles.getTimelineList();
    			for (int i = 0; i < missleList.size(); i++)
    				missleList.get(i).pause();
				
			}
			
			
				removeEnemies(enemyList);
				framecount++;
			}
		};
		
		
		//			(NAME, TEXTURE IMAGE, POSITION, PRICE, HP, DMG, ROF, RANGE)

		addStoreItem("Tower 1", "tank1.png", 1, 1000, 500, 500, 5, 100);
		addStoreItem("Tower 2", "tank2.png", 2, 750, 250, 700, 4, 50);
		addStoreItem("Tower 3", "tank3.png", 3, 1500, 750, 2500, 1, 250);
		addStoreItem("Tower 4", "tank4.png", 4, 0, 0, 0, 0, 0);
//		addStoreItem("Tower 5", "temp.jpg", 5, 0, 0, 0, 0, 0);
//		addStoreItem("Tower 6", "temp.jpg", 6, 0, 0, 0, 0, 0);
//		addStoreItem("Tower 7", "temp.jpg", 7, 0, 0, 0, 0, 0);
//		addStoreItem("Tower 8", "temp.jpg", 8, 0, 0, 0, 0, 0);
//		addStoreItem("Tower 9", "temp.jpg", 9, 0, 0, 0, 0, 0);
//		addStoreItem("Tower 10", "temp.jpg", 10, 0, 0, 0, 0, 0);
		
		ScrollPane shoppane = new ScrollPane();
		shoppane.setContent(storegrid);
		shoppane.setFitToWidth(true);
		GridPane.setConstraints(shoppane, 25, 0, 5, 15);
		gridpane.getChildren().addAll(shoppane);
		
		//---
		
		//Info Bar - WIP
		
		//String currentHPstr = Integer.toString(Main.getHealth());	
		
		
		HBox infobar = new HBox();
		infobar.setPadding(new Insets(10, 10, 10, 10));
		infobar.setSpacing(250);
		infobar.setStyle("-fx-background-color: #ff5d00");
		
		Label currentHealth= new Label();
		currentHealth.textProperty().bind(Main.getHealthStr());
		
		Label currentMoney = new Label();
		currentMoney.textProperty().bind(Main.getMoneyStr());
		
		Label currentLevel= new Label();
		currentLevel.textProperty().bind(Main.getLevelStr());
		
		ScrollPane infopane = new ScrollPane();
		infopane.setContent(infobar);
		infopane.setFitToWidth(true);
		infobar.getChildren().addAll(currentHealth, currentMoney, currentLevel);
		GridPane.setConstraints(infopane, 0, 13, 25, 1);
		gridpane.getChildren().add(infopane);
		
		//---
		
//		spawnEnemies(reference, 1, enemyList); 
		
		scene = new Scene(gridpane, WIDTH, HEIGHT);
		
		stage.setTitle("The Python Wizards");
		stage.setScene(scene);
		stage.show();
	
		
	
//		timer.start();
		
		
	}
	
	public static ArrayList<Enemy> getEnemyList() { return Game.enemyList; }
	
	
	public void removeEnemies (ArrayList<Enemy> enemyList) throws ConcurrentModificationException {
		for (int i = 0; i < enemyList.size(); i++)
		{
			if(enemyList.get(i).getHealth() <= 0) {
				Main.removeEnemies();
				enemyList.get(i).removeEnemy(false);
			}
			
		}
		
	}
	

	public void drawGrid() throws FileNotFoundException {
		//Grid builder - Creates a grid of Rectangles, each rectangle is a node with its own texture 	
		for (int col = 0; col < ((WIDTH)/ TILE_SIZE); col++) {
			for (int row = 0; row < ((HEIGHT)/ TILE_SIZE); row++) {
				Rectangle rec = new Rectangle(TILE_SIZE, TILE_SIZE);
				ImagePattern texturePattern = new ImagePattern(backgroundImage("grass.jpg"));
				rec.setId(Integer.toString(col) + ", " + Integer.toString(row)); //Assign Id to each node
				rec.setFill(texturePattern);
//				rec.setStroke(Color.BLACK);
//				System.out.println(rec.getId());
				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				gridpane.getChildren().addAll(rec);
			}
		}
	}
	
	
	public void addStoreItem(String name, String image, int position, int price, int HP_val, int DMG_val, int ROF_val, int Range_val) throws FileNotFoundException {
		int pos = (((position * 3) +1) - 3);
		Rectangle item = new Rectangle(TILE_SIZE * 2, TILE_SIZE * 2);
		ImagePattern texture = new ImagePattern(backgroundImage(image));
		item.setFill(texture);
		item.setStroke(Color.BLACK);
		GridPane.setConstraints(item, 0, pos-1, 2, 2);
		
		Label NAME = new Label(name);
		GridPane.setConstraints(NAME, 2, pos-1, 1, 1, HPos.CENTER, VPos.CENTER);

		Label HP = new Label("HP: " + HP_val);
		GridPane.setConstraints(HP, 2, pos, 1, 1, HPos.CENTER, VPos.TOP);
		
		Label DMG = new Label("DMG: " + DMG_val);
		GridPane.setConstraints(DMG, 2, pos, 1, 1, HPos.CENTER, VPos.CENTER);
		
		Label ROF = new Label("ROF: " + ROF_val);
		GridPane.setConstraints(ROF, 2, pos, 1, 1, HPos.CENTER, VPos.BOTTOM);
		
		//Button BUY = new Button("$" + price + " Buy");
		Button BUY = placeTower(price, HP_val, DMG_val, ROF_val, Range_val, towerList, image);
		GridPane.setConstraints(BUY, 0, pos+1, 1, 1, HPos.CENTER, VPos.CENTER);
		
		storegrid.getChildren().addAll(item, NAME, HP, DMG, ROF, BUY);
		//storegrid.setGridLinesVisible(true);
	}
	
	/*
	 * Creates a horizontal/vertical path for enemies to move on
	 */
	public static void enemyPath(String direction, int row, int col, int length, Enemy e1) {

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
    	//Rectangle base = new Rectangle(50, 50, Color.BLUE);
    	//GridPane.setConstraints(base, 24, 5);
    	textgame.setBase(5, 24, "B");
    	//gridpane.getChildren().add(base);
    	//GridVersionGame.base = base;
    }
    
    public static Button placeTower(int price, int hp, int dmg, int rof, int range, ArrayList<Tower> towerList, String image) 
    {
    	Button twr = new Button("Buy");
        twr.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                    	
            		gridpane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {  //From https://stackoverflow.com/questions/28320110/javafx-how-to-get-column-and-row-index-in-gridpane
                        @Override
                        public void handle(MouseEvent e) {
                        	
                            for(Node node: gridpane.getChildren()) {
                                if(node instanceof Rectangle) {
                                	
                                	 if(node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY()) 
                                     		&& e.getSceneX() <= 1250 
                                     		&& e.getSceneY() <= 650 
                                     		&& ((textgame.getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != "X") 
                                     		&& (textgame.getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != " ") 
                                     		&& (textgame.getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != "B" ))) {
                                		 if ((Main.getMoney() - price) >= 0){
						                    	System.out.println(Double.toString(node.getLayoutX()) + "/" + Double.toString(node.getLayoutY()));
						                        System.out.println( "Tower at:  " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex(node));
						                    	Tower t1 = new Tower(GridPane.getColumnIndex(node), GridPane.getRowIndex(node), price, hp, dmg, rof, range, image, gridpane);
						                    	textgame.editGridTower(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), "X");
						                    	System.out.println("HERE" + textgame.getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)));
						                    	Main.drawGame();
						                    	towerList.add(t1);
						                    	gridpane.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
						                    	Main.setHealth(Base.getHealth()); //For testing 
						                    	Main.setMoney(Main.getMoney()-price);
            								}
                                		 else {
                                			 System.out.println("Insufficient Funds");
                                			 gridpane.removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
                                		 }
            
                                    	}
                                }
                            }
                        }
                    });
            }
        });

        return twr;
    }
    
    // spawns enemies for that round then disappears and increments the round
    public static Button startButton()
{
    	Button start = new Button("Start Round");
    	start.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
        	Leveling.increaseCurrentLevel();
        	queueList = new ArrayList<Enemy>(scalingAlgo.returnEnemyList(Leveling.returnCurrentLevel()));
        	System.out.println(queueList);
        	System.out.println(enemyList);
        	timer.start();
        	start.setVisible(false);
        	
//        	gridpane.getChildren().remove(start);  
        }
	});
	return start;
}

    public static Button playButton(Timeline timeline)
    {
    	Button play = new Button("Play");
    	play.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			timer.start();
    			ArrayList<Timeline> enemyList = Enemy.getTimelineList();
    			for (int i = 0; i < enemyList.size(); i++  )
    				enemyList.get(i).play();
    			ArrayList<Timeline> missleList = Missles.getTimelineList();
    			for (int i = 0; i < missleList.size(); i++)
    				missleList.get(i).play();
    		}
    	});
		return play;
    	
    }
    
    // experimenting with a pause button
    public static Button pauseButton(Timeline timeline) {
    	Button pause = new Button("||");
    	pause.setOnAction(new EventHandler<ActionEvent>() 
    	{
    		
    		@Override
    		public void handle(ActionEvent event) {
    			
    			timer.stop();
    			ArrayList<Timeline> enemyList = Enemy.getTimelineList();
    			for (int i = 0; i < enemyList.size(); i++  )
    				enemyList.get(i).pause();
    			ArrayList<Timeline> missleList = Missles.getTimelineList();
    			for (int i = 0; i < missleList.size(); i++)
    				missleList.get(i).pause();
    	}});
		return pause;
    }

//	public static void main(String[] args) {
//		launch(args);
//	}


	public static Enemy getReference() {
		return reference;
	}
	
}

