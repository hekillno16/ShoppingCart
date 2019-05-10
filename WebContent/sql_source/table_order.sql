CREATE TABLE order_ (
	id int PRIMARY KEY AUTO_INCREMENT,
	uid int	
);

CREATE TABLE orderitem (
	id int PRIMARY KEY AUTO_INCREMENT,
	pid int,
	num int,
	oid int	
);