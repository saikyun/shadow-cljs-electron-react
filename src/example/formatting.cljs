(ns example.formatting)

(defn left-pad
  [length padding string]
  (let [s (str string)
        l (max 0 (- length (count s)))]
    (str (apply str (repeat l padding))
         s)))

(defn format-single
  [format date]
  (case format
    :yyyy (.getFullYear date)
    :mm (left-pad 2 "0" (inc (.getMonth date)))
    :dd (left-pad 2 "0" (.getDate date))
    format))

(defn format
  [[f & fs] date & res]
  (cond
    (nil? date) ""
    (nil? f)    res
    :otherwise  (recur fs
                       date
                       (str res (format-single f date)))))
