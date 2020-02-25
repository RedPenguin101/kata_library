(ns mastermind.core-test
  (:require [midje.sweet :refer :all]
            [mastermind.core :refer :all]))

(facts "about transforming inputs to guesses"
  (fact "it returns all zeros as a vector of 4 zeros"
    (input->guess "0000") => [0 0 0 0]
    (input->guess "0101") => [0 1 0 1]
  ))

(facts "about doing a guess"
  (fact "given an empty game and a guess, it scores the guess and adds it to the game"
    (do-guess {:code [1 2 3 4]} [4 3 2 1]) => {:code [1 2 3 4] :guesses [{:guess [4 3 2 1] :score [0 4]}]})
  (fact "given a game that already has a guess, it appends the guess and score to the end of the guesses"
    (do-guess {:code [1 2 3 4] :guesses [{:guess [0 0 0 0] :score [0 0]}]} [4 3 2 1]) => {:code [1 2 3 4] :guesses [{:guess [0 0 0 0] :score [0 0]} {:guess [4 3 2 1] :score [0 4]}]})
)

(facts "about finding position matches"
  (fact "given a matching code, all positions should be true"
    (find-position-matches [1 2 3 4] [1 2 3 4]) => [true true true true])
  (fact "given a non-matching code, all positions should be false"
    (find-position-matches [1 2 3 4] [4 3 2 1]) => [false false false false])
  (fact "given a part-matching code, some positions should be true"
    (find-position-matches [1 2 3 4] [1 2 4 3]) => [true true false false])
)

(facts "about scoring positions"
       (fact "a matching code should have a score of 4"
             (position-score [1 2 3 4] [1 2 3 4]) => 4)
       (fact "a non-matching code should have a score of 0"
             (position-score [1 2 3 4] [4 3 2 1]) => 0)
       (fact "a partially matching code should have a score"
             (position-score [1 2 3 4] [1 2 4 5]) => 2))

(facts "about scoring values"
       (fact "a code with no similarities should score zero"
             (value-score [0 0 0 0] [1 1 1 1]) => 0)
       (fact "a code with one correct peg in the wrong place should score 1"
             (value-score [1 0 0 0] [2 1 0 0]) => 1)
       (fact "a code with two correct pegs but in the wrong place should score 2"
             (value-score [1 0 0 0] [0 1 0 0]) => 2)
       (fact "breaking1"
             (value-score [4 4 1 3] [1 2 3 4]) => 3))

(facts "about detecting when a game is solved"
       (fact "a game which has a score of 4 in a guess should be a win"
             (is-solved? {:guesses [{:guess [0 0 0 0] :score [4 0]}]}) => true)
       (fact "a game which has a position score of not 4 in the last position should be a win"
             (is-solved? {:guesses [{:score [3 0]}]}) => false)
       (fact "a multi-guess game which has a position score of 4 in the last position should be a win"
             (is-solved? {:guesses [{:guess :one} {:score [4 0]}]}) => true)
       (fact "a multi-guess game which has a position score of less than 4 in the last position should not be a win"
             (is-solved? {:guesses [{:score [4 0]} {:score [0 4]}]}) => false))

(facts "about detecting whether you're out of turns"
       (fact "a game with zero guesses is not out of turns"
             (out-of-turns? {}) => false)
       (fact "a game with 10 guesses is out of turns"
             (out-of-turns? {:guesses
                             [{:test 0} {:test 0} {:test 0} {:test 0} {:test 0} {:test 0} {:test 0} {:test 0} {:test 0} {:test 0}]})
             => true))