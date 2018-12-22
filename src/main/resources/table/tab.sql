create table if not exists "user"
(
	id bigserial not null
		constraint user_pkey
			primary key,
	name varchar(255),
	surname varchar(255),
	age integer
)
;

create unique index if not exists user_id_uindex
	on "user" (id)
;

