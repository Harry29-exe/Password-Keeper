create sequence hibernate_sequence start 1 increment 1;
create table authorized_devices
(
    id          int8         not null,
    client_name varchar(255) not null,
    ip_address  varchar(255) not null,
    public_id   uuid         not null,
    user_id     int8         not null,
    primary key (id)
);
create table login_events
(
    id         int8      not null,
    login_date timestamp not null,
    device     int8      not null,
    primary key (id)
);
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
create table unauthorized_devices
(
    id                   uuid         not null,
    authorization_link   varchar(100) not null,
    client_name          varchar(255) not null,
    ip_address           varchar(255) not null,
    link_expiration_date timestamp    not null,
    user_id              int8,
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
alter table if exists authorized_devices
    add constraint authorized_device unique (ip_address, client_name, user_id);
alter table if exists authorized_devices
    add constraint UK_i090e8x5rawfmeoc6msisuilw unique (public_id);
alter table if exists passwords_storage
    add constraint UKgfpov6f7om3860a5qc7aji4yo unique (password_name, password_owner);
alter table if exists unauthorized_devices
    add constraint UK_yag25bkyo7mcrwosv7otfdcq unique (authorization_link);
alter table if exists user_entity
    add constraint UK_7dlp7qss3gjetumusoq0vn6je unique (public_id);
alter table if exists user_entity
    add constraint UK_2jsk4eakd0rmvybo409wgwxuw unique (username);
alter table if exists authorized_devices
    add constraint FKnkhnob5gfwu9x5qo3dxy2py9e foreign key (user_id) references user_entity;
alter table if exists login_events
    add constraint FKfs0vld0k1yi909m7x6810x2mx foreign key (device) references authorized_devices;
alter table if exists passwords_storage
    add constraint FK4mfpqbpcgn9dug5pqaliw2xf5 foreign key (password_owner) references user_entity;
alter table if exists unauthorized_devices
    add constraint FK5q9jw9xi5pv8o24b3wxehm4sd foreign key (user_id) references user_entity;
