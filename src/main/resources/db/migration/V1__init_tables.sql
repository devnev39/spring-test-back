create table task (
    id int primary key AUTO_INCREMENT,
    completed bit default 0,
    created_at TIMESTAMP default (sysdate()),
    updated_at TIMESTAMP default (sysdate()),
    name varchar(255)
)