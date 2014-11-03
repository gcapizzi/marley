(ns marley.core
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:text "Hello world!"}))

(defcomponent title
  [data owner]
  (render [_] (dom/h1 (:text data))))

(om/root
  title
  app-state
  {:target (. js/document (getElementById "app"))})
