/**
 * @author Marjut Mikkola
 * @version 20191115
 * @since 1.6
 */
class Main {
    
    /**
     * Runs the game Battleship.
     * 
     * @param args Command line parameters. Do not used.
     */
    public static void main(String[] args) {
        
        Game game = new Game();

        game.start();

        game.runTheGame();

        game.endTheGame();
    }  
}
        
// End of file
             