select b.user_id, b.nickname, b.addr '전체주소', b.num '전화번호'
from
(
select writer_id, count(*) as cnt
from USED_GOODS_BOARD
group by writer_id
) a
join
(
select user_id, nickname, concat(city, ' ', street_address1, ' ', street_address2) as addr, concat(substr(TLNO, 1, 3), '-', substr(TLNO, 4, 4), '-', substr(TLNO, 8, 4)) as num
from USED_GOODS_USER
) b
on a.writer_id = b.user_id
where a.cnt >= 3
order by b.user_id desc
