(ns libekorma.util
	(:require [korma.db :refer [postgres]])
	(:import [java.util Date]
       		 [java.sql Timestamp]))

(def dbcon (postgres {:db "libekorma" :user "postgres" :password "Aw34esz"}))

(defn cur-time []
	(Timestamp. (.getTime (Date.))))
