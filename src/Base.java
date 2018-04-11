import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class Base {

	private static int health = TextGame.getHealth();
	private int Xcoord = TextGame.getBaseCol();;
	private int Ycoord = TextGame.getBaseRow();;
	
	public static int getHealth() {return health;}
	public int getX() {return this.Xcoord;}
	public int getY() {return this.Ycoord;}
	
	
	public static void setHealth(int dmg) { 
		if (health - dmg >= 0) {
			health = health-dmg;
			TextGame.setHealth(TextGame.getHealth()-dmg);
			}
		}
	public void setX(int x) { this.Xcoord = x;}
	public void setY(int y) { this.Ycoord = y;}
	
	
	
	
	public Base(GridPane gridpane) {
		Rectangle base = new Rectangle(50, 50);
		ImageLoader.setImage("uofclogo.png", base);
		GridPane.setConstraints(base, Xcoord, Ycoord);
		gridpane.getChildren().add(base);
		//GridVersionGame.base = base;
	}
	
	
//	public void takeDamage(ArrayList<Enemy> enemyList) {
//		
//		Iterator<Enemy> iter = enemyList.iterator();
//		while(iter.hasNext()) {
//			Enemy enemy = (Enemy)iter.next();
//			
//			if(distanceFrom(enemy) < 25) {
//				System.out.println("Test");
//				setHealth(enemy.getDmg());
//				enemy.setHealth(1000000);
//				return;
//			}
//			
//		}
//		
//		
//		
//	}
	
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