CREATE TABLE roles(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50));

INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');

CREATE TABLE users(
id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
password VARCHAR(120) NOT NULL);

CREATE TABLE user_roles(
user_id int,
role_id int,
FOREIGN KEY (user_id) REFERENCES users(id),
FOREIGN KEY (role_id) REFERENCES roles(id),
PRIMARY KEY(user_id, role_id)
);

CREATE TABLE departments(
id INT PRIMARY KEY AUTO_INCREMENT,
code VARCHAR(5),
name VARCHAR(30)
);

INSERT INTO departments(code, name) VALUES
('1','ATLANTICO'),
('2', 'ANTIOQUIA');

CREATE TABLE cities(
id INT PRIMARY KEY AUTO_INCREMENT,
code VARCHAR(5),
name VARCHAR(30),
department_id INT,
FOREIGN KEY (department_id) REFERENCES departments(id)
);

INSERT INTO cities(code, name, department_id) VALUES
('1', 'BARRANQUILLA', 1),
('2', 'BARANOA', 1),
('3', 'MEDELLIN', 2),
('4', 'ENVIGADO', 2);

CREATE TABLE materials(
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50),
description VARCHAR(150),
type VARCHAR(20),
price VARCHAR(7),
buy_date date,
sell_date date,
state VARCHAR(15),
city_id INT,
FOREIGN KEY (city_id) REFERENCES cities(id)
);

INSERT INTO materials(name, description, type, price, buy_date, sell_date, state, city_id) VALUES
('CEMENTO', 'MATERIAL UTILIZADO PARA CONTRUCCION', 'CONSTRUCCION', '100000', '2024-02-21', '2024-02-22', 'ACTIVE', 1),
('ARENA', 'MATERIAL UTILIZADO PARA CONTRUCCION', 'CONSTRUCCION', '20000', '2024-02-19', '2024-02-21', 'ACTIVE', 2),
('TORNILLO', 'MATERIAL PARA ARMAR COSAS', 'HIERROS', '100', '2024-01-11', '2024-02-02', 'ACTIVE', 3),
('MARTILLO', 'MATERIAL UTILIZADO PARA ENZAMBLE DE ELEMENTOS', 'HERRAMIENTAS', '70000', '2024-02-11', '2024-02-12', 'ACTIVE', 1);
