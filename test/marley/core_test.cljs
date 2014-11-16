(ns marley.core-test
  (:require-macros [cemerick.cljs.test :refer (is deftest testing done)])
  (:require [cemerick.cljs.test :as t]
            [marley.test-utils :as utils]
            [dommy.core :as dommy :refer-macros [sel sel1]]
            [om.core :as om :include-macros true]
            [marley.core :as core]))

(deftest card-view-test
  (let [c (utils/new-container!)
        card {:title "a title"
              :description "a description"}]
    (om/root core/card-view card {:target c})
    (is (= "a title" (dommy/text (dommy/sel1 c :h3))))
    (is (= "a description" (dommy/text (dommy/sel1 c :p))))))

(def data {:cards [{:title "one" :description "description one"}
                   {:title "two" :description "description two"}
                   {:title "three" :description "description three"}]})

(deftest cards-view-test
  (let [c (utils/new-container!)]
    (om/root core/cards-view data {:target c})
    (is (= ["one" "two" "three"] (map dommy/text (dommy/sel c ["#todo" :div :h3]))))))

(deftest ^:async delete-a-card-test
  (let [c (utils/new-container!)]
    (om/root core/cards-view data {:target c})
    (utils/fire! (dommy/sel1 c ["div:first-child" :button]) :click)
    (js/setTimeout
      (fn []
        (is (= ["two" "three"] (map dommy/text (dommy/sel c ["#todo" :div :h3]))))
        (done)) 1000)))
