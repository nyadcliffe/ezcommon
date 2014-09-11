select
	group_cd,
	group_nm
from
	group_mst
where
	delete_flg = '0'
order by group_cd