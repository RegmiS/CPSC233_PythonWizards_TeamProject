import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Tower{
	
	private Rectangle rectangle;
	private int ID;
	private int damage;
	private int health;
	private int price;
	private int upgradePrice;
	private int xCoord;
	private int yCoord;
	private int range;
	private int level = 0;
	private int cost;
	private Pane pane;
	
	public int getUPrice() {return this.upgradePrice;}
	public int getDMG() {return this.damage;}
	public int getHP() {return this.health;}
	public int getPrice() {return price;}
	public int getX() {return this.xCoord;}
	public int getY() {return this.yCoord;}
	public int getCost() {return this.cost;}
	public int getLevel() {return this.level;}
	public int getRange() {return this.range;}
	public Pane getPane() {return this.pane;}
	
	
	
	private void setPane(Pane pane) {
		this.pane = pane;
	}
	private void setUPrice(int price) {
		this.upgradePrice = price;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	private void setLevel() {
		this.level = getLevel() + 1;
	}

	private void setXCoord(int x) {
		this.xCoord = x;
	}
	
	private void setYCoord(int y) {
		this.yCoord = y;
	}
	
	private void setPrice(int price) {
		this.price = price;
	}
	
	private void setHP(int hp) {
		this.health = hp;
	}
	
	private void setDMG(int dmg) {
		this.damage = dmg;
	}
	

	
	private void setRange(int range) {
		this.range = range;
	}
	
	
	public Tower(int xc, int yc, int price, int hp, int dmg, int range, String filename, GridPane canvas, ArrayList<Tower> towerList)  {
		
		//setID(id);
		setXCoord(xc);
		setYCoord(yc);
		setPrice(price);
		setHP(hp);
		setDMG(dmg);
		setLevel();
		setRange(range);
		setPane(canvas);
		setUPrice(getPrice() *2);
		setCost(price);
		
		Platform.runLater(new Runnable() {
			@Override 
			public void run() { //From https://stackoverflow.com/a/17395191/8645685
				rectangle = new Rectangle(35, 35);
				//rectangle.addEventFilter(MouseEvent.MOUSE_CLICKED, openStats);
				GridPane.setConstraints(rectangle, xc, yc);
				GridPane.setHalignment(rectangle, HPos.CENTER);
				FileInputStream inputStream;
				try {
					inputStream = new FileInputStream("res/images/" + filename);
					Image image = new Image(inputStream);
					ImagePattern img = new ImagePattern(image);
					rectangle.setFill(img);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				canvas.getChildren().add(rectangle);
				
				Label label = new Label();
				
				//right click on towers for menu
				
		        rectangle.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
		 
		            @Override
		            public void handle(ContextMenuEvent event) {
		            	ContextMenu contextMenu = new ContextMenu();
		            	
		            	
		            	MenuItem item1 = new MenuItem("Upgrade! (" + getUPrice() + ")");
		 		        item1.setOnAction(new EventHandler<ActionEvent>() {
		 		        	
		 		            @Override
		 		            public void handle(ActionEvent event) {
		 		                label.setText("Select Menu Item 1");
		 		                if ((TextGame.getMoney() - getUPrice()) >= 0) {
		 		                
		 		                	upgrade();
		 		                }
		 		            }
		 		        });
		 		        MenuItem item2 = new MenuItem("Sell! (" + getCost()/2 +")");
		 		        item2.setOnAction(new EventHandler<ActionEvent>() {
		 		 
		 		            @Override
		 		            public void handle(ActionEvent event) {
		 		                sell(towerList);
		 		            }
		 		        });
		            	
		            	String statsString = "Level:            " + getLevel() + "\n" +
								 "Range:          " + getRange() + "\n" +
								 "Damage:       " + getDMG() + "\n" ;
		            	
		            	MenuItem stats = new MenuItem(statsString);
		            	contextMenu.getItems().addAll(stats,item1, item2);
		                contextMenu.show(rectangle, event.getScreenX(), event.getScreenY());
		            }
		        });
				
				
				
				
				
				
				
			}
		});
		
		
		
	}
	
	
	
	
	
	/**Upgrades the tower
	 * 
	 */
	public void upgrade() {
	
		setLevel();
		
		TextGame.setMoney(TextGame.getMoney()-getUPrice());// cost money to upgrade
		setCost(getUPrice()); //updates total cost of tower
		setUPrice(2* getUPrice()); // next upgrade doubles in cost
		
		
		//upgrades increase range and damage of tower
		int newRange = (int) (getRange()*1.05); 
		setRange(newRange);
		int newDamage = (int) (getDMG()*1.1);
		setDMG(newDamage);
		
	
		
		
		
	}
	
	
	/**Sells the tower for 1/2 the price?
	 * 
	 */
	public void sell(ArrayList<Tower> towerList) {
		
		TextGame.setMoney(TextGame.getMoney()+getCost()/2); //Adds have the cost of the tower to money

		TextGame.editGridTower(getY(),getX(),"#"); //removes tower from text version

		this.pane.getChildren().remove(this.rectangle); //removes tower from veiw
	}
	
	
	/**Check in range
	 * checks the list until the first enemy is in range, the does dmg to that enemy
	 * @param enemyList list of all the enemies on the board
	 */
	public void checkInRange(ArrayList<Enemy> enemyList) {

		Iterator<Enemy> iter = enemyList.iterator();
		while(iter.hasNext()) {
			Enemy enemy = (Enemy)iter.next();
			
			if(distanceFrom(enemy) < range) {
				Missles m1 = new Missles(this.pane, enemy, getX(),getY(), this.damage);
				enemy.setHealth(this.damage);
				return;
			}
			
		}
		
	}
	
	
	
	/**
	 * @param enemy, takes in a single enemy and finds the distance from a tower
	 * @return the distance from a tower
	 */
	public double distanceFrom(Enemy enemy) {
		double distance;
		double tx = this.xCoord * 50;
		
		double ty = this.yCoord * 50;
		double ex = enemy.getCircle().getTranslateX();
	
		double ey =  enemy.getCircle().getTranslateY();
		
		double x  = Math.abs(tx -ex);
		
		double y = Math.abs(ey - ty);
		
		distance = Math.sqrt(x*x + y*y);
	
		return distance;
	}
	
}
