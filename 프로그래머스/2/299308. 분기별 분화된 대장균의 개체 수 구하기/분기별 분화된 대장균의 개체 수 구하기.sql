select concat(a.quarter, 'Q') as quarter, count(a.quarter) as ecoli_count
from (
    select 
        case
        when (month(DIFFERENTIATION_DATE) <= 3) then 1
        when (month(DIFFERENTIATION_DATE) <= 6) then 2
        when (month(DIFFERENTIATION_DATE) <= 9) then 3
        when (month(DIFFERENTIATION_DATE) <= 12) then 4
        end as quarter
    from ECOLI_DATA
    order by quarter asc
) as a
group by quarter
    