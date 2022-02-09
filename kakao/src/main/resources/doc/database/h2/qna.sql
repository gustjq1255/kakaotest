drop table if exists qna CASCADE;
create table qna
(
seq bigint auto_increment not null,
que_email varchar(50),
que_title varchar(50),
que_content varchar(2000),
que_date varchar(50),
status varchar(50),
ans_user_id varchar(50),
ans_user_name varchar(50),
ans_content varchar(2000),
ans_date varchar(50),
in_date varchar(50),
in_user_id varchar(50),
up_date varchar(50),
up_user_id varchar(50),
primary key (seq)
);




drop table if exists users CASCADE;
create table users
(
user_id varchar(50) not null,
user_name varchar(50),
user_group varchar(50),
in_date varchar(50),
in_user_id varchar(50),
up_date varchar(50),
up_user_id varchar(50),
primary key (user_id)
);