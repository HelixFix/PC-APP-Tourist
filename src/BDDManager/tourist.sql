DROP DATABASE IF EXISTS VOYAGE;

CREATE DATABASE VOYAGE;
USE VOYAGE;

CREATE TABLE ville(
        ID_ville int Auto_increment NOT NULL PRIMARY KEY,
        nom_ville varchar(50)  NOT NULL,
        description varchar(200)  NOT NULL
);

CREATE TABLE point_interet(
        ID_pt_interet  Int Auto_increment NOT NULL PRIMARY KEY,
        nom_pt_interet varchar(50)  NOT NULL,
        epoque varchar(50)  NOT NULL,
        categorie varchar(50)  NOT NULL,
        description_pt_interet longtext  NOT NULL,
        nom_architecte varchar(50)  NOT NULL,
        publier tinyint(1) NOT NULL,
        chemin_photo1 longblob   NULL,
        chemin_photo2 longblob   NULL,
        chemin_photo3 longblob   NULL,
        ID_ville int NOT NULL,
        CONSTRAINT fkVilleInteret FOREIGN KEY (ID_ville) REFERENCES ville(ID_ville)
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


INSERT INTO `ville` (`ID_ville`,`nom_ville`,`description`) VALUES (null,"Rome","Cum sociis natoque penatibus et magnis dis parturient"),(null,"Pékin","Cum sociis natoque penatibus et magnis dis parturient"),(null,"Grenade","Cum sociis natoque penatibus et magnis dis parturient");




INSERT INTO `utilisateur` (`ID_utilisateur`, `nom_utilisateur`, `prenom`, `pseudo`, `mot_de_passe`, `droit_acces`, `activer`) VALUES (NULL, 'Admin', 'Admin', 'Admin', 'Admin', '0', '1');
INSERT INTO `utilisateur` (`ID_utilisateur`, `nom_utilisateur`, `prenom`, `pseudo`, `mot_de_passe`, `droit_acces`, `activer`) VALUES (NULL, 'Editeur', 'Editeur', 'Editeur', 'Editeur', '1', '1');
INSERT INTO `utilisateur` (`ID_utilisateur`, `nom_utilisateur`, `prenom`, `pseudo`, `mot_de_passe`, `droit_acces`, `activer`) VALUES (NULL, 'User1', 'User1', 'User1', 'User1', '2', '1');
INSERT INTO `utilisateur` (`ID_utilisateur`, `nom_utilisateur`, `prenom`, `pseudo`, `mot_de_passe`, `droit_acces`, `activer`) VALUES (NULL, 'User2', 'User2', 'User2', 'User2', '2', '0');