(ns marley.core-test
  (:require [cemerick.cljs.test :as t :refer-macros [is deftest done]]
            [marley.test-utils :as utils :refer-macros [poll]]
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

(def app {:cards [{:title "one" :description "description one"}
                  {:title "two" :description "description two"}
                  {:title "three" :description "description three"}]})

(deftest cards-view-test
  (let [c (utils/new-container!)]
    (om/root core/cards-view app {:target c})
    (is (= ["one" "two" "three"] (map dommy/text (dommy/sel c ["#todo" :div :h3]))))))

(deftest ^:async delete-a-card-test
  (let [c (utils/new-container!)]
    (om/root core/cards-view app {:target c})
    (utils/fire! (dommy/sel1 c ["div:first-child" :button]) :click)
    (poll
      (is (= ["two" "three"] (map dommy/text (dommy/sel c ["#todo" :div :h3])))))))
