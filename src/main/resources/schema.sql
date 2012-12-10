CREATE DOMAIN ENUM_statut AS varchar DEFAULT 'Attente' CHECK VALUE IN ('Attente','Acceptee','Refusee');

CREATE TABLE Utilisateur
(
U_ID int NOT NULL AUTO_INCREMENT,
nom varchar(255),
mail varchar(255),
note float,
PRIMARY KEY(U_ID)
)

CREATE TABLE Produit
(
P_ID int NOT NULL AUTO_INCREMENT,
nom varchar(255),
prixDepart double,
description varchar(1000),
V_ID int,
PRIMARY KEY (P_ID),
FOREIGN KEY (V_ID) REFERENCES Utilisateur(U_ID)
)

CREATE TABLE Offre
(
O_ID int NOT NULL AUTO_INCREMENT,
montant double,
date date,
statut ENUM_statut,
PRIMARY KEY(O_ID),
A_ID int,
P_ID int,
FOREIGN KEY (A_ID) REFERENCES Utilisateur(U_ID),
FOREIGN KEY (P_ID) REFERENCES Produit(P_ID)
)
