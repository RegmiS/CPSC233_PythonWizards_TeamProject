import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Leveling {
    public double normal = 1.00;
    public double hard = 1.33;
    public double extereme = 1.66;
    public double difficulty = 1.00;
    public int totalLevels;
    public static int currentLevel = 0;
    public int points = 0;
    public String message = "";
    public int towerCost = 50;
    public int num_enemyTiers;
    public int num_bossTiers;
    public int baseHealth = 100000;
    public int enemyBaseHealth = 1000;
    public int enemyBaseDamage = 100 ;
    public int baseEnemyRadius = 5; 
    public int TILE_SIZE = 50;

    public HashMap<Integer, HashMap<String, Integer>> num_EnemiesList = new HashMap<Integer, HashMap<String, Integer>>();
    ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
    ArrayList<Color> list_colors = new ArrayList<Color>();

    // what needs to done done
    // 1. a way to decide how many enemies get spawned for each level and difficulty
    // the points assigned to them depending on the level
    // enemies need: health, color(type), points rewarded
    // the message returned that gets called everytime an enemy dies to update the stats.
    // function to be called when an enemy dies, a function to be called when enemy reaches base
    // function to check health everytime an enemy reaches base

    public Leveling(String difficulty, int levels){
        this.totalLevels = levels;
        setDifficulty(difficulty);
        setTieredEnemies();
        setTieredBosses();
        addTieredEnemies();
        populateColorList();
        Main.setHealth(baseHealth);

    }
    
    // theres a list of 7 tiered enemies, 3 bosses
    // added randomly
    // first boss round, boss gets spawned in 
    // adds 6, removes 3 of the first tier, if not then remove from the one after it, add to the one after it.

    public void setDifficulty(String difficulty){
        if(difficulty == "normal")
            this.difficulty = this.normal;
        else if(difficulty == "hard"){
            this.difficulty = this.hard;
        }
        else if(difficulty == "extereme")
            this.difficulty = this.extereme;
    }
    
    public void setTieredEnemies() {
    	this.num_enemyTiers = this.totalLevels /4 + 2;
    }
    
    public void setTieredBosses() {
    	this.num_bossTiers = this.num_enemyTiers/3+1;
    }
    
    public void addPoints(int pts){
        this.points += pts + ((currentLevel*2)/3);
    }
    public int returnPoints(){
        return this.points;
    }

    public boolean checkConditions(int health){
        // game is over
        if(health <= 0 || currentLevel > this.totalLevels){
            return false;
        }
        // game still going on
        else{
            return true;
        }
    }

    public void addTieredEnemies(){
        for(int a = 0; a < this.totalLevels; a++) {
        	HashMap<String, Integer> enemy = new HashMap<String, Integer>();
        	this.num_EnemiesList.put(a, enemy);
        }
        //testing to see if things can be added in
        
        for(int b = 0; b < this.num_EnemiesList.size(); b++) {
        	HashMap<String, Integer> round = this.num_EnemiesList.get(b);
        	int counter = 0;
        	int bosstier_counter = 0;
        	for(int c = 0; c <= b; c++) {
        		String num_currentRound = Integer.toString(c);
        		if(c==0) {
        			//if the current round is 0, then it adds the initial amount of 30 enemies
        			round.put(num_currentRound, 30);
        		}
        		else if(c < this.num_enemyTiers && c!= 0) {
        			//if the current round is not 0 and less than 7, then it increments the first tier of the 0th place enemy by -3 and adds 6 of the new tier
        			// if the first one subtracted 3 is =< 0, it just removes that key
        			round.put(num_currentRound, 6);
        			int num_firstTier = round.get(String.valueOf(counter));
        			if((num_firstTier - 3) > 0) {
        				round.put(Integer.toString(counter), num_firstTier-3);
        			}
        			else {
        				round.remove(Integer.toString(counter));
        				counter++;
        			}
        		}
        		
        		else if(c > this.num_enemyTiers-1 && (c% 4 != 2) && (c% 4 != 0)) {
        			// after the highest tier enemy spawns, after that round, that tier enemy gets added +3 while the lowest
        			// gets -3
        			String highiest_tier = String.valueOf(this.num_enemyTiers-1);
        			int num_highTierEnmy = round.get(highiest_tier);
        			round.put(Integer.toString(this.num_enemyTiers-1), num_highTierEnmy+2);
        			int num_firstTier = round.get(String.valueOf(counter));
        			if((num_firstTier - 3) > 0) {
        				round.put(Integer.toString(counter), num_firstTier-3);
        			}
        			else {
        				round.remove(Integer.toString(counter));
        				counter++;
        			}
        			
        		}
        	}
        }
        //printRoundInfo();
        //printoutNums();
    }
    
    public void printRoundInfo() {
    	for(int c = 0; c < this.num_EnemiesList.size(); c++) {
        	System.out.println(Integer.toString(c) + this.num_EnemiesList.get(c).toString());
        }
    	
    }
    
    public void populateColorList() {
    	for(int a = 0; a < this.num_enemyTiers; a++) {
    		Random r = new Random();
        	int firstnum = r.nextInt((255-0) + 0);
        	int secondnum = r.nextInt((255-0) + 0);
        	int thirdnum = r.nextInt((255-0) + 0);
        	
        	Color newcolor = Color.rgb(firstnum, secondnum, thirdnum);
        	list_colors.add(newcolor);
    	}
    	
    }
    
    public ArrayList<Enemy> returnEnemyList(int round) {
    	ArrayList<Enemy> list_enemies = new ArrayList<Enemy>();
    	int arraylistnum = round -1;
    	// returns the hashmap <string, integers> associated with the round through hashmap<round, <string,integers>
    	// once you have the hashmap, you go through each "String", "value" and add it to the arraylist according to the vals
    	
		HashMap<String, Integer> enemiesThisRound = new HashMap<String, Integer>(this.num_EnemiesList.get(arraylistnum));
		
		for (Map.Entry<String, Integer> iterate : enemiesThisRound.entrySet()) {
			String tier = iterate.getKey();
			int numEnemies = iterate.getValue();
			int tier_num = Integer.valueOf(tier);
			Color tier_color = list_colors.get(tier_num);
			int health = this.enemyBaseHealth + ((this.enemyBaseHealth * tier_num)/2);
			int damage = this.enemyBaseDamage + ((this.enemyBaseDamage * tier_num)/2);
			int radius = this.baseEnemyRadius + ((this.baseEnemyRadius * tier_num)/4);
			for(int a = 0; a < numEnemies; a++) {
				//  Enemy(int TILE_SIZE, int heath, Color color, int damage, int radius)
				Enemy newenemy = new Enemy(this.TILE_SIZE, health, tier_color, damage, radius );
				list_enemies.add(newenemy);
			}
		}
//		System.out.println(list_enemies);
		return list_enemies;
    	
    }

//    public void setEnemyHealth(int health) {
//    	
//    }

    public boolean checkTowerPlacement(int towerpoints){
        boolean possibleTower = false;
        if((this.points - towerpoints) > 0){
            possibleTower = true;
        }
        return possibleTower;
    }
    
    public static int returnCurrentLevel() {
    	return currentLevel;
    }
    public static void increaseCurrentLevel() {
    	currentLevel +=1;
    	Main.setLevel();
    }
    
//    public void printRoundMechanic() {
//    	prints out all the stuff
//    	 for (String s : map.keySet()) {
//    	      System.out.println(s);
//    	    }
//    }
//    
//    public void makeEnemies() {
//    	for (int a = 0; a < this.totalLevels; a++) {
//    		Enemy a1 = new Enemy();
//    	}
//    }
    public void printoutNums() {
    	//https://stackoverflow.com/questions/18429684/remove-a-value-from-hashmap-based-on-key
    	
    	for(int a = 0; a < this.num_EnemiesList.size(); a++) {
    		System.out.println("Round" +  Integer.toString(a) + " : ");
    		HashMap<String, Integer> round = new HashMap<String, Integer>(this.num_EnemiesList.get(a));
    		
    		for (Map.Entry<String, Integer> iterate : round.entrySet()) {
    		    System.out.println("Tier = " + iterate.getKey() + ", Number of Enemies = " + iterate.getValue());
    		}
    		System.out.println();
    	}
    }
    
    
}