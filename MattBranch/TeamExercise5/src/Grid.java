import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author Matt
 *
 */
public class Grid extends StackPane{
		
		/**Grid
		 * Constructor for each square of the game board
		 */
		public Grid(TicTacToe Turn, int col, int row) 
		{
			
			Rectangle plots = new Rectangle(100, 100);
			plots.setFill(null);
			plots.setStroke(Color.BLACK);
			
			
			Canvas canvas = new Canvas(100,100);
			setAlignment(Pos.CENTER);
			getChildren().addAll(plots,canvas);
			
			
			setOnMouseClicked(event -> {
				if(!(Turn.checkWin())) {
					return;
				}
				
				if( event.getButton() == MouseButton.PRIMARY) {
					draw(Turn,canvas,col,row);
				}
				
			});
		}
		
		
		/**draw
		 * 
		 * depending on the turn draws an X or O on the selected square
		 * @param Turn
		 * @param canvas
		 * @param col
		 * @param row
		 */
		public void draw(TicTacToe Turn, Canvas canvas, int col, int row) {
	
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.RED);
			gc.setLineWidth(5);
			if(Turn.getFlag(col, row) == -1) {
				if(Turn.getTurn() == 0)
				{
				
					gc.strokeLine(25, 25, 75, 75);
					gc.strokeLine(25, 75, 75, 25);
					Turn.setBoard(Turn.getTurn(), col, row);
					Turn.printBoard();
					Turn.setTurn(Turn.getTurn()+1);
				
				}else{
				
					gc.strokeOval(25, 25, 50, 50);
					Turn.setBoard(Turn.getTurn(), col, row);
					Turn.printBoard();
					Turn.setTurn(Turn.getTurn()+1);
				}
			

			}
		}
}
		

