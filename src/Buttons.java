import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Buttons {
	
    public static Button startButton()
{
    	Button start = new Button("Start Round");
    	start.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
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

    public static Button playButton(Timeline timeline)
    {
    	Button play = new Button("Play");
    	play.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent event) {
    			Game.getTimer().start();
    			ArrayList<Timeline> enemyList = Enemy.getTimelineList();
    			for (int i = 0; i < enemyList.size(); i++  )
    				enemyList.get(i).play();
    			ArrayList<Timeline> missleList = Missles.getTimelineList();
    			for (int i = 0; i < missleList.size(); i++)
    				missleList.get(i).play();
    		}
    	});
		return play;
    	
    }
    
    // experimenting with a pause button
    public static Button pauseButton(Timeline timeline) {
    	Button pause = new Button("||");
    	pause.setOnAction(new EventHandler<ActionEvent>() 
    	{
    		
    		@Override
    		public void handle(ActionEvent event) {
    			
    			Game.getTimer().stop();
    			ArrayList<Timeline> enemyList = Enemy.getTimelineList();
    			for (int i = 0; i < enemyList.size(); i++  )
    				enemyList.get(i).pause();
    			ArrayList<Timeline> missleList = Missles.getTimelineList();
    			for (int i = 0; i < missleList.size(); i++)
    				missleList.get(i).pause();
    	}});
		return pause;
    }

	
	  public static Button placeTower(int price, int hp, int dmg, int range, ArrayList<Tower> towerList, String image) 
	    {
	    	Button twr = new Button("Buy");
	        twr.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent event) {
	                    	
	            		Game.getGridpane().addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {  //From https://stackoverflow.com/questions/28320110/javafx-how-to-get-column-and-row-index-in-gridpane
	                        @Override
	                        public void handle(MouseEvent e) {
	                        	
	                            for(Node node: Game.getGridpane().getChildren()) {
	                                if(node instanceof Rectangle) {
	                                	
	                                	 if(node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY()) 
	                                     		&& e.getSceneX() <= 1250 
	                                     		&& e.getSceneY() <= 650 
	                                     		&& ((Game.getTextgame().getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != "X") 
	                                     		&& (Game.getTextgame().getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != " ") 
	                                     		&& (Game.getTextgame().getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)) != "B" ))) {
	                                		 if ((TextGame.getMoney() - price) >= 0){
							                    	System.out.println(Double.toString(node.getLayoutX()) + "/" + Double.toString(node.getLayoutY()));
							                        System.out.println( "Tower at:  " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex(node));
							                    	Tower t1 = new Tower(GridPane.getColumnIndex(node), GridPane.getRowIndex(node), price, hp, dmg, range, image, Game.getGridpane(),towerList);
							                    	TextGame.editGridTower(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), "X");
							                    	System.out.println("HERE" + Game.getTextgame().getTextgame().get(GridPane.getRowIndex(node)).get(GridPane.getColumnIndex(node)));
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
