(defproject marley "0.1.0-SNAPSHOT"
  :description "An aspiring Trello clone"
  :url "https://github.com/gcapizzi/marley"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [prismatic/om-tools "0.3.6"]
                 [om "0.8.0-alpha1"]
                 [weasel "0.4.2"]]

  :source-paths ["src"]

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.3"]]
                   :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :cljsbuild {:builds [{:id "marley"
                                         :source-paths ["src"]
                                         :compiler {:output-to "marley.js"
                                                    :output-dir "out"
                                                    :optimizations :none
                                                    :source-map true}}]}}})
