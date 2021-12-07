use
    db_sneakers
go

-- Insert, update OrderDetail
drop trigger if exists after_insert_update_OrderDetail
go
create trigger after_insert_update_OrderDetail
    on OrderDetail
    after insert,
    update as
    declare
        @idOrder int, @orderDetailCost real, @idOrderDetail int, @username varchar(50), @idProduct int
    select @idOrder = new.idOrder, @orderDetailCost = new.quantity * p.cost, @idOrderDetail = new.id,
           @username =  ord.username, @idProduct = new.idProduct
    from inserted new, product p, [Order] ord
    where new.idProduct = p.id and new.idOrder = ord.id
begin
    update OrderDetail
    set cost = @orderDetailCost
    where id = @idOrderDetail

    update [Order]
    set cost = (select sum(cost) from OrderDetail where OrderDetail.idOrder = @idOrder)
    where id = @idOrder

    delete Cart
    where Cart.idProduct = @idProduct
      and Cart.username = @username
end
go

-- Insert, update Account
drop trigger if exists after_insert_Account
go
create trigger after_insert_Account
    on Account
    instead of insert as
    declare
        @username    varchar(50),
        @password    varchar(100),
        @fullname    nvarchar(200),
        @avatar      nvarchar(max),
        @phoneNumber varchar(10),
        @address     nvarchar(max),
        @sex         varchar(6),
        @dateOfBirth date,
        @isAdmin     bit
    select @username = new.username,
           @password = HASHBYTES('SHA2_512', new.password + CAST('namtrungtantoan' as varchar(30))),
           @fullname = new.fullname,
           @avatar = new.avatar,
           @phoneNumber = new.phoneNumber,
           @address = new.address,
           @sex = new.sex,
           @dateOfBirth = new.dateOfBirth,
           @isAdmin = new.isAdmin
    from inserted new
begin
    insert into Account(username, password, fullname, avatar, phoneNumber, address, sex, dateOfBirth, isAdmin)
    values (@username, @password, @fullname, @avatar, @phoneNumber, @address, @sex, @dateOfBirth, @isAdmin);
    if
        (@avatar = '' or @avatar is null)
        update Account
        set avatar = default
        where username = @username
end
go

drop trigger if exists after_update_Account
go
create trigger after_update_Account
    on Account
    after
        update as
    declare
        @id int, @password_new varchar(100), @password_old varchar(100), @avatar nvarchar(max)
    select @id = new.id, @password_new = new.password, @password_old = old.password, @avatar = new.avatar
    from inserted new,
         deleted old
begin
    if
        (@password_new != @password_old)
        update Account
        set password = HASHBYTES('SHA2_512', @password_new + CAST('namtrungtantoan' as varchar(30)))
        where id = @id
    if (@avatar = '' or @avatar is null)
        update Account
        set avatar = default
        where id = @id
end
go

-- Insert, update Cart
drop trigger if exists after_insert_update_Cart
go
create trigger after_insert_update_Cart
    on Cart
    after insert,
    update as
    declare
        @id int, @cost real
    select @id = new.id, @cost = new.quantity * p.cost
    from inserted new,
         product p
    where new.idProduct = p.id
begin
    update Cart
    set cost = @cost
    where id = @id
end
go
