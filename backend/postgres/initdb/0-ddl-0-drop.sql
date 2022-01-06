alter table if exists client_devices
    drop constraint if exists FKp4od3xmphj0s9upht9mpqax1c;
alter table if exists client_devices
    drop constraint if exists FK6lhu6kmb2fmqn1ccsrtdf8qxf;
alter table if exists login_events
    drop constraint if exists FKgm7xnurayksgf1au5vfmpqdxk;
alter table if exists login_events
    drop constraint if exists FKpypyesxe14woublgpukw6b40v;
alter table if exists passwords_storage
    drop constraint if exists FK4mfpqbpcgn9dug5pqaliw2xf5;
drop table if exists client_devices cascade;
drop table if exists device_authorization_link cascade;
drop table if exists login_events cascade;
drop table if exists passwords_storage cascade;
drop table if exists user_entity cascade;
drop sequence if exists hibernate_sequence;
