import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int rounds = 0;
        int score = 0;

        System.out.println(" Welcome to the Number Guessing Game!");

        String playAgain;
        do {
            int numberToGuess = rand.nextInt(100) + 1;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("\n Guess a number between 1 and 100. You have 5 attempts.");

            while (attempts < 5) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == numberToGuess) {
                    System.out.println(" Correct! You guessed it in " + attempts + " tries.");
                    guessed = true;
                    score += 10;
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            }

            if (!guessed) {
                System.out.println(" You ran out of attempts. The number was: " + numberToGuess);
            }

            rounds++;
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = sc.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("\n Game Over!");
        System.out.println("You played " + rounds + " round(s). Final Score: " + score);
    }
}
