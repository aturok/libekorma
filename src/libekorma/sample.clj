(ns libekorma.sample
	(:use [korma.core]
		  [korma.db]
		  [libekorma.entities]
		  [libekorma.util]))

(defdb dbconnection dbcon)

(defn insert-sample-tags []
	(insert tag
		(values
			[{:tag "health"}
			 {:tag "leisure"}
			 {:tag "paperwork"}])))

(defn insert-sample-tasks []
	(insert task
		(values
			[{:title "Write down some stuff" :created_time (cur-time)}
			 {:title "Do pull-ups" :description "12-12-12" :created_time (cur-time)}
			 {:title "Buy some milk" :created_time (cur-time)}
			 {:title "Pick a movie for the weekend" :description "Maybe MiB3 or something" :created_time (cur-time)}])))