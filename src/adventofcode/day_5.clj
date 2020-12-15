(ns adventofcode.day-5
  (:require [clojure.string :as str]))

(def alpha->bit
  {\F 0,
   \B 1
   \R 1
   \L 0})

(defn shift-left [i] (bit-shift-left i 1))

(defn append-bit [i b]
  (+ (shift-left i) b))

(defn alpha->id [text]
  (->> text
       (map alpha->bit)
       (reduce append-bit)))

(defn load-seats [path]
  (->> path
       slurp
       str/split-lines
       (map alpha->id)))

(defn max-seat-id-in [path]
  (->> path
       load-seats
       (apply max)))

(defn adjacent? [[a b]]
  (= (inc a) b))

(defn find-free-seat [path]
  (->> path
       load-seats
       sort
       (partition 2 1)
       (drop-while adjacent?)
       first
       first
       inc))

(defn decode [seat]
  (let [seat-id (alpha->id seat)
        [row col] (map alpha->id (split-at 7 seat))]
    (str "row " row ", column " col ", seat ID " seat-id ".")))