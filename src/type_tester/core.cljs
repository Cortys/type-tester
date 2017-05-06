(ns type-tester.core
  (:require [reagent.core :as r]))

(defonce current-task (r/atom "A"))

(defn type-task
  [task]
  [:div {:class "type-task"} @task])

(defn content
  []
  [type-task current-task])

(defn next-task!
  []
  (reset! current-task (char (+ 65 (rand-int 26)))))

(r/render [content] (.getElementById js/document "content"))

(js/setInterval #(next-task!) 150)
