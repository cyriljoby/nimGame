import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private boolean turn;

    public Game() {
        this.player1 = null;
        this.player2 = null;
        this.turn = false;
    }
    Scanner scanner = new Scanner(System.in);

    public void setUp() {
        do {
            play();
            System.out.println("Do you want to play again? (yes/no)");
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        } while (true);

        player1.closeScanner();
        player2.closeScanner();
    }

    public void play() {
        Board.populate();
        Board.displayBoard();

        System.out.println("What game mode would you like to play: AI or multiplayer");

        player1 = new Player("Player 1", false);
        String gameMode = scanner.nextLine();

        if ("AI".equalsIgnoreCase(gameMode)) {
            System.out.println("You chose AI.");
            System.out.println("Player 1 name:");
            String name1 = scanner.nextLine();
            player1 = new Player(name1, false);

            player2 = new Player("AI", true);
        } else if ("multiplayer".equalsIgnoreCase(gameMode)) {
            System.out.println("You chose multiplayer mode.");
            System.out.println("Player 1 name:");
            String name1 = scanner.nextLine();
            player1 = new Player(name1, false);
            System.out.println("Player 2 name:");
            String name2 = scanner.nextLine();
            player2 = new Player("Player 2", false);
        } else {
            System.out.println("Invalid game mode. Please choose either AI or multiplayer.");
            return;
        }

        turn = new Random().nextBoolean();

        while (!isGameOver()) {
            Board.displayBoard();

            if (turn) {
                int stonesToRemove = player1.getPlayerMove();
                Board.removeStones(stonesToRemove);
            } else {
                int stonesToRemove = player2.getPlayerMove();
                System.out.println(player2.getName() + " removes " + stonesToRemove + " stones.");
                Board.removeStones(stonesToRemove);
            }

            switchPlayer();
        }

        decideWinner();
    }

    private boolean isGameOver() {
        return Board.isBoardEmpty();
    }

    private void decideWinner() {
        if (isGameOver()) {
            String winner = turn ? player2.getName() : player1.getName();
            System.out.println("Game Over!" + winner + " wins!");
        }
    }

    private void switchPlayer() {
        turn = !turn;
    }
}
