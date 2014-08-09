(ns libekorma.util
	(:require [korma.db :refer [postgres]]
			  [clojure.data.json :as json])
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
