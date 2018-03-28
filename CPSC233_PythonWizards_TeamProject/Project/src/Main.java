import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Main {
	
	// all the static variables that are used throughout the game itself
    public static int HEALTH = 100;
    public static int MONEY = 2500;
    public static int LEVEL = 0;
    public static int ENEMIES = 0;
    private static int HEIGHT;
    private static int WIDTH;
    private static int TILE_SIZE;
    private static int NUM_ROWS;
    private static int NUM_COLOUMS;
    
    private static StringProperty HEALTHstr = new SimpleStringProperty("Health: " + Integer.toString(HEALTH));
    private static StringProperty MONEYstr = new SimpleStringProperty("Money: " + Integer.toString(MONEY));
    private static StringProperty LEVELstr = new SimpleStringProperty("Level: " + Integer.toString(LEVEL));
    
    // public arraylist that hosts all of the grid game
    public static ArrayList< ArrayList<String>> textgame;
    
    // all the getters and setters for the game
    
   public static StringProperty getHealthStr() {
	   return HEALTHstr;
   }
   
   public static StringProperty getMoneyStr() {
	   return MONEYstr;
   }
   
   public static StringProperty getLevelStr() {
	   return LEVELstr;
   }
   
   public static int getHealth() {
	   return HEALTH;
   }
   
   public static int getMoney() {
	   return MONEY;
   }
   
   public static int getLevel() {
	   return LEVEL;
   }
    
    public static void setHealth(int healthval) {
        Main.HEALTH = healthval;
        Main.HEALTHstr.set("HP: " + Integer.toString(healthval));
    }
    public static void setMoney(int moneyVal) {
        Main.MONEY = moneyVal;
        Main.MONEYstr.set("Money: " + Integer.toString(moneyVal));
    }
    public static void setLevel(int levelVal) {
        Main.LEVEL = levelVal;
        Main.LEVELstr.set("Level: " + Integer.toString(levelVal));
    }
    public void addEnemies() {
    	Main.ENEMIES += 1;
    }
    
    public void removeEnemies() {
    	Main.ENEMIES -= 1;
    }
   
    // constructor for the class( used in the grid game to create an object of this class)
    public Main(int height, int width, int tile_size) {
    	Main.HEIGHT = height;
    	Main.WIDTH = width;
    	Main.TILE_SIZE = tile_size;
    	Main.NUM_ROWS = Main.HEIGHT/Main.TILE_SIZE;
    	Main.NUM_COLOUMS = Main.WIDTH / Main.TILE_SIZE;
        
        textgame = new ArrayList<ArrayList<String>>();
        createArrayList();

    }
    
    //returns the game in case it needs to be edited or changed
    public ArrayList<ArrayList<String>> getTextgame()
    {
    	return Main.textgame;
    }

    // creates the arraylist to use for the game in the constructor
    public static void createArrayList() {
        for(int a = 0; a < NUM_ROWS; a++)
            textgame.add(new ArrayList<String>());

        for( int b = 0; b < NUM_ROWS; b++){
            for(int c = 0; c < NUM_COLOUMS; c++){
                textgame.get(b).add("#");
            }
        }
    }

    //draws the game using a simple loop
    public static void drawGame() {
    	System.out.println();
        for(int a = 0; a < NUM_ROWS; a++){
        	String row = "";
            for(int b = 0; b < NUM_COLOUMS; b++){
            	row += textgame.get(a).get(b);
            }
            System.out.println(row);
        }

        System.out.print("Health = " + HEALTH + " Level = " + LEVEL + " Enemies = " + ENEMIES);

        System.out.println();
        for(int d = 0; d<NUM_COLOUMS; d++) {
        	System.out.print("*");
        }
        System.out.println("");
    }
    
    //editing the game, enemies and paths
    public void editGridTower(int xcord, int ycord, String val) {
    	textgame.get(xcord).set(ycord, val);
    	
    }
    
    public void editGridPath(int xcord, int ycord, String val) {
    	textgame.get(xcord).set(ycord, val);
    }
    
    public void setBase(int xcord, int ycord, String val) {
    	textgame.get(xcord).set(ycord, val);
    }
    
    public void updateEnemy(int xcord, int ycord, String val) {
    	textgame.get(xcord).set(ycord, val);
    }
}


