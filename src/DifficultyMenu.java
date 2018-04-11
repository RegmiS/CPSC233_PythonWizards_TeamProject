import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DifficultyMenu extends MainMenu{
	

	private static int WIDTH = 1460, HEIGHT = 720; //Set window dimensions
	
	
	private static Pane background;
	private static GridPane menu;
	private static Scene scene;
	private static String difficulty = "Normal";
	private static int numRounds = 20;
	private static String endlessRounds = "Endless";
	private static String endlessMode = "Disabled"; 
	private static StringProperty DIFFICULTYstr = new SimpleStringProperty("Difficulty: " + difficulty);
	private static StringProperty ROUNDSstr = new SimpleStringProperty("Number of rounds: " + Integer.toString(numRounds));
	private static StringProperty ENDLESSstr = new SimpleStringProperty("Endless mode: " + endlessMode);
		
	public DifficultyMenu(Pane background, GridPane menu, Scene menuScene) {
		DifficultyMenu.setBackground(background);
		DifficultyMenu.setMenu(menu);
		DifficultyMenu.setScene(menuScene);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			
			Pane background = new Pane();
//			background.getChildren().add(ImageLoader.menuBackgroundImage("castle.jpg"));	
			GridPane menu = new GridPane();
			menu.setMinSize(WIDTH, HEIGHT);
			menu.setPadding(new Insets(10, 10, 10, 10));
			menu.setVgap(10);
			menu.setHgap(10);
			menu.setAlignment(Pos.CENTER);
			

			
			HBox difficultyButtons = new HBox();
			difficultyButtons.setSpacing(100);
			
			Button start = new Button("Start Game");
			Button normal = new Button("Normal");
			Button hard = new Button("Hard");
			Button extreme = new Button("Extreme");
			Button endlessMode = new Button("Endless Mode");
			
			GridPane.setHalignment(start, HPos.CENTER);
			GridPane.setHalignment(normal, HPos.CENTER);
			GridPane.setHalignment(hard, HPos.CENTER);
			GridPane.setHalignment(extreme, HPos.CENTER);
			
			Label difficulty = new Label("Choose Difficulty: ");
			difficulty.setFont(new Font("Arial", 30));
			difficulty.setTextFill(Color.BLACK);
			
			Label rounds = new Label("Enter number of rounds (default 20): ");
			rounds.setFont(new Font("Arial", 19));
			rounds.setTextFill(Color.BLACK);
			TextField roundsInput = new TextField();
			
			Label currentDifficulty = new Label();
			currentDifficulty.setFont(new Font("Arial", 19));
			currentDifficulty.textProperty().bind(DIFFICULTYstr);
			
			Label currentRounds= new Label();
			currentRounds.setFont(new Font("Arial", 19));
			currentRounds.textProperty().bind(ROUNDSstr);
			
			Label isEndlessMode = new Label();
			isEndlessMode.setFont(new Font("Arial", 19));
			isEndlessMode.textProperty().bind(ENDLESSstr);
		
			GridPane.setConstraints(difficulty, 0, 0, 3, 1);
			GridPane.setConstraints(normal, 3, 0);
			GridPane.setConstraints(hard, 4, 0);
			GridPane.setConstraints(extreme, 5, 0);
			GridPane.setConstraints(rounds, 0, 2, 6, 1);
			GridPane.setConstraints(roundsInput, 4, 2, 2, 1);
			GridPane.setConstraints(endlessMode, 0, 3);
			GridPane.setConstraints(start, 1, 3);
			GridPane.setConstraints(currentDifficulty, 0, 4, 2, 1);
			GridPane.setConstraints(currentRounds, 0, 5);
			GridPane.setConstraints(isEndlessMode, 2, 4, 3, 1);
			
			menu.getChildren().addAll(
					difficulty, 
					normal, 
					hard, 
					extreme, 
					rounds, 
					roundsInput, 
					endlessMode,
					start,
					currentDifficulty, 
//					currentRounds, 
					isEndlessMode);
			
//			menu.setGridLinesVisible(true);
			
			background.getChildren().add(menu);
			
			scene = new Scene(background);
			primaryStage.setTitle("The Python Wizards");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			normal.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {	
					setDifficulty("Normal");
					Game.setDifficulty("Normal");
				}	
			});
			
			hard.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {	
					setDifficulty("Hard");
					Game.setDifficulty("Hard");
				}	
			});
			
			extreme.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {	
					setDifficulty("Extreme");
					Game.setDifficulty("Extreme");
				}	
			});
			
			endlessMode.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (getEndlessMode().equals("Disabled")) {
						roundsInput.clear();
						setEndlessMode("Enabled");
						setNumRounds(999);
						Game.setNumRounds(999);
					}else {
						setEndlessMode("Disabled");
						setNumRounds(20);
						Game.setNumRounds(20);
					}
				}	
			});
			
			start.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (getEndlessMode().equals("Disabled") && !roundsInput.getText().isEmpty()) {
						setNumRounds(Integer.parseInt(roundsInput.getText()));
						Game.setNumRounds(Integer.parseInt(roundsInput.getText()));
						Game game = new Game(menu, menu, scene);
						try {
							System.out.println("Loading...");	
							game.start(primaryStage);
							System.out.println("Done");
						} catch (Exception e) {
						e.printStackTrace();
						}
					}else if (getEndlessMode().equals("Enabled")){
						Game game = new Game(menu, menu, scene);
						try {
							System.out.println("Loading...");	
							game.start(primaryStage);
							System.out.println("Done");
						} catch (Exception e) {
						e.printStackTrace();
						}
					}else {
						setNumRounds(20);
						Game.setNumRounds(20);
						Game game = new Game(menu, menu, scene);
						try {
							System.out.println("Loading...");	
							game.start(primaryStage);
							System.out.println("Done");
						} catch (Exception e) {
						e.printStackTrace();
						}
					}
				}	
			});
			
	}
	
	public static StringProperty getDifficultyStr() {
		return DIFFICULTYstr;
	}
	
    public static void setDifficulty(String difficultyVal) {
        difficulty = difficultyVal;
        DIFFICULTYstr.set("Difficulty: " + difficultyVal);
    }
    
    public static int getNumRounds() {
		return numRounds;
	}
    
    public static StringProperty getNumRoundsStr() {
		return ROUNDSstr;
	}
    
    public static void setNumRounds(int NumRoundsVal) {
    	if (getEndlessMode().equals("Disabled")) {
    		numRounds = NumRoundsVal;
    		ROUNDSstr.set("Number of rounds: " + Integer.toString(NumRoundsVal));
    	}else {
    		numRounds = NumRoundsVal;
    		ROUNDSstr.set("Number of rounds: " + endlessRounds);
    	}
    }
    
    public static void setEndlessMode(String isEndlessModeVal) {
        endlessMode = isEndlessModeVal;
        ENDLESSstr.set("Endless mode: " + isEndlessModeVal);
    }  
    
    public static String getEndlessMode() {
    	return endlessMode;
    }
    
	public static Pane getBackground() {
		return background;
	}

	public static void setBackground(Pane background) {
		DifficultyMenu.background = background;
	}

	public static GridPane getMenu() {
		return menu;
	}

	public static void setMenu(GridPane menu) {
		DifficultyMenu.menu = menu;
	}

	public static Scene getScene() {
		return scene;
	}

	public static void setScene(Scene scene) {
		DifficultyMenu.scene = scene;
	}

	public static String getEndlessRounds() {
		return endlessRounds;
	}

	public static void setEndlessRounds(String endlessRounds) {
		DifficultyMenu.endlessRounds = endlessRounds;
	}

	public static String getDifficulty() {
		return difficulty;
	}
}
