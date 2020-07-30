select
    gpt.id || ust.id as id
  , gpt.id as group_id
  , gpt.name as group_name
  , ust.id as user_id
  , ust.name as user_name
from group_tbl gpt, group_user_tbl gut, user_tbl ust
where gpt.id = gut.group_id and gut.user_id = ust.id
order by gpt.name, ust.name

