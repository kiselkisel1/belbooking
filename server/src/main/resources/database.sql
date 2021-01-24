drop database if exists tourism;

create database if not exists tourism;

USE tourism;

CREATE TABLE IF NOT EXISTS `user` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100),
    `surname` VARCHAR(100),
    `email` VARCHAR(200) NOT NULL UNIQUE,
    `password` VARCHAR(200) NOT NULL,
    `role` VARCHAR(100)
);

create table if not exists service(
	   id int not null auto_increment primary key,
	  `name` VARCHAR(100) not null,
	  `description` VARCHAR(200),
	  `type` VARCHAR(100) not null,
	  `subtype` VARCHAR(100),
	  `price` DOUBLE not null,
	  `is_booked` INT not null,
	  `is_active` INT not null
);

create table if not exists booking(
	id int not null auto_increment primary key,
	`status` VARCHAR(45) not null,
	`booking_date` DATE,
	`payment_currency` VARCHAR(45),
	`service_id` INT,
	`user_id` INT,
	foreign key (service_id) references service(id)
	on update cascade
    on delete cascade,

    foreign key (user_id) references user(id)
	on update cascade
    on delete cascade
);

create table if not exists `bookmarks`(
    id int not null primary key auto_increment,
	`user_id` INT,
	`service_id` INT,

	foreign key (service_id) references service(id)
	on update cascade
    on delete cascade,

    foreign key (user_id) references user(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `comments` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `description` VARCHAR(400),
    `rating` FLOAT,
    `comment_date` DATE NOT NULL,
    `service_id` INT,

	foreign key (service_id) references service(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `contact_details` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    `phone_number` VARCHAR(30) not null,
    `mobile_phone` VARCHAR(30),
    `email` VARCHAR(100) not null,
    `address` VARCHAR(200),
    `service_id` INT,

    foreign key (service_id) references service(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `housing` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `stars` INT,
    `center_distance` DOUBLE,
    `category` VARCHAR(45),

    foreign key (id) references service(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `facility` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `facility_name` VARCHAR(45) not  null,
    `extra_price` DOUBLE not null,
    `housing_id` INT,

    foreign key (housing_id) references housing(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `region` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) not null
);

CREATE TABLE IF NOT EXISTS `location` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    `address` VARCHAR(200),
    `latitude` DOUBLE,
    `longitude` DOUBLE,
    `service_id` INT,
    `region_id` INT,

    foreign key (service_id) references service(id)
	on update cascade
    on delete cascade,

    foreign key (region_id) references region(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `menu` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `menu_name` VARCHAR(45) not null,
    `service_id` INT,

    foreign key (service_id) references service(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `menu_item` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `menu_item_name` VARCHAR(100) not null,
    `menu_item_price` DOUBLE not null,
    `menu_item_description` VARCHAR(100),
    `menu_id` INT,

    foreign key (menu_id) references menu(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `opening_hours` (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `day_of_week` VARCHAR(45),
    `open_time` TIME(4),
    `close_time` TIME(4),
    `service_id` INT,

    foreign key (service_id) references service(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `picture` (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `picture_url` VARCHAR(200) not null,
  `is_active` INT not null,
  `service_id` INT,

  foreign key (service_id) references service(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `stock` (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `discount` DOUBLE not null,
  `begin_date` DATE not null,
  `end_date` DATE not null,
  `service_id` INT,

  foreign key (service_id) references service(id)
	on update cascade
    on delete cascade
);

CREATE TABLE IF NOT EXISTS `tourism` (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `start_date` DATE not null,
  `end_date` DATE not null,

  foreign key (id) references service(id)
	on update cascade
    on delete cascade
  );

  CREATE TABLE IF NOT EXISTS `tour_program` (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `date` DATE,
  `activities_description` VARCHAR(400),
  `extra_description` VARCHAR(200),
  `tourism_id` INT,

  foreign key (tourism_id) references tourism(id)
	on update cascade
    on delete cascade
  );

  CREATE TABLE IF NOT EXISTS `transport` (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `lease_type` VARCHAR(100) NULL DEFAULT NULL,
  `category` VARCHAR(100) NULL DEFAULT NULL,

  foreign key (id) references service(id)
	on update cascade
    on delete cascade
  );