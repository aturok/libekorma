(ns libekorma.util
	(:require [korma.db :refer [postgres]]
			  [clojure.data.json :as json]
			  [clojure.java.io :as io])
	(:import [java.util Date]
       		 [java.sql Timestamp]))

(def dbcon (postgres {:db "libekorma" :user "postgres" :password "Aw34esz"}))

(defn cur-time []
	(Timestamp. (.getTime (Date.))))

(def to-json json/write-str)

(extend-type java.sql.Timestamp
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))

(defn body-as-string [ctx]
  (if-let [body (get-in ctx [:request :body])]
    (condp instance? body
      java.lang.String body
      (slurp (io/reader body)))))

(defn keywordify [dict]
	(into {}
		(map (fn [[k v]] [(keyword k) v]) dict)))

(defn parse-json-body [context]
	(if-let [body (body-as-string context)]
		(keywordify (json/read-str body))
		{}))
