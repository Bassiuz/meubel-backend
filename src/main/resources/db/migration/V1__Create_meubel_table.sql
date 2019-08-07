create table MEUBEL (
    ID int not null AUTO_INCREMENT,
    NAME varchar(100) not null,
    DESCRIPTION varchar(500) not null,
    SHOPURL varchar(500) not null,
    IMAGEURL varchar(500) not null,
    SHOP varchar(100) not null,

    PRIMARY KEY (ID)
);