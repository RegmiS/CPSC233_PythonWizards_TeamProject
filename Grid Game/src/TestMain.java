public class TestMain {

    public static void main (String arghs[]){
        Main tester = new Main(720,1280,50);
        tester.createArrayList();
        tester.drawGame();
        
        tester.editGridPath(2, 0, "-");
        tester.editGridPath(2, 1, "-");
        tester.drawGame();
        
        /*
        enemyPath("right", 2, 0, 5);
		enemyPath("down", 2, 5, 5);
		enemyPath("left", 7, 5, 3);
		enemyPath("down", 7, 2, 3);
		enemyPath("right", 10, 2, 10);
		enemyPath("up", 10, 12, 5);
		enemyPath("right", 5, 12, 12);
		*/


    }
}
