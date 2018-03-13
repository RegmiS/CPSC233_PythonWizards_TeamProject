import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class Main extends Application
{
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		 launch(args);
	} 
	
	/**
	 * Starts the the game.
	 */
	@Override
    public void start(Stage primaryStage) 
	{
        primaryStage.setTitle("Tic Tac Toe!");
        TicTacToe board = new TicTacToe();
        primaryStage.setScene(new Scene(createBoard(board)));
        primaryStage.show();
        board.printBoard();
 
	}
	
	
	
	/**
	 * 
	 * makes a grid of rectangles
	 * @param Turn
	 * @return root
	 */
	public Pane createBoard(TicTacToe Turn) {
		
		Pane root = new Pane();
		root.setPrefSize(Turn.getGameSize() * 100, Turn.getGameSize() * 100);
		
		
		for(int i = 0; i<Turn.getGameSize(); i++) {
			for(int j = 0; j<Turn.getGameSize(); j++) {
				Grid grid = new Grid(Turn,j,i);
				grid.setTranslateX(i*100);
				grid.setTranslateY(j*100);
				root.getChildren().add(grid);
			}
		}
		return root;
	}
}
