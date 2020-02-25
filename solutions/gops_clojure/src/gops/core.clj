(ns gops.core
  (:gen-class))

(comment
  "Data representation of a game, array of dics where index is turn number"
  
  [{:score [0 0]
    :bounty-deck [1 ... 8]
    :player-decks [[1 .. 8] [1 ... 8]]} ...])

(def eight-game [{:score [0 0] :bounty-deck [1 2 3 4 5 6 7 8] :player-decks [[1 2 3 4 5 6 7 8] [1 2 3 4 5 6 7 8]]}])

(defn- game-over? [game-states]
  (zero? (count (:bounty-deck (last game-states)))))

(defn- match-strat [_ card]
  card)

(defn- highest-strat [deck _]
  (apply max deck))

(defn- random-strat [deck _]
  (rand-nth deck))

(defn- change-score [[p1 p2] p1-card p2-card bounty-card]
  (cond
    (> p1-card p2-card) [(+ bounty-card p1) p2]
    (< p1-card p2-card) [p1 (+ bounty-card p2)]
    :else [p1 p2]))

(defn- next-turn [{:keys [score bounty-deck player-decks]} p1-strat p2-strat]
  (let [bounty-card (rand-nth bounty-deck)
        p1-card (p1-strat (player-decks 0) bounty-card)
        p2-card (p2-strat (player-decks 1) bounty-card)]
    {:score (change-score score p1-card p2-card bounty-card) 
     :bounty-deck (vec (remove #{bounty-card} bounty-deck))
     :player-decks [(vec (remove #{p1-card} (player-decks 0))) 
                    (vec (remove #{p2-card} (player-decks 1)))]
     :turn (str "bounty was " bounty-card ". p1 played " p1-card ", p2 played " p2-card)}))


(defn play-game [game-states p1-strat p2-strat]
  (if (game-over? game-states)
    game-states
    (recur (conj game-states (next-turn (last game-states) p1-strat p2-strat)) p1-strat p2-strat)))


(comment
  (def nil-game [{:score [0 0] :bounty-deck [] :player-decks [[] []]}])
  (play-game nil-game match-strat match-strat)
  ; returns the game states because bounty deck is empty
  
  (play-game [{:score [0 0] :bounty-deck [1] :player-decks [[1] [1]]}] match-strat match-strat)
  ;; => [{:score [0 0], :bounty-deck [1], :player-decks [[1] [1]]} {:score [0 0], :bounty-deck [], :player-decks [[] []]}]
  
  (def eight-game [{:score [0 0] :bounty-deck [1 2 3 4 5 6 7 8] :player-decks [[1 2 3 4 5 6 7 8] [1 2 3 4 5 6 7 8]]}])
  
  (play-game eight-game match-strat random-strat)

  )

(defn -main
  [& args]
  (println (play-game eight-game match-strat random-strat)))

