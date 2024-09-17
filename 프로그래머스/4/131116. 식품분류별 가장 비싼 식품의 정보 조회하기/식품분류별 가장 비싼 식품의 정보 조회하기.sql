SELECT f.category, f.price AS max_price, f.product_name
FROM food_product f
INNER JOIN (
    SELECT category, MAX(price) AS max_price
    FROM food_product
    WHERE category IN ('과자', '국', '김치', '식용유')
    GROUP BY category
) m ON f.category = m.category AND f.price = m.max_price
ORDER BY max_price DESC