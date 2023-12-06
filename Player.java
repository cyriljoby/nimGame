import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private boolean isAI;
    private Scanner scanner = new Scanner(System.in);

    public Player(String name, boolean isAI) {
        this.name = name;
        this.isAI = isAI;
    }

    public String getName() {
        return name;
    }

    public int getPlayerMove() {
        if (isAI) {
            return getAIMove();
        } else {
            System.out.println(name + " move");
            int maxStonesToRemove = Board.getPileSize() / 2;
            System.out.println("Enter the number of stones to remove (1-" + maxStonesToRemove + "): ");
            int stonesToRemove = scanner.nextInt();

            // Add input validation to ensure a valid move
            while (stonesToRemove > maxStonesToRemove) {
                System.out.println("Invalid move. Enter a number between 1 and " + maxStonesToRemove + ".");
                stonesToRemove = scanner.nextInt();
            }
            return stonesToRemove;
        }
    }

    private int getAIMove() {
        int pileSize = Board.getPileSize();

        // Check if the current pile size is already in the form 2^n-1
        if ((pileSize & (pileSize + 1)) == 0) {
            // If yes, the AI player wins by taking the entire pile
            return pileSize;
        } else {
            int nearestPowerOfTwo = Integer.highestOneBit(pileSize);
            int sticksToRemove = pileSize - nearestPowerOfTwo + 1;
            int maxStonesToRemove = Board.getPileSize() / 2;

            return Math.min(sticksToRemove, maxStonesToRemove);
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
