(ns gameoflife.core)

(defn in? [collection element]
  (some #(= element %) collection))

(defn is-neighbour [co-ord1 co-ord2]
  (->> (map #(Math/abs (- %1 %2)) co-ord1 co-ord2)
       (apply max)
       (= 1)))

(defn neighbours [co-ord live-cells]
  (->> (map #(is-neighbour co-ord %) live-cells)
       (filter true?)
       (count)))

(defn game-range [live-cells]
  (let [g-range (flatten live-cells)]
    (range (dec (apply min g-range)) (+ 2 (apply max g-range)))))

(defn lives? [co-ord live-cells]
  (let [neighbours (neighbours co-ord live-cells)]
    (if (in? live-cells co-ord)
      (in? [2 3] neighbours)
      (= neighbours 3))))

(defn advance [live-cells]
  (for [x (game-range live-cells)
        y (game-range live-cells)
        :when (lives? [x y] live-cells)]
    [x y]))

(def play #(iterate advance %))

(def glider [[2 0] [0 1] [2 1] [1 2] [2 2]])
(take 10 (play glider))

(def square [[1 1] [2 1] [1 2] [2 2]])
(take 10 (play square))

(def blinker [[1 2] [2 2] [3 2]])
(take 10 (play blinker))
