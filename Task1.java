import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        // Game Configuration
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        
        int totalScore = 0;
        int roundsPlayed = 0;
        boolean playAgain = true;

        System.out.println("\n=== Welcome to the Number Guessing Game! ===\n");
        System.out.println("I'm thinking of a number between " + minRange + " and " + maxRange + ".\n");
        System.out.println("You have " + maxAttempts + " attempts per round. Let's begin!\n");

        while (playAgain) {
            roundsPlayed++;
            // Generate random number between minRange (inclusive) and maxRange (inclusive)
            int targetNumber = random.nextInt((maxRange - minRange) + 1) + minRange;
            int attempts = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("--- Round " + roundsPlayed + " ---");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess (Attempt " + (attempts + 1) + "/" + maxAttempts + "): ");
                
                // Input handling validation
                while (!scanner.hasNextInt()) {
                    System.out.println("That's not a valid number. Please try again.");
                    System.out.print("Enter your guess: ");
                    scanner.next(); 
                }
                
                int userGuess = scanner.nextInt();
                attempts++;

                // Conditionals for feedback
                if (userGuess == targetNumber) {
                    System.out.println("\nYeeeh! Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    // Score calculation: higher points for fewer attempts
                    int roundScore = (maxAttempts - attempts + 1) * 10;
                    totalScore += roundScore;
                    System.out.println("Round Score: " + roundScore + " points.");
                    hasGuessedCorrectly = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("\nOoops! Out of attempts! The correct number was: " + targetNumber);
            }

            System.out.println("Current Total Score: " + totalScore + " points.");
            System.out.println("-----------------------\n");

            // Ask to play multiple rounds
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next().trim().toLowerCase();
            playAgain = response.equals("yes") || response.equals("y");
            System.out.println();
        }

        // Final Score Display
        System.out.println("=== Game Over ===");
        System.out.println("Rounds Played: " + roundsPlayed);
        System.out.println("Final Score  : " + totalScore + " points.");
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}