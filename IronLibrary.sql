DROP SCHEMA IF EXISTS library;
CREATE SCHEMA library;
USE library;

CREATE TABLE book (
isbn VARCHAR (255) NOT NULL, 
title VARCHAR (255), 
cathegory VARCHAR (255), 
quantity INT,
PRIMARY KEY (isbn)
);

CREATE TABLE author (
author_id INT NOT NULL AUTO_INCREMENT,
name VARCHAR (255),
email VARCHAR (255),
author_book VARCHAR (255),
PRIMARY KEY (author_id),
FOREIGN KEY (author_book) REFERENCES book (isbn)
);

CREATE TABLE student (
usn VARCHAR (255) NOT NULL, 
name VARCHAR (255), 
PRIMARY KEY (usn)
);


CREATE TABLE issue (
issue_id INT NOT NULL AUTO_INCREMENT,
issue_date VARCHAR (255),
return_date VARCHAR (255),
usn_student VARCHAR (255),
issue_book VARCHAR (255),
PRIMARY KEY (issue_id),
FOREIGN KEY (usn_student) REFERENCES student (usn),
FOREIGN KEY (issue_book) REFERENCES book (isbn)
);
