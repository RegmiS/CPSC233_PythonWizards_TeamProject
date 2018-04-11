import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.*;

public class MainMenu extends Application {
	
	
<<<<<<< HEAD
	private static int WIDTH = 1250, HEIGHT = 700; //Set window dimensions
=======
	private static int WIDTH = 1460, HEIGHT = 720; //Set window dimensions
>>>>>>> 08045504c986bbba7888e0e9e03851ccb7133a28

	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() { 
		return HEIGHT;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
// Buttons, labels, text, etc., with event handlers & coordinates -- Try to see if this can be put into its own method
		
			MediaPlayer player = ImageLoader.getPlayer("res/sound/menu.wav");
		 
	       
			Pane background = new Pane();
			background.setMaxSize(WIDTH, HEIGHT);
			GridPane canvas = new GridPane();
			canvas.setMinSize(WIDTH, HEIGHT);
			canvas.setPadding(new Insets(10, 10, 10, 10));
			canvas.setHgap(200);
			canvas.setVgap(50);
			canvas.setAlignment(Pos.CENTER);
//			canvas.setGridLinesVisible(true);
			//canvas.getColumnConstraints().add(new ColumnConstraints(50));
			
			Button playButton = new Button("Play");
<<<<<<< HEAD
			Button loadButton = Buttons.loadButton();
			Button exitButton = Buttons.exitButton(primaryStage);
			
			Rectangle ship = new Rectangle(200,200);
			Circle alien = new Circle(50);
			Circle alien2 = new Circle(50);
			ImageLoader.setImage("spaceship.png", ship);
			ImageLoader.setImage("alien1W.png", alien);
			ImageLoader.setImage("alien1P.png", alien2);
			
			alien2.setLayoutX(275);
			alien2.setLayoutY(425);
			alien.setLayoutX(275);
			alien.setLayoutY(300);
			ship.setLayoutX(175);
			ship.setLayoutY(100);
				
=======
			Button helpButton = new Button("Help");
			Button exitButton = new Button("Exit");
>>>>>>> 08045504c986bbba7888e0e9e03851ccb7133a28
			Label title = new Label("Tower Defence Game");
			title.setFont(new Font("Arial", 65));
			title.setTextFill(Color.BLACK);
			title.setLayoutX(50);
//			GridPane.setHalignment(title, HPos.LEFT);
			GridPane.setHalignment(playButton, HPos.CENTER);
			GridPane.setHalignment(loadButton, HPos.CENTER);
			GridPane.setHalignment(exitButton, HPos.CENTER);
<<<<<<< HEAD
			background.getChildren().add(ImageLoader.menuBackgroundImage("background.jpg"));
			canvas.add(playButton, 0, 8);
			canvas.add(loadButton, 1, 8);
			canvas.add(exitButton, 2, 8);
			background.getChildren().addAll(canvas, ship, alien, alien2);
			background.getChildren().add(title);
			
			
			
			
=======
			GridPane.setConstraints(title, 0, 0);
			GridPane.setConstraints(playButton, 0, 1);
			GridPane.setConstraints(helpButton, 0, 2);
			GridPane.setConstraints(exitButton, 0, 3);
			background.getChildren().add(ImageLoader.menuBackgroundImage("castle.jpg"));		
			background.getChildren().add(canvas);
			canvas.getChildren().addAll(title, playButton, helpButton, exitButton);
>>>>>>> 08045504c986bbba7888e0e9e03851ccb7133a28
			Scene scene = new Scene(background);
			
//			canvas.setGridLinesVisible(true);
			
			primaryStage.setTitle("The Python Wizards");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
			
			
			playButton.setOnAction(new EventHandler<ActionEvent>() {
				//Play button Event handler, will call next Game state when pressed
				@Override
				public void handle(ActionEvent event){
<<<<<<< HEAD
					Game game = new Game(scene);
					try {
						System.out.println("Loading...");	
						game.start(primaryStage);
						player.stop();
						
=======
					DifficultyMenu difficultyMenu = new DifficultyMenu(canvas, canvas, scene);
					try {
						System.out.println("Loading...");	
						difficultyMenu.start(primaryStage);
						System.out.println("Done");
>>>>>>> 08045504c986bbba7888e0e9e03851ccb7133a28
					} catch (Exception e) {
					e.printStackTrace();
					}
				}
			});
		
	}
	public static void main(String args[]) {
		launch(args);
	}
}