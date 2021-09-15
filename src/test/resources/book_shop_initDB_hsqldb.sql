DROP TABLE orders IF EXISTS;
DROP TABLE requests IF EXISTS;
DROP TABLE storage IF EXISTS;
DROP TABLE books IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE roles IF EXISTS;

DROP SEQUENCE book_id IF EXISTS;
DROP SEQUENCE order_id IF EXISTS;
DROP SEQUENCE request_id IF EXISTS;
DROP SEQUENCE storage_id IF EXISTS;
DROP SEQUENCE role_id IF EXISTS;
DROP SEQUENCE user_id IF EXISTS;

CREATE SEQUENCE book_id AS INTEGER START WITH 100000;
CREATE SEQUENCE order_id AS INTEGER START WITH 100000;
CREATE SEQUENCE request_id AS INTEGER START WITH 100000;
CREATE SEQUENCE storage_id AS INTEGER START WITH 100000;
CREATE SEQUENCE role_id AS INTEGER START WITH 100000;
CREATE SEQUENCE user_id AS INTEGER START WITH 100000;

CREATE TABLE books
(
    id           integer    GENERATED BY DEFAULT AS SEQUENCE book_id,
    name_book    VARCHAR(255)   NOT NULL,
    name_author  VARCHAR(255)   NOT NULL,
    date         timestamp      NOT NULL,
    price        numeric(10, 2) NOT NULL,
    status_book  varchar(7) DEFAULT 'ABSENT',
    data_receipt timestamp,
    PRIMARY KEY (id),
    CHECK (status_book IN ('ABSENT', 'INSTOCK'))
);

CREATE TABLE orders
(
    id            integer    GENERATED BY DEFAULT AS SEQUENCE order_id,
    name_client   VARCHAR(255)   NOT NULL,
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
    id integer GENERATED BY DEFAULT AS SEQUENCE request_id,
    book_id integer NOT NULL ,
    count_request integer NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

CREATE TABLE storage
(
    id integer GENERATED BY DEFAULT AS SEQUENCE storage_id,
    book_id integer NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

CREATE TABLE roles
(
    id integer GENERATED BY DEFAULT AS SEQUENCE request_id,
    name varchar(20) NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id integer GENERATED BY DEFAULT AS SEQUENCE user_id,
    login VARCHAR(255) NOT NULL UNIQUE ,
    passwords VARCHAR(255) NOT NULL ,
    role_id integer default '100000',
    PRIMARY KEY (id),
    Foreign Key (role_id) REFERENCES roles(id)

)