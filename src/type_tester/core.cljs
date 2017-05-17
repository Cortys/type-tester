(ns type-tester.core
  (:require [reagent.core :as r]
            [amalloy.ring-buffer :refer [ring-buffer]]))

(def buf-size 5)
(def state (r/atom {:use-numbers false
                    :count 0
                    :tasks (ring-buffer buf-size)}))

(declare fill-tasks!)

(defn type-task
  [task]
  [:div {:class "type-task", :key (:id task)}
        (:letter task)])

(defn controls
  []
  [:div {:id "controls"}
        [:input {:id "useNumbers"
                 :type "checkbox"
                 :checked (:use-numbers @state)
                 :on-change #(do (swap! state
                                        assoc :use-numbers (-> % .-target .-checked))
                                 (fill-tasks!))}]])

(defn content
  []
  (let [state @state]
       [:div {:id "content"}
             [controls]
             [:div {:id "letters"}
                   (map type-task (:tasks state))]]))

(defn current-task
  []
  (second (:tasks @state)))

(defn generate-letter
  []
  (char (if (= (:use-numbers @state) true)
          (+ 48 (rand-int 10))
          (+ 65 (rand-int 26)))))

(defn next-task!
  []
  (swap! state update :tasks conj {:id (:count (swap! state update :count inc))
                                   :letter (generate-letter)}))

(defn fill-tasks!
  []
  (doall (repeatedly buf-size next-task!)))

(defn key-pressed-handler!
  [event]
  (when (= (:letter (current-task)) (char (.-keyCode event)))
        (next-task!)))

(defn init!
  []
  (r/render [content] (.getElementById js/document "host"))
  (.addEventListener js/document "keydown" key-pressed-handler!)

  (fill-tasks!))

(init!)
