DROP DATABASE IF EXISTS TOURIST;

CREATE DATABASE TOURIST;

USE CONCESSION;

CREATE TABLE `avoir` (
  `ID_pt_interet` int NOT NULL,
  `ID_utilisateur` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `point_interet` (
  `ID_pt_interet` int NOT NULL,
  `epoque` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `categorie` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `description_pt_interet` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `nom_architecte` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `publier` tinyint(1) NOT NULL,
  `chemin_photo1` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `chemin_photo2` varchar(150) COLLATE utf8mb4_general_ci NOT NULL,
  `chemin_photo3` varchar(150) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `utilisateur` (
  `ID_utilisateur` int NOT NULL,
  `nom_utilisateur` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `pseudo` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `mot_de_passe` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `droit_acces` int NOT NULL,
  `activer` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `ville` (
  `ID_ville` int NOT NULL,
  `nom_ville` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `ID_pt_interet` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `avoir`
  ADD KEY `avoir_fk0` (`ID_pt_interet`),
  ADD KEY `avoir_fk1` (`ID_utilisateur`);

ALTER TABLE `point_interet`
  ADD PRIMARY KEY (`ID_pt_interet`);

ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`ID_utilisateur`);

ALTER TABLE `ville`
  ADD PRIMARY KEY (`ID_ville`),
  ADD KEY `ville_fk0` (`ID_pt_interet`);

ALTER TABLE `avoir`
  ADD CONSTRAINT `avoir_fk0` FOREIGN KEY (`ID_pt_interet`) REFERENCES `point_interet` (`ID_pt_interet`),
  ADD CONSTRAINT `avoir_fk1` FOREIGN KEY (`ID_utilisateur`) REFERENCES `utilisateur` (`ID_utilisateur`);

ALTER TABLE `ville`
  ADD CONSTRAINT `ville_fk0` FOREIGN KEY (`ID_pt_interet`) REFERENCES `point_interet` (`ID_pt_interet`);
COMMIT;

