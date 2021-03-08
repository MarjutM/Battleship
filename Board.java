/**
 * @author Marjut Mikkola
 * @version 20191114
 * @since 1.6
 */
class Board {

    final int EMPTY = 0;
    final int SHIP = 3;
    final int MISS = 4;
    final int HIT = 5;
    final int VERTICAL = 1;
    final int HORIZONTAL = 2; 
    final int SUNKEN = 6;  
    
    /**
     * Creates a board (10*10) for ships. 
     */
    private int[][] ships = createBoard();

    /** 
     * Creates a board(10*10) for hits and misses.
     */
    private int[][] hits = createBoard();

    Ship carrier = new Ship("Carrier", 5);
    Ship battleship = new Ship("Battleship", 4);
    Ship cruiser = new Ship("Cruiser", 3);
    Ship submarine = new Ship("Submarine", 3);
    Ship destroyer = new Ship("Destroyer", 2);
    
    /**  
     * An array that includes all ship objects and their facts.
     */
    Ship[] shipObjects = { carrier, battleship, cruiser, submarine, destroyer };

    /**
     * Initializes a board with ships, constructor.
     */
    public Board() {
        setShip();
    }
    
    /** 
     * Returns a board with placed ships.
     * 
     * @return board with ships
     */
    public int[][] getShips() {
        return ships;
    }

    /**
     * Returns a board with hits and misses.
     * 
     * @return board with hits and misses
     */
    public int[][] getHits() {
        return hits;
    }
    
    /**
     * Returns scores.
     * 
     * @return scores, int value
     */
    public int getScores() {

        int scores = 0;

        for (Ship ship: shipObjects) {

            if (ship.isSunken()) {

                scores++; // one point per every sunken ship
            }
        }

        return scores;
    }

    /**
     * Creates a 10*10 board.
     * 
     * @return board, two dimension array (10*10)
     */
    public int[][] createBoard() {

        int[][] board = new int[10][10];

        // Sets the value 0 to every column.
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                
                board[row][col] = EMPTY;
            }
        }

        return board;
    }

    /**
     * Resolves a coordinate randomly.
     * 
     * @return coordinate, int value (0-9)
     */
    private int randomCoordinate() {
        int coordinate = (int)(Math.random() * 10);
                
        return coordinate;
    }

    /**
     * Resolves a coordinate randomly.
     * 
     * <p>Takes into account that ship don't go over the board.
     * 
     * @param shipSize the size of the ship.
     * @return randomly resolved coordinate (0-9)
     */
    private int randomCoordinate(int shipSize) {
        int coordinate = (int)(Math.random() * (10 - shipSize + 1));

        return coordinate;
    }

    /**
     * Determines the direction of the ship.
     * 
     * @return int value, 1 = vertical or 2 = horizontal
     */
    private int randomDirection() {
        int direction = (int)(Math.random() * 2 + 1);

        return direction;
    }

    /**
     * Places the ships on the board.
     * 
     */
    private void setShip() {
                          
        int direction;
        int coordinateX;
        int coordinateY;
        int shipSize;

        // Goes through the array of ship objects.
        for (Ship ship : shipObjects) {
            shipSize = ship.getSize();
            direction = randomDirection();
            // saves the direction of the ship object
            ship.setDirection(direction);
            
            // while loop checks where it is possible to place the ship.
            outer:
            
            while (true) {
                coordinateX = randomCoordinate();
                coordinateY = randomCoordinate();
                
                // Sets ship if direction is horizontal.
                if (direction == HORIZONTAL) {
                    coordinateX = randomCoordinate(shipSize);
                    int row = coordinateY;

                    // Checks if the square before the ship is empty.
                    if (coordinateX > 0 
                        && ships[row][coordinateX - 1] == SHIP) {
                        continue outer;
                    }
                    
                    // Checks if there is already a ship on that location 
                    // or side.
                    for (int col = coordinateX; 
                        col < coordinateX + shipSize; col++) {
                        
                        // above
                        if (row > 0 && ships[row - 1][col] == SHIP) {
                            continue outer;
                        }
                        
                        // below
                        if (row < 9 && ships[row + 1][col] == SHIP) {
                            continue outer;
                        }

                        // location
                        if (ships[row][col] == SHIP) {
                            continue outer;
                        }
                    }

                    // Checks if the square after the setting ship is empty.
                    if (coordinateX + shipSize < 10 
                        && ships[row][coordinateX + shipSize] == SHIP) {
                        continue outer;
                    } 
                    
                    // Sets the ship horizontal.
                    for (int col = coordinateX; col < coordinateX + shipSize; 
                        col++) {
                        ships[row][col] = SHIP;
                    }
                    
                // Sets ship if direction is vertical.
                } else if (direction == VERTICAL) {
                    coordinateY = randomCoordinate(shipSize);
                    int col = coordinateX;
                    
                    // Checks if there is already a ship in square before 
                    // the ship.
                    if (coordinateY > 0 
                        && ships[coordinateY - 1][col] == SHIP) {
                        continue outer;
                    }
                    
                    // Checks if there is already a ship on that location 
                    // or side.
                    for (int row = coordinateY; row < coordinateY + shipSize; 
                        row++) {
                       
                        // above
                        if (col > 0  && ships[row][col - 1] == SHIP) {
                            continue outer;
                        }
                        
                        // below
                        if (col < 9 && ships[row][col + 1] == SHIP) {
                            continue outer;
                        }
                        
                        // location
                        if (ships[row][col] == SHIP) {
                            continue outer;
                        }
                    }

                    // Checks if there is already a ship in square after 
                    // the ship.
                    if (coordinateY + shipSize < 10 
                        && ships[coordinateY + shipSize][col] == SHIP) {
                        continue outer;
                    }
                    
                    // Places the ship on the board, vertical.
                    for (int row = coordinateY; row < coordinateY + shipSize; 
                        row++) {
                            ships[row][col] = SHIP;
                    }
                } 
                
                // Saves the starting coordinates of the ship.
                ship.setCoordinateX(coordinateX);
                ship.setCoordinateY(coordinateY);
                break;
            } 
        }    
    } 
    
    /**
     * Shoots at the given coordinates. 
     * 
     * <p>Examines also if the ship is sunken.
     * 
     * @param coordinateX coordinate X (column)
     * @param coordinateY coordinate Y (row)
     * @return null (= ship is "alive") or ship (= ship is sunken)
     */
    public Ship shootAt(int coordinateX, int coordinateY) {
    
        if (ships[coordinateY][coordinateX] == SHIP) { 
            
            ships[coordinateY][coordinateX] = HIT;
            hits[coordinateY][coordinateX] = HIT;
            System.out.println("It was a HIT! (X)");
        } else {

            ships[coordinateY][coordinateX] = MISS;
            hits[coordinateY][coordinateX] = MISS;
            System.out.println("It was a MISS! (M)");
        }
        
        // Examines the array of shipObjects.
        // Uses the methods isHit() and isSunken() by class Ship.
        for (Ship ship: shipObjects) {
            if (ship.isHit(coordinateX,coordinateY)) {
                if (ship.isSunken()) {
                    markSunken(ship);
                }

                return ship;
            }
        }

        return null;
    }

    /**
     * Marks to the board if any ship is sunken.
     * 
     * @param ship ship object of the Ship class
     */
    public void markSunken(Ship ship) {
        // starting coordinate X of the ship.
        int coordinateX = ship.getCoordinateX(); 
        // starting coordinate Y of the ship.
        int coordinateY = ship.getCoordinateY(); 
        // direction of the ship
        int direction = ship.getDirection(); 
        int row = coordinateY;
        int col = coordinateX;
        
        // direction is horizontal
        if (direction == HORIZONTAL) {
            for (col = coordinateX; col < coordinateX + ship.getSize(); col++) {
                ships[row][col] = SUNKEN;
                hits[row][col] = SUNKEN;
            }
        // direction is vertical
        } else {
            for (row = coordinateY; row < coordinateY + ship.getSize(); row++) {
                ships[row][col] = SUNKEN;
                hits[row][col] = SUNKEN;
            }
        }
    }

    /**
     * Displays indent.
     * 
     * @param indent wanted indent (int value)
     */
    public void printIndent(int indent) {
        String space = " ";
        
        for (int i = 0; i < indent; i++) {
            System.out.print(space);
        }
    }

    /**
     * Displays the board.
     * 
     * @param board two dimension array
     */
    private void printBoard(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        char character = 'A';
        String space = " ";

        System.out.println();
        // Displays the headline of a board.
        for (int i = 0; i < cols; i++) {
            
            if (i == 0) {
                
                printIndent(5);
            }
            
            System.out.print(character + space);
            character++;
        }

        System.out.println();
        
        // Displays the underline for the headline.
        for (int i = 0; i < cols; i++) {
            
            if (i == 0) {
                
             printIndent(4);
            }

            System.out.print("--");
        }

        System.out.println();
        
        // Displays the sidebar of the board and the board.
        int number = 1;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                
                // Sidebar
                if (col == 0) {
                    
                    System.out.printf("%2d", number);
                    System.out.print(" | ");
                    number++;
                }
                
                // Board
                int value = board[row][col];
                
                if (value == SHIP) {
                    
                    System.out.print("o" + space);
                } else if (value == EMPTY) {
                    
                    System.out.print("." + space);
                } else if (value == MISS) {
                    
                    System.out.print("M" + space);
                } else if (value == HIT) {
                    
                    System.out.print("X" + space);
                } else if (value == 6) {
                    System.out.print("S" + space);
                }
            }

            System.out.println();
        }
    }

    /**
     * Prints the board with ships.
     */
    public void printBoardShips() {

        printBoard(ships);
    }

    /**
     * Prints the board with hits.
     */
    public void printBoardHits() {
        printBoard(hits);
    }
}

// End of file
