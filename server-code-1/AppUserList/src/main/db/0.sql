drop database IF EXISTS MyDB;
create database MyDB;
use MyDB;
create table appUsers(
userid int NOT NULL AUTO_INCREMENT,
firstName varchar(30) Not null, 
lastName varchar(30) Not null,
PRIMARY KEY (userid));
create table accounts (
accountId int NOT NULL AUTO_INCREMENT,
username varchar(30) Not null default "", 
password varchar(255) Not null default "",
role Int default 0,
PRIMARY KEY (accountId)
);