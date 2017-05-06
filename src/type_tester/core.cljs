(ns type-tester.core
  (:require [reagent.core :as r]))

(defn content
  []
  [:div [:h1 "Hello World!"]
        [:p "This is a reagent test."]])

(r/render [content] (.getElementById js/document "content"))
