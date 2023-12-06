import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private boolean turn;
    private String player1Name;
    private String player2Name;


    // Constructor to initialize the game with a board
    public Game() {
        this.board = board;
        this.turn = turn;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        
    }

    // Method to play the game
        public void play() {
        Board.displayBoard();

        System.out.println("What game mode would you like to play: AI or multiplayer");

        // Use a Scanner to get user input
        Scanner scanner = new Scanner(System.in);
        String gameMode = scanner.nextLine();


        // Now you can use the value of gameMode to determine the game flow
        if ("AI".equalsIgnoreCase(gameMode)) {
            System.out.println("You chose AI.");
            while (!isGameOver()) {
                // Display the current state of the board
                Board.displayBoard();

                if (turn) {
                    // Human player's move
                    int stonesToRemove = getPlayerMove();
                    Board.removeStones(stonesToRemove);
                } else {
                    // AI player's move (random move for example)
                    int stonesToRemove = getRandomAIMove();
                    System.out.println(player2Name + " removes " + stonesToRemove + " stones.");
                    Board.removeStones(stonesToRemove);
                }

                // Switch to the next player
                switchPlayer();
            }


        } else if ("multiplayer".equalsIgnoreCase(gameMode)) {
            System.out.println("You chose multiplayer mode.");

            System.out.println("Player 1 name:");
            player1Name = scanner.nextLine();
            System.out.println("Player 2 name:");
            player2Name = scanner.nextLine();

            turn = new Random().nextBoolean();
            while (!isGameOver()) {
                // Display the current state of the board
                Board.displayBoard();

                // Get the player's move
                int stonesToRemove = getPlayerMove();

                // Update the board based on the player's move
                board.removeStones(stonesToRemove);

                // Switch to the next player
                switchPlayer();
            }
            if (isGameOver()) {
                String winner;
                if (turn){
                    winner = player2Name;
                }
                else{
                    winner = player1Name;
                }

                System.out.println("Game Over!"+  winner + " wins!");
            }

        } else {
            System.out.println("Invalid game mode. Please choose either AI or multiplayer.");
        }

        // Close the Scanner to prevent resource leaks
        scanner.close();
    }


    // You need to implement these methods based on your game's rules
    private boolean isGameOver() {
        return Board.isBoardEmpty();
    }

    private int getPlayerMove() {
        // Implement logic to get the player's move
        if (turn){
            System.out.println(player1Name + " move");
        }
        else{
            System.out.println(player2Name +" move");
        }
        Scanner scanner = new Scanner(System.in);
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
    
    private int getRandomAIMove() {
        // Implement logic for the AI player's move
        // Here, we generate a random number between 1 and half of the pile size
        int maxStonesToRemove = Math.min(3, Board.getPileSize() / 2);
        return new Random().nextInt(maxStonesToRemove) + 1;
    }

    private void switchPlayer() {
        turn = !turn;
    }
}
