import java.util.Random;

public class Board {
    private static int pileSize; // Assuming a single pile for simplicity

    // This method populates the game board with an initial configuration
    public static void populate() {
        Random random = new Random();
        pileSize = random.nextInt(41) + 10;
        Board.displayBoard();
    }

    public static void displayBoard() {
        System.out.println("Current Pile Size: " + pileSize);
    }
    public static void removeStones(int stonesToRemove) {
        pileSize -= stonesToRemove;
    }

    public static int getPileSize(){
        return pileSize;
    }
    public static boolean isBoardEmpty() {
        if (pileSize>1){
            return false;
        }
        else{
            return true;
        }
    }

    // Additional methods and attributes can be added based on your game's requirements
}
