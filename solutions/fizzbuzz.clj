(ns cljkata.fizzbuzz)

(comment
  "Write a program that outputs increasing integers, but when the number 
  is divisible by 3 it outputs fizz, by 5 it outputs buzz, by 3 and 5 it 
  outputs fizzbuzz")

(defn replace-if-nil 
  "if thing is nil, return replacement, else return the thing" 
  [thing replacement]
  (if (nil? thing)
    replacement
    thing))

(def replacements {3 "fizz" 5 "buzz"})

(defn replace-number [n] 
  (replace-if-nil (->> replacements
                       (map #(when (zero? (mod n (% 0))) (% 1)))
                       (apply str)
                       (not-empty))
                  n))

(defn fizzbuzz [n]
  (map replace-number (range 1 (inc n))))

(comment 
  (fizzbuzz 16)
  (def answer [1 2  "fizz" 4  "buzz"  "fizz" 7 8  "fizz"  "buzz" 11  "fizz" 13 14  "fizzbuzz" 16])
  (= answer (fizzbuzz 16)))
