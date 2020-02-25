(ns mastermind.core)
(declare move-looper)

;;;;
;; Game Logic
;;;;

(def code-num #(rand-int 6))

(defn pick-random-code []
  (vector code-num code-num code-num code-num))

(defn find-position-matches
  "Given a code and a guess, returns a vector which has `true`
  in all positions where the guess matches the code"
  [code guess]
  (map #(= %1 %2) code guess))

(defn position-score
  "Gives one point for every guessed position that matches the target code"
  [code guess]
  (count (filter true? (find-position-matches code guess))))

(defn remove-true-values [vec1 vecbool]
  (remove true? (map #(if %2 %2 %1) vec1 vecbool)))

(defn filter-out-position-matches
  "given a code and a guess, returns a vector containing a filtered code and
  guess with only the pegs which are NOT position matches"
  [code guess]
  (let [matches (find-position-matches code guess)]
    [(remove-true-values code matches)
     (remove-true-values guess matches)]))

(defn value-score
  "Given a code an a guess, gives one point for each guess peg which is not a
   position match, but which does correspond to a peg in the code"
  [code guess]
  (let [filtered (filter-out-position-matches code guess)
        f-code (first filtered)
        f-guess (second filtered)]
    (count (filter true? (map #(contains? (set f-guess) %) (set f-code))))))

(defn score-guess
  "given a code and a guess, returns a vector [p v] where
  p is the number of position scores and v is the number of value
  scores"
  [code guess]
  [(position-score code guess) (value-score code guess)])

(defn do-guess
  "When given a game and a guess, scores the guess and returns
  a new game with the guess and score appended"
  [game guess]
  (let [code (:code game)
        old-turns (vec (:guesses game))
        score (score-guess code guess)
        turn {:guess guess :score score}]
    {:code code :guesses (conj old-turns turn)})
  )

(defn is-solved? [game]
  (= [4 0] (:score (last (:guesses game)))))

(defn number-of-turns
  "Returns the number of turns elapsed"
  [game]
  (count (:guesses game)))

(defn out-of-turns? [game]
  (>= (number-of-turns game) 10))

(defn initialize-game []
  {:code (pick-random-code)})

;;;;
;; Board rendering
;;;

(defn render-empty-row []
  ". . . .")

(defn render-turn [turn]
  (let [guess (:guess turn)
        score (:score turn)]
    (str (clojure.string/join " " guess) " => " score)))

(defn render-guess [game guess-num]
  (if (> guess-num (number-of-turns game))
    (render-empty-row)
    (render-turn ((:guesses game) (dec guess-num)))))

(defn render-board [game]
  (doseq [guess-num (range 1 11)]
    (println (render-guess game guess-num))))

;;;;
;; CLI interaction
;;;;

(defn input->guess
  "takes an input of 4 digits and returns a guess (vector of 4
  integers between 0 and 5)"
  [input]
  (vec (map #(Character/getNumericValue %) input)))

(defn prompt-guess
  [game]
  (println "Input your guess as a 4 digit string of characters")
  (let [guess (input->guess (read-line))]
    (move-looper (do-guess game guess))))

(defn move-looper
  [game]
  (render-board game)
  (cond
    (is-solved? game) (println "You win!")
    (out-of-turns? game) (println (str "Sorry, you're out of turns. The answer was " (clojure.string/join " " (:code game))))
    :else (prompt-guess game)))

(defn -main
  [& args]
  (println "Get ready to play Mastermind!")
  (move-looper (initialize-game)))