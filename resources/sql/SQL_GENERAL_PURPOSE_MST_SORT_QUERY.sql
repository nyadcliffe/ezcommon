select
	division_id,
	division_cd,
	sort_number,
	division_nm
from
	general_purpose_mst
where
	division_id = ?
	and delete_flg = '0'
order by division_nm