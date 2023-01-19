--liquibase formatted sql
--changeset aleksander-misztal:1


INSERT INTO kdf.person Values(1,1,"Andrzej", "Łęcina", 111222333, "lecina.glazury@szef.de",1);
INSERT INTO kdf.person Values(2,1,"Andżela", "Szuldz", 111222333, "andza@juesej.us",1);
INSERT INTO kdf.person Values(3,1,"Brajan", "Śmietana", 111222333, "smietanka@interia.pl",1);

INSERT INTO kdf.allocation Values(1,1,1);
INSERT INTO kdf.allocation Values(2,2,1);
INSERT INTO kdf.allocation Values(3,3,1);

INSERT INTO kdf.address Values(1, "Wladyslawa IV", 12, "Gdynia", "Polska", "81-121");
INSERT INTO kdf.address Values(2, "Wzgorze swietego maksymiliana", 7, "Gdynia", "Polska", "81-111");
INSERT INTO kdf.address Values(3, "Dworcowa", 3, "Sopot", "Polska", "82-111");

INSERT INTO kdf.object Values(1,"U7 Gdynia",1,2,"BOWLING", 111222345);
INSERT INTO kdf.object Values(2,"U7 Sopot",1,3,"BOWLING", 111222346);

INSERT INTO kdf.station Values(1,1,"FREE",6,1);
INSERT INTO kdf.station Values(2,1,"FREE",6,2);
INSERT INTO kdf.station Values(3,1,"FREE",6,3);
INSERT INTO kdf.station Values(4,1,"FREE",6,4);
INSERT INTO kdf.station Values(5,2,"FREE",6,1);
INSERT INTO kdf.station Values(6,2,"FREE",6,2);
