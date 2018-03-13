import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tiles extends StackPane{

	private int size = 10;
	
	
	public Tiles(int col, int row) {	
		
		Rectangle tile = new Rectangle(this.size,this.size);
		tile.setFill(Color.GREEN);
		tile.setStroke(Color.BLACK);
		tile.setStrokeWidth(1);
		
		Canvas canvas = new Canvas(this.size,this.size);
		setAlignment(Pos.CENTER);
		
		getChildren().addAll(tile,canvas);
		
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
