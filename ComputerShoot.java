/**
 * @author Marjut Mikkola
 * @version 20191116
 * @since 1.6
 */
class ComputerShoot {  

    final int MISS = 4;
    final int HIT = 5;
    final int SHIP = 3;
    final int EMPTY = 0;
    
    /**
     * Resolves a coordinate randomly.
     * 
     * @return coordinate (0-9)
     */
    private int randomCoordinate() {
        int coordinate = (int)(Math.random() * 10);
                
        return coordinate;
    }

    /**
     * Shoots at the randomly resolved coordinates on player's board.
     * 
     * @param playerBoard variable of the Board class
     */
    private void randomShooting(Board playerBoard) {
        
        // player's board with ships.
        int[][] board = playerBoard.getShips();
        int coordinateX;
        int coordinateY;
        
        // resolves coordinates randomly as long as there is a square 
        // where the computer haven't shot yet.
        while (true) {
            coordinateX = randomCoordinate();
            coordinateY = randomCoordinate();
            
            if (board[coordinateY][coordinateX] == EMPTY 
                || board[coordinateY][coordinateX] == SHIP) {
                break;
            } 
        }

        // Variable of the Ship class, 
        // Marks to the player's board if the shot was hit or miss.
        Ship ship = playerBoard.shootAt(coordinateX, coordinateY);
        
        // Displays if any ship is sunken.
        if (ship != null && ship.isSunken()) {
                
            System.out.println(ship.getName() + " is sunken.");
        }
    }

    /**
     * Determines should computer use aiShooting or randomShooting.
     * 
     * @param playerBoard variable of the Board class
     * @return computer's scores.
     */
    public int aiShooting(Board playerBoard) {

        if (!doAiShooting(playerBoard)) {

            randomShooting(playerBoard);
        }

        return playerBoard.getScores();
    }

    /**
     * Enables computer to shoot with AI.
     * 
     * <p> Examines is there any hits on player's board.
     * 
     * @param playerBoard variable of the Board class
     * @return true = ai shooting, false = random shooting
     */
    private boolean doAiShooting(Board playerBoard) {
        int coordinateX = 0;
        int coordinateY = 0;
        boolean found = false;
        
        // player's board with ships.
        int[][] board = playerBoard.getShips();
        
        outer:
        // Checks the board if there is any hit.
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                coordinateX = col;
                coordinateY = row;
                
                // hit found
                if (board[row][col] == HIT) {

                    // checks the above of the hit, direction known
                    if (row > 0) {
                        coordinateY--;
                       
                        while (coordinateY >= 0) {
                            if (board[coordinateY][col] == EMPTY 
                                || board[coordinateY][col] == SHIP) {
                                
                                found = true;
                                break outer;
                            } else if (board[coordinateY][col] == MISS) {
                                
                                break;
                            } else {
                                
                                coordinateY--;
                            }
                        }
                        
                        coordinateY = row;
                    }
                    // checks the below the hit, direction known
                    if (row < 9) {
                        coordinateY++;
                        
                        while (coordinateY <= 9) {
                            if (board[coordinateY][col] == EMPTY 
                                || board[coordinateY][col] == SHIP) {
                                
                                found = true;
                                break outer;
                            } else if (board[coordinateY][col] == MISS) {
                                
                                break;
                            } else {
                                
                                coordinateY++;
                            }
                        }
                        
                        coordinateY = row;
                    }
                    
                    // checks the left side of the hit direction known
                    if (col > 0) {
                        coordinateX--;
                            
                        while (coordinateX >= 0) {
                            if (board[row][coordinateX] == EMPTY 
                                || board[row][coordinateX] == SHIP) {
                                
                                found = true;
                                break outer;
                            } else if (board[row][coordinateX] == MISS) {
                                    
                                break;
                            } else {
                                coordinateX--;
                            }
                        }
                        
                        coordinateX = col;
                    }

                    // checks the rigth side of the hit, direction known
                    if (col < 9) {
                        coordinateX++;
                        
                        while (coordinateX <= 9) {
                            if (board[row][coordinateX] == EMPTY 
                                || board[row][coordinateX] == SHIP) {
                                
                                found = true;
                                break outer;
                            } else if (board[row][coordinateX] == MISS) {
                                
                                break;
                            } else {
                                coordinateX++;
                            }
                        }
                        
                        coordinateX = col;
                    }

                    // checks the above of the hit, direction unknown
                    if (row > 0) {

                        if (board[row - 1][col] == EMPTY 
                            || board[row - 1][col] == SHIP) {
                            
                            coordinateY--;
                            found = true;
                            break outer;
                        }
                    } 

                    // checks the below of the hit, direction unknown
                    if (row < 9) {

                        if (board[row + 1][col] == EMPTY 
                            || board[row + 1][col] == SHIP) {
                            
                            coordinateY++;
                            found = true;
                            break outer;
                        }
                    }

                    // checks the left side of the hit, direction unknown
                    if (col > 0) {

                        if (board[row][col - 1] == EMPTY 
                            || board[row][col - 1] == SHIP) {
                            
                            found = true;
                            coordinateX--;
                            break outer;
                        }
                    }

                    // checks the right side of the hit, direction unknown.
                    if (col < 9) {

                        if (board[row][col + 1] == EMPTY 
                            || board[row][col + 1] == SHIP) {
                            found = true;
                            coordinateX++;
                            break outer;
                        }
                    }
                }
            }
        }
        
        // hit found
        if (found) {

            // variable of the Ship class, 
            // marks to the player's board if the shot was hit or miss.
            Ship ship = playerBoard.shootAt(coordinateX, coordinateY);
            
            // Displays if any ship is sunken.
            if (ship != null && ship.isSunken()) {
                
                System.out.println(ship.getName() + " is sunken (S).");
            }
        }

        return found;
    }
}

// End of file
