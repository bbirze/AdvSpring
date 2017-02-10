CREATE TABLE customer(customerID INT IDENTITY PRIMARY KEY,
	name VARCHAR(100), phone VARCHAR(100), email VARCHAR(100));
	
INSERT INTO customer (name, phone, email) VALUES
	('Sam Smith', '555-1212', 'sam@sam.com');
	
INSERT INTO customer (name, phone, email) VALUES
	('Sue Jones', '555-1313', 'sue@sue.com');
	
INSERT INTO customer (name, phone, email) VALUES
('Harry Wolfe', '555-1414', 'harry@wolfe.com');