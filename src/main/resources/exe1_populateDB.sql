DELETE FROM laptop;
DELETE FROM printer;
DELETE FROM pc;
DELETE FROM product;

INSERT INTO product (maker, model, type)
VALUES ('Samsung', 'sa200pc', 'PC'),
       ('HP', 'hp200pc', 'PC'),
       ('Lenovo', 'le200pc', 'PC'),
       ('Fujitsu', 'fu200pc', 'PC'),
       ('Toshiba', 'to200pc', 'PC'),
       ('Samsung', 'sa201lap', 'Laptop'),
       ('HP', 'hp201lap', 'Laptop'),
       ('Lenovo', 'le201lap', 'Laptop'),
       ('Fujitsu', 'fu201lap', 'Laptop'),
       ('Toshiba', 'to201lap', 'Laptop'),
       ('Samsung', 'sa202p', 'Printer'),
       ('HP', 'hp202p', 'Printer'),
       ('Lenovo', 'le202p', 'Printer'),
       ('Fujitsu', 'fu202p', 'Printer'),
       ('Toshiba', 'to202p', 'Printer');

INSERT INTO pc (code, model, speed, ram, hd, cd, price)
VALUES (1, 'sa200pc', 2500, 8196, 500, '4x', 500),
       (2, 'hp200pc', 2000, 2048, 100, '2x', 647),
       (3, 'le200pc', 1800, 1024, 450, '8x', 384),
       (4, 'fu200pc', 2100, 4096, 1000, '6x', 435),
       (5, 'to200pc', 2300, 1024, 700, '12x', 950);

INSERT INTO laptop (code, model, speed, ram, hd, price, screen)
VALUES (1, 'sa201lap', 2500, 8196, 500, 953, 17),
       (2, 'hp201lap', 2000, 2048, 100, 1200, 19),
       (3, 'le201lap', 1800, 1024, 450, 845, 15),
       (4, 'fu201lap', 2100, 4096, 1000, 635, 21),
       (5, 'to201lap', 2300, 1024, 700, 1540, 13);

INSERT INTO printer (code, model, color, type, price)
VALUES (1, 'sa202p', 'y', 'Laser', 259),
       (2, 'hp202p', 'n', 'Laser', 456),
       (3, 'le202p', 'y', 'Jet', 657),
       (4, 'fu202p', 'y', 'Matrix', 748),
       (5, 'to202p', 'n', 'Jet', 312);

