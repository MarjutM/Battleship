/**
 * @author Marjut Mikkola
 * @version 20191116
 * @since 1.6
 */
class SetShips {    

    // private int EMPTY = 0;
    private int VERTICAL = 1;
    private int HORIZONTAL = 2;   
    private int SHIP = 3;  
    
    Ship carrier = new Ship("Carrier", 5);
    Ship battleship = new Ship("Battleship", 4);
    Ship cruiser = new Ship("Cruiser", 3);
    Ship submarine = new Ship("Submarine", 3);
    Ship destroyer = new Ship("Destroyer", 2);

    // Creates an array about ships.
    Ship[] ships = { carrier, battleship, cruiser, submarine, destroyer };
    
    /**
     * Resolves a coordinate randomly.
     * 
     * @return randomly resolved coordinate, int value
     */
    public int randomCoordinate() {
        int coordinate = (int)(Math.random() * 10);
                
        return coordinate;
    }

    /**
     * Resolves a coordinate randomly and take into account that ship don't go over board.
     * 
     * @param shipSize int value of what is the size of ship.
     * @return randomly resolved coordinate, int value.
     */
    public int randomCoordinate(int shipSize) {
        int coordinate = (int)(Math.random() * (10 - shipSize + 1));

        return coordinate;
    }

    /**
     * Resolves the direction that the ship is set.
     * 
     * @return int value 1 or 2
     */
    public int randomDirection() {
        int direction = (int)(Math.random() * 2 + 1);

        return direction;
    }

    public void setShip(int[][] board) {
                          
        int direction;
        int coordinateX;
        int coordinateY;
        int shipSize;

        // Sets ships to the board.
        for (Ship ship : ships) {
            shipSize = ship.getSize();
            direction = randomDirection();
            ship.setDirection(direction);
            
            // while loop checks where it is possible to set the ship.
            outer:
            while (true) {
                coordinateX = randomCoordinate();
                coordinateY = randomCoordinate();
                
                // Sets ship if direction is horizontal.
                if (direction == HORIZONTAL) {
                    coordinateX = randomCoordinate(shipSize);
                    int row = coordinateY;

                    
                    // Checks if the square before starting coordinate is empty.
                    if (coordinateX > 0 && board[row][coordinateX - 1] == SHIP) {
                        continue outer;
                    }
                    
                    // Checks if there is already a ship on that location or side.
                    for (int col = coordinateX; col < coordinateX + shipSize; col++) {
                        
                        // above
                        if (row > 0 && board[row - 1][col] == SHIP) {
                            continue outer;
                        }
                        
                        // below
                        if (row < 9 && board[row + 1][col] == SHIP) {
                            continue outer;
                        }

                        // location
                        if (board[row][col] == SHIP) {
                            continue outer;
                        }
                    }

                    // Checks if the square after the setting ship is empty.
                    if (coordinateX + shipSize < 10 && board[row][coordinateX + shipSize] == SHIP) {
                        continue outer;
                    } 
                    
                    // Sets the ship on board horizontal.
                    for (int col = coordinateX; col < coordinateX + shipSize; col++) {
                        board[row][col] = SHIP;
                    }
                    
                // Sets ship if direction is vertical.
                } else if (direction == VERTICAL) {
                    coordinateY = randomCoordinate(shipSize);
                    int col = coordinateX;
                    
                    // Checks if there is already a ship in square before starting coordinate.
                    if (coordinateY > 0 && board[coordinateY - 1][col] == SHIP) {
                        continue outer;
                    }
                    
                    // Checks if there is already a ship on that location or side.
                    for (int row = coordinateY; row < coordinateY + shipSize; row++) {
                       
                        // above
                        if (col > 0  && board[row][col - 1] == SHIP) {
                            continue outer;
                        }
                        
                        // below
                        if (col < 9 && board[row][col + 1] == SHIP) {
                            continue outer;
                        }
                        
                        // location
                        if (board[row][col] == SHIP) {
                            continue outer;
                        }
                    }

                    //Checks if there is already a ship in square after the setting ship.
                    if (coordinateY + shipSize < 10 && board[coordinateY + shipSize][col] == SHIP) {
                        continue outer;
                    }
                    
                    // Sets the ship on board vertical.
                    for (int row = coordinateY; row < coordinateY + shipSize; row++) {
                            board[row][col] = SHIP;
                    }
                } 
                
                ship.setCoordinateX(coordinateX);
                ship.setCoordinateY(coordinateY);
                break;
            } 
        }    
    }     
}
    
// End of file
