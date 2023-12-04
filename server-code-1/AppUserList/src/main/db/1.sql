INSERT INTO appUsers (FirstName,LastName) values ("Ed","Smith");
INSERT INTO appUsers (FirstName,LastName) values ("John","Smith");
INSERT INTO appUsers (FirstName,LastName) values ("Kenneth","Lowe");
INSERT INTO appUsers (FirstName,LastName) values ("Amari","Blake");
INSERT INTO appUsers (FirstName,LastName) values ("Grant","Berg");
INSERT INTO appUsers (FirstName,LastName) values ("Erik","Cross");
INSERT INTO appUsers (FirstName,LastName) values ("Adam","Blake");

insert into accounts (username, password,role) values ("admin", "63a9f0ea7bb98050796b649e85481845",1);
insert into accounts (username, password,role) values ("staff", "5d41402abc4b2a76b9719d911017c592",0);

insert into authAccounts (username,role) values ("{adminUserId}",1);