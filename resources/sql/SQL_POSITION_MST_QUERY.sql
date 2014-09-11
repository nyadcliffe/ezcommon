select
	position_cd,
	position_nm
from
	position_mst
where
	delete_flg = '0'
order by position_cd