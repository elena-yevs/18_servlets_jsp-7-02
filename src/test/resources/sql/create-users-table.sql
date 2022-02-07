CREATE TABLE IF NOT EXISTS roles
(
    roleId IDENTITY PRIMARY KEY,
    roleName VARCHAR(256) NOT NULL
    );

CREATE TABLE IF NOT EXISTS users
(
    id      IDENTITY PRIMARY KEY,
    roleId    INTEGER          NOT NULL,
    login     VARCHAR(256) NOT NULL,
    password  VARCHAR(256) NOT NULL,
    email     VARCHAR(256) NOT NULL,
    firstName VARCHAR(256) NOT NULL,
    lastName  VARCHAR(256) NOT NULL,
    birthday  DATE         NOT NULL,
    FOREIGN KEY (roleId) REFERENCES roles (roleId)
    );
