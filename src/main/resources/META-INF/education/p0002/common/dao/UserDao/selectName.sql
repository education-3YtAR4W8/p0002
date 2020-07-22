select
    ut.name
from
    group_tbl gt

    inner join
            group_user_tbl gut
            ON gt.id = gut.group_id
    inner join
        user_tbl ut
            ON gut.user_id = ut.id
where
    gt.id = /* id */'hoge'
