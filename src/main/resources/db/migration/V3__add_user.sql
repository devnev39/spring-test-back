create table user (
    id int primary key AUTO_INCREMENT,
    name VARCHAR(25),
    email VARCHAR(25) UNIQUE,
    password VARCHAR(256)
);

alter table task
add column userid int,
add constraint fk_user_id foreign key (userid) references user(id);