/****** Object: Procedure getPersonById  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS getPersonById $$
CREATE PROCEDURE getPersonById(IN idPerson INT)
BEGIN
	SELECT
		A.id 'id',
		trim(A.first_name) 'firstName',
		trim(A.father_name) 'fatherLastName',
		trim(A.mother_name) 'motherLastName',
		A.sex,
		A.birth_date 'birthDate',
		A.document,
		A.email,
		A.telephone,
		A.mobile,
        A.user_register 'userRegister',
        A.time_stamp 'timeStamp'
	FROM xxxxx.person A
	WHERE A.id = idPerson;
END $$
DELIMITER ;

/****** Object: Procedure getPersonList  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS getPersonList $$
CREATE PROCEDURE getPersonList(IN rowsPerPage INT,
                               IN pageNumber INT)
BEGIN
	SELECT
		A.id,
		trim(A.first_name) 'firstName',
		trim(A.father_name) 'fatherLastName',
		trim(A.mother_name) 'motherLastName',
		A.sex,
		A.birth_date 'birthDate',
		A.document,
		A.email,
		A.telephone,
		A.mobile,
        A.user_register 'userRegister',
        A.time_stamp 'timeStamp'
	FROM xxxxx.person A
	ORDER BY A.id ASC LIMIT pageNumber, rowsPerPage;
END $$
DELIMITER ;

/****** Object: Procedure updatePerson  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS updatePerson $$
CREATE PROCEDURE updatePerson(IN idPerson INT,
                               IN firstName VARCHAR(45),
                               IN fatherName VARCHAR(45),
                               IN motherName VARCHAR(45),
                               IN sex TINYINT(4),
                               IN birthDate DATETIME,
                               IN document VARCHAR(45),
                               IN email VARCHAR(45),
                               IN telephone VARCHAR(45),
                               IN mobile VARCHAR(45))
BEGIN
	UPDATE xxxxx.person
    SET
		first_name = firstName,
        father_name = fatherName,
        mother_name = motherName,
        sex = sex,
        birth_date = birthDate,
        document = document,
        email = email,
        telephone = telephone,
        mobile = mobile
	WHERE id = idPerson;
END $$
DELIMITER ;

/****** Object: Procedure deletePerson  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS deletePerson $$
CREATE PROCEDURE deletePerson(IN idPerson INT)
BEGIN
	DELETE FROM xxxxx.person
	WHERE id = idPerson;
END $$
DELIMITER ;

/****** Object: Procedure createPerson  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS createPerson $$
CREATE PROCEDURE createPerson(IN firstName VARCHAR(45),
                               IN fatherName VARCHAR(45),
                               IN motherName VARCHAR(45),
                               IN sex TINYINT(4),
                               IN birthDate DATETIME,
                               IN document VARCHAR(45),
                               IN email VARCHAR(45),
                               IN mobile VARCHAR(45),
                               IN telephone VARCHAR(45),
                               IN userRegister VARCHAR(20))
BEGIN
	INSERT INTO xxxxx.person (first_name,
							  father_name,
                              mother_name,
                              sex,
                              birth_date,
                              document,
                              email,
                              mobile,
                              telephone,
                              user_register)
    VALUES(firstName,
           fatherName,
           motherName,
           sex,
           birthDate,
           document,
		   email,
           mobile,
           telephone,
           userRegister);

	SELECT LAST_INSERT_ID();
END $$
DELIMITER ;

/****** Object: Procedure createAndGetPersonCreated  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS createAndGetPersonCreated $$
CREATE PROCEDURE createAndGetPersonCreated(IN firstName VARCHAR(45),
										   IN fatherName VARCHAR(45),
										   IN motherName VARCHAR(45),
										   IN sex TINYINT(4),
										   IN birthDate DATETIME,
										   IN document VARCHAR(45),
										   IN email VARCHAR(45),
										   IN mobile VARCHAR(45),
										   IN telephone VARCHAR(45),
										   IN userRegister VARCHAR(20))
BEGIN
	INSERT INTO xxxxx.person (first_name,
							  father_name,
                              mother_name,
                              sex,
                              birth_date,
                              document,
                              email,
                              mobile,
                              telephone,
                              user_register)
    VALUES(firstName,
           fatherName,
           motherName,
           sex,
           birthDate,
           document,
		   email,
           mobile,
           telephone,
           userRegister);

	SELECT
		A.id,
		trim(A.first_name) 'firstName',
		trim(A.father_name) 'fatherLastName',
		trim(A.mother_name) 'motherLastName',
		A.sex,
		A.birth_date 'birthDate',
		A.document,
		A.email,
		A.telephone,
		A.mobile,
        A.user_register 'userRegister',
        A.time_stamp 'timeStamp'
	FROM xxxxx.person A WHERE A.id = LAST_INSERT_ID();
END $$
DELIMITER ;

/****** Object: Procedure getUserInformation  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS getUserInformation $$
CREATE PROCEDURE getUserInformation(IN username VARCHAR(50), IN password TEXT)
BEGIN
	SELECT
		A.id 'id',
		A.person_id 'idPerson',
		A.time_stamp 'timeStamp',
		A.username 'username',
		A.password 'password',
		B.name 'role',
        A.active,
        A.user_register 'userRegister'
	FROM xxxxx.user A
		INNER JOIN xxxxx.role B ON A.role_id = B.id
	WHERE A.username = username
		AND A.password = password;
END $$
DELIMITER ;

/****** Object: Procedure getUser  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS getUser $$
CREATE PROCEDURE getUser(IN username VARCHAR(50))
BEGIN
	SELECT
		A.username 'username',
		B.name 'role'
	FROM xxxxx.user A
		INNER JOIN xxxxx.role B ON A.role_id = B.id
	WHERE A.username = username;
END $$
DELIMITER ;

/****** Object: Procedure createSession  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS createSession $$
CREATE PROCEDURE createSession(IN idUser INT,
                               IN token TEXT,
                               IN timeRelease BIGINT,
                               IN expirationTime DATETIME)
BEGIN
	INSERT INTO xxxxx.session (user_id,
							   token,
                               time_release,
                               expiration_time)
    VALUES(idUser,
           token,
           timeRelease,
           expirationTime);

	SELECT LAST_INSERT_ID();
END $$
DELIMITER ;

/****** Object: Procedure updateSession  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS updateSession $$
CREATE PROCEDURE updateSession(IN username VARCHAR(50),
							   IN token TEXT,
                               IN expirationTime DATETIME)
BEGIN
	UPDATE xxxxx.session
    SET
		token = token,
        expiration_time = expirationTime
	WHERE user_id = (SELECT id FROM xxxxx.user WHERE username = username);
END $$
DELIMITER ;

/****** Object: Procedure deleteSession  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS deleteSession $$
CREATE PROCEDURE deleteSession(IN username VARCHAR(50))
BEGIN
	DELETE FROM xxxxx.session
	WHERE user_id = (SELECT
	                    id
	                 FROM xxxxx.user
	                 WHERE username = username
		                AND active = true);
END $$
DELIMITER ;

/****** Object: Procedure createUser  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS createUser $$
CREATE PROCEDURE createUser(IN username VARCHAR(50),
						    IN password TEXT,
						    IN idPerson INT,
						    IN idRole INT,
						    IN active TINYINT(4),
						    IN userRegister VARCHAR(20))
BEGIN
	INSERT INTO xxxxx.user (username,
							password,
                            person_id,
                            role_id,
                            active,
                            user_register)
    VALUES(username,
           password,
           idPerson,
           idRole,
           active,
           userRegister);

	SELECT
		A.id 'id',
		A.person_id 'idPerson',
		A.time_stamp 'timeStamp',
		A.username 'username',
		A.password 'password',
		B.name 'role',
        A.active,
        A.user_register 'userRegister'
	FROM xxxxx.user A
		INNER JOIN xxxxx.role B ON A.role_id = B.id
	WHERE A.id = LAST_INSERT_ID();
END $$
DELIMITER ;

/****** Object: Procedure validateUserExists  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS validateUserExists $$
CREATE PROCEDURE validateUserExists(IN username VARCHAR(50))
BEGIN
	SELECT IF(COUNT(0) > 0, 1, 0)
	FROM xxxxx.user
	WHERE username = username
		AND active = true;
END $$
DELIMITER ;