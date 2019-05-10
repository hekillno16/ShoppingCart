CREATE TABLE user (
	id int PRIMARY KEY AUTO_INCREMENT,
	name varchar(50),
	password varchar(50)
);

INSERT INTO user (name, password) VALUES ("Tom", "tom123");
INSERT INTO user (name, password) VALUES ("John", "john123");
INSERT INTO user (name, password) VALUES ("Sarah", "sarah123");