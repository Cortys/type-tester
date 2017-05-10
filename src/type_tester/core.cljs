(ns type-tester.core
  (:require [reagent.core :as r]))

(def state (r/atom {:tasks '()}))

(defn type-task
  [task]
  [:div {:class "type-task"}
   (:letter task)])

(defn content
  []
  (let [state @state]
       [:div {:id "letters"}
        [type-task nil]
        [type-task (second (:tasks state))]
        [type-task (first (:tasks state))]]))

(defn next-task!
  []
  (swap! state update :tasks conj {:letter (char (+ 65 (rand-int 26)))}))

(defn current-task
  []
  (second (:tasks @state)))

(defn key-pressed-handler!
  [event]
  (when (= (:letter (current-task)) (char (.-keyCode event)))
        (next-task!)
        (println @state)))

(defn init!
  []
  (r/render [content] (.getElementById js/document "content"))
  (.addEventListener js/document "keydown" key-pressed-handler!)

  (next-task!)
  (next-task!))

(init!)
