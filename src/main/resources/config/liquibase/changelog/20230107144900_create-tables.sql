--liquibase formatted sql
--changeset aleksander-misztal:1

CREATE TABLE Authority (
    id int auto_increment primary key ,
    name varchar(50) not null,
    description varchar(255)
);

CREATE TABLE Person (
    id int auto_increment primary key ,
    authority_id int not null REFERENCES Authority(id),
    object_id int not null REFERENCES Object(id),
    name varchar(255) not null,
    lastname varchar(255) not null,
    phone int(9) not null,
    main varchar(255) not null,
    address int not null
);

CREATE TABLE Allocation (
    id int auto_increment primary key,
    person_id int not null REFERENCES Person(id),
    building_id int not null REFERENCES Object(id)
);

CREATE TABLE Address(
    id int auto_increment primary key,
    street varchar(255) not null,
    number varchar(255) not null,
    city varchar(255) not null,
    country varchar(255) not null,
    postal_code  varchar(6) not null
);

CREATE TABLE Object (
    id int auto_increment primary key,
    address int not null REFERENCES Address(id),
    category varchar(255) not null
);

CREATE TABLE Reservation (
    id int auto_increment primary key,
    station_id int not null REFERENCES Station(id),
    date_start TIMESTAMP not null,
    date_end TIMESTAMP not null

);

CREATE TABLE Station (
    id int auto_increment primary key,
    number int not null,
    status bool not null,
    capacity int not null
)