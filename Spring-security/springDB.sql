create database exampleSecurity;
use exampleSecurity;

create table user(
id int primary key auto_increment,
username varchar(39) not null unique,
passwordd varchar(80) not null,
email varchar(50) unique
);

create table role(
id int primary key auto_increment,
name varchar(50) not null
);

create table user_role(
user_id int not null ,
role_id int not null,
primary key(user_id,role_id),
foreign key(user_id) references user(id),
foreign key(role_id) references role(id)
);

INSERT INTO role(name) values ("ROLE_USER"),("ROLE_ADMIN");

INSERT INTO user(name, passwordd,email) values 
("admin","{bcrypt}$2a$16$EKOorFS127oeqES8Mn/e1e2.r2SYNN4MJiuiMxxfldCGA3wd4Wdqe","admin_Pocta");

INSERT INTO user_role(user_id,role_id) values (1,1),(2,2);