(ns cljkata.bowling)

(comment
  "* A game is made up of 10 frames, each consisting of two shots
   * If you knock down 10 pins in the first roll of a frame (a 'Strike'). 
     This ends the frame, and your score for that frame will be 10 plus the 
     sum of your next two shots
   * If you knock down 10 pins with the two rolls of the frame (a spare), 
     your score for that framw will be 10 plus the sum of you next ONE shot
   * If you knock down less than 10 pins with the two rolls in a frame 
     (an 'open frame'), you score the number of pins you knocked down
   * For the 10th and last frame, if you roll a spare you get one extra 
     shot, and if you roll a strike you get 2 extra shots.")

(comment
  "rolls represented as a vector of integers")

(defn score-roll [])

(defn score-game [game]
  (reduce + game))

(= 0 (score-game (repeat 20 0)))
(= 20 (score-game (repeat 20 1)))
