drop table if exists xxxxx.user;
drop table if exists xxxxx.permission;
drop table if exists xxxxx.role;
drop table if exists xxxxx.person;
drop table if exists xxxxx.session;

drop schema if exists xxxxx;
create schema xxxxx;

-- -----------------------------------------------------
-- Table `xxxxx`.`person`
-- -----------------------------------------------------
create table xxxxx.person(
	id serial primary key not null,
	first_name varchar(45),
	father_name varchar(45),
	mother_name varchar(45),
    sex boolean not null default true,
	birth_date timestamptz,
	document varchar(45) not null,
	email varchar(50),
	mobile varchar(20),
	telephone varchar(20),
    user_register varchar(20) not null,
	search_field tsvector,
	time_stamp timestamptz default now()
);
create index idx_search_person ON xxxxx.person using GIN(search_field);

-- -----------------------------------------------------
-- Table `xxxxx`.`role`
-- -----------------------------------------------------
create table xxxxx.role(
	id serial primary key not null,
	name varchar(50) unique not null
);

-- -----------------------------------------------------
-- Table `xxxxx`.`permission`
-- -----------------------------------------------------
create table xxxxx.permission(
	id serial primary key not null,
	role_id int not null references xxxxx.role(id) on delete cascade,
	name varchar(50) unique not null
);

-- -----------------------------------------------------
-- Table `xxxxx`.`user`
-- -----------------------------------------------------
create table xxxxx.user(
	id serial primary key not null,
	username varchar(50) unique not null,
	password text not null,
	person_id int not null references xxxxx.person(id) on delete cascade,
	role_id int not null references xxxxx.role(id) on delete cascade,
	active boolean not null default true,
    user_register varchar(20) not null,
	time_stamp timestamptz default now()
);
create index idx_person ON xxxxx.user(person_id);

-- -----------------------------------------------------
-- Table `xxxxx`.`session`
-- -----------------------------------------------------
create table xxxxx.session(
	id serial primary key not null,
	user_id int not null references xxxxx.user(id),
	token text not null,
	time_release int not null,
	expiration_time timestamptz
);
create index idx_user ON xxxxx.session(user_id);
