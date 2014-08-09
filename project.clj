(defproject libekorma "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
  				 [korma "0.3.2"]
  				 [org.postgresql/postgresql "9.2-1002-jdbc4"]
  				 [compojure "1.1.3"]
  				 [ring "1.3.0"]
  				 [liberator "0.10.0"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler libekorma.app/app})
