DROP TABLE IF EXISTS product;
CREATE TABLE product (
	id int DEFAULT NULL,
	name varchar(50) DEFAULT NULL,
	price float DEFAULT NULL
);

INSERT INTO product VALUES(1,'CPU',500);
INSERT INTO product VALUES(2,'Laptop',1500);
INSERT INTO product VALUES(3,'Monitor',180);
INSERT INTO product VALUES(4,'Cable',10);
