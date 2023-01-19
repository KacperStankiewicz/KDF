--liquibase formatted sql
--changeset aleksander-misztal:1

CREATE TABLE IF NOT EXISTS authority (
    name varchar(50) not null primary key
);

CREATE TABLE IF NOT EXISTS owner (
    id bigint auto_increment primary key ,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    phone varchar(15),
    address_id bigint not null references address(id)
);


CREATE TABLE IF NOT EXISTS person (
    id bigint auto_increment primary key ,
    object_id bigint not null REFERENCES object(id),
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    phone varchar(15) not null,
    email varchar(255) not null,
    address_id bigint not null REFERENCES address(id)
);

CREATE TABLE IF NOT EXISTS allocation (
    id bigint auto_increment primary key,
    person_id bigint not null REFERENCES person(id),
    object_id bigint not null REFERENCES object(id)
);

CREATE TABLE IF NOT EXISTS address(
    id bigint auto_increment primary key,
    street varchar(255) not null,
    number varchar(255) not null,
    city varchar(255) not null,
    country varchar(255) not null,
    postal_code  varchar(6) not null
);

CREATE TABLE IF NOT EXISTS object (
    id bigint auto_increment primary key,
    name varchar(255) NOT NULL,
    owner_id bigint not null REFERENCES owner(id),
    address_id bigint not null REFERENCES address(id),
    category varchar(255) not null,
    nip varchar(255) not null
);

CREATE TABLE IF NOT EXISTS owner_objects(
    owner_id bigint not null REFERENCES owner(id),
    object_id bigint not null REFERENCES object(id)
);

CREATE TABLE IF NOT EXISTS reservation (
    id bigint auto_increment primary key,
    station_id bigint not null REFERENCES station(id),
    start_date DATETIME not null,
    end_date DATETIME not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    phone varchar(15) not null ,
    email varchar(255) not null ,
    num_of_people int not null
);

CREATE TABLE IF NOT EXISTS station (
    id bigint auto_increment primary key,
    object_id bigint REFERENCES object(id),
    status varchar(50) not null,
    capacity int not null,
    station_number int not null
);

CREATE TABLE IF NOT EXISTS owner_authority(
    owner_id bigint not null references owner (id),
    authority varchar(50) not null references authority (name)
)