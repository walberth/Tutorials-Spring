INSERT INTO application (id, name, description, owner) VALUES (1, 'Trackzilla','A bug tracking application', 'Kesha Williams');
INSERT INTO application (id, name, description, owner) VALUES (2, 'Expenses','An application used to submit expenses', 'Jane Doe');
INSERT INTO application (id, name, description, owner) VALUES (3, 'Bookings','An application used to book tickets', 'John Doe');
INSERT INTO application (id, name, description, owner) VALUES (4, 'Invoice Search','An application used to search invoices ', 'Mary Richards');
INSERT INTO application (id, name, description, owner) VALUES (5, 'Audits','An application used for auditing purposes.', 'Tiffany Stewart');
INSERT INTO ticket (id, title, description, application_id, status) VALUES (1, 'Sort Feature','Add the ability to sort tickets by severity',1,'OPEN');
INSERT INTO ticket (id, title, description, application_id, status) VALUES (2, 'Search Feature','Add the ability to search by invoice date',4,'IN PROGRESS');
INSERT INTO ticket (id, title, description, application_id, status) VALUES (3, 'Audit','Add the ability to audit by year',5,'CLOSED');
INSERT INTO ticket (id, title, description, application_id, status) VALUES (4, 'Booking Feature','Add the ability to book tickets online',3,'OPEN');
INSERT INTO release (id, name, description, release_date) VALUES (1, 'Release 1', 'Q1 Release Containing High Priority Bugs', '2030-02-14');
INSERT INTO release (id, name, description, release_date) VALUES (2, 'Release 2', 'Q2 Release Containing High Priority Enhancements', '2030-05-27');
INSERT INTO release (id, name, description, release_date) VALUES (3, 'Release 3', 'Q3 Release Containing Bugs', '2030-09-14');
INSERT INTO release (id, name, description, release_date) VALUES (4, 'Release 4', 'Q4 Release Containing Enhancements', '2030-12-10');

