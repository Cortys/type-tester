(ns type-tester.core
  (:require [reagent.core :as r]
            [amalloy.ring-buffer :refer [ring-buffer]]))

(def buf-size 5)

(def state (r/atom {:count 0
                    :tasks (ring-buffer buf-size)}))

(defn type-task
  [task]
  [:div {:class "type-task", :key (:id task)}
   (:letter task)])

(defn content
  []
  (let [state @state]
       [:div {:id "letters"}
        (map type-task (:tasks state))]))

(defn current-task
  []
  (second (:tasks @state)))

(defn next-task!
  []
  (swap! state update :tasks conj {:id (:count (swap! state update :count inc))
                                   :letter (char (+ 65 (rand-int 26)))}))

(defn key-pressed-handler!
  [event]
  (when (= (:letter (current-task)) (char (.-keyCode event)))
        (next-task!)))

(defn init!
  []
  (r/render [content] (.getElementById js/document "content"))
  (.addEventListener js/document "keydown" key-pressed-handler!)

  (doall (repeatedly buf-size next-task!)))

(init!)
