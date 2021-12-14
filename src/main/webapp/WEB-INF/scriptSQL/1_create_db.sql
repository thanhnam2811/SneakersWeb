drop database if exists db_sneakers
go

create database db_sneakers
go

use db_sneakers
go

-- Brand (id, name, email)
create table Brand (
	id int primary key identity, 
	name nvarchar(100), 
	email varchar(200),
	logo nvarchar(MAX) null default 'img/brand/logo.png'
)

-- Product (id, name, idBrand, image, describe, quantity, cost, saleDate)
create table Product(
	id int primary key identity, 
	idBrand int references brand(id)
        on delete cascade
        on update cascade,
	name nvarchar(200), 
	image nvarchar(max), 
	describe nvarchar(max), 
	quantity int check (quantity >=0 ), 
	cost real check (cost >= 0), 
	saleDate date default getdate()
)

-- Account (id, username, password, fullname, avatar, phoneNumber, address, sex, dateOfBirth, isAdmin)
create table Account (
	id int primary key identity, 
	username varchar(50) unique, 
	password varchar(100) check (len(password) >= 8), 
	fullname nvarchar(200), 
	avatar nvarchar(max) default 'img/avatar/avatar.png', 
	phoneNumber varchar(10), 
	address nvarchar(max), 
	sex varchar(6) check (sex = 'Male' or sex = 'Female'), 
	dateOfBirth date check (datediff(year, dateOfBirth, getdate()) >= 18), 
	isAdmin bit default 0
)

-- Comment (id, idProduct, username, comment)
create table Comment (
	id int primary key identity, 
	idProduct int references product(id)
        on delete cascade
        on update cascade,
	username varchar(50) references account(username)
        on delete cascade
        on update cascade,
	comment nvarchar(max)
)

-- Cart (id, username, idProduct, quantity, cost)
create table Cart (
	id int primary key identity, 
	username varchar(50) references account(username)
        on delete cascade
        on update cascade,
	idProduct int references product(id), 
	quantity int check ( quantity >= 0), 
	cost real not null default 0,
	unique (username, idProduct)
)

-- Order (id, username, cost,  purchaseDate, address, phoneNumber, fullname)
create table [Order] (
	id int primary key identity, 
	username varchar(50) references account(username)
    on delete cascade
    on update cascade,
	cost real not null default 0 check (cost >= 0),  
	purchaseDate date not null default getdate(), 
	address nvarchar(max), 
	phoneNumber varchar(10) not null, 
	fullname nvarchar(100)
)

-- OrderDetail (id, idOrder, idProduct, quantity, cost)
create table OrderDetail (
	id int primary key identity, 
	idOrder int references [Order](id)
        on delete cascade
        on update cascade,
	idProduct int references Product(id)
        on delete cascade
        on update cascade,
	quantity int check (quantity >= 0), 
	cost real not null default 0 check (cost >= 0)
)
