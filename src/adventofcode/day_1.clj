(ns adventofcode.day-1
  (:require [clojure.string :as str]))

(defn load-ints [path]
  (->> path
       slurp
       str/split-lines
       (map #(Integer/parseInt %))))

(defn has-pair? [total population candidate]
  (let [required (- total candidate)
        pairing (population required)]
    pairing
    ))


(defn find-pair [total candidates]
  (filter #(has-pair? total  candidates %) candidates))


(defn find-triple [total path]
  (let [candidates (into #{} (load-ints path))]
    (loop [c candidates]
      (let [candidate (first c)
            remaining (disj c candidate)
            match-or-not (find-pair (- total candidate) remaining)]
        (if (empty? match-or-not)
          (recur remaining)
          (cons candidate match-or-not))
        ))))