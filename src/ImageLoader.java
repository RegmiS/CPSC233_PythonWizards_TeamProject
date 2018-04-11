import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.*;


public class ImageLoader {
	
	public static Image backgroundImage(String filename) throws FileNotFoundException {
		/*
		 * Takes image filename from "res/images/" directory and sets it as window background 
		 */
		FileInputStream inputStream = new FileInputStream("res/images/" + filename);
		Image image = new Image(inputStream);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(Game.getTileSize()); 
		imageView.setFitHeight(Game.getTileSize());
		
		return image;
		
	}
	
	public static ImageView menuBackgroundImage(String filename) throws FileNotFoundException {
		/*
		 * Takes image filename from "res/images/" directory and sets it as window background 
		 */
		FileInputStream inputStream = new FileInputStream("res/images/" + filename);
		Image image = new Image(inputStream);
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(MainMenu.getWidth()); //Set image to current window dimensions 
		imageView.setFitHeight(MainMenu.getHeight());
		
		return imageView;
		
	}
	
	public static void setImage(String filename, Shape s) 
	{
		FileInputStream inputStream;
		ImagePattern img = null;
		try {
			inputStream = new FileInputStream("res/images/" + filename);
			Image image = new Image(inputStream);
			img = new ImagePattern(image);
			s.setFill(img);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	public static MediaPlayer getPlayer(String path)
	{
		File source = new File(path);
	 	Media media = new Media(source.toURI().toString()); 
	 	MediaPlayer player = new MediaPlayer(media);
		player.setCycleCount(MediaPlayer.INDEFINITE);
	 	player.setAutoPlay(true);
	 	return player; 
	}
}
