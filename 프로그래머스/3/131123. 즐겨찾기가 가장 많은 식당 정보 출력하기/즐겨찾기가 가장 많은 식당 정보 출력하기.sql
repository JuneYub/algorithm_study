select FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
from (
SELECT FOOD_TYPE, REST_ID, REST_NAME, FAVORITES
, rank() over(partition by food_type order by FAVORITES desc, rest_id) as rn
from REST_INFO
) 
where rn = 1
order by FOOD_TYPE desc