(ns marley.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om-tools.core :refer-macros [defcomponent]]
            [om-tools.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]
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
  (render-state [this {:keys [delete]}]
    (dom/div
      (dom/h3 (:title card))
      (dom/p (:description card))
      (dom/button {:on-click #(put! delete @card)} "delete"))))

(defn add-card-on-enter
  [e app owner]
  (if (= "Enter" (.-key e))
    (let [input (om/get-node owner "new-card")
          new-card {:title (.-value input) :description ""}]
        (om/transact! app :cards #(conj % new-card))
        (set! (.-value input) ""))))

(defcomponent cards-view
  [app owner]
  (init-state [_]
    {:delete (chan)})
  (will-mount [_]
    (let [delete (om/get-state owner :delete)]
      (go (loop []
        (let [card (<! delete)]
          (om/transact! app :cards
            (fn [xs] (vec (remove #(= card %) xs))))
          (recur))))))
  (render-state [this {:keys [delete]}]
    (dom/div {:id "todo"}
      (dom/h2 "Todo")
      (om/build-all card-view (:cards app) {:init-state {:delete delete}})
      (dom/input {:type "text"
                  :ref "new-card"
                  :auto-focus true
                  :on-key-press #(add-card-on-enter % app owner)}))))

(defn bootstrap!
  []
  (om/root
    cards-view
    app-state
    {:target (. js/document (getElementById "app"))}))

(set! (.-onload js/window) bootstrap!)
