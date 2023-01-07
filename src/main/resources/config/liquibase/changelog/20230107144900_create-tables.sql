--liquibase formatted sql
--changeset aleksander-misztal:1

CREATE TABLE Authority (
    id int not null auto_increment primary key ,
    name varchar not null(50),
    description varchar(255)
);

CREATE TABLE Person (
    id int not null auto_increment primary key ,
    authority_id int not null REFERENCES Authority(id),
    object_id int not null REFERENCES Object(id),
    name varchar(255) not null,
    lastname varchar(255) not null,
    phone int(9) not null,
    main varchar(255) not null,
    address int not null
);

CREATE TABLE Allocation (
    id int not null auto_increment primary key,
    person_id int not null REFERENCES Person(id),
    building_id int not null REFERENCES Object(id)
);

CREATE TABLE Address(
    id int not null auto_increment primary key,
    street not null varchar(255),
    number not null varchar(255),
    city not null varchar(255),
    country not null varchar(255),
    postal_code not null varchar(6)
);

CREATE TABLE Category (
    id int not null auto_increment primary key,
    name varchar(255) not null
);

CREATE TABLE Object (
    id int not null auto_increment primary key,
    address int not null REFERENCES Address(id),
    category int not null REFERENCES Category(id)
);

CREATE TABLE Reservation (
    id int not null auto_increment primary key,
    station_id int not null REFERENCES Station(id),
    date_start TIMESTAMP not null,
    date_end TIMESTAMP not null

);

CREATE TABLE Station (
    id int not null auto_increment primary key,
    number int not null,
    status bool not null,
    capacity int not null
)