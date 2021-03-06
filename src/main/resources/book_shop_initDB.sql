DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS storage;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

DROP SEQUENCE IF EXISTS book_id;
DROP SEQUENCE IF EXISTS order_id;
DROP SEQUENCE IF EXISTS request_id;
DROP SEQUENCE IF EXISTS storage_id;
DROP SEQUENCE IF EXISTS role_id;
DROP SEQUENCE IF EXISTS user_id;

CREATE SEQUENCE book_id START WITH 100000;
CREATE SEQUENCE order_id START WITH 100000;
CREATE SEQUENCE request_id START WITH 100000;
CREATE SEQUENCE storage_id START WITH 100000;
CREATE SEQUENCE role_id START WITH 100000;
CREATE SEQUENCE user_id START WITH 100000;

CREATE TABLE books
(
    id           integer    DEFAULT nextval('book_id'),
    name_book    text           NOT NULL,
    name_author  text           NOT NULL,
    date         timestamp      NOT NULL,
    price        numeric(10, 2) NOT NULL,
    status_book  varchar(7) DEFAULT 'ABSENT',
    data_receipt timestamp,
    PRIMARY KEY (id),
    CHECK (status_book IN ('ABSENT', 'INSTOCK'))
);

CREATE TABLE orders
(
    id            integer    DEFAULT nextval('order_id'),
    name_client   text           NOT NULL,
    book_id       integer        NOT NULL,
    cost          numeric(10, 2) NOT NULL,
    status_order  varchar(9) DEFAULT 'NEW',
    date_complete timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE ,
    CHECK ( status_order IN ('NEW', 'COMPLETED', 'CANCEL'))
);

CREATE TABLE requests
(
    id integer DEFAULT nextval('request_id'),
    book_id integer NOT NULL ,
    count_request integer NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

CREATE TABLE storage
(
    id integer DEFAULT nextval('storage_id'),
    book_id integer NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

CREATE TABLE roles
(
    id integer DEFAULT nextval('role_id'),
    name varchar(20) NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id integer DEFAULT nextval('user_id'),
    login text NOT NULL UNIQUE ,
    passwords text NOT NULL ,
    role_id integer default '100000',
    PRIMARY KEY (id),
    Foreign Key (role_id) REFERENCES roles(id)

)