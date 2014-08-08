(ns libekorma.entities
	(:use [korma.core]))

(declare tag)

(defentity task
	(pk :task_id)
	(entity-fields :task_id
				   :title
				   :description
				   :is_done
				   :is_cancelled
				   :created_time
				   :finished_time)
	(many-to-many tag :tasktag))

(defentity tag
	(pk :tag_id)
	(entity-fields :tag_id
				   :tag)
	(many-to-many task :tasktag))

(defentity tasktag
	(entity-fields :task_id :tag_id))
