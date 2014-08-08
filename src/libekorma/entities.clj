(ns libekorma.entities
	(:use [korma.core]))

(defentity task
	(pk :task_id)
	(entity-fields :task_id
				   :title
				   :description
				   :is_done
				   :is_cancelled
				   :created_time
				   :finished_time))

(defentity tag
	(pk :tag_id)
	(entity-fields :tag_id
				   :tag))