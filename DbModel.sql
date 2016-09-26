create database parcial1;

use parcial1;

create table tracking (
    id int not null auto_increment primary key,
    idTaxi smallint,
    dataDaMovimentacao timestamp,
    longitude double,
    latitude double
);

create table tracking_index (
    id int not null auto_increment primary key,
    idTaxi smallint,
    dataDaMovimentacao timestamp,
    longitude double,
    latitude double
);


alter table tracking_index
add index (longitude, latitude);
