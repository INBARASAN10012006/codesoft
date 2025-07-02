import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int roundsWon = 0;
        int totalRounds = 0;
        String playAgain;

        do {
            int secretNumber = random.nextInt(100) + 1; // 1 to 100
            int attemptsLeft = 5;
            boolean guessedCorrectly = false;

            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts to guess it!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();

                if (guess == secretNumber) {
                    System.out.println("Congratulations! You guessed it!");
                    guessedCorrectly = true;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you're out of attempts! The number was " + secretNumber + ".");
            }

            totalRounds++;
            if (guessedCorrectly) {
                roundsWon++;
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().toLowerCase();
        } while (playAgain.equals("yes"));

        System.out.println("\nGame Over! You won " + roundsWon + " out of " + totalRounds + " rounds.");
        scanner.close();
    }
}
   
