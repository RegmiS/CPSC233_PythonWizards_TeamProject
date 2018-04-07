import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	private static TextGame textgame;
	private static Timeline timeline;
	private static Enemy reference;
	private static AnimationTimer timer;
	private static int framecount = 0;

	private static ArrayList<Enemy> enemyList;
	private static ArrayList<Tower> towerList;
	private static ArrayList<Enemy> queueList;
	
	private static Leveling scalingAlgo = new Leveling("normal", 20);
	
	public Game(GridPane gridpane, GridPane storegrid, Scene scene1) {
		Game.setGridpane(gridpane);
		Game.setStoregrid(storegrid);
		scene = scene1;
		textgame = new TextGame(HEIGHT-50, WIDTH-200, getTileSize());
		reference = new Enemy();	
	}
	
	@Override 
	public void start(Stage stage) throws Exception {
		setGridpane(new GridPane());
		setStoregrid(new GridPane());
		getStoregrid().setVgap(10);
		getStoregrid().setHgap(10);
		getStoregrid().setPadding(new Insets(10, 10, 10, 10));
		
		Game.enemyList = new ArrayList<Enemy>();
		Game.timeline = new Timeline();
		setTowerList(new ArrayList<Tower>());

		Enemy.setPane(getGridpane());
		drawGrid();
		spawnBase(); // does text base
		Base b1 = new Base(getGridpane());

        Button start = Buttons.startButton();
        GridPane.setConstraints(start, 21, 0);
        GridPane.setColumnSpan(start, 2);
        Button pause = Buttons.pauseButton(timeline);
        GridPane.setConstraints(pause, 24, 0);
        GridPane.setColumnSpan(pause, 1);
        Button play = Buttons.playButton(timeline);
        GridPane.setConstraints(play, 23, 0);
        GridPane.setColumnSpan(play, 1);
        getGridpane().getChildren().addAll(start, pause, play);
		
		
		//Enemy path
        new RandomPath();

		//
		TextGame.drawGame();
		//
		
		setTimer(new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				
				if (framecount % 10 == 0) {
					getTowerList().forEach(Tower -> Tower.checkInRange(enemyList));
				}
				if (framecount % 40 == 0 && !getQueueList().isEmpty() ) {
					
					getQueueList().get(0).displayEnemy();
					getQueueList().remove(getQueueList().get(0));
				}
				else if (framecount % 40 == 0 && enemyList.isEmpty())
				{
					start.setVisible(true);
					getTimer().stop();
				}
				if (framecount % 100 == 0) 
					TextGame.drawGame();
				
				if(Base.getHealth() <= 0) {
				
				getTimer().stop();
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
		});
		
		
//						  (NAME, TEXTURE IMAGE, POSITION, PRICE, HP, DMG, ROF, RANGE)

		Store.addStoreItem("Tower 1", "tank1.png", 1, 1000, 500, 500, 5, 100);
		Store.addStoreItem("Tower 2", "tank2.png", 2, 750, 250, 700, 4, 50);
		Store.addStoreItem("Tower 3", "tank3.png", 3, 1500, 750, 2500, 1, 250);
		Store.addStoreItem("Tower 4", "tank4.png", 4, 0, 0, 0, 0, 0);
		
		ScrollPane shoppane = new ScrollPane();
		shoppane.setContent(getStoregrid());
		shoppane.setFitToWidth(true);
		GridPane.setConstraints(shoppane, 25, 0, 5, 15);
		getGridpane().getChildren().addAll(shoppane);
		
//
		
		//Info Bar - WIP
	
		HBox infobar = new HBox();
		infobar.setPadding(new Insets(10, 10, 10, 10));
		infobar.setSpacing(250);
		infobar.setStyle("-fx-background-color: #ff5d00");
		
		Label currentHealth= new Label();
		currentHealth.textProperty().bind(TextGame.getHealthStr());
		
		Label currentMoney = new Label();
		currentMoney.textProperty().bind(TextGame.getMoneyStr());
		
		Label currentLevel= new Label();
		currentLevel.textProperty().bind(TextGame.getLevelStr());
		
		ScrollPane infopane = new ScrollPane();
		infopane.setContent(infobar);
		infopane.setFitToWidth(true);
		infobar.getChildren().addAll(currentHealth, currentMoney, currentLevel);
		GridPane.setConstraints(infopane, 0, 13, 25, 1);
		getGridpane().getChildren().add(infopane);
		
		//
		 	
		scene = new Scene(getGridpane(), WIDTH, HEIGHT);
		
		stage.setTitle("The Python Wizards");
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static ArrayList<Enemy> getEnemyList() { return Game.enemyList; }
	
	public void removeEnemies (ArrayList<Enemy> enemyList) throws ConcurrentModificationException {
		for (int i = 0; i < enemyList.size(); i++)
		{
			if(enemyList.get(i).getHealth() <= 0) {
				TextGame.removeEnemies();
				enemyList.get(i).removeEnemy(false);
			}
			
		}
		
	}
	

	public void drawGrid() throws FileNotFoundException {
		//Grid builder - Creates a grid of Rectangles, each rectangle is a node with its own texture 	
		for (int col = 0; col < ((WIDTH)/ getTileSize()); col++) {
			for (int row = 0; row < ((HEIGHT)/ getTileSize()); row++) {
				Rectangle rec = new Rectangle(getTileSize(), getTileSize());
				ImagePattern texturePattern = new ImagePattern(ImageLoader.backgroundImage("grass.jpg"));
//				rec.setId(Integer.toString(col) + ", " + Integer.toString(row)); //Assign Id to each node
				rec.setFill(texturePattern);
				GridPane.setRowIndex(rec, row);
				GridPane.setColumnIndex(rec, col);
				getGridpane().getChildren().addAll(rec);
			}
		}
	}
	
    public static void spawnBase() {
    	textgame.setBase(5, 24, "B");
    }



	public static Enemy getReference() {
		return reference;
	}


	public static GridPane getGridpane() {
		return gridpane;
	}


	public static void setGridpane(GridPane gridpane) {
		Game.gridpane = gridpane;
	}
	
	public static TextGame getTextgame() {
		return textgame;
	}


	public static ArrayList<Enemy> getQueueList() {
		return queueList;
	}


	public static void setQueueList(ArrayList<Enemy> queueList) {
		Game.queueList = queueList;
	}


	public static Leveling getScalingAlgo() {
		return scalingAlgo;
	}


	public static void setScalingAlgo(Leveling scalingAlgo) {
		Game.scalingAlgo = scalingAlgo;
	}


	public static AnimationTimer getTimer() {
		return timer;
	}


	public static void setTimer(AnimationTimer timer) {
		Game.timer = timer;
	}


	public static int getTileSize() {
		return TILE_SIZE;
	}


	public static ArrayList<Tower> getTowerList() {
		return towerList;
	}


	public static void setTowerList(ArrayList<Tower> towerList) {
		Game.towerList = towerList;
	}


	public static GridPane getStoregrid() {
		return storegrid;
	}


	public static void setStoregrid(GridPane storegrid) {
		Game.storegrid = storegrid;
	}
	
}

