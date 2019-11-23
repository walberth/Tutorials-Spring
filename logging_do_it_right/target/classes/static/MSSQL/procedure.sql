/****** Object:  StoredProcedure [dbo].[getPersonById]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.getPersonById') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.getPersonById
GO
CREATE PROCEDURE [dbo].[getPersonById] (
  @idPerson INT
)
AS
BEGIN
	SELECT
		A.id 'id',
		RTRIM(A.firstName) 'firstName',
		RTRIM(A.fatherLastName) 'fatherLastName',
		RTRIM(A.motherLastName) 'motherLastName',
		A.sex,
		A.birthDate 'birthDate',
		A.document,
		A.email,
		A.telephone,
		A.mobile,
		A.usernameRegister 'userRegister',
		A.timeStamp 'timeStamp'
	FROM [dbo].[person] (NOLOCK) A
	WHERE A.id = @idPerson;
END
GO

/****** Object:  StoredProcedure [dbo].[getPersonList]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.getPersonList') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.getPersonList
GO
CREATE PROCEDURE [dbo].[getPersonList] (
  @rowsPerPage INT,
  @pageNumber INT
)
AS
BEGIN
	SELECT
		A.id 'id',
		RTRIM(A.firstName) 'firstName',
		RTRIM(A.fatherLastName) 'fatherLastName',
		RTRIM(A.motherLastName) 'motherLastName',
		A.sex,
		A.birthDate 'birthDate',
		A.document,
		A.email,
		A.telephone,
		A.mobile,
		A.usernameRegister 'userRegister',
		A.timeStamp 'timeStamp'
	FROM [dbo].[person] (NOLOCK) A
	ORDER BY A.id
	OFFSET @rowsPerPage*@PageNumber ROWS
	FETCH NEXT @rowsPerPage ROWS ONLY;
END
GO

/****** Object:  StoredProcedure [dbo].[updatePerson]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.updatePerson') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.updatePerson
GO
CREATE PROCEDURE [dbo].[updatePerson] (
	@idPerson INT,
    @firstName INT,
    @fatherName VARCHAR(45),
    @motherName VARCHAR(45),
    @sex BIT,
    @birthDate DATETIME,
    @document VARCHAR(45),
    @email VARCHAR(45),
    @telephone VARCHAR(45),
    @mobile VARCHAR(45)
  )
AS
BEGIN
	UPDATE [dbo].[person]
	SET
		firstName = @firstName,
		fatherLastName = @fatherName,
		motherLastName = @motherName,
		sex = @sex,
		birthDate = @birthDate,
		document = @document,
		email = @email,
		telephone = @telephone,
		mobile = @mobile
	WHERE id = @idPerson;
END
GO

/****** Object:  StoredProcedure [dbo].[deletePerson]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.deletePerson') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.deletePerson
GO
CREATE PROCEDURE [dbo].[deletePerson] (
	@idPerson INT
  )
AS
BEGIN
	DELETE FROM [dbo].[person]
	WHERE id = @idPerson;
END
GO

/****** Object:  StoredProcedure [dbo].[createPerson]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.createPerson') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.createPerson
GO
CREATE PROCEDURE [dbo].[createPerson] (
    @firstName INT,
    @fatherName VARCHAR(45),
    @motherName VARCHAR(45),
    @sex BIT,
    @birthDate DATETIME,
    @document VARCHAR(45),
    @email VARCHAR(45),
    @mobile VARCHAR(45),
    @telephone VARCHAR(45),
	@userRegister VARCHAR(20)
  )
AS
BEGIN
    INSERT [dbo].[person] ([firstName],
						   [motherLastName],
						   [fatherLastName],
						   [sex],
						   [birthDate],
						   [document],
						   [email],
						   [mobile],
						   [telephone],
						   [usernameRegister])
    VALUES (@firstName,
            @motherName,
            @fatherName,
            @sex,
            @birthDate,
            @document,
            @email,
            @mobile,
            @telephone,
            @userRegister);

	SELECT @@IDENTITY;
END
GO

/****** Object:  StoredProcedure [dbo].[createAndGetPersonCreated]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.createAndGetPersonCreated') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.createAndGetPersonCreated
GO
CREATE PROCEDURE [dbo].[createAndGetPersonCreated] (
    @firstName INT,
    @fatherName VARCHAR(45),
    @motherName VARCHAR(45),
    @sex BIT,
    @birthDate DATETIME,
    @document VARCHAR(45),
    @email VARCHAR(45),
    @mobile VARCHAR(45),
    @telephone VARCHAR(45),
	@userRegister VARCHAR(20)
  )
AS
BEGIN
    INSERT [dbo].[person] ([firstName],
						   [motherLastName],
						   [fatherLastName],
						   [sex],
						   [birthDate],
						   [document],
						   [email],
						   [mobile],
						   [telephone],
						   [usernameRegister])
    VALUES (@firstName,
            @motherName,
            @fatherName,
            @sex,
            @birthDate,
            @document,
            @email,
            @mobile,
            @telephone,
            @userRegister);

	SELECT
		A.id 'id',
		RTRIM(A.firstName) 'firstName',
		RTRIM(A.fatherLastName) 'fatherLastName',
		RTRIM(A.motherLastName) 'motherLastName',
		A.sex,
		A.birthDate 'birthDate',
		A.document,
		A.email,
		A.telephone,
		A.mobile,
		A.usernameRegister 'userRegister',
		A.timeStamp 'timeStamp'
	FROM [dbo].[person] (NOLOCK) A
	WHERE A.id = @@IDENTITY;
END
GO

/****** Object:  StoredProcedure [dbo].[getUserInformation]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.getUserInformation') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.getUserInformation
GO
CREATE PROCEDURE [dbo].[getUserInformation] (
  @username VARCHAR(50),
  @password VARCHAR(MAX)
)
AS
BEGIN
	SELECT
		A.id 'id',
		A.idPerson 'idPerson',
		A.timeStamp 'timeStamp',
		A.username 'username',
		A.password 'password',
		B.name 'role',
        A.active,
        A.usernameRegister 'userRegister'
	FROM [dbo].[user] (NOLOCK) A
		INNER JOIN [dbo].[role] (NOLOCK) B ON A.idRole = B.id
	WHERE A.username = @username
		AND A.password = @password;
END
GO

/****** Object:  StoredProcedure [dbo].[getUser]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.getUser') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.getUser
GO
CREATE PROCEDURE [dbo].[getUser] (
  @username VARCHAR(50)
)
AS
BEGIN
	SELECT
		A.username 'username',
		B.name 'role'
	FROM [dbo].[user] (NOLOCK) A
		INNER JOIN [dbo].[role] (NOLOCK) B ON A.idRole = B.id
	WHERE A.username = @username;
END
GO

/****** Object:  StoredProcedure [dbo].[createSession]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.createSession') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.createSession
GO
CREATE PROCEDURE [dbo].[createSession] (
    @idUser INT,
    @token VARCHAR(MAX),
    @timeRelease INT,
    @expirationTime DATETIME
  )
AS
BEGIN
    INSERT [dbo].[session] ([idUser],
						    [token],
						    [timeRelease],
						    [expirationTime])
    VALUES (@idUser,
            @token,
            @timeRelease,
            @expirationTime);

	SELECT @@IDENTITY;
END
GO

/****** Object:  StoredProcedure [dbo].[updateSession]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.updateSession') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.updateSession
GO
CREATE PROCEDURE [dbo].[updateSession] (
	@username VARCHAR(50),
    @token VARCHAR(MAX),
    @expirationTime DATETIME
  )
AS
BEGIN
	UPDATE [dbo].[session]
	SET
		token = @token,
		expirationTime = @expirationTime
	WHERE id = (SELECT id FROM [dbo].[user] WHERE username = @username);
END
GO

/****** Object:  StoredProcedure [dbo].[deleteSession]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.deleteSession') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.deleteSession
GO
CREATE PROCEDURE [dbo].[deleteSession] (
	@username VARCHAR(50)
  )
AS
BEGIN
	DELETE FROM [dbo].[session]
	WHERE id = (SELECT id FROM [dbo].[user] WHERE username = @username AND active = 1);
END
GO

/****** Object:  StoredProcedure [dbo].[createUser]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.createUser') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.createUser
GO
CREATE PROCEDURE [dbo].[createUser] (
    @username VARCHAR(50),
    @password VARCHAR(MAX),
    @idPerson INT,
    @idRole INT,
    @active BIT,
	@userRegister VARCHAR(20)
  )
AS
BEGIN
    INSERT [dbo].[user] ([idPerson],
						 [idRole],
						 [username],
						 [password],
						 [active],
						 [usernameRegister])
    VALUES (@idPerson,
            @idRole,
            @username,
            @password,
            @active,
            @userRegister);

	SELECT
		A.id 'id',
		A.idPerson 'idPerson',
		A.timeStamp 'timeStamp',
		A.username 'username',
		A.password 'password',
		B.name 'role',
        A.active,
        A.usernameRegister 'userRegister'
	FROM [dbo].[user] (NOLOCK) A
		INNER JOIN [dbo].[role] B ON A.idRole = B.id
	WHERE A.id = @@IDENTITY;
END
GO

/****** Object:  StoredProcedure [dbo].[validateUserExists]  ******/
IF EXISTS (SELECT * FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N'dbo.validateUserExists') AND OBJECTPROPERTY(ID, N'IsProcedure') = 1)
    DROP PROCEDURE dbo.validateUserExists
GO
CREATE PROCEDURE [dbo].[validateUserExists] (
  @username VARCHAR(50)
)
AS
BEGIN
	SELECT
		IIF(COUNT(0) > 0, 1, 0)
	FROM [dbo].[user] (NOLOCK) A
	WHERE A.username = @username
		AND A.active = 1;
END
GO
