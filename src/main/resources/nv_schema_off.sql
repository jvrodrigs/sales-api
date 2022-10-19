create table client(
    id integer primary key auto_increment,
    name varchar(100),
    cpf varchar(11)
);

create table product(
    id integer primary key auto_increment,
    name varchar(100),
    description varchar(250),
    price_unit numeric(20,2),
    created_at timestamp
);

create table request(
    id integer primary key auto_increment,
    client_id integer references client(id),
    name varchar(100),
    description varchar(250),
    status varchar(20)
    value_total numeric(20,2)
);

create table item_order(
   id integer primary key auto_increment,
   request_id integer references request(id),
   product_id integer references product(id),
   count_product integer
)