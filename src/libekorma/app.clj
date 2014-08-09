(ns libekorma.app
	(:require [compojure.core :refer [defroutes ANY]]
			  [liberator.core :refer [defresource]]
			  [korma.core :refer
			  	[select insert values where delete update set-fields]]
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

(defn task-update-request-malformed?
	[{{method :request-method} :request :as ctx}]
	(if (= :put method)
		(let [task-data (util/parse-json-body ctx)]
			(cond
				(empty? task-data)
					[true {:message "No new values specififed"}]
			 	(and (contains? task-data :title)
			 		 (empty? (:title task-data)))
					[true {:message "Empty title is not allowed"}]
				true
					[false {:task-data task-data}]))
		false))

(defresource one-task-r [task-id]
	:available-media-types ["application/json"]
	:allowed-methods [:get :delete :put]
	:exists?
		(fn [_]
			(if-let [task
				(first (select task (where {:task_id task-id})))]
				[true {:task task}]
				[false {:message "Task not found"}]))
	:delete!
		(fn [{{task-id :task_id} :task}]
			(delete task
				(where {:task_id task-id})))
	:can-put-to-missing? false
	:malformed? task-update-request-malformed?
	:put!
		(fn [{new-task :task-data old-task :task}]
			(let [just-completed?
					(and (true? (:is_done new-task))
						 (false? (:is_done old-task)))
				  just-cancelled?
					(and (true? (:is_cancelled new-task))
						 (false? (:is_cancelled old-task)))
				  finished-time-dict
				  	(if (or just-completed? just-cancelled?)
				  		{:finished_time (util/cur-time)}
				  		{})
				  updated
				  	(into finished-time-dict
				  		(filter
				  			(fn [[k _]] (#{:title :description :is_cancelled :is_done} k))
				  			new-task))]
				(update task
					(set-fields updated)
					(where {:task_id (:task_id old-task)}))))
	:handle-ok
		(fn [{task :task}]
			(util/to-json task)))

(defroutes app
	(ANY "/" [] "Hello, I am a cool service!")
	(ANY "/tasks" [] tasks-r)
	(ANY "/task/:task-id" [task-id] (one-task-r (Integer/parseInt task-id))))
