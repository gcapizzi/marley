(defproject marley "0.1.0-SNAPSHOT"
  :description "An aspiring Trello clone"
  :url "https://github.com/gcapizzi/marley"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [om "0.8.0-alpha2"]
                 [prismatic/om-tools "0.3.6"]
                 [prismatic/dommy  "1.0.0"]
                 [weasel "0.4.2"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [com.cemerick/clojurescript.test "0.3.1"]]

  :cljsbuild {:builds [{:id "tests"
                        :source-paths ["src" "test"]
                        :compiler {:output-to "marley.js"
                                   :output-dir "out"
                                   :optimizations :whitespace
                                   :preamble ["react/react.min.js"]
                                   :pretty-print true}}]
              :test-commands {"tests" ["phantomjs" :runner
                                       "test/vendor/es5-shim.min.js"
                                       "marley.js"]}}

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.1.3"]]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
