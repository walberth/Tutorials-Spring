/****** Object:  Insert person ******/
insert into xxxxx.person (first_name,
                          father_name,
                          mother_name,
                          sex,
                          birth_date,
                          document,
                          email,
                          mobile,
                          telephone,
                          user_register)
values ('Gualberto',
		'Gutierrez',
		'Leon',
        true,
		'1956-04-02',
		'09523652',
		'',
		'995236526',
		'4256329',
		'wgutierrez');


/****** Object:  Insert role ******/
INSERT INTO xxxxx.role (name)
VALUES ('admin');

/****** Object:  Insert permission ******/
INSERT INTO xxxxx.permission (role_id,
							  name)
VALUES (1, 'Delete'),
	   (1, 'Update'),
	   (1, 'Read'),
	   (1, 'Create');

/****** Object:  Insert user ******/
INSERT INTO xxxxx.user (username,
                        password,
                        person_id,
                        role_id,
                        active,
                        user_register)
VALUES ('wgutierrez',
        '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e',
        1,
        1,
        true,
        'wgutierrez');