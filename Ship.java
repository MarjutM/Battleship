/**
 * @author Marjut Mikkola
 * @version 20191116
 * @since 1.6
 */
class Ship {

    /**
     * The name of the ship.
     */
    private String name;
    
    /**
     * The size of the ship.
     */
    private int size;
    
    /**
     * The starting coordinate X of the ship.
     */
    private int coordinateX;
   
    /**
     * The starting coordinate Y of the ship.
     */
    private int coordinateY;
   
    /**
     * The direction (vertical/horizontal) of the ship.
     */
    private int direction;
    
    /**
     * The health of the ship. Used to count when ship is sunken.
     */
    private int health;
    
    final int HORIZONTAL = 2;
    final int VERTICAL = 1;

    /**
     * Creates ship object with name, size and health, constructor.
     * 
     * @param name name of the ship
     * @param size size of the ship
     */
    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        // used to count when ship is sunken.
        this.health = size; 
    }
    
    /**
     * Sets the name to the ship.
     * 
     * @param name String variable
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the ship.
     * 
     * @return String variable
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the size of the ship.
     * 
     * @param size int variable
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Returns the size of the ship.
     * 
     * @return int variable
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the starting coordinate X to the ship.
     * 
     * @param coordinateX int value (0-9)
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Returns the starting coordinate X of the ship.
     * 
     * @return int value (0-9)
     */
    public int getCoordinateX() {
        return coordinateX;
    }

    /**
     * Sets the starting coordinate Y to the ship.
     * 
     * @param coordinateY int value (0-9)
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    /**
     * Returns the starting coordinate Y of the ship.
     * 
     * @return int value (0-9)
     */
    public int getCoordinateY() {
        return coordinateY;
    }

    /**
     * Sets the direction to the ship.
     * 
     * @param direction int value 1 or 2
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Returns the direction of the ship.
     * 
     * @return int value 1 = vertical or 2 = horizontal
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Resolves if the shot is a hit and reduces health of the ship.
     * 
     * @param coordinateX shooting coordinate X
     * @param coordinateY shooting coordinate Y
     * @return true = hit or false = not a hit
     */
    public boolean isHit(int coordinateX, int coordinateY) {

        if (direction == HORIZONTAL) {
            if (coordinateY == this.coordinateY 
                && (coordinateX >= this.coordinateX 
                && coordinateX < this.coordinateX + size)) {
                
                health--;
                return true;
            } 
        // direction = vertical.
        } else {
            
            if (coordinateX == this.coordinateX 
                && (coordinateY >= this.coordinateY 
                && coordinateY < this.coordinateY + size)) {
                health--;
                return true;
            }
        }

        return false;
    }

    /**
     * Resolves is the ship sunken.
     * 
     * @return true = sunken, false = not sunken
     */
    public boolean isSunken() {
        if (health < 1) {
            return true;
        } else {
            return false;
        }
    }
}

// End of file
