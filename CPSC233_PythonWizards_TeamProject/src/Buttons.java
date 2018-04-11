import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Buttons {
	
//	public static Button playButton(Scene scene, Stage stage) {
//		Button playButton = new Button("Play");
//		playButton.setOnAction(new EventHandler<ActionEvent>() {
//			//Play button Event handler, will call next Game state when pressed
//			@Override
//			public void handle(ActionEvent event){
//				Game game = new Game(scene);
//				try {
//					System.out.println("Loading...");	
//					game.start(stage);
//					System.out.println("Done");
//				} catch (Exception e) {
//				e.printStackTrace();
//				}
//			}
//		});
//		return playButton;
//	}
	
	public static Button loadButton()
	{
		Button loadButton = new Button("Load");
		loadButton.setOnAction(new EventHandler<ActionEvent>() {
			//Placeholder button		
			@Override
			public void handle(ActionEvent event) {	
				
			}	
		});
		return loadButton;
	}
	
	public static Button exitButton(Stage stage) {
		Button exitButton = new Button("Exit");
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			//Exit scene when pressed
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
		return exitButton;
	}
	
    public static Button startButton()
{
    	Button start = new Button("Start Round");
    	start.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
        	Game.setState(true);
        	Leveling.increaseCurrentLevel();
        	Game.setQueueList(new ArrayList<Enemy>(Game.getScalingAlgo().returnEnemyList(Leveling.returnCurrentLevel())));
        	System.out.println(Game.getQueueList());
        	System.out.println(Game.getEnemyList());
        	Game.getTimer().start();
        	start.setVisible(false);
        		
//        	gridpane.getChildren().remove(start);  
        }
	});
	return start;
}

//    public static Button playButton(Timeline timeline)
//    {
//    	Button play = new Button("Play");
//    	play.setOnAction(new EventHandler<ActionEvent>() {
//    		@Override
//    		public void handle(ActionEvent event) {
//    			ArrayList<Timeline> enemyList = Enemy.getTimelineList();
//    			for (int i = 0; i < enemyList.size(); i++  )
//    				enemyList.get(i).play();
//    			ArrayList<Timeline> missleList = Missles.getTimelineList();
//    			for (int i = 0; i < missleList.size(); i++)
//    				missleList.get(i).play();
//    			Game.getTimer().start();
//    			System.out.println(Game.getQueueList());
//    		}
//    	});
//		return play;
//    	
//    }
    
    // experimenting with a pause button
    public static Button pauseButton(Timeline timeline) {
    	Button pause = new Button("||");
    	pause.setOnAction(new EventHandler<ActionEvent>() 
    	{
    		
    		@Override
    		public void handle(ActionEvent event) {
    			
    			if (Game.getState() == 1)
    			{
    			Game.getTimer().stop();
    			ArrayList<Timeline> enemyList = Enemy.getTimelineList();
    			for (int i = 0; i < enemyList.size(); i++  )
    				enemyList.get(i).pause();
    			ArrayList<Timeline> missleList = Missles.getTimelineList();
    			for (int i = 0; i < missleList.size(); i++)
    				missleList.get(i).pause();
    			Game.setState(false);
    			}
    			else if (Game.getState() == 0 && !Game.getEnemyList().isEmpty())
    			{
    				Game.getTimer().start();
    				ArrayList<Timeline> enemyList = Enemy.getTimelineList();
    				for (int i = 0; i < enemyList.size(); i++  )
    					enemyList.get(i).play();
    				ArrayList<Timeline> missleList = Missles.getTimelineList();
    				for (int i = 0; i < missleList.size(); i++)
    					missleList.get(i).play();
    				Game.setState(true);
    			}
    		}});
		return pause;
    }
    	
    			



	
	  public static Button placeTower(int price, int hp, int dmg, int range, ArrayList<Tower> towerList, String image) 
	    {
	    	Button twr = new Button("Buy");
	        twr.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	                    	
	            		Game.getGridpane().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {  
	                        @Override
	                        public void handle(MouseEvent e) {
	                        	
	                            for(Node node: Game.getGridpane().getChildren()) {
	                                if(node instanceof Rectangle) {
	                                		if(node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY()) 
	                                     		&& e.getSceneX() <= 1050 
	                                     		&& e.getSceneY() <= 650 
	                                     		
	                                     		&& ((TextGame.getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != "X")
	                                     		&& (TextGame.getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != " ") 
	                                     		&& (TextGame.getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != "B" ))) {
	                                		 if ((TextGame.getMoney() - price) >= 0){
							                    	System.out.println(Double.toString(node.getLayoutX()) + "/" + Double.toString(node.getLayoutY()));
							                        System.out.println( "Tower at:  " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex(node));
							                    	Tower t1 = new Tower(GridPane.getColumnIndex(node), GridPane.getRowIndex(node), price, hp, dmg, range, image, Game.getGridpane(),towerList);
							                    	TextGame.editGridTower(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), "X");
							                    	System.out.println("HERE" + TextGame.getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)));
							                    	TextGame.drawGame();
							                    	towerList.add(t1);
							                    	Game.getGridpane().removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
							                    	TextGame.setHealth(Base.getHealth()); //For testing 
							                    	TextGame.setMoney(TextGame.getMoney()-price);
	            								}
	                                		 else {
	                                			 System.out.println("Insufficient Funds");
	                                			 Game.getGridpane().removeEventFilter(MouseEvent.MOUSE_CLICKED, this);
	                                		 }
	            
	                                    	}
	                              
	                                }
	                                
	                            }
	                        }
	                    });
	            }
	        });

	        return twr;
	    }

}
