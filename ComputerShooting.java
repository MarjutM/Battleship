/**
 * @author Marjut Mikkola
 * @version 20191116
 * @since 1.6
 */
class ComputerShooting {  

    private int MISS = 4;
    private int HIT = 5;
    private int SHIP = 3;
    private int EMPTY = 0;
    
    /**
     * Resolves a coordinate randomly.
     * 
     * @return randomly resolved coordinate, int value
     */
    public int randomCoordinate() {
        int coordinate = (int)(Math.random() * 10);
                
        return coordinate;
    }

    // Pitäiskö tän metodin palauttaa, jotain, jos on osuma tai ei, jotta 
    // tietokone tietää siirtyä AI ampumiseen?
    public void randomShooting(Board playerBoard) {
        
        int[][] board = playerBoard.getShips();
        int coordinateX;
        int coordinateY;
        while (true) {
            coordinateX = randomCoordinate();
            coordinateY = randomCoordinate();
            // System.out.println(coordinateX);
            // System.out.println(coordinateY);
            if (board[coordinateY][coordinateX] == EMPTY || board[coordinateY][coordinateX] == SHIP) {
                break;
            } 
        }

        Ship ship = playerBoard.shootAt(coordinateX, coordinateY);
            
        
            if (ship != null && ship.isSunken()) {
                System.out.println(ship.getName() + " is sunken.");
                
            }

    }

    // AI ampuminen
    public int aiShooting(Board playerBoard) {

        if (!isHit(playerBoard)) {

            randomShooting(playerBoard);

        }

        return playerBoard.getScores();


    }

    public boolean isHit(Board playerBoard) {
        int coordinateX = 0;
        int coordinateY = 0;
        boolean found = false;

        int[][] board = playerBoard.getShips();
        outer:
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                coordinateX = col;
                coordinateY = row;
                
                if (board[row][col] == HIT) {

                    if (row > 0) {
                        coordinateY--;
                        while (coordinateY >= 0) {
                            if (board[coordinateY][col] == EMPTY || board[coordinateY][col] == SHIP) {
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

                    if (row < 9) {
                        coordinateY++;
                        while (coordinateY <= 9) {
                            if (board[coordinateY][col] == EMPTY || board[coordinateY][col] == SHIP) {
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
                    
                    if (col > 0) {
                        coordinateX--;
                        while (coordinateX >= 0) {
                            if (board[row][coordinateX] == EMPTY || board[row][coordinateX] == SHIP) {
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

                    if (col < 9) {
                        coordinateX++;
                        while (coordinateX <= 9) {
                            if (board[row][coordinateX] == EMPTY || board[row][coordinateX] == SHIP) {
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

                 


                    

                    if (row > 0) {

                        if (board[row - 1][col] == EMPTY || board[row - 1][col] == SHIP) {
                             coordinateY--;
                             found = true;
                             break outer;

                        }
                    } 

                    if (row < 9) {

                        if (board[row + 1][col] == EMPTY || board[row + 1][col] == SHIP) {
                            coordinateY++;
                            found = true;
                            break outer;
                        }
                    }

                    if (col > 0) {

                        if (board[row][col - 1] == EMPTY || board[row][col - 1] == SHIP) {
                            found = true;
                            coordinateX--;
                            break outer;
                        }
                    }

                    if (col < 9) {

                        if (board[row][col + 1] == EMPTY || board[row][col + 1] == SHIP) {
                            found = true;
                            coordinateX++;
                            break outer;
                        }
                    }
                }
            }
        }
        
        if (found) {

            Ship ship = playerBoard.shootAt(coordinateX, coordinateY);
            
        
            if (ship != null && ship.isSunken()) {
                System.out.println(ship.getName() + " is sunken.");
                //return scores+=1;
            //} else {
              //  return scores;
            }
        }

        return found;
    }

    




    
}




   

    




        
    
