use db_sneakers
go

-- findUser
drop proc if exists findAccount
go
create proc findAccount
	@username varchar(50), @password varchar(100)
as
	select * from Account where username = @username and password = HASHBYTES('SHA2_512', @password+CAST('namtrungtantoan' as varchar(30)))
go

-- changePassword
drop proc if exists changePassword
go
create proc changePassword
	@username varchar(50), @oldpassword varchar(100), @newpassword varchar(100)
as
	update Account 
	set password = @newpassword
	where username = @username and password = HASHBYTES('SHA2_512', @oldpassword+CAST('namtrungtantoan' as varchar(30)))
go
