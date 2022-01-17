insert into user_entity (id, password, public_id, storage_password, username) values

-- Strong_P@ssword123
-- Strong_storage_P@ssword123
(1, '$2a$10$MmK8A.Eiiv4/gnm88OsQeuLLGcKn14gbD1OvwAl2M1QdEBmMVEju2', gen_random_uuid(),
 '$2a$10$NBMsQ5RBiULJQVFiuskO4OjE5Zi9zRXPAH0KFLVrwj2CtPcMdYwwW', 'bob'),
-- gre@T_passw321
-- gre@T_passw321_for_storage
(2, '$2a$10$N7kKNUAYJmF9GkGdTYrpq.rZvw/F4XzoGjWtIMNBMuVF7sQYMDo2m', gen_random_uuid(),
 '2a$10$wbVbb4Fvh32ZCkihJOLNy.U9CKpnpFSZZzxq0Rgipw0c7oTuHlxZi', 'alice'),
-- some_P@assw_222
-- some_storaGe1_p@ssw
(3, '$2a$10$OaIC0594Raerz.QXVayV8u9YVDPVSeT0q4QXB9Rg6HiyTae9jRAZe', gen_random_uuid(),
 '2a$10$vltWy.fnRgzKwdqxz3.x1.5PRooStrlFK9Sjhv6W32jeFshzUBXwu', 'alex');
