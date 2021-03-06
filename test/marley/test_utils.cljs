(ns marley.test-utils
  (:require [dommy.core :as dommy :refer-macros [sel1]]))

(defn- container-div []
  (let [id (str "container-" (gensym))
        elem (dommy/create-element "div")]
    (dommy/set-attr! elem :id id)
    [elem (str "#" id)]))

(defn- insert-container! [container]
  (dommy/append! (dommy/sel1 js/document :body) container))

(defn new-container! []
  (let [[n s] (container-div)]
    (insert-container! n)
    (dommy/sel1 s)))

(defn fire!
  [node event-type & [update-event!]]
  (let [update-event! (or update-event! identity)
        event (.createEvent js/document "Event")]
    (.initEvent event (name event-type) true true)
    (.dispatchEvent node (update-event! event))))

(defn fire-enter-keypress!
  [node]
  (fire! node :keypress (fn [e] (do (set! (.-keyCode e) 13) e))))

(defn insert-value!
  [input value]
  (dommy/set-value! input value)
  (fire-enter-keypress! input))
