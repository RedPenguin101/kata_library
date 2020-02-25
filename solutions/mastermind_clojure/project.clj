(defproject mastermind "0.0.1-SNAPSHOT"
  :description "Mastermind CLI game"
  :main ^:skip-aot mastermind.core
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :profiles {:dev {:dependencies [[midje "1.9.9"]]}
             :midje {}})

  
