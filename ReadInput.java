import java.io.Console;
/**
 * @author Marjut Mikkola
 * @version 20191206
 * @since 1.6
 */
class ReadInput {
    
    /**
     * Reads the player's input.
     * 
     * @param prompt String variable, the question.
     * @return str, String variable
     */
    private String readString(String prompt) {
        Console console = System.console();
        String str;
        str = console.readLine(prompt).trim();
        return str;
    }

    /**
     * Checks is the input in right length.
     * 
     * @param str player's input, String variable
     * @return true = rigth input, false = wrong input
     */
    private boolean isString(String str) {
        
        if (str.length() == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the character is between A-J.
     * 
     * @param input the players input, converted to char.
     * @return true = right char, false = wrong char
     */
    private boolean rightInputX(char input) {
        return (input >= 'A' && input <= 'J');
    }

    /**
     * Converts the player's input to int.
     * 
     * @param str player's input
     * @return coordinateX (0-9)
     */
    public int coordinateX(String str) {
        int coordinateX;
        char coordinate; 
        String input;

        // Asks for the coordinateX as long as the player inputs right String.
        while (true) {
            input = readString(str);
            
            if (isString(input)) {
                
                coordinate = input.toUpperCase().charAt(0);
            } else {
                
                System.out.println("Invalid input. Try again.");
                continue;
            }
                
            if (rightInputX(coordinate)) {
                
                coordinateX = coordinate - 65;
                break;
            } else {
                
                System.out.println("Invalid input. Try again.");
            }
        }
    
        return coordinateX;
    }

    /**
     * Checks if number is between 1-10.
     * 
     * @param input player's input converted to int
     * @return true = right input, false = wrong input
     */
    private boolean rightInputY(int input) {
        return (input >= 1 && input <= 10);
    }

    /**
     * Converts the player's input to int.
     * 
     * @param str player's input
     * @return int value (0-9)
     */
    public int coordinateY(String str) {
        
        int coordinateY; 
        String input;
       
        while (true) {
            while (true) {
                try {
                    input = readString(str);
                    coordinateY = Integer.parseInt(input);
                    break;
                } catch (Exception error) {
                    System.err.println("Invalid input. Try again.");
                }
            }
        
            if (rightInputY(coordinateY)) {
                
                coordinateY = coordinateY - 1;
                break;
            } else {
                
                System.out.println("Invalid input. Try again.");
                continue;
            }
        }
        
       return coordinateY;  
    }
}

// End of file
