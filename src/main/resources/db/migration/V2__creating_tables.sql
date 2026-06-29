

create table categories(
	category_id int primary key auto_increment,
	category_name varchar(255) not null
	
);

create table products(
	
	product_id int primary key auto_increment,
	category_id int not null,
	
	product_name varchar(255) not null,
	product_price decimal(10,2) not null,
	product_img varchar(500) ,
	
	created_at timestamp default current_timestamp,
	
	foreign key (category_id) references categories(category_id)
	
);