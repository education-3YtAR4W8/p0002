insert into group_tbl (id, name) values ('g1', '部署１');
insert into group_tbl (id, name) values ('g2', '部署２');
insert into group_tbl (id, name) values ('g3', '部署３');
insert into group_tbl (id, name) values ('g4', 'クラブ１');
insert into group_tbl (id, name) values ('g5', 'クラブ２');

insert into user_tbl (id, name) values ('u1', 'Ａさん');
insert into user_tbl (id, name) values ('u2', 'Ｂさん');
insert into user_tbl (id, name) values ('u3', 'Ｃさん');
insert into user_tbl (id, name) values ('u4', 'Ｄさん');
insert into user_tbl (id, name) values ('u5', 'Ｅさん');
insert into user_tbl (id, name) values ('u6', 'Ｆさん');
insert into user_tbl (id, name) values ('u7', 'Ｇさん');
insert into user_tbl (id, name) values ('u8', 'Ｈさん');
insert into user_tbl (id, name) values ('u9', 'Ｉさん');
insert into user_tbl (id, name) values ('u10', 'Ｊさん');

insert into group_user_tbl (group_id, user_id) values ('g1', 'u10');
insert into group_user_tbl (group_id, user_id) values ('g1', 'u5');
insert into group_user_tbl (group_id, user_id) values ('g1', 'u3');
insert into group_user_tbl (group_id, user_id) values ('g1', 'u1');
insert into group_user_tbl (group_id, user_id) values ('g2', 'u9');
insert into group_user_tbl (group_id, user_id) values ('g2', 'u8');
insert into group_user_tbl (group_id, user_id) values ('g2', 'u6');
insert into group_user_tbl (group_id, user_id) values ('g3', 'u7');
insert into group_user_tbl (group_id, user_id) values ('g3', 'u4');
insert into group_user_tbl (group_id, user_id) values ('g3', 'u2');
insert into group_user_tbl (group_id, user_id) values ('g4', 'u3');
insert into group_user_tbl (group_id, user_id) values ('g4', 'u6');
insert into group_user_tbl (group_id, user_id) values ('g4', 'u7');
insert into group_user_tbl (group_id, user_id) values ('g5', 'u1');
insert into group_user_tbl (group_id, user_id) values ('g5', 'u2');
insert into group_user_tbl (group_id, user_id) values ('g5', 'u3');
insert into group_user_tbl (group_id, user_id) values ('g5', 'u8');
