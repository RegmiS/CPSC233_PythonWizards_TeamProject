	import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameMenuGrid extends Application {
	
	
	private static int WIDTH = 1280, HEIGHT = 720; //Set window dimensions

	public static int getWidth() {
		return WIDTH;
	}
	public static int getHeight() { 
		return HEIGHT;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
// Buttons, labels, text, etc., with event handlers & coordinates -- Try to see if this can be put into its own method
		
		
			GridPane canvas = new GridPane();
			canvas.setMinSize(WIDTH, HEIGHT);
			canvas.setPadding(new Insets(10, 10, 10, 10));
			canvas.setVgap(50);
			canvas.setVgap(50);
			canvas.setAlignment(Pos.CENTER_RIGHT);
			//canvas.setGridLinesVisible(true);
			//canvas.getColumnConstraints().add(new ColumnConstraints(50));
			
			Button playButton = new Button("Play");
			Button helpButton = new Button("Help");
			Button exitButton = new Button("Exit");
				
			Label title = new Label("Tower Defence Game");
			title.setFont(new Font("Arial", 65));
			GridPane.setHalignment(title, HPos.CENTER);
			GridPane.setHalignment(playButton, HPos.CENTER);
			GridPane.setHalignment(helpButton, HPos.CENTER);
			GridPane.setHalignment(exitButton, HPos.CENTER);
			//canvas.add(backgroundImage("grass.jpg"), 0, 0);			
			canvas.add(title, 0, 0);
			canvas.add(playButton, 1, 1);
			canvas.add(helpButton, 1, 2);
			canvas.add(exitButton, 1, 3);
			
			Scene scene = new Scene(canvas);
			
			primaryStage.setTitle("The Python Wizards");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			playButton.setOnAction(new EventHandler<ActionEvent>() {
				//Play button Event handler, will call next Game state when pressed
				@Override
				public void handle(ActionEvent event){
					canvas.getChildren().clear();
					try {
						canvas.getChildren().add(backgroundImage("grass.jpg"));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					GridVersionGame game = new GridVersionGame(canvas, scene);
					try {
						System.out.println("Loading...");	
						game.start(primaryStage);
						System.out.println("Done");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//					System.out.println("Coming soon...");
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
	
	public ImageView backgroundImage(String filename) throws FileNotFoundException {
		/*
		 * Takes image filename from "res/images/" directory and sets it as window background 
		 */
		FileInputStream inputStream = new FileInputStream("res/images/" + filename);
		Image image = new Image(inputStream);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(WIDTH); //Set image to current window dimensions 
		imageView.setFitHeight(HEIGHT);
		
		return imageView;
		
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}