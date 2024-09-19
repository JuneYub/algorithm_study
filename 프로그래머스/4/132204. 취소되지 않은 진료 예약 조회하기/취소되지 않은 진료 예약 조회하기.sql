
select a.apnt_no, 
    (select PT_NAME from PATIENT where PT_NO = a.PT_NO) PT_NAME,  
a.PT_NO, a.MCDP_CD, 
    (select DR_NAME from DOCTOR where DR_ID = a.MDDR_ID) DR_NAME,
a.APNT_YMD
from
(
select apnt_no, PT_NO, MCDP_CD, MDDR_ID, APNT_YMD
from APPOINTMENT
where date_format(apnt_ymd, '%Y-%m-%d') = '2022-04-13' and
APNT_CNCL_YN = 'N' and
MCDP_CD = 'CS'
order by APNT_YMD asc
) a 