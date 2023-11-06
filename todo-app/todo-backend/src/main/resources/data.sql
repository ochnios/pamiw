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