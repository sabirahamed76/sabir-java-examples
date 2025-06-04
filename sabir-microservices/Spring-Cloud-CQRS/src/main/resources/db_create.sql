
CREATE TABLE users(
                     id SERIAL NOT NULL PRIMARY KEY,
                     firstname VARCHAR (100),
                     lastname VARCHAR (20),
                     state VARCHAR (20)
);

CREATE TABLE product(
                     id SERIAL NOT NULL PRIMARY KEY,
                     description VARCHAR (100),
                     price DOUBLE PRECISION
);

CREATE TABLE purchase_order(
                     id SERIAL NOT NULL PRIMARY KEY,
                     userId BIGINT,
					 productId BIGINT,
                     orderDate Date
);
CREATE TABLE purchase_order_summary(
					state VARCHAR (20) NOT NULL PRIMARY KEY,
					totalSale DOUBLE PRECISION
);

select * from product;
select * from users;
select * from purchase_order;
select * from purchase_order_summary;


insert into product (description,price) values ('apple',10.5);
insert into product (description,price) values ('orange',11.5);

insert into users (firstName,lastName,state) values ('sabeer','ahamed','primary');
insert into users (firstName,lastName,state) values ('siddique','badsha','secondary');

