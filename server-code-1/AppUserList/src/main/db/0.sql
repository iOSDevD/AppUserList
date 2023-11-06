drop database IF EXISTS MyDB;
create database MyDB;
use MyDB;
create table appUsers(
userid int NOT NULL AUTO_INCREMENT,
firstName varchar(30) Not null, 
lastName varchar(30) Not null,
PRIMARY KEY (userid));