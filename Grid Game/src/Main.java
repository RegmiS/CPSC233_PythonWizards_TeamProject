import java.awt.List;
import java.util.ArrayList;

import java.util.ArrayList;

public class Main {

    public static int HEALTH = 0;
    public static int LEVEL = 0;
    public static int ENEMIES = 0;
    private static int HEIGHT;
    private static int WIDTH;
    private static int TILE_SIZE;
    private static int NUM_ROWS;
    private static int NUM_COLOUMS;

    public static ArrayList< ArrayList<String>> textgame;

    public void setHealth(int healthval) {
        this.HEALTH = healthval;
    }
    public void setLevel(int levelVal) {
        this.LEVEL = levelVal;
    }
    public void setEnemies(int enemieNum) {
        this.ENEMIES = enemieNum;
    }

    public Main(int height, int width, int tile_size) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.TILE_SIZE = tile_size;
        this.NUM_ROWS = this.HEIGHT/this.TILE_SIZE;
        this.NUM_COLOUMS = this.WIDTH / this.TILE_SIZE;
        
        textgame = new ArrayList<ArrayList<String>>();
        createArrayList();

    }
    
    public ArrayList<ArrayList<String>> getTextgame()
    {
    	return this.textgame;
    }

    public static void createArrayList() {
        for(int a = 0; a < NUM_ROWS; a++)
            textgame.add(new ArrayList<String>());

        for( int b = 0; b < NUM_ROWS; b++){
            for(int c = 0; c < NUM_COLOUMS; c++){
                textgame.get(b).add("#");
            }
        }
    }

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


