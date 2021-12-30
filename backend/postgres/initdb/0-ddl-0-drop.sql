alter table if exists passwords_storage
    drop constraint if exists FK4mfpqbpcgn9dug5pqaliw2xf5;
drop table if exists passwords_storage cascade;
drop table if exists user_entity cascade;
drop sequence if exists hibernate_sequence;
