/****** Object:  Insert person ******/
INSERT INTO [dbo].[person]
           ([firstName],
		    [motherLastName],
			[fatherLastName],
			[sex],
			[birthDate],
			[document],
			[email],
			[mobile],
			[telephone],
			[usernameRegister])
     VALUES
           ('Gualberto',
			'Gutierrez',
			'Leon',
			1,
			'1956-04-02',
			'09523652',
			'',
			'995236526',
			'4256329',
			'wgutierrez');
GO

/****** Object:  Insert role ******/
INSERT INTO [dbo].[role] ([name])
VALUES ('admin');
GO

/****** Object:  Insert permission ******/
INSERT INTO [dbo].[permission] ([idRole],
							    [name])
VALUES (1, 'Delete'),
	   (1, 'Update'),
	   (1, 'Read'),
	   (1, 'Create');

/****** Object:  Insert user ******/
INSERT INTO [dbo].[user] ([idPerson],
						  [idRole],
						  [username],
                          [password],
                          [active],
                          [usernameRegister])
VALUES (1,
        1,
		'wgutierrez',
        '$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e',
        1,
        'wgutierrez');


