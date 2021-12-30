create sequence hibernate_sequence start 1 increment 1;
create table passwords_storage
(
    id               int8         not null,
    encoded_password text         not null,
    iv               oid          not null,
    password_name    varchar(255) not null,
    password_url     varchar(255),
    salt             oid          not null,
    password_owner   int8         not null,
    primary key (id)
);
create table user_entity
(
    id               int8         not null,
    password         varchar(255) not null,
    public_id        uuid         not null,
    storage_password varchar(255) not null,
    username         varchar(255) not null,
    primary key (id)
);

alter table if exists passwords_storage
    add constraint UKgfpov6f7om3860a5qc7aji4yo unique (password_name, password_owner);
alter table if exists user_entity
    add constraint UK_7dlp7qss3gjetumusoq0vn6je unique (public_id);
alter table if exists user_entity
    add constraint UK_2jsk4eakd0rmvybo409wgwxuw unique (username);
alter table if exists passwords_storage
    add constraint FK4mfpqbpcgn9dug5pqaliw2xf5 foreign key (password_owner) references user_entity;
