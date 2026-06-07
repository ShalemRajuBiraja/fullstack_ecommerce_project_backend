create table users(

user_id int auto_increment primary key,

full_name varchar(255) not null,
email varchar(255) not null unique,
mobile_number varchar(20) not null unique, 
password varchar(255) not null,

role varchar(20) default 'user',

created_at timestamp default current_timestamp,
updated_at timestamp default current_timestamp on update current_timestamp

);