--liquibase formatted sql
--changeset aleksander-misztal:1


INSERT INTO KDF.Person Values(1,1,"Andrzej", "Łęcina", 111222333, "lecina.glazury@szef.de",1);
INSERT INTO KDF.Person Values(2,1,"Andżela", "Szuldz", 111222333, "andza@juesej.us",1);
INSERT INTO KDF.Person Values(3,1,"Brajan", "Śmietana", 111222333, "smietanka@interia.pl",1);

INSERT INTO KDF.Allocation Values(1,1,1);
INSERT INTO KDF.Allocation Values(2,2,1);
INSERT INTO KDF.Allocation Values(3,3,1);

INSERT INTO KDF.Address Values(1, "Wladyslawa IV","20", "Gdynia", "Polska", "81-121");
INSERT INTO KDF.Address Values(2, "Wzgorze swietego maksymiliana","20", "Gdynia", "Polska", "81-111");
INSERT INTO KDF.Address Values(3, "Dworcowa","20", "Sopot", "Polska", "82-111");

INSERT INTO KDF.Object Values(1,"U7 Gdynia",2,"BOWLING", 111222345);
INSERT INTO KDF.Object Values(2,"U7 Sopot",3,"BOWLING", 111222346);

INSERT INTO KDF.Reservation Values(1,4,"2023-01-20 12:00:00","2023-01-20 13:00:00", "Jan", "Popita", 999999999,"popitka@onet.pl",6);
INSERT INTO KDF.Reservation Values(2,3,"2023-01-20 13:00:00","2023-01-20 15:00:00", "Jan", "Popita", 999999999,"popitka@onet.pl",6);
INSERT INTO KDF.Reservation Values(3,2,"2023-01-21 15:00:00","2023-01-21 16:00:00", "Jan", "Popita", 999999999,"popitka@onet.pl",6);
INSERT INTO KDF.Reservation Values(4,1,"2023-01-22 12:00:00","2023-01-22 17:00:00", "Jan", "Popita", 999999999,"popitka@onet.pl",6);

INSERT INTO KDF.Station Values(1,1,"FREE",6,1);
INSERT INTO KDF.Station Values(2,1,"FREE",6,2);
INSERT INTO KDF.Station Values(3,1,"FREE",6,3);
INSERT INTO KDF.Station Values(4,1,"FREE",6,4);
INSERT INTO KDF.Station Values(5,2,"FREE",6,1);
INSERT INTO KDF.Station Values(6,2,"FREE",6,2);
