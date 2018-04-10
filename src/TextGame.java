import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TextGame {
	
	// all the static variables that are used throughout the game itself
    public static int HEALTH = 100;
    public static int MONEY = 300000;
    public static int LEVEL = 0;
    public static int ENEMIES = 0;
    private static int HEIGHT;
    private static int WIDTH;
    private static int TILE_SIZE;
    private static int NUM_ROWS;
    private static int NUM_COLOUMS;
    
    private static int baseRow;
    private static int baseCol;
    
    private static StringProperty HEALTHstr = new SimpleStringProperty("Health: " + Integer.toString(HEALTH));
    private static StringProperty MONEYstr = new SimpleStringProperty("Money: " + Integer.toString(MONEY));
    private static StringProperty LEVELstr = new SimpleStringProperty("Round: " + Integer.toString(LEVEL));
    
   
    
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
        TextGame.HEALTH = healthval;
        TextGame.HEALTHstr.set(" Health: " + Integer.toString(healthval));
    }
    public static void setMoney(int moneyVal) {
        TextGame.MONEY = moneyVal;
        TextGame.MONEYstr.set("Money: " + Integer.toString(moneyVal));
    }
    public static void setLevel() {
        TextGame.LEVEL += 1;
        TextGame.LEVELstr.set("Round: " + Integer.toString(TextGame.LEVEL));
    }
    public static void addEnemies() {
    	TextGame.ENEMIES += 1;
    	
    }
    
    public static void removeEnemies() { 
    	ENEMIES--;
    	}
    
    
   
    // constructor for the class( used in the grid game to create an object of this class)
    public TextGame(int height, int width, int tile_size) {
    	TextGame.HEIGHT = height;
    	TextGame.WIDTH = width;
    	TextGame.TILE_SIZE = tile_size;
    	TextGame.NUM_ROWS = TextGame.HEIGHT/TextGame.TILE_SIZE;
    	TextGame.NUM_COLOUMS = TextGame.WIDTH / TextGame.TILE_SIZE;
        
        textgame = new ArrayList<ArrayList<String>>();
        createArrayList();

    }
    
    //returns the game in case it needs to be edited or changed
    public ArrayList<ArrayList<String>> getTextgame()
    {
    	return TextGame.textgame;
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
    public static void editGridTower(int xcord, int ycord, String val) {
    	textgame.get(xcord).set(ycord, val);
    	
    }
    
    public void editGridPath(int xcord, int ycord, String val) {
    	textgame.get(xcord).set(ycord, val);
    }
    
    public void setBase(int xcord, int ycord, String val) {
    	textgame.get(xcord).set(ycord, val);
    	setBaseRow(xcord);
    	setBaseCol(ycord);
    }
    
    public void updateEnemy(int xcord, int ycord, String val) {
    	textgame.get(xcord).set(ycord, val);
    }

	public static int getBaseCol() {
		return baseCol;
	}

	public static void setBaseCol(int baseCol) {
		TextGame.baseCol = baseCol;
	}

	public static int getBaseRow() {
		return baseRow;
	}

	public static void setBaseRow(int baseRow) {
		TextGame.baseRow = baseRow;
	}
}


