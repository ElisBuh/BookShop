DROP TABLE IF EXISTS pc;
DROP TABLE IF EXISTS laptop;
DROP TABLE IF EXISTS printer;
DROP TABLE IF EXISTS product;

CREATE TABLE product(
    maker varchar(10) NOT NULL ,
    model varchar(50) NOT NULL ,
    type varchar(50) NOT NULL ,
    PRIMARY KEY (model),
    CHECK (type IN ('PC','Laptop', 'Printer'))
);

CREATE TABLE pc(
    code integer NOT NULL ,
    model varchar(50) NOT NULL ,
    speed smallint NOT NULL ,
    ram smallint NOT NULL ,
    hd real NOT NULL ,
    cd varchar(10) NOT NULL ,
    price money,
    PRIMARY KEY (code),
    FOREIGN KEY (model) REFERENCES product(model)
);

CREATE TABLE laptop(
    code integer NOT NULL ,
    model varchar(50) NOT NULL ,
    speed smallint NOT NULL ,
    ram smallint NOT NULL ,
    hd real NOT NULL ,
    price money ,
    screen smallint NOT NULL ,
    PRIMARY KEY (code),
    FOREIGN KEY (model) REFERENCES product(model)
);

CREATE TABLE printer(
    code integer NOT NULL ,
    model varchar(50) NOT NULL ,
    color char(1) NOT NULL ,
    type varchar(10) NOT NULL ,
    price money,
    PRIMARY KEY (code),
    FOREIGN KEY (model) REFERENCES product(model),
    CHECK ( color IN ('y','n')),
    CHECK ( type IN ('Laser', 'Jet', 'Matrix'))
);