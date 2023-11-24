DROP DATABASE if exists juego;
CREATE DATABASE juego;
USE juego;

CREATE TABLE  estadisticas(
nombrePersonaje varchar(25), 
tipoPersonaje varchar(25) ,
duracionPartida int ,
vidasRestantes int ,
oroObtenido int
);

select *from estadisticas;