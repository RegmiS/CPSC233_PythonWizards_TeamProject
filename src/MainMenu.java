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
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application {
	
	
	private static int WIDTH = 1460, HEIGHT = 720; //Set window dimensions

	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() { 
		return HEIGHT;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
// Buttons, labels, text, etc., with event handlers & coordinates -- Try to see if this can be put into its own method
		
			Pane background = new Pane();
			GridPane canvas = new GridPane();
			canvas.setMinSize(WIDTH, HEIGHT);
			canvas.setPadding(new Insets(10, 10, 10, 10));
			canvas.setVgap(50);
			canvas.setVgap(50);
			canvas.setAlignment(Pos.CENTER);
			//canvas.setGridLinesVisible(true);
			//canvas.getColumnConstraints().add(new ColumnConstraints(50));
			
			Button playButton = new Button("Play");
			Button helpButton = new Button("Help");
			Button exitButton = new Button("Exit");
			Label title = new Label("Tower Defence Game");
			title.setFont(new Font("Arial", 65));
			title.setTextFill(Color.YELLOW);
			GridPane.setHalignment(title, HPos.CENTER);
			GridPane.setHalignment(playButton, HPos.CENTER);
			GridPane.setHalignment(helpButton, HPos.CENTER);
			GridPane.setHalignment(exitButton, HPos.CENTER);
			GridPane.setConstraints(title, 0, 0);
			GridPane.setConstraints(playButton, 0, 1);
			GridPane.setConstraints(helpButton, 0, 2);
			GridPane.setConstraints(exitButton, 0, 3);
			background.getChildren().add(ImageLoader.menuBackgroundImage("castle.jpg"));		
			background.getChildren().add(canvas);
			canvas.getChildren().addAll(title, playButton, helpButton, exitButton);
			Scene scene = new Scene(background);
			
//			canvas.setGridLinesVisible(true);
			
			primaryStage.setTitle("The Python Wizards");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			playButton.setOnAction(new EventHandler<ActionEvent>() {
				//Play button Event handler, will call next Game state when pressed
				@Override
				public void handle(ActionEvent event){
					DifficultyMenu difficultyMenu = new DifficultyMenu(canvas, canvas, scene);
					try {
						System.out.println("Loading...");	
						difficultyMenu.start(primaryStage);
						System.out.println("Done");
					} catch (Exception e) {
					e.printStackTrace();
					}
				}
			});
			
			helpButton.setOnAction(new EventHandler<ActionEvent>() {
				//Placeholder button		
				@Override
				public void handle(ActionEvent event) {	
					System.out.println("Good Luck");
				}	
			});
			
			exitButton.setOnAction(new EventHandler<ActionEvent>() {
				//Exit scene when pressed
				@Override
				public void handle(ActionEvent event) {
					System.out.println("Goodbye");
					primaryStage.close();
				}
			});
	}
	public static void main(String args[]) {
		launch(args);
	}
}