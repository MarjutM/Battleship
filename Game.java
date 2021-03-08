/**
 * @author Marjut Mikkola
 * @version 20191208
 * @since 1.6
 */
class Game {

    Board board = new Board();

    Board playerBoard = new Board();
    Board computerBoard = new Board();
    
    /**
     * Used to save the scores of the computer.
     */
    private int compScores = 0;

    /**
     * Used to save the scores of the player.
     */
    private int playerScores = 0;
    
    /**
     * Asks the player to press enter to continue.
     */
    private void pressEnter() {

        System.out.println("Press ENTER to continue.");

        try {
            System.in.read();
        } catch (Exception e) {

        }
    }

    /**
     * Prints an empty line.
     */
    private void printLine() {

        System.out.println();
    }

    /**
     * Displays the rules of the game.
     */
    private void rules() {

        String rules = "This is a battleship game where you can " +
                        "play against the computer. \n" +
                        "The ships are placed to the both boards " +
                        "automatically \n" +
                        "so that they can't touch each other. \n" +
                        "The direction of the ship can be vertical or " +
                        "horizontal. \n" +
                        "There are five ships on both boards: " +
                        "Carrier (size 5), \n" +
                        "Battleship (size 4), Cruiser (size 3), " +
                        "Submarine (size 3), \n" +
                        "and Destroyer (size 2). \n" +
                        "You and the computer will shoot at each other's " +
                        "boards in turn. \n" +
                        "You and the computer earn one point per every " +
                        "sunken ship. \n" +
                        "The winner is that who has 5 points at first " +
                        "but the draw is also \n" +
                        "possible, because the computer has the final turn.";
        
        System.out.println(rules);
    }
    
    /**
     * Starts the game.
     */
    public void start() {
        
        printLine();
        rules();
        printLine();
        pressEnter();
        board.printIndent(7);
        System.out.println("PLAYER'S BOARD");
        printLine();
        playerBoard.printBoardShips();
        printLine();
        pressEnter();
        board.printIndent(6);
        System.out.println("COMPUTER'S BOARD");
        computerBoard.printBoardHits();
        printLine();
        System.out.println("Are you ready to play?");
        printLine();
        pressEnter();
    }

    /**
     * Runs the game.
     */
    public void runTheGame() {

        ComputerShoot computerShoot = new ComputerShoot();
        PlayerShoot playerShoot = new PlayerShoot();

        while (compScores < 5 && playerScores < 5) {
        System.out.println("Where do you want to shoot?");
        printLine();
        board.printIndent(6);
        System.out.println("COMPUTER'S BOARD");
        computerBoard.printBoardHits();
        printLine();
        playerScores = playerScores + playerShoot.playerShooting(computerBoard);
        printLine();
        board.printIndent(6);
        System.out.println("COMPUTER'S BOARD");
        printLine();
        computerBoard.printBoardHits(); 
        printLine();
        System.out.println("PLAYER SCORES: " + playerScores);
        printLine();
        pressEnter();
        System.out.println("Computer shoots:");
        compScores = computerShoot.aiShooting(playerBoard);
        printLine();
        board.printIndent(7);
        System.out.println("PLAYER'S BOARD");
        playerBoard.printBoardShips();   
        printLine();
        System.out.println("COMPUTER SCORES: " + compScores);
        printLine();  
        pressEnter();  
        }  
    }

    /**
     * Ends the game and displays who wins.
     */
    public void endTheGame() {
        
        if (compScores == 5) {
            
            System.out.println("Game over! You lost.");
            printLine();
        } else if (compScores == 5 && playerScores == 5) {
            
            System.out.println("End of the game, it is a draw.");
            printLine();
        } else {
            
            System.out.println("You win!");
            printLine();
        }
    }
}

// End of file.
