# Katas

## Interview question style katas

### Two Sum

Given an array of integers, return 2 indices such that they add up to a specific target

Input array = [2 15 11 7]
Target = 9

### Fizz buzz

Write a program that outputs increasing integers, but when the number is divisible by 3 it outputs fizz, by 5 it outputs buzz, by 3 and 5 it outputs fizzbuzz

## Slightly larger

### Conways game of life

Build a simulation of Conways game of life. The rules are:

1. Any live cell with two or three neighbours survives
2. Any dead cell with three live neighbours becomes a live cell
3. Any other live cells dies in the next generation, or stays dead if it's already dead.

### Bowling game

Rules:
* A game is made up of 10 frames, each consisting of two shots
* If you knock down 10 pins in the first roll of a frame (a 'Strike'). This ends the frame, and your score for that frame will be 10 plus the sum of your next two shots
* If you knock down 10 pins with the two rolls of the frame (a spare), your score for that framw will be 10 plus the sum of you next ONE shot
* If you knock down less than 10 pins with the two rolls in a frame (an 'open frame'), you score the number of pins you knocked down
* For the 10th and last frame, if you roll a spare you get one extra shot, and if you roll a strike you get 2 extra shots.

### Mastermind

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

### GOPS

The basic game is for two players, using three suits from a standard 52-card pack. Cards rank Ace (low), 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K (high). As a prize, the Ace is worth 1 point, cards 2-10 face value, Jack 11, Queen 12 and King 13.

The cards are sorted into suits. One suit (traditionally diamonds) is shuffled and stacked face down as a bounty pile. Each of the other players takes one complete suit. 

The top card of the prize pile is turned face up. Then each player selects a card from their hand with which to bid for it and places it face down. When both players are ready, the bid cards are revealed simultaneously, and the higher bid wins the prize card. The bid cards are then discarded and the prize card is placed beside the player who won it. The next card of the prize pile is turned face up and players bid for it in the same way.

When both players run out of bid cards the play ends. Each player totals the value of the bounty cards they have won in bids (ace=1, 2-10 face value, J=11, Q=12, K=13) and the greater total wins the game.

Easy mode: If the bids of the two players are equal, no-one scores.

Harder mode: If the bids of the two players are equal, the bid cards are discarded but the prize card remains on offer. A new prize card is turned face up and the next bid is for the two prize cards together, then for three prize cards if there is another tie, and so on. If the player's last bid cards are equal, the last prize card (and any others remaining from immediately preceding tied bids) are not won by either player.

Build a program that can play GOPs, with three different available strategies for players to determine their bid available.
