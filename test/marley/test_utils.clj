(ns marley.test-utils)

(defmacro poll
  [assertions]
  `(js/setInterval (fn [] (if (and ~assertions) (cemerick.cljs.test/done))) 100))
