/****** Object: Function getPersonById  ******/
drop function if exists getPersonById(integer);
create or replace function getPersonById(integer) returns table(id int,
															    firstName varchar(45),
															    fatherLastName varchar(45),
															    motherLastName varchar(45),
															    sex bool,
															    birthDate timestamptz,
															    "document" varchar(45),
															    email varchar(50),
															    telephone varchar(20),
															    mobile varchar(20),
															    userRegister varchar(20),
															    "timeStamp" timestamptz) as
$$
	select
		A.id,
		trim(A.first_name),
		trim(A.father_name),
		trim(A.mother_name),
		A.sex,
		A.birth_date,
		A.document,
		A.email,
		A.telephone,
		A.mobile,
        A.user_register,
        A.time_stamp
	from xxxxx.person A
	where A.id = $1;
$$
language sql;

/****** Object: Procedure getPersonList  ******/
drop function if exists getPersonList(integer, integer);
create or replace function getPersonList(integer, integer) returns table (id int,
																		  firstName varchar(45),
																		  fatherLastName varchar(45),
																		  motherLastName varchar(45),
																		  sex bool,
																		  birthDate timestamptz,
																		  "document" varchar(45),
																		  email varchar(50),
																		  telephone varchar(20),
																		  mobile varchar(20),
																		  userRegister varchar(20),
																		  "timeStamp" timestamptz) as
$$
	select
		A.id,
		trim(A.first_name),
		trim(A.father_name),
		trim(A.mother_name),
		A.sex,
		A.birth_date,
		A.document,
		A.email,
		A.telephone,
		A.mobile,
        A.user_register,
        A.time_stamp
	from xxxxx.person A
	order by A.id asc offset $1*$2 limit $1;
$$
language sql;

/****** Object: Procedure updatePerson  ******/
drop procedure if exists updatePerson(integer,
                                      varchar(45),
                                      varchar(45),
                                      varchar(45),
                                      bool,
                                      timestamptz,
                                      varchar(45),
                                      varchar(50),
                                      varchar(20),
                                      varchar(20));
create or replace procedure updatePerson(integer,
                                         varchar(45),
                                         varchar(45),
                                         varchar(45),
                                         bool,
                                         timestamptz,
                                         varchar(45),
                                         varchar(50),
                                         varchar(20),
                                         varchar(20))
language plpgsql
as $$
begin
	update xxxxx.person
	set first_name = $2,
		father_name = $3,
		mother_name = $4,
		sex = $5,
		birth_date = $6,
		document = $7,
		email = $8,
		mobile = $9,
		telephone = $10
	 where id = $1;
end;
$$;

/****** Object: Procedure deletePerson  ******/
drop procedure if exists deletePerson(integer);
create or replace procedure deletePerson(integer)
language plpgsql
as $$
begin
	delete from xxxxx.person
	where id = $1;
end;
$$;

/****** Object: Function createPerson  ******/
drop function if exists createPerson(varchar(45),
                                     varchar(45),
                                     varchar(45),
                                     bool,
                                     timestamptz,
                                     varchar(45),
                                     varchar(50),
                                     varchar(20),
                                     varchar(20),
                                     varchar(20));
create or replace function createPerson(varchar(45),
                                        varchar(45),
                                        varchar(45),
                                        bool,
                                        timestamptz,
                                        varchar(45),
                                        varchar(50),
                                        varchar(20),
                                        varchar(20),
                                        varchar(20)) returns bigint as
$$
	insert into xxxxx.person(first_name,
							 father_name,
                             mother_name,
                             sex,
                             birth_date,
                             document,
                             email,
                             mobile,
                             telephone,
                             user_register)
	values ($1, $2, $3, $4, $5, $6, $7, $8, $9, $1);

	select currval(pg_get_serial_sequence('person','id'));
$$
language sql;

/****** Object: Function createAndGetPersonCreated  ******/
drop function if exists createAndGetPersonCreated(varchar(45),
			                                      varchar(45),
			                                      varchar(45),
			                                      bool,
			                                      timestamptz,
			                                      varchar(45),
			                                      varchar(50),
			                                      varchar(20),
			                                      varchar(20),
			                                      varchar(20));
create or replace function createAndGetPersonCreated(varchar(45),
			                                         varchar(45),
			                                         varchar(45),
			                                         bool,
			                                         timestamptz,
			                                         varchar(45),
			                                         varchar(50),
			                                         varchar(20),
			                                         varchar(20),
			                                         varchar(20)) returns table (id int,
																				 firstName varchar(45),
																				 fatherLastName varchar(45),
																				 motherLastName varchar(45),
																				 sex bool,
																				 birthDate timestamptz,
																				 "document" varchar(45),
																				 email varchar(50),
																				 telephone varchar(20),
																				 mobile varchar(20),
																				 userRegister varchar(20),
																				 "timeStamp" timestamptz) as
$$
	insert into xxxxx.person(first_name,
							 father_name,
                             mother_name,
                             sex,
                             birth_date,
                             document,
                             email,
                             mobile,
                             telephone,
                             user_register)
	values ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10);

	select
		A.id,
		trim(A.first_name),
		trim(A.father_name),
		trim(A.mother_name),
		A.sex,
		A.birth_date,
		A.document,
		A.email,
		A.telephone,
		A.mobile,
        A.user_register,
        A.time_stamp
	from xxxxx.person A
	where A.id = currval(pg_get_serial_sequence('person','id'));
$$
language sql;

/****** Object: Function getUserInformation  ******/
drop function if exists getUserInformation(varchar(50), text);
create or replace function getUserInformation(varchar(50), text) returns table(id int,
																			   idPerson int,
																			   "timeStamp" timestamptz,
																			   username varchar(50),
																			   "password" text,
																			   "role" varchar(45),
																			   active bool,
																			   userRegister varchar(20)) as
$$
	select
		A.id,
		A.person_id,
		A.time_stamp,
		A.username,
		A.password,
		B.name,
        A.active,
        A.user_register
	from xxxxx.user A
		inner join xxxxx.role B ON A.role_id = B.id
	where A.username = $1
		AND A.password = $2;
$$
language sql;

/****** Object: Function getUser  ******/
drop function if exists getUser(varchar(50), text);
create or replace function getUser(varchar(50)) returns table(username varchar(50),
															  "role" varchar(45)) as
$$
	select
		A.username,
		B.name
	from xxxxx.user A
		inner join xxxxx.role B ON A.role_id = B.id
	where A.username = $1;
$$
language sql;

/****** Object: Function createSession  ******/
drop function if exists createSession(integer,
                                      text,
                                      bigint,
                                      timestamptz);
create or replace function createSession(integer,
	                                     text,
	                                     bigint,
	                                     timestamptz) returns bigint as
$$
	insert into xxxxx.session(user_id,
							 token,
                             time_release,
                             expiration_time)
	values ($1, $2, $3, $4);

	select currval(pg_get_serial_sequence('session','id'));
$$
language sql;

/****** Object: Procedure updateSession  ******/
drop procedure if exists updateSession(varchar(50),
                                       text,
                                       timestamptz);
create or replace procedure updateSession(varchar(50),
                                       	  text,
                                          timestamptz)
language plpgsql
as $$
begin
	update xxxxx.session
	set token = $2,
		expiration_time = $3
	 where user_id = (SELECT id FROM xxxxx.user WHERE username = $1);
end;
$$;

/****** Object: Procedure deleteSession  ******/
drop procedure if exists deleteSession(varchar(50));
create or replace procedure deleteSession(varchar(50))
language plpgsql
as $$
begin
	delete from xxxxx.session
	 where user_id = (SELECT
	 				  	id
 				  	  FROM xxxxx.user
 				  	  WHERE username = $1
 				  	 	AND active = true);
end;
$$;

/****** Object: Function createUser  ******/
drop function if exists createUser(varchar(50),
                                   text,
                                   int,
                                   int,
                                   bool,
                                   varchar(20));
create or replace function createUser(varchar(50),
	                                  text,
	                                  int,
	                                  int,
	                                  bool,
	                                  varchar(20)) returns table (id int,
																  idPerson int,
																  "timeStamp" timestamptz,
																  username varchar(50),
																  "password" text,
																  "role" varchar(45),
																  active bool,
																  userRegister varchar(20)) as
$$
	insert into xxxxx.user(username,
						   password,
                           person_id,
                           role_id,
                           active,
                           user_register)
	values ($1, $2, $3, $4, $5, $6);

	select
		A.id,
		A.person_id,
		A.time_stamp,
		A.username,
		A.password,
		B.name,
        A.active,
        A.user_register
	from xxxxx.user A
		inner join xxxxx.role B ON A.role_id = B.id
	where A.id = currval(pg_get_serial_sequence('user','id'));
$$
language sql;

/****** Object: Function validateUserExists  ******/
drop function if exists validateUserExists(varchar(50));
create or replace function validateUserExists(varchar(50)) returns integer as
$$
	select case when count(0) > 0 then 1 else 0 end
	from xxxxx.user
	where username = $1
		and active = true;
$$
language sql;

