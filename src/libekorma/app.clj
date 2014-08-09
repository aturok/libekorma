(ns libekorma.app
	(:require [compojure.core :refer [defroutes ANY]]))

(defroutes app
	(ANY "/" [] "Hello, I am a cool service!"))
