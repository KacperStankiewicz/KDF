--liquibase formatted sql
--changeset aleksander-misztal:1

CREATE TABLE Authority (
    id int not null auto_increment primary key ,
    name varchar(50),
    description varchar(255)
);

CREATE TABLE Person (
    id int not null auto_increment primary key ,
    authority_id int REFERENCES Authority(id),
    object_id int REFERENCES Object(id),
    name varchar(255),
    lastname varchar(255),
    phone int(9),
    main varchar(255),
    address int
);

CREATE TABLE Allocation (
    id int not null auto_increment primary key,
    person_id int REFERENCES Person(id),
    building_id int REFERENCES Object(id)
);

CREATE TABLE Address(
    id int not null auto_increment primary key,
    street varchar(255),
    number varchar(255),
    city varchar(255),
    country varchar(255),
    postal_code varchar(6)
);

CREATE TABLE Category (
    id int not null auto_increment primary key,
    name varchar(255)
);

CREATE TABLE Object (
    id int not null auto_increment primary key,
    address int REFERENCES Address(id),
    category int REFERENCES Category(id)
);

CREATE TABLE Reservation (
    id int not null auto_increment primary key,
    station_id int REFERENCES Station(id),
    date_start TIMESTAMP,
    date_end TIMESTAMP

);

CREATE TABLE Station (
    id int not null auto_increment primary key,
    number int,
    status bool,
    capacity int
)