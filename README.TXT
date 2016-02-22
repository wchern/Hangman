PROJECT TITLE: Hangman
PURPOSE OF PROJECT: Computer Programming, Period 3
VERSION or DATE: 1.0, 10/05/2015
AUTHOR: William Chern

PSUEDOCODE:

Prompt Player1 for a word

Retrieve Player1's word, save to newly declared variable
    Make sure Player1's input is hidden or cleared
    
Count the number of characters in Player1's word, store to variable

Create collection of blank spaces corresponding to number of characters in the word, store to variable

Establish guess count, store to a variable

Establish a guessed letter bank, store to variable, which is initially empty

Display blank spaces, remaining guesses

Prompt Player2 for a letter, and only a letter (not another symbol/character)

Search through the word for Player2's letter

If found, replace corresponding blank space(s) with that letter
    
If not, reduce guess count
    Display Letter Not Found
    
Store Player2's guessed letter to the letter bank

If guess count equals 0, display Game Over

If no remaining blank spaces, display Congrats

Ask user if they wish to play again
    If yes, loop program again
