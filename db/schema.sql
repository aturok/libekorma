create table task (
	task_id SERIAL,
	title character varying(100) not null,
	description character varying(1000),
	is_done boolean not null default false,
	is_cancelled boolean not null default false,
	created_time timestamp with time zone not null,
	finished_time timestamp with time zone);

create table tag (
	tag_id SERIAL,
	tag character varying(50) not null);

create table tasktag (
	task_id int not null,
	tag_id int not null
);