create table recorder
(
    id     bigint auto_increment,
    name   varchar(255) not null,
    date_of_birth date not null ,
    primary key (id)
)