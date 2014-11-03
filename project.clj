(defproject marley "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [prismatic/om-tools "0.3.6"]
                 [om "0.8.0-alpha1"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]

  :source-paths ["src"]

  :cljsbuild {:builds [{:id "marley"
                        :source-paths ["src"]
                        :compiler {:output-to "marley.js"
                                   :output-dir "out"
                                   :optimizations :none
                                   :source-map true}}]})
