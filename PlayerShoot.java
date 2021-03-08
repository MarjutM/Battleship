/**
 * @author Marjut Mikkola
 * @version 20191201
 * @since 1.6
 */
class PlayerShoot {

    ReadInput readInput = new ReadInput();
    final int EMPTY = 0;
    final int SHIP = 3;

    /**
     * Asks the player where to shoot.
     * 
     * @param computerBoard variable of the Board class
     * @return scores of the player
     */
    public int playerShooting(Board computerBoard) {
        
        // computer's board with ships.
        int[][] board = computerBoard.getShips();
        int coordinateX;
        int coordinateY;
        int scores = 0;

        // Asks for the coordinates as long as the player 
        // inputs the right coordinates. 
        while (true) {
            coordinateX = readInput.coordinateX("Give X coordinate [A-J]: ");
            coordinateY = readInput.coordinateY("Give Y coordinate [1-10]: ");
           
            // the player can shoot only one time in the same spot.
            if (board[coordinateY][coordinateX] == EMPTY 
                || board[coordinateY][coordinateX] == SHIP) {
                
                break;
            } else {
                
                System.out.println("You have already shot there." +
                                    "Give new coordinates.");
            }
        }
        
        // variable of the Ship class, 
        // marks to the computer's board if the shot was hit or miss.
        Ship ship = computerBoard.shootAt(coordinateX, coordinateY);
            
        // Displays if any ship is sunken.
        if (ship != null && ship.isSunken()) {
                
            System.out.println(ship.getName() + " is sunken (S).");
            // player gets one point
            return scores+=1;
        } else {
            return scores;
        }
    }
}

// End of file
