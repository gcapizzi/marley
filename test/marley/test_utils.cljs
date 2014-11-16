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
  [node event-type]
  (if (.-createEvent js/document)
    (let [event (.createEvent js/document "Event")]
      (.initEvent event (name event-type) true true)
      (.dispatchEvent node event))))
