drop database rascadb;
drop user rascauser;
create user rascauser with password 'password';
create database rascadb with template=template0 owner=rascauser;
\connect rascadb;
alter default privileges grant all on tables to rascauser;
alter default privileges grant all on sequences to rascauser;

create table rasca_users(
user_id integer primary key not null,
first_name varchar(20) not null,
last_name varchar(20) not null,
email varchar(20) not null,
password text not null
);

create table rasca_categories(
category_id integer primary key not null,
user_id integer not null,
title varchar(20) not null,
description varchar (50) not null
);

alter table rasca_categories add constraint cat_users_fk
foreign key (user_id) references rasca_users(user_id);

create table rasca_transactions(
transaction_id integer primary key not null,
category_id integer not null,
user_id integer not null,
amount numeric (19,2) not null,
note varchar(59) not null,
transaction_date date not null
);

alter table rasca_transactions add constraint trans_cat_fk
foreign key (category_id) references rasca_categories(category_id);

alter table rasca_transactions add constraint trans_users_fk
foreign key (user_id) references rasca_users(user_id);

create sequence rasca_users_seq increment 1 start 1;
create sequence rasca_categories_seq increment 1 start 1;
create sequence rasca_transactions_seq increment 1 start 1;
