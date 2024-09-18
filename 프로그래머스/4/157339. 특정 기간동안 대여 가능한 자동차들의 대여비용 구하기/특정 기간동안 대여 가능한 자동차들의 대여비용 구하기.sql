select c.car_id, c.car_type, c.FEE
from
(
select a.car_id, a.car_type, a.daily_fee*((100 - replace(b.discount_rate, '%', ''))/100)*30 as FEE
from 
(
    select * from CAR_RENTAL_COMPANY_CAR 
    where (car_type = 'SUV' or car_type ='세단') and
    car_id not in
    (
        select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where (start_date between '2022-11-01' and '2022-11-30')
        or (end_date between '2022-11-01' and '2022-11-30')
        or (start_date < '2022-11-01' and end_date > '2022-11-30')
    ) 
) a
join
(
select CAR_TYPE, DISCOUNT_RATE
from CAR_RENTAL_COMPANY_DISCOUNT_PLAN
where (CAR_TYPE = 'SUV' or CAR_TYPE = '세단') and DURATION_TYPE = '30일 이상'
) b
on a.CAR_TYPE = b.CAR_TYPE
) c
where c.FEE >= 500000 and c.FEE < 2000000
order by c.FEE desc, c.car_type asc, c.car_id desc
