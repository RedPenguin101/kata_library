(ns two-sum)

(comment
  "given an array of two integers, return 2 indices such that they add up to
  a specific target"
  
  (find-indices [2 15 11 7] 9) ; => [0 4] 

  "approach will be to iterate over array, O(N^2)")

(defn sum-to-target? [a b target]
  (= (+ a b) target))

(defn find-indices [input target]
  (for [a (range (count input))
        b (range (count input))
        :where (sum-to-target? (input a) (input b) target)]
    [a b]))


