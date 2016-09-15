CREATE TABLE ecategory(
	title VARCHAR(255) NOT NULL PRIMARY KEY,
	removable BOOLEAN NOT NULL
);

CREATE TABLE icategory(
	title VARCHAR(255) NOT NULL PRIMARY KEY,
	removable BOOLEAN NOT NULL
);

CREATE TABLE expenses(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	hashid INT NOT NULL,
	category varchar(255) NOT NULL,
	mydate DATE NOT NULL,
	title VARCHAR(255) NOT NULL,
	price INT NOT NULL,
	FOREIGN KEY (category) REFERENCES ecategory(title)
);

CREATE TABLE incomes(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	hashid INT NOT NULL,
	category varchar(255) NOT NULL,
	mydate DATE NOT NULL,
	title VARCHAR(255) NOT NULL,
	amount INT NOT NULL,
	FOREIGN KEY (category) REFERENCES icategory(title)
);

INSERT INTO ecategory (title, removable) VALUES (
	'Livsmedel',
	false
);

INSERT INTO ecategory (title, removable) VALUES (
	'Fritid',
	false
);

INSERT INTO ecategory (title, removable) VALUES (
	'Resor',
	false
);

INSERT INTO ecategory (title, removable) VALUES (
	'Boende',
	false
);

INSERT INTO ecategory (title, removable) VALUES (
	'Övrigt',
	false
);

INSERT INTO icategory (title, removable) VALUES (
	'Lön',
	false
);

INSERT INTO icategory (title, removable) VALUES (
	'Övrigt',
	false
);

INSERT INTO expenses(hashid, category, mydate, title, price) VALUES (
	677725337,
	'Livsmedel',
	CURDATE(),
	'Mat',
	150
);

INSERT INTO expenses(hashid, category, mydate, title, price) VALUES (
	677725337,
	'Livsmedel',
	CURDATE(),
	'Mat',
	150
);

INSERT INTO expenses(hashid, category, mydate, title, price) VALUES (
	677725337,
	'Livsmedel',
	CURDATE(),
	'ICA Maxi',
	564
);

INSERT INTO expenses(hashid, category, mydate, title, price) VALUES (
	677725337,
	'Boende',
	CURDATE(),
	'Hyra',
	15000
);

INSERT INTO expenses(hashid, category, mydate, title, price) VALUES (
	677725337,
	'Fritid',
	CURDATE(),
	'Fotboll',
	99
);

INSERT INTO incomes (hashid, category, mydate, title, amount) VALUES (
	677725337,
	'Lön',
	CURDATE(),
	'Lön',
	91000
);