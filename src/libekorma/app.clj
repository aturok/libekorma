(ns libekorma.app
	(:require [compojure.core :refer [defroutes ANY]]
			  [liberator.core :refer [defresource]]
			  [korma.core :refer [select insert values]]
			  [korma.db :refer [defdb]]
			  [libekorma.util :as util]
			  [libekorma.entities :refer [task]]))

(defdb dbconnection util/dbcon)

(defn error [message]
	{:message message})

(defn tasks-request-malformed?
	[{{method :request-method} :request :as ctx}]
	(if (= :post method)
		(let [task-data (util/parse-json-body ctx)]
			(if (empty? (:title task-data))
				[true {:message "Task title missing or empty"}]
				[false {:task-data task-data}]))
		false))

(defresource tasks-r
	:available-media-types ["application/json"]
	:allowed-methods [:get :post]
	:malformed? tasks-request-malformed?
	:post!
		(fn [{task-data :task-data}]
			(let [data (into task-data {:created_time (util/cur-time)})]
				(insert task (values data))))
	:handle-ok (fn [_]
				(util/to-json
					(select task))))

(defroutes app
	(ANY "/" [] "Hello, I am a cool service!")
	(ANY "/tasks" [] tasks-r))
