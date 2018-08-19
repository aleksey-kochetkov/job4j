SELECT product.* FROM product
INNER JOIN type ON type.id = product.type_id
WHERE type.name = 'СЫР';

SELECT * FROM product WHERE name LIKE '%мороженое%';

SELECT * FROM product
WHERE expired_date BETWEEN '2018-09-01' AND '2018-09-30';

SELECT * FROM product WHERE price = (SELECT max(price) FROM product);

SELECT count(*) FROM product WHERE type_id = 1;

SELECT * FROM product
INNER JOIN type ON type.id = product.type_id
WHERE (type.name = 'СЫР' OR type.name = 'МОЛОКО');

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
-- Некорректный пункт - в условии нет поля количество. Но запрос можно
-- написать, если бы количество было в поле product.qty.
SELECT type.name FROM product
INNER JOIN type ON type.id = product.type_id
WHERE product.qty < 10;

SELECT product.name, type.name FROM product
INNER JOIN type ON type.id = product.type_id;