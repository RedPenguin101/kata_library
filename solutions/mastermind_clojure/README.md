# Mastermind Kata

The project uses [Midje](https://github.com/marick/Midje/).

## Description

You have been asked to write a Mastermind CLI game. The computer picks a 4 digit code at random from the numbers 0 to 5 inclusive, and the player has 10 attempts to guess the code by typing in the terminal. The computer will score the players guess, with one red-peg for each correct color in the correct place, and one white peg for each correct color in the _incorrect_ place (this can be considered a 2 element vector of the respective scores).

The board should be displayed graphically on screen after each turn, showing the entire history of the game, with the player's guesses and the scores for those guesses, as well as the 'empty' rows.

```
1 2 3 4 => [1 2]
4 3 5 3 => [0 3]
. . . .
. . . .
etc.
```

If the player guesses correctly (i.e. has a score of [4 0]) the computer should congratulate the player and end the game.

If the player goes through all 10 guesses without cracking the code, the computer should finish the game and tell the player what the code was.

