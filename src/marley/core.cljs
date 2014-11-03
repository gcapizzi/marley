(ns marley.core
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [weasel.repl :as ws-repl]))

(enable-console-print!)
(ws-repl/connect "ws://localhost:9001")

(def app-state (atom {:text "Hello world!"
                      :list ["one" "two" "three"]}))

(defcomponent title
  [data owner]
  (render [_] (dom/h1 (:text data))))

(defcomponent list
  [data owner]
  (render [_] (dom/ul (for [item (:list data)] (dom/li item)))))

(om/root
  list
  app-state
  {:target (. js/document (getElementById "app"))})
