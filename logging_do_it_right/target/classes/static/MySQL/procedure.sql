/****** Object: Procedure getPersonById  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS getPersonById $$
CREATE PROCEDURE getPersonById(IN id INT)
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
	WHERE A.id = id;
END $$
DELIMITER ;

/****** Object: Procedure getAllPerson  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS getAllPerson $$
CREATE PROCEDURE getAllPerson()
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
	ORDER BY A.id ASC;
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

/****** Object: Procedure deletePerson  ******/
DELIMITER $$
DROP PROCEDURE IF EXISTS deletePerson $$
CREATE PROCEDURE deletePerson(IN idPerson INT)
BEGIN
	DELETE FROM xxxxx.person
	WHERE id = idPerson;
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
