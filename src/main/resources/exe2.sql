-- task1
SELECT model, speed, hd
FROM pc
where price < '500';
-- task2
SELECT DISTINCT maker
FROM product
WHERE model IN (SELECT model FROM printer);
-- task3
SELECT model, ram, screen
FROM laptop
WHERE price > '1000';
-- task4
SELECT *
FROM printer
where color = 'y';
-- task5
SELECT model, speed, hd
FROM pc
WHERE cd = '12x'
   OR cd = '24x' AND price > '600';
-- task6
SELECT DISTINCT product.maker, laptop.speed
FROM product,
     laptop
WHERE product.model = laptop.model
  AND laptop.hd >= 10;
-- task7
SELECT m.model, price
FROM (SELECT model, price
      FROM pc
      UNION
      SELECT model, price
      FROM laptop
      UNION
      SELECT model, price
      FROM printer
     ) AS m
         JOIN
     product p ON m.model = p.model
WHERE p.maker = 'HP';
-- task8
SELECT maker
FROM product
WHERE type = 'PC'
  AND maker NOT IN (SELECT maker
                    FROM product
                    WHERE type = 'Laptop'
);
-- task9
SELECT DISTINCT maker
FROM product
WHERE model IN (SELECT pc.model FROM pc WHERE speed > 450);
-- task10
SELECT model, price
FROM printer
WHERE price = (SELECT price FROM printer ORDER BY price DESC LIMIT 1);
-- task11
SELECT avg(speed)
FROM pc;
-- task12
SELECT avg(speed)
FROM laptop
WHERE price > '1000';
-- task13
SELECT avg(speed)
FROM pc
WHERE model IN (SELECT model FROM product WHERE maker = 'HP');
-- task14
SELECT speed, avg(price)
FROM pc
GROUP BY speed;
-- task15
SELECT hd
FROM pc
GROUP BY hd
HAVING count(hd) > 1;
-- task16
SELECT max(model), min(model), speed, ram
FROM pc
GROUP BY speed, ram
HAVING count(*) = 2
   AND max(model) <> min(model)
ORDER BY speed DESC, ram ASC;
-- task17
SELECT DISTINCT type, laptop.model, speed
FROM laptop,
     product
WHERE product.model = laptop.model
  AND laptop.speed < (SELECT max(speed) FROM PC);
-- task18
SELECT product.maker, printer.price
FROM printer
         INNER JOIN product ON printer.model = product.model AND printer.color LIKE 'y'
WHERE price = (
    SELECT min(price)
    FROM printer
    WHERE color LIKE 'y'
)
GROUP BY product.maker, printer.price;
-- task19
SELECT product.maker, avg(laptop.screen)
FROM product
         INNER JOIN laptop ON laptop.model = laptop.model
WHERE product.type LIKE 'Laptop'
GROUP BY product.maker;
-- task20
SELECT maker, count(*)
FROM (
         SELECT maker,
                model
         FROM product
         WHERE type LIKE 'PC'
         GROUP BY maker, model
     ) product
GROUP BY product.maker
HAVING count(*) >= 3;
-- task21
SELECT maker, max(pc.price)
FROM product
         INNER JOIN pc ON pc.model = product.model
WHERE product.type LIKE 'PC'
GROUP BY product.maker;
-- task22
SELECT speed, avg(price)
FROM pc
WHERE speed > 600
GROUP BY speed;
-- task23
SELECT DISTINCT maker
FROM Product prod
WHERE (prod.model IN (SELECT model
                      FROM PC
                      WHERE speed >= 750
) OR
       prod.model IN (SELECT model
                      FROM Laptop
                      WHERE speed >= 750
       )
    )
  AND EXISTS(SELECT *
             FROM Product
             WHERE Product.maker = prod.maker
               AND Product.type = 'PC'
    )
  AND EXISTS(SELECT *
             FROM Product
             WHERE Product.maker = prod.maker
               AND Product.type = 'Laptop'
    );
-- task24
SELECT modl1.model
FROM (SELECT model, price
      FROM pc
      UNION
      SELECT model, price
      FROM laptop
      UNION
      SELECT model, price
      FROM printer
     ) AS modl1
WHERE price >=
      (SELECT max(price)
       FROM (
                SELECT max(price) as price
                FROM pc
                UNION
                SELECT max(price) as price
                FROM printer
                UNION
                SELECT max(price) as price
                FROM laptop
            ) AS modl2);
-- task25
SELECT maker
FROM product
WHERE type LIKE 'Printer'
  AND maker IN (
    SELECT maker
    FROM product
    WHERE model IN (
        SELECT pc1.model
        FROM pc AS pc1
        WHERE pc1.speed = (
            SELECT max(speed)
            FROM pc
            WHERE ram = (
                SELECT min(ram)
                FROM pc
            )
        )
          AND pc1.ram = (
            SELECT min(ram)
            FROM pc
        )
        GROUP BY pc1.model
    )
    GROUP BY maker
)
GROUP BY maker