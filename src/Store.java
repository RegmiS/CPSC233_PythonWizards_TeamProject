import java.io.FileNotFoundException;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Store {
	
	public static void addStoreItem(String name, String image, int position, int price, int HP_val, int DMG_val, int Range_val) throws FileNotFoundException {
		int pos = (((position * 3) +1) - 3);
		Rectangle item = new Rectangle(Game.getTileSize() * 2, Game.getTileSize() * 2);
		ImagePattern texture = new ImagePattern(ImageLoader.backgroundImage(image));
		item.setFill(texture);
		item.setStroke(Color.BLACK);
		GridPane.setConstraints(item, 0, pos-1, 2, 2);
		
		Label NAME = new Label(name);
		GridPane.setConstraints(NAME, 2, pos-1, 1, 1, HPos.CENTER, VPos.CENTER);

		Label HP = new Label("HP: " + HP_val);
		GridPane.setConstraints(HP, 2, pos, 1, 1, HPos.CENTER, VPos.TOP);
		
		Label DMG = new Label("DMG: " + DMG_val);
		GridPane.setConstraints(DMG, 2, pos, 1, 1, HPos.CENTER, VPos.CENTER);
		
		Label ROF = new Label("COST: " + price);
		GridPane.setConstraints(ROF, 2, pos, 1, 1, HPos.CENTER, VPos.BOTTOM);
		
		Button BUY = Buttons.placeTower(price, HP_val, DMG_val, Range_val, Game.getTowerList(), image);
		GridPane.setConstraints(BUY, 0, pos+1, 1, 1, HPos.CENTER, VPos.CENTER);
		Game.getStoregrid().getChildren().addAll(item, NAME, HP, DMG, ROF, BUY);
		//storegrid.setGridLinesVisible(true);
	}
}
