import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class Base {

	private int health = 50;
	private int Xcoord = 24;
	private int Ycoord = 5;
	
	public int getHealth() {return this.health;}
	public int getX() {return this.Xcoord;}
	public int getY() {return this.Ycoord;}
	
	
	public void setHealth(int dmg) { this.health = this.health-dmg;}
	public void setX(int x) { this.Xcoord = x;}
	public void setY(int y) { this.Ycoord = y;}
	
	
	
	
	public Base(GridPane gridpane) {
		Rectangle base = new Rectangle(50, 50, Color.BLUE);
		GridPane.setConstraints(base, Xcoord, Ycoord);
		gridpane.getChildren().add(base);
		//GridVersionGame.base = base;
	}
	
	
	public void takeDamage(ArrayList<Enemy> enemyList) {
		
		Iterator<Enemy> iter = enemyList.iterator();
		while(iter.hasNext()) {
			Enemy enemy = (Enemy)iter.next();
			
			if(distanceFrom(enemy) < 25) {
				System.out.println("Test");
				setHealth(enemy.getDmg());
				enemy.setHealth(1000000);
				return;
			}
			
		}
		
		
		
	}
	
	public double distanceFrom(Enemy enemy) {
		double distance;
		double tx = this.Xcoord * 50;
		
		double ty = this.Ycoord * 50;
		double ex = enemy.getCircle().getTranslateX();
	
		double ey =  enemy.getCircle().getTranslateY();
		
		double x  = Math.abs(tx -ex);
		
		double y = Math.abs(ey - ty);
		
		distance = Math.sqrt(x*x + y*y);
	
		return distance;
	}
	
	
}
