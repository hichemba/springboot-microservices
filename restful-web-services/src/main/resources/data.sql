insert into USER_TABLE (id, birth_date, name) values (1001, current_date(), 'Hichem');
insert into USER_TABLE (id, birth_date, name) values (1002, current_date(), 'Naoures');
insert into USER_TABLE (id, birth_date, name) values (1003, current_date(), 'Ahmed');

insert into POST  (id, description, user_id) values (2001, 'Hichem 1st post', 1001);
insert into POST  (id, description, user_id) values (2002, 'Hichem 2nd post', 1001);
insert into POST  (id, description, user_id) values (2003, 'Hichem 3rd post', 1001);
insert into POST  (id, description, user_id) values (2004, 'Ahmed 1st post', 1003);
