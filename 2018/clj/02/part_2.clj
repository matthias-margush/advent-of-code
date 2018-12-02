#!/usr/bin/env clojure

(ns part-2
  "--- Part Two ---

  Confident that your list of box IDs is complete, you're ready to find the
  boxes full of prototype fabric.

  The boxes will have IDs which differ by exactly one character at the same
  position in both strings. For example, given the following box IDs:

  abcde
  fghij
  klmno
  pqrst
  fguij
  axcye
  wvxyz

  The IDs abcde and axcye are close, but they differ by two characters (the
  second and fourth). However, the IDs fghij and fguij differ by exactly one
  character, the third (h and u). Those must be the correct boxes.

  What letters are common between the two correct box IDs? (In the example
  above, this is found by removing the differing character from either ID,
  producing fgij.)"

  (:require [clojure.java.io :as io]
            [clojure.math.combinatorics :refer [combinations]]
            [clojure.set :as set]))

(defn ->set-of-indexes-and-chars
  "(->set-of-indexes-and-chars abcdef) ;; => #{[0 \\a] [2 \\c] [5 \\f] [4 \\e] [3 \\d] [1 \\b]}"
  [s]
  (->>
   (map (fn [i c] [i c]) (range) s)
   (into #{})))

(defn distance
  [[s1 s2]]
  (count
   (set/difference
    (->set-of-indexes-and-chars s1)
    (->set-of-indexes-and-chars s2))))

(with-open [input (io/reader "input.txt")]
  (->>
   (combinations (line-seq input) 2)
   (filter #(= 1 (distance %)))
   (map (fn [[s1 s2]] (map #(if (= %1 %2) %1) s1 s2)))
   (first)
   (apply str)
   (println)))
