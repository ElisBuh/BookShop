DELETE FROM orders;
DELETE FROM requests;
DELETE FROM storage;
DELETE FROM books;

ALTER SEQUENCE book_id RESTART WITH 100000;
ALTER SEQUENCE order_id RESTART WITH 100000;
ALTER SEQUENCE request_id RESTART WITH 100000;
ALTER SEQUENCE storage_id RESTART WITH 100000;

INSERT INTO books (name_book, name_author, date, price)
VALUES ('Война и Мир', 'Лев Толстой', '2020-01-30', 25),
       ('Горе от ума', 'Александр Грибоедов', '2020-02-12', 12),
       ('Великий Гэтсби', 'Фрэнсис Скотт Фицджеральд', '2020-01-15', 22),
       ('Идиот', 'Федор Достоевский', '2020-05-13', 41),
       ('Грозовой перевал', 'Эмили Бронте', '2020-04-01', 31),
       ('Маленький принц', 'Сент-Экзюпери А.Д', '2020-06-11', 38),
       ('Коллекционер', 'Джон Фаулз', '2020-09-25', 69),
       ('Мастер и Маргарита', 'Михаил Булгаков', '2020-06-06', 73),
       ('Дон Кихот', 'Мигель де Сервантес', '2020-11-24', 64);

INSERT INTO storage (book_id)
VALUES (100002),
       (100007),
       (100005);
UPDATE books
SET status_book  = 'INSTOCK',
    data_receipt = '2020-10-24'
WHERE id = 100002;
UPDATE books
SET status_book  = 'INSTOCK',
    data_receipt = '2020-05-15'
WHERE id = 100007;
UPDATE books
SET status_book  = 'INSTOCK',
    data_receipt = '2020-4-19'
WHERE id = 100005;

INSERT INTO requests (book_id, count_request)
VALUES (100001, 2),
       (100006, 1);


INSERT INTO orders (name_client, book_id, cost)
VALUES ('Роман', 100002, 12),
       ('Саша', 100007, 69),
       ('Ира', 100005, 31);

UPDATE orders
SET status_order  = 'COMPLETED',
    date_complete = '2021-01-24'
WHERE book_id = 100002;
UPDATE orders
SET status_order = 'CANCEL'
WHERE book_id = 100005;
