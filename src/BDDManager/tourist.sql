DROP DATABASE IF EXISTS VOYAGE;

CREATE DATABASE VOYAGE;
USE VOYAGE;

CREATE TABLE point_interet(
        ID_pt_interet  Int Auto_increment NOT NULL PRIMARY KEY,
        nom_pt_interet varchar(50)  NOT NULL,
        epoque varchar(50)  NOT NULL,
        categorie varchar(50)  NOT NULL,
        description_pt_interet varchar(200)  NOT NULL,
        nom_architecte varchar(50)  NOT NULL,
        publier tinyint(1) NOT NULL,
        chemin_photo1 varchar(150)  NOT NULL,
        chemin_photo2 varchar(150)  NOT NULL,
        chemin_photo3 varchar(150)  NOT NULL
);

CREATE TABLE utilisateur(
        ID_utilisateur int Auto_increment NOT NULL PRIMARY KEY,
        nom_utilisateur varchar(50)  NOT NULL,
        prenom varchar(50)  NOT NULL,
        pseudo varchar(50)  NOT NULL,
        mot_de_passe varchar(50)  NOT NULL,
        droit_acces int NOT NULL,
        activer tinyint(1) NOT NULL
);

CREATE TABLE avoir(
        ID_pt_interet int NOT NULL,
        ID_utilisateur int NOT NULL,
        CONSTRAINT fkInteretAvoir FOREIGN KEY (ID_pt_interet) REFERENCES point_interet(ID_pt_interet),
        CONSTRAINT fkUtilisateurAvoir FOREIGN KEY (ID_utilisateur) REFERENCES utilisateur(ID_utilisateur)
);

CREATE TABLE ville(
        Id_Acteur       Int Auto_increment NOT NULL PRIMARY KEY,
        Nom_Acteur      Varchar (50) NOT NULL,
        Prenom_Acteur   Varchar (50) NOT NULL
);

