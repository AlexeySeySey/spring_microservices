create table if not exists users(id int auto_increment primary key, role_id int, email varchar(255) unique, password varchar(255));

create table if not exists permissions(id int auto_increment primary key, name varchar(255));
create table if not exists role_permission(id int auto_increment primary key, role_id int, permission_id int);

create table if not exists roles(id int auto_increment primary key, name varchar(255));
INSERT INTO ROLES VALUES(1, 'client');