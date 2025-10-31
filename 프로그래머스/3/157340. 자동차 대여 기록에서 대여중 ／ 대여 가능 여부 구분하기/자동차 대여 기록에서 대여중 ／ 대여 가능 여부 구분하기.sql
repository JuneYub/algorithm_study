
select distinct car_id,
case when car_id in (
                        select car_id
                          from CAR_RENTAL_COMPANY_RENTAL_HISTORY
                         where to_char(start_date, 'YYYYMMDD') <= '20221016' and to_char(end_date, 'YYYYMMDD') >= '20221016'
                         group by car_id)
then '대여중' else '대여 가능' end as AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
order by car_id desc;
