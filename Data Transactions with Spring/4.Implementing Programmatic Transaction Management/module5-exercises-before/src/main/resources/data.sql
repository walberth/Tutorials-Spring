INSERT INTO application (id, name, description, owner) VALUES (1, 'Trackzilla','A bug tracking application', 'Kesha Williams');
INSERT INTO application (id, name, description, owner) VALUES (2, 'Expenses','An application used to submit expenses', 'Jane Doe');
INSERT INTO application (id, name, description, owner) VALUES (3, 'Bookings','An application used to book tickets', 'John Doe');
INSERT INTO application (id, name, description, owner) VALUES (4, 'Invoice Search','An application used to search invoices ', 'Mary Richards');
INSERT INTO application (id, name, description, owner) VALUES (5, 'Audits','An application used for auditing purposes.', 'Tiffany Stewart');
INSERT INTO ticket (id, title, description, application_id, status) VALUES (1, 'Sort Feature','Add the ability to sort tickets by severity',1,'OPEN');
INSERT INTO ticket (id, title, description, application_id, status) VALUES (2, 'Search Feature','Add the ability to search by invoice date',4,'IN PROGRESS');
INSERT INTO ticket (id, title, description, application_id, status) VALUES (3, 'Audit','Add the ability to audit by year',5,'CLOSED');
INSERT INTO ticket (id, title, description, application_id, status) VALUES (4, 'Booking Feature','Add the ability to book tickets online',3,'OPEN');


