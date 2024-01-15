(ns exercises.core
  (:gen-class))

(comment
  ;;;; 1 - Use the str, vector, list, hash-map, and hash-set functions. 
  (str 1 "kiko" 2)
  (vector 1 3 2)
  (list 0 93 -2 12 "kiko")
  (hash-map :a 1 :b 2 :c 3 :d 2)
  (hash-set 1 2 3 4 5 34 2 1)
  (conj (hash-set 1 2 1) 4)
  (into (hash-set 1 2 1) [3])
  (empty? #{})
  (empty? #{2})

  ;;;; 2 - Write a function that takes a number and adds 100 to it.
  (do
    (defn add100
      [number]
      (+ number 100))
    (add100 90))

  ;;;; 3 - Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction:
  (do
    (defn dec-maker
      [number]
      #(- % number))
    (def dec4 (dec-maker 4))
    (dec4 10))

  ;;;; 4 - Write a function, mapset, that works like map except the return value is a set:
  (do (defn mapset
        [f collection]
        (loop [[first & remaing] collection
               result #{}]
          (if (nil? first)
            result
            (recur
             remaing
             (conj result (f first))))))
      (mapset inc [2 10]))

  (do (defn mapset
        [f collection]
        (reduce #(conj % (f %2))
                #{} collection))
      (mapset dec [2 3 1 2 34 4]))

 ;;;; 5 - Create a function that’s similar to symmetrize-body-parts except that it has to work with weird space aliens with radial symmetry. Instead of two eyes, arms, legs, and so on, they have five.
  (do (def asym-body-parts
        [{:name "head" :size 3}
         {:name "left-eye" :size 1}
         {:name "left-ear" :size 1}
         {:name "mouth" :size 1}
         {:name "nose" :size 1}
         {:name "neck" :size 2}
         {:name "left-shoulder" :size 3}
         {:name "left-upper-arm" :size 3}
         {:name "chest" :size 10}
         {:name "back" :size 10}
         {:name "left-forearm" :size 3}
         {:name "abdomen" :size 6}
         {:name "left-kidney" :size 1}
         {:name "left-hand" :size 2}
         {:name "left-knee" :size 2}
         {:name "left-thigh" :size 4}
         {:name "left-lower-leg" :size 3}
         {:name "left-achilles" :size 1}
         {:name "left-foot" :size 2}])
      
      (defn matching-part
        [types part]
         (reduce
          #(conj 
            % 
            {:name (clojure.string/replace (:name part) #"^left-" %2)
             :size (:size part)})
          []
          types)
        )
      
      (matching-part ["right-" "top-" "center-" "bottom-"] (second asym-body-parts))

      (defn symmetrize-alien-body-parts
        [body-parts types]
        (reduce 
         #(if (re-find #"^left-" (:name %2))
            (into % (matching-part types  %2))
            (conj % %2))
         [] body-parts))

      (symmetrize-alien-body-parts asym-body-parts ["right-" "top-" "center-" "bottom-"])
      )
  )
