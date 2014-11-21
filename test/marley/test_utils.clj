(ns marley.test-utils)

(defmacro poll
  [assertions]
  `(js/setInterval #(if (and ~assertions) (cemerick.cljs.test/done)) 100))
