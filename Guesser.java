import java.util.Scanner;

public class Guesser {
    public static void main(String[] args) {
        // Opening a scanner to read user input
        Scanner scanner = new Scanner(System.in);
        String playAgain;
    
        // Main game loop
        do {
        	System.out.println("\nWelcome to the guessing game!");
            // Prompting the user to choose a guessing game.
            System.out.print("Which guessing game would you like to play? (1 for Birthday, 2 for Number): ");
            int game = scanner.nextInt();
            scanner.nextLine();
    
            // If the user types 1, play the birthday game.
            if (game == 1) {
            	System.out.println("\nBirthday Game: Think of a number between 1 and 31, inclusive. I will guess it using 5 sets.");
                guess(5, 31, "birthday", scanner);
            // If the user types 2, play the number game.
            } else if (game == 2) {
            	System.out.println("\nNumber Game: Think of a number between 1 and 255, inclusive. I will guess it using multiple sets.");
            	// Prompting the user to enter the maximum possible value for the number game.
                System.out.print("Enter the maximum possible value of your chosen number: ");
                int max = scanner.nextInt();
                scanner.nextLine();
                // Calculating the number of sets required to guess the number.
                int N = (int) Math.ceil(Math.log(max) / Math.log(2));
                guess(N, max, "number", scanner);
            // Exception handling for invalid input.
            } else {
                System.out.println("Invalid option. Please enter 1 for Birthday or 2 for Number.");
            }
    
            // Prompting the user if they would like to play again.
            System.out.print("\nDo you want to play again? (Y/N) ");
            playAgain = scanner.nextLine().trim().toUpperCase();
        // Looping until the user types "N".
        } while (playAgain.equals("Y"));
    
        // Closing the scanner and ending the program.
        System.out.println("\nThank you for playing!");
        scanner.close();
    }

    // Function to guess the user's birthday or number during the game.
    public static void guess(int N, int max, String type, Scanner scanner) {
    	// Generating the sets for the guessing game. 128 is the maximum amount of elements in a set.
        int[][] sets = new int[N][128]; 
        sets = generateSets(N, max, sets);

        int chosenNumber = 0;
        // Iterating through the sets and asking the user if their chosen number is in each set.
        for (int i = 0; i < N; i++) {
            System.out.println("\nSet " + (i + 1) + ":");
            for (int j = 0; j < sets[i].length && sets[i][j] != 0; j++) {
                System.out.print(sets[i][j] + " ");
            }
            System.out.print("\nDoes your " + type + " appear in this set? (Y/N) ");
            String response = scanner.nextLine().trim().toUpperCase();
            // Calculating the chosen number based on the user's responses.
            if (response.equals("Y")) {
            	// Adding the value of the set to the chosen number.
                chosenNumber += (int) Math.pow(2, i);
            }
        }

        // Printing the chosen number.
        System.out.println("\nYour " + type + " is " + chosenNumber + ".");

        // Locator Function
        System.out.println("\nLOCATOR FUNCTION");
        System.out.print("\nEnter a number to search for within the sets: ");
        int searchNumber = scanner.nextInt();
        scanner.nextLine();
        // Calling the searchInSets function to locate the user's number in the sets.
        boolean located = searchInSets(sets, searchNumber);
        if (located) {
            System.out.println("The number " + searchNumber + " was found in the sets.");
        } else {
            System.out.println("The number " + searchNumber + " was not found in the sets.");
        }
    }
    
    // Function to generate the sets for the guessing game.
    public static int[][] generateSets(int N, int max, int[][] sets) {
    	// Iterating through the sets and adding the values to each set.
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 1; j <= max; j++) {
            	//If the value of the set is not 0, add the value to the set.
                if ((j / (int) Math.pow(2, i)) % 2 != 0) {
                    sets[i][count++] = j;
                }
            }
        }
        // Returning the sets.
        return sets;
    }

    // Helper function to search for a number in the sets
    public static boolean searchInSets(int[][] sets, int number) {
    	// Iterating through the sets and checking if the number is in the set.
        for (int i = 0; i < sets.length; i++) {
            for (int j = 0; j < sets[i].length && sets[i][j] != 0; j++) {
            	// If the number is found, return true.
                if (sets[i][j] == number) {
                    return true;
                }
            }
        }
        // Otherwise, return false.
        return false;
    }
}
