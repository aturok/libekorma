(ns libekorma.app
	(:require [compojure.core :refer [defroutes ANY]]
			  [liberator.core :refer [defresource]]
			  [korma.core :refer [select]]
			  [korma.db :refer [defdb]]
			  [libekorma.util :as util]
			  [libekorma.entities :refer [task]]))

(defdb dbconnection util/dbcon)

(defresource tasks-r
	:available-media-types ["application/json"]
	:allowed-methods [:get]
	:handle-ok (fn [_]
				(util/to-json
					(select task))))

(defroutes app
	(ANY "/" [] "Hello, I am a cool service!")
	(ANY "/tasks" [] tasks-r))
