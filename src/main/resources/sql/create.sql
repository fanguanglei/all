create table admin
(
  id serial,
  name varchar(30),
  password varchar(50),
  city_name varchar(30),
  phone char(11),
constraint pk_admin_id primary key( id)
);
create table city(
	id serial,
	city_name varchar(30),
	initial varchar(30),
	constraint pk_city_id primary key( id)
);

