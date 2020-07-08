select
  u.name
from
  user_tbl u
  left outer join
    group_user_tbl gu
    on
    u.id = gu.user_id
where
  gu.group_id = /* groupId */'abc'