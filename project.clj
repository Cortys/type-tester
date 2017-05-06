(defproject type-tester "0.1.0"
  :description "A small ClojureScript experiment."
  :url "https://github.com/Cortys/type-tester"

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-figwheel "0.5.10"]]

  :dependencies [; Dev:
                 [proto-repl "0.3.1"]

                 ; Runtime:
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.521"]
                 [reagent "0.6.1"]]

  :main type-tester.core

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main type-tester.core
                                   :asset-path "js/out"
                                   :output-to "resources/public/js/main.js"
                                   :output-dir "resources/public/js/out"
                                   :pretty-print true
                                   :source-map true}}]}

  :figwheel {:css-dirs ["resources/public/css"]})
