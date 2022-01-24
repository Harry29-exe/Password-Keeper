create sequence hibernate_sequence start 1000 increment 1;
create table account_lock
(
    id          int8      not null,
    locked_from timestamp not null,
    locked_to   timestamp not null,
    user_id     int8,
    primary key (id)
);
create table client_devices
(
    id                    int8         not null,
    ip_address            varchar(255) not null,
    is_authorized         boolean      not null,
    public_id             uuid         not null,
    client_name           varchar(255) not null,
    authorization_link_id int8,
    user_id               int8         not null,
    primary key (id)
);
create table device_authorization_link
(
    id                 int8      not null,
    active_until       timestamp not null,
    authorization_link uuid      not null,
    generated_at       timestamp not null,
    primary key (id)
);
create table login_events
(
    id         int8         not null,
    ip_address varchar(255) not null,
    login_date timestamp    not null,
    public_id  uuid         not null,
    result     int4         not null,
    user_agent varchar(500),
    device_id  int8,
    user_id    int8         not null,
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
create table user_entity
(
    id               int8         not null,
    email            varchar(255) not null,
    password         varchar(255) not null,
    public_id        uuid         not null,
    storage_password varchar(255) not null,
    username         varchar(255) not null,
    primary key (id)
);
alter table if exists client_devices
    add constraint authorized_device unique (ip_address, client_name, user_id);
alter table if exists client_devices
    add constraint UK_d02peygoj71xmcv6ct01n2x2q unique (public_id);
alter table if exists client_devices
    add constraint UK_eppopimw49weicvymodf4nvcn unique (authorization_link_id);
alter table if exists device_authorization_link
    add constraint UK_hhpdvcei93u4tacftscq3vi6r unique (authorization_link);
alter table if exists login_events
    add constraint UK_pd80mvsdf7ske04b994xmqtpt unique (public_id);
alter table if exists passwords_storage
    add constraint UKgfpov6f7om3860a5qc7aji4yo unique (password_name, password_owner);
alter table if exists user_entity
    add constraint UK_4xad1enskw4j1t2866f7sodrx unique (email);
alter table if exists user_entity
    add constraint UK_7dlp7qss3gjetumusoq0vn6je unique (public_id);
alter table if exists user_entity
    add constraint UK_2jsk4eakd0rmvybo409wgwxuw unique (username);
alter table if exists account_lock
    add constraint FKsmulqkm9935chcib7gkx6qhc7 foreign key (user_id) references user_entity;
alter table if exists client_devices
    add constraint FKp4od3xmphj0s9upht9mpqax1c foreign key (authorization_link_id) references device_authorization_link;
alter table if exists client_devices
    add constraint FK6lhu6kmb2fmqn1ccsrtdf8qxf foreign key (user_id) references user_entity;
alter table if exists login_events
    add constraint FKgm7xnurayksgf1au5vfmpqdxk foreign key (device_id) references client_devices;
alter table if exists login_events
    add constraint FKpypyesxe14woublgpukw6b40v foreign key (user_id) references user_entity;
alter table if exists passwords_storage
    add constraint FK4mfpqbpcgn9dug5pqaliw2xf5 foreign key (password_owner) references user_entity;
