import java.util.Scanner; //import Scanner class from Java library
import javax.swing.JOptionPane; //import JOptionPane class from the Java library

/**
 * HangmanRunner
 * 
 * @author William Chern 
 * @version 1.0, 10/05/2015
 */
public class HangmanRunner
{
    public static void main(String[] args) {
        System.out.println("Welcome to Hangman!");
        boolean cont = true; //initialize and declare boolean cont to determine whether to continue
        boolean successful = false; 
        //initialize and declare boolean successful to determine (later in the cycle) whether to print a congratulatory message to user
        while (cont && !successful) {
            Scanner reader = new Scanner(System.in); //construct scanner object, assign its memory location to identifier reader
            String originalWord = JOptionPane.showInputDialog(null, "Welcome to Hangman! Please enter a word: ");
            /* invoke showInputDialog class method to prompt user for a word, 
             *   save the returned String to newly-declared String object referenced by originalWord */

            String nonLetters = "1234567890!@#$%^&*()[]{}_+-=:;'<>,.`|~\" \\";
            /*declare and initialize a new String object reference which contains characters that are not letters,
            used to check whether user input has illegal characters which are not letters*/

            boolean validWordEntry=false; //boolean variable used to control a loop which determines whether a user has entered a valid word
            while (!validWordEntry) {
                validWordEntry = true;
                if (originalWord.length()<1) {
                    //if the user inputs a blank string (the length is less than 1), set the boolean to false to continue the loop
                    originalWord = JOptionPane.showInputDialog(null, "Please enter a valid word without symbols, numbers, or spaces: ");
                    validWordEntry=false;
                }
                for (int i=0; i<nonLetters.length(); i++) {
                    if (originalWord.contains(nonLetters.substring(i, i+1))) {
                        /* mathematically loop through all the characters in the String object which 
                         *   contains non-letters, invokes "contains" method on String object referenced by originalWord.
                         * 
                         * If it does contain any non-letter character, user is prompted again for a valid entry */
                        originalWord = JOptionPane.showInputDialog(null, "Please enter a valid word without symbols, numbers, or spaces: ");
                        validWordEntry=false;
                    }
                }
            }

            originalWord = originalWord.toLowerCase();
            /* invokes toLowerCase method on String object referenced by originalWord, 
             *   saves returned String object back to originalWord and overrides the original.
             * Enables easier checking later in the lifecycle of the program. */

            int wordLength = originalWord.length(); 
            // invokes length method on String object referenced by originalWord, saves returned int to new var wordLength
            String player2GuessedString = ""; // declare String object ref player2GuessedString, initialize as blank variable
            for (int i=0; i<wordLength; i++) {
                player2GuessedString += "_ "; // loops through and adds a number of "_ " corresponding to the wordLength
            }

            int guessesRemaining = wordLength+2;
            // declare and initialize the number of guesses remaining to the user, which is the length of the word plus 2 more chances
            String guessedLetters = "";
            /* declare and intialize String object referenced by guessedLetters, 
             *  will be used to store all the letters the user has already guessed
             *  (initially a blank string)
             */

            // while the user still has remaining guesses and while the user has not successfully guessed the word
            while ((guessesRemaining > 0) && (!successful)) {
                System.out.println("\nStatus: " + player2GuessedString + 
                    "\n Guesses Remaining: " + guessesRemaining + "\n Already Guessed: " + guessedLetters + "\nPlease guess a letter: ");
                // displays current string, number of remaining guesses, and the letters already guessed to the user
                String player2Guess = reader.next();
                // prompts user for new guess, saves Scanner input to newly initialized String object referenced by player2Guess
                player2Guess = player2Guess.toLowerCase();
                // invokes toLowerCase method on String object referenced player2Guess, returns and overrides existing player2Guess

                boolean validGuess=true; // used to control loops determining whether user has entered a valid guess
                if (player2Guess.length()>1) {
                    System.out.println("Please only guess 1 letter.");
                    validGuess=false;
                    // if user has entered more than 1 letter, the guess is not valid
                }
                for (int i=0; i<nonLetters.length(); i++) {
                    if (player2Guess.contains(nonLetters.substring(i, i+1))) {
                        System.out.println("Please guess a letter (no symbols or numbers).");
                        validGuess=false;
                        // mathematically loop through String object reference containing non-letters, checks if player2Guess contains any non-letter
                        // if there is a non-letter, the guess is not valid
                    }
                }
                // if the guess is valid, the logic for checking against the word and the guessed letter bank will execute
                if (validGuess) {
                    boolean alreadyGuessed = false;
                    // alreadyGuessed boolean variable controls whether the word will be matched against the actual word
                    for (int i=0; i<guessedLetters.length(); i++) {
                        if (player2Guess.equalsIgnoreCase(guessedLetters.substring(i, i+1))) {
                            alreadyGuessed = true;
                            System.out.println("You've already guessed this!");
                            // mathematically loops through String containing all the guessed letters
                            // if the letter has already been guessed, the alreadyGuessed is set to true
                        }
                    }
                    // if the user has already guessed the letter, the code matching the guess against the actual word will not execute
                    if (!alreadyGuessed) {
                        guessesRemaining--; // the number of guesses decreases because the actual matching takes place
                        guessedLetters += player2Guess; //since the user has not already guessed the letter, the guess is added to the bank
                        if (originalWord.contains(player2Guess)) {
                            System.out.println("Letter Found!");
                            for (int i=0; i<wordLength; i++) { // uses a mathematically for loop and iterates through each letter of the original word
                                if (player2Guess.equalsIgnoreCase(originalWord.substring(i, i+1))) {
                                    player2GuessedString = player2GuessedString.substring(0, i*2) + player2Guess + player2GuessedString.substring((i*2)+1);
                                    /* the player2Guessed String object reference is overriden with a concatenation,
                                     * where the letter is inserted at the position (multipled by 2, to account for the spaces) that
                                     * it is located in the original word */
                                }
                            }
                        }
                        else {
                            System.out.println("Letter Not Found");
                        }
                    }
                }
                boolean noMoreBlanks = true;
                // each time after a letter is guessed, the loop checks if there are no more underscores in the player2GuessedString
                for (int i=0; i<player2GuessedString.length(); i++) {
                    if (player2GuessedString.substring(i, i+1).equals("_")){
                        noMoreBlanks = false;
                        // if player2GuessedString object still contains any underscore, noMoreBlanks=false
                    }
                }
                if (noMoreBlanks) {
                    System.out.println("\nCongratulations! \n The word was: " + originalWord);
                    successful = true;
                    /* if there are no more underscores in player2GuessedString, the boolean variable noMoreBlanks will be true.
                     * Thus, the successful boolean will be set to true, which will exit the while loop above */
                }
            }

            if ((guessesRemaining<=0) && (!successful)) {
                System.out.println("\nGame Over..." + "\n The word was: " + originalWord);
                // if user has no remaining guesses and not successful in guessing the word, display "Game Over" and original word
            }

            boolean validContResponse = false; // boolean validContResponse is used to ensure that only valid input ("y" or "n") is accepted
            String contResponse; //declare String object reference to store user input
            while (!validContResponse) {
                System.out.println("\nWould you like to play again? (y or n) ");
                contResponse = reader.next();
                if (contResponse.equalsIgnoreCase("n")){
                    cont = false;
                    validContResponse = true;
                    reader.close();
                    System.out.println("\nThanks for playing Hangman!");
                    // if user inputs "n" (or "N") for no, cont=false, validContResponse=true (to exit the loop), scanner is closed
                }
                else if (contResponse.equalsIgnoreCase("y")){
                    cont = true;
                    successful = false;
                    validContResponse = true;
                    System.out.println("\nNew game of Hangman");
                    // if user inputs "y" (or "Y") for yes, cont=true, successful=false, validContResponse=true (to exit the loop)
                }
                else {
                    System.out.println("\nWould you like to play again? (y or n) ");
                    contResponse = reader.next();
                    // if user input does not match either "y" or "n", loop continues to ask user for valid response
                }
            }
        }
    }
}
