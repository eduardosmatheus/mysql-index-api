create database parcial1;

use parcial1;

create table tracking (
    id int not null auto_increment primary key,
    idTaxi smallint,
    dataDaMovimentacao date,
    longitude varchar(20),
    latitude varchar(20)
);

create table tracking_index (
    id int not null auto_increment primary key,
    idTaxi smallint,
    dataDaMovimentacao date,
    longitude varchar(20),
    latitude varchar(20)
);


alter table tracking_index
add index (longitude, latitude);
