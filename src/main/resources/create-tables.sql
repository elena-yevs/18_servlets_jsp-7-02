DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    roleId int NOT NULL AUTO_INCREMENT,
    roleName VARCHAR(256) NOT NULL,
    PRIMARY KEY (roleId)
);

CREATE TABLE users
(
    id int NOT NULL AUTO_INCREMENT,
    roleId int NOT NULL  AUTO_INCREMENT,
    login     VARCHAR(256) NOT NULL,
    password  VARCHAR(256) NOT NULL,
    email     VARCHAR(256) NOT NULL,
    firstName VARCHAR(256) NOT NULL,
    lastName  VARCHAR(256) NOT NULL,
    birthday  DATE         NOT NULL,
    FOREIGN KEY (roleId) REFERENCES roles (roleId)
);

INSERT INTO roles(roleName)
VALUES ('admin');

INSERT INTO roles(roleName)
VALUES ('user');

INSERT INTO users(login, password, email, firstName, lastName, birthday)
VALUES ('admin', 'admin', 'adm@gmail.com', 'Ryan', 'Gosling', '19801112');

INSERT INTO users(login, password, email, firstName, lastName, birthday)
VALUES ('user', 'user', 'mark@gmail.com', 'Mark', 'Sheppard', '20201112');
