(defproject wesa "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring "1.8.0"]
                 [ring/ring-defaults "0.3.2"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [org.danielsz/system "0.4.5"]
                 [environ "1.1.0"]
                 [twitter-api "1.8.0"]
                 [com.github.apanimesh061/vader-sentiment-analyzer "1.0"]
                 [log4j/log4j "1.2.17"]
                 [org.slf4j/slf4j-nop "1.7.30"]
                 [org.apache.lucene/lucene-analyzers-common "6.4.1"]
                 ]
  :plugins [[lein-environ "1.1.0"]]
  :repl-options {:init-ns wesa.core}
  :profiles {:dev [:project/dev :profiles/dev]
             :profiles/dev {}
             :project/dev {:source-paths ["dev"]
                           :env {:http-port 3000}}
             :prod [:project/prod :profiles/prod]
             :project/prod {:env {:http-port 8000
                                  :repl-port 8001}}
             :profiles/prod {}
             :uberjar {:aot          :all
                       :uberjar-name "wesa.jar"
                       }}
  :main ^:skip-aot wesa.core
  :target-path "target/%s")
