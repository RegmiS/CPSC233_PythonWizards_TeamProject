import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class HighScore {
	private static String fileToWriteOnto = "output.txt";
	private static HashMap<String, BigInteger> scores = new HashMap<String, BigInteger>();
	private static String name;
	private static String highscorestring;
	
	public static String getName() {
		return name;
	}
	
	public static void setName(String s) {
		name = s;
	}
	
	public HighScore() {
		fileToWriteOnto = "output.txt";
	}
	
	public static void writeFile(String output, String newScore) {
		File outputFile = new File(output);
		PrintWriter pw = null;
		try { pw = new PrintWriter(new FileWriter(outputFile, true)); } 
		catch (IOException e)
		{
			System.out.println("Cannot open file to write");
			return;
		}
		pw.println(newScore);
		pw.close();
	}
	
	public void clearText(String output) {
		PrintWriter pw = null;
		try { pw = new PrintWriter(new FileWriter(output)); }
		catch (IOException e) {
			System.out.println("Cannot open file to write");
			return;
		}
		pw.print("");
		pw.close();
	}
	
	public static void readFile(String output) {
		Scanner scan = null;
		try { scan = new Scanner(new FileReader(output));}
		catch(IOException e) {
			System.out.println("cannot open file to read");
			scan.close();
		}
		
		while (scan.hasNextLine()) {
            String nextline = scan.nextLine();
            List<String> lst = Arrays.asList(nextline.split(" "));
            String score = lst.get(lst.size() - 2);
            BigInteger number = new BigInteger(score);
            String name = lst.get(0) + " " + lst.get(1);
            scores.put(name, number);
            
		}
	}
	
	public static void sortValues() {
		List<BigInteger> values = new ArrayList<BigInteger>();
		
		for (Entry<String, BigInteger> iterate : scores.entrySet()) {
			String name = iterate.getKey();		
			BigInteger Score = iterate.getValue();
			values.add(Score);		
		}

		
		Collections.sort(values);
		Collections.reverse(values);
		
		ArrayList<String> testingsorting = new ArrayList<String>();
		for(int a =0; a < values.size(); a++) {
			BigInteger sortedScores = values.get(a);
			//System.out.println(sortedScores);

			for( String nameInScore : scores.keySet()) {
				if(scores.get(nameInScore).equals(sortedScores)) {
					//newScores.put(nameInScore, sortedScores);
					//testing.put(nameInScore, sortedScores);
					testingsorting.add(nameInScore);
				}
			}
			
		}
		HashMap<String, Integer> newsortedHashMap = new HashMap<String, Integer>();
		String testingname = testingsorting.get(0);
		BigInteger scoretoreturn = scores.get(testingname);
		highscorestring = testingname + " " + scoretoreturn + " pts";
}
	
	public void printHashMap() {
		System.out.println(scores.toString());
	}
	
	public static String returnHighScore(int points) {
		String nametowrite = name + " " + points + " pts";
		writeFile(fileToWriteOnto, nametowrite);
		readFile(fileToWriteOnto);
		sortValues();
		return highscorestring;
	}

}


