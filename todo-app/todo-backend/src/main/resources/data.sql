-- Inserting entries into CATEGORIES
INSERT INTO CATEGORIES (NAME)
VALUES ('Science');
INSERT INTO CATEGORIES (NAME)
VALUES ('Maths');
INSERT INTO CATEGORIES (NAME)
VALUES ('Art');

-- Inserting entries into USERS
INSERT INTO USERS (NAME, SURNAME, EMAIL)
VALUES ('John', 'Smith', 'johnsmith@gmail.com');
INSERT INTO USERS (NAME, SURNAME, EMAIL)
VALUES ('Jane', 'Doe', 'janedoe@gmail.com');
INSERT INTO USERS (NAME, SURNAME, EMAIL)
VALUES ('Robert', 'Martin', 'robertmartin@gmail.com');

-- Inserting entries into TASKS
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (1, 1, 'Physics Assignment', 'Complete the Physics paper', 'InProgress');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (2, 2, 'Algebra Quiz', 'Prepare for the Algebra Quiz', 'New');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (3, 3, 'Art Exhibition', 'Prepare artwork for the exhibition', 'Completed');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (1, 2, 'Biology Report', 'Write a report on human anatomy', 'New');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (2, 1, 'Geometry Project', 'Design a 3D model for the project', 'InProgress');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (3, 1, 'Photography Contest', 'Submit photos for the contest', 'Completed');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (3, 3, 'Sculpture Class', 'Attend the sculpture class', 'InProgress');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (1, 3, 'Chemistry Lab', 'Perform the lab experiment', 'New');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (2, 2, 'Trigonometry Test', 'Revise for the Trigonometry test', 'New');
INSERT INTO TASKS (CATEGORY_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (2, 1, 'Calculus Homework', 'Solve the calculus problems', 'InProgress');