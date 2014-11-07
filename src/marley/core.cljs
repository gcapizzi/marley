(ns marley.core
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [weasel.repl :as ws-repl]))

(enable-console-print!)
(ws-repl/connect "ws://localhost:9001")

(def app-state
  (atom
    {:cards [{:title "First card" :description "First card description"}
             {:title "Second card" :description "Second card description"}
             {:title "Third card" :description "Third card description"}]}))

(defcomponent card-view
  [card owner]
  (render [this]
    (dom/div
      (dom/h3 (:title card))
      (dom/p (:description card))
      (dom/button "delete"))))

(defcomponent cards-view
  [data owner]
  (render [this]
    (dom/div
      (dom/h2 "Todo")
      (om/build-all card-view (:cards data)))))

(om/root
  cards-view
  app-state
  {:target (. js/document (getElementById "app"))})
