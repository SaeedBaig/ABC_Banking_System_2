-- Reset database
--
-- Write with SQLite3 syntax in mind

DROP TABLE Admin;
DROP TABLE Customer;
DROP TABLE Account;
DROP TABLE "Transaction";
-- Transaction is a reserved keyword in SQLite. Gotta quote it

CREATE TABLE Admin (
	-- `INTEGER PRIMARY KEY` in SQLite is autoincrement by default
	id INTEGER PRIMARY KEY,
	name VARCHAR(30) NOT NULL
);

CREATE TABLE Customer (
	id INTEGER PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	email VARCHAR(30) NOT NULL,
	address VARCHAR(30) NOT NULL,
	phone VARCHAR(30) NOT NULL,
	gender VARCHAR(30) NOT NULL
);

CREATE TABLE Account (
	id INTEGER PRIMARY KEY,
	customer_id INT UNSIGNED NOT NULL,
	balance DOUBLE NOT NULL,
	transaction_count INT NOT NULL,
	transaction_fee DOUBLE NOT NULL,
	account_type VARCHAR(30) NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES Customer(id)
);

CREATE TABLE "Transaction" (
	id INTEGER PRIMARY KEY,
	account_id INT UNSIGNED NOT NULL,
	transaction_date VARCHAR(50) NOT NULL,
	amount DOUBLE NOT NULL,
	FOREIGN KEY (account_id) REFERENCES Account (id)
);
