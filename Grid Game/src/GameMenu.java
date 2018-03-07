import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameMenu extends Application {
	
	
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
		
		Button playButton = new Button("Play");
		Button helpButton = new Button("Help");
		Button exitButton = new Button("Exit");
		
		Label title = new Label("Tower Defence Game");
		title.setFont(new Font("Arial", 65));
		
		GridPane canvas = new GridPane();
		canvas.setHgap(10);
		canvas.setVgap(10);
		canvas.setPadding(new Insets(10, 10, 10, 10));
		//canvas.setPrefSize(WIDTH, HEIGHT); //Set window size 
		Scene scene = new Scene(canvas, 300, 200);
		
		GridPane.setConstraints(title, 10, 20);
		
		canvas.getChildren().addAll( //Place images, buttons, etc. on canvas
				//backgroundImage("grass.jpg"),
				title,
				playButton, 
				helpButton, 
				exitButton);
		primaryStage.setTitle("The Python Wizards"); //Window title
		primaryStage.setScene(scene);
		primaryStage.show();
		
		canvas.add(playButton, 10, 10);
		canvas.add(helpButton, 10, 20);
		canvas.add(exitButton, 10, 30);
				
		
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
				Game game = new Game(canvas, scene);
				try {
					game.start(primaryStage);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				System.out.println("Coming soon...");
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

		//playButton.setLayoutX((WIDTH / 2) - 100);
		//playButton.setLayoutY(HEIGHT / 2);
		//helpButton.setLayoutX(WIDTH / 2);
		//helpButton.setLayoutY(HEIGHT / 2);
		//exitButton.setLayoutX((WIDTH / 2) + 100);
		//exitButton.setLayoutY(HEIGHT / 2);
		title.setLayoutX((WIDTH / 2) - 300); //Placeholder position 
		title.setLayoutY((HEIGHT / 2) - 100);
		
//-------------------------------------------------------------------------

	
		
		
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
	
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}		
}

