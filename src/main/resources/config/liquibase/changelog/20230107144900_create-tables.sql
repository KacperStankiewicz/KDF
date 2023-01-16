--liquibase formatted sql
--changeset aleksander-misztal:1

CREATE TABLE IF NOT EXISTS Authority (
    id bigint auto_increment primary key ,
    name varchar(50) not null,
    description varchar(255)
);

CREATE TABLE IF NOT EXISTS Owner (
    id bigint auto_increment primary key ,
    firstname varchar(255) not null
);

CREATE TABLE IF NOT EXISTS Person (
    id bigint auto_increment primary key ,
    authority_id bigint not null REFERENCES Authority(id),
    object_id bigint not null REFERENCES Object(id),
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    phone varchar(15) not null,
    main varchar(255) not null,
    address_id bigint not null REFERENCES Address(id)
);

CREATE TABLE IF NOT EXISTS Allocation (
    id bigint auto_increment primary key,
    person_id bigint not null REFERENCES Person(id),
    object_id bigint not null REFERENCES Object(id)
);

CREATE TABLE IF NOT EXISTS Address(
    id bigint auto_increment primary key,
    street varchar(255) not null,
    number varchar(255) not null,
    city varchar(255) not null,
    country varchar(255) not null,
    postal_code  varchar(6) not null
);

CREATE TABLE IF NOT EXISTS Object (
    id bigint auto_increment primary key,
    name varchar(255) NOT NULL,
    owner_id bigint not null REFERENCES Person(id),
    address_id bigint not null REFERENCES Address(id),
    category varchar(255) not null,
    nip varchar(255) not null
);

CREATE TABLE IF NOT EXISTS owner_objects(
    owner_id bigint not null REFERENCES Person(id),
    object_id bigint not null REFERENCES Object(id)
);

CREATE TABLE IF NOT EXISTS Reservation (
    id bigint auto_increment primary key,
    station_id bigint not null REFERENCES Station(id),
    start_date DATETIME not null,
    end_date DATETIME not null,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    phone varchar(15) not null ,
    email varchar(255) not null ,
    num_of_people int not null
);

CREATE TABLE IF NOT EXISTS Station (
    id bigint auto_increment primary key,
    object_id bigint REFERENCES Object(id),
    status varchar(50) not null,
    capacity int not null,
    station_number int not null
);