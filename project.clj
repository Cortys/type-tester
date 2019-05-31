(defproject type-tester "0.1.0"
  :description "A small ClojureScript experiment."
  :url "https://github.com/Cortys/type-tester"

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-figwheel "0.5.10"]]

  :dependencies [; Dev:
                 [proto-repl "0.3.1"]

                 ; Runtime:
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520"]
                 [amalloy/ring-buffer "1.3.0"]
                 [reagent "0.8.1"]]

  :main type-tester.core

  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]
                        :figwheel true
                        :compiler {:main type-tester.core
                                   :asset-path "js/dev"
                                   :output-to "resources/public/js/main.js"
                                   :output-dir "resources/public/js/dev"
                                   :pretty-print true
                                   :source-map true}}
                       {:id "production"
                        :source-paths ["src"]
                        :compiler {:main type-tester.core
                                   :output-to "resources/public/js/main.js"
                                   :output-dir "target/out"
                                   :optimizations :advanced}}]}

  :figwheel {:css-dirs ["resources/public/css"]})
