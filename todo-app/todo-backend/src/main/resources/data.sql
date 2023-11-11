-- Inserting entries into CATEGORIES
INSERT INTO CATEGORIES (CATEGORY_ID, NAME)
VALUES (1, 'Science');
INSERT INTO CATEGORIES (CATEGORY_ID, NAME)
VALUES (2, 'Maths');
INSERT INTO CATEGORIES (CATEGORY_ID, NAME)
VALUES (3, 'Art');

-- Inserting entries into USERS
INSERT INTO USERS (USER_ID, NAME, SURNAME, EMAIL)
VALUES (1, 'John', 'Smith', 'johnsmith@gmail.com');
INSERT INTO USERS (USER_ID, NAME, SURNAME, EMAIL)
VALUES (2, 'Jane', 'Doe', 'janedoe@gmail.com');
INSERT INTO USERS (USER_ID, NAME, SURNAME, EMAIL)
VALUES (3, 'Robert', 'Martin', 'robertmartin@gmail.com');

-- Inserting entries into TASKS
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (1, 1, 1, 'Physics Assignment', 'Complete the Physics paper', 'InProgress');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (2, 2, 2, 'Algebra Quiz', 'Prepare for the Algebra Quiz', 'New');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (3, 3, 3, 'Art Exhibition', 'Prepare artwork for the exhibition', 'Completed');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (1, 4, 2, 'Biology Report', 'Write a report on human anatomy', 'New');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (2, 5, 1, 'Geometry Project', 'Design a 3D model for the project', 'InProgress');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (3, 6, 1, 'Photography Contest', 'Submit photos for the contest', 'Completed');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (3, 7, 3, 'Sculpture Class', 'Attend the sculpture class', 'InProgress');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (1, 8, 3, 'Chemistry Lab', 'Perform the lab experiment', 'New');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (2, 9, 2, 'Trigonometry Test', 'Revise for the Trigonometry test', 'New');
INSERT INTO TASKS (CATEGORY_ID, TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS)
VALUES (2, 10, 1, 'Calculus Homework', 'Solve the calculus problems', 'InProgress');