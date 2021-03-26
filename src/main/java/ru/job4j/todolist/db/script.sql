create table item (
id serial primary key,
description text,
created date,
done boolean,
user_id int not null references user_item(id)
);

create table user_item (
id serial primary key,
name varchar(2000),
email varchar(2000),
password varchar(2000)
);


