(ns marley.core-test
  (:require-macros [cemerick.cljs.test :refer (is deftest testing)])
  (:require [cemerick.cljs.test :as t]
            [dommy.core :as dommy :refer-macros [sel sel1]]
            [om.core :as om :include-macros true]
            [marley.core :as core]))

(defn container-div []
  (let [id (str "container-" (gensym))
        elem (dommy/create-element "div")]
    (dommy/set-attr! elem :id id)
    [elem (str "#" id)]))

(defn insert-container! [container]
  (dommy/append! (dommy/sel1 js/document :body) container))

(defn new-container! []
  (let [[n s] (container-div)]
    (insert-container! n)
    (dommy/sel1 s)))

(deftest card-view-test
  (let [c (new-container!)
        card {:title "a title"
              :description "a description"}]
    (om/root core/card-view card {:target c})
    (is (= "a title" (dommy/text (dommy/sel1 c :h3))))
    (is (= "a description" (dommy/text (dommy/sel1 c :p))))))
