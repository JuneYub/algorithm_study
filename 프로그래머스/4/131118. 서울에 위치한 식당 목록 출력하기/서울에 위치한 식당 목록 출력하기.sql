SELECT 
    ri.rest_id AS REST_ID,
    ri.rest_name AS REST_NAME,
    ri.food_type AS FOOD_TYPE,
    ri.favorites AS FAVORITES,
    ri.address AS ADDRESS,
    ROUND(AVG(rr.review_score), 2) AS SCORE
FROM 
    rest_info ri
JOIN 
    rest_review rr ON ri.rest_id = rr.rest_id
WHERE 
    ri.address LIKE '서울%'
GROUP BY 
    ri.rest_id, ri.rest_name, ri.food_type, ri.favorites, ri.address
ORDER BY 
    SCORE DESC, ri.favorites DESC;