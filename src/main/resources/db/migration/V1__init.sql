CREATE TABLE products (id serial, title varchar(100), price numeric(6,2), views int DEFAULT(0));

INSERT INTO products (title, price)
VALUES
('Product #1', 10),
('Product #2', 20),
('Product #3', 30),
('Product #4', 40),
('Product #5', 50),
('Product #6', 60),
('Product #7', 70),
('Product #8', 40),
('Product #9', 30),
('Product #10', 20),
('Product #11', 20),
('Product #12', 30),
('Product #13', 40),
('Milk #2', 10),
('Milk', 70);

CREATE TABLE  items (id serial, title varchar(100));

INSERT INTO items (title) VALUES ('Box'), ('Tree'), ('House');

CREATE TABLE users(
    id  serial,
    username varchar(50) NOT NULL,
    password varchar(80) NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles(
   id          serial,
   name        varchar(50) DEFAULT  NULL,
   PRIMARY KEY (id)
);

CREATE TABLE users_roles(
    user_id  int NOT NULL,
    role_id  int NOT NULL,

    PRIMARY KEY(user_id, role_id),

    CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
    REFERENCES users (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION,

    CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
    REFERENCES roles (id)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);


INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, name, email)
VALUES
('admin','$2a$04$60BlherkF7WrRf09MX.5GesIPPZaFiG9yi2poMeiDN2FHJV7eCo8W', 'Bob Johnson', 'bob@gmail.com');


INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2);








-- oldTask
-- CREATE TABLE users (
--                        username VARCHAR(50) NOT NULL,
--                        password VARCHAR(100) NOT NULL,
--                        enabled boolean NOT NULL,
--                        PRIMARY KEY (username)
-- );
-- INSERT INTO users
-- VALUES
-- ('user1', '{noop}123', true),
-- ('user2', '{noop}123', true),
-- ('user3', '{bcrypt}123', true);
--
-- CREATE TABLE authorities (
--     username varchar(50) NOT NULL,
--     authority varchar(50) NOT NULL,
--
--     CONSTRAINT authorities_idx UNIQUE (username, authority),
--
--     CONSTRAINT authorities_ibfk_1
--     FOREIGN KEY (username)
--     REFERENCES users (username)
-- );
--
-- INSERT INTO authorities
-- VALUES
-- ('user1', 'ROLE_ADMIN'),
-- ('user1', 'ROLE_USER'),
-- ('user2', 'ROLE_USER');