-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Client : localhost
-- Généré le : mar 02 Mai 2015
-- Version du serveur : 5.6.20-log



--
-- Base de donnée rfid_badgeuse
--

CREATE DATABASE IF  NOT EXISTS `rfid_badgeuse` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE rfid_badgeuse;
--
-- Table structure for table `cours`
--


CREATE TABLE IF NOT  EXISTS `cours` (
  `id_c` int(11) NOT NULL AUTO_INCREMENT,
  `heureDebut` time NOT NULL,
  `heureFin` time NOT NULL,
  `libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`id_c`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `eleve`
--

CREATE TABLE IF NOT EXISTS `eleve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(45) NOT NULL,
  `id_historisation` int(11),
  `nom` varchar(45) NOT NULL,
  `idCarte` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `presence`
--

CREATE TABLE IF NOT EXISTS `presence` (
  `idEtud` int(11) NOT NULL,
  `idCours` int(11) NOT NULL,
  `presence` enum('r','p'),
  `Date` date NOT NULL,
  PRIMARY KEY (`idEtud`,`idCours`),
  KEY `idCours` (`idCours`),
  CONSTRAINT `presence_ibfk_1` FOREIGN KEY (`idCours`) REFERENCES `cours` (`id_c`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `presence_ibfk_2` FOREIGN KEY (`idEtud`) REFERENCES `eleve` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cours VALUES('','9:00:00','14:00:00',"JEE");
INSERT INTO cours VALUES('','14:00:00','17:00:00',"RFID");
INSERT INTO eleve (prenom, nom, idCarte) VALUES('Tom','Phily','8B1D8B16');
INSERT INTO eleve (prenom, nom, idCarte) VALUES('Marina','Helie-Zadeh','7B498C16');
INSERT INTO eleve (prenom, nom, idCarte) VALUES('Stéphanie','Carrié','0BD09816');
INSERT INTO presence (idEtud, idCours, Date) VALUES(1,1,'2016-05-09');
INSERT INTO presence (idEtud, idCours, Date) VALUES(2,1,'2016-05-09');

 GRANT SELECT,UPDATE,INSERT ON rfid_badgeuse.* TO 'rfid'@'localhost' IDENTIFIED BY 'rfid' WITH MAX_QUERIES_PER_HOUR 100000 MAX_UPDATES_PER_HOUR 10000 MAX_CONNECTIONS_PER_HOUR 10000 MAX_USER_CONNECTIONS 10000;
 SET PASSWORD FOR 'rfid'@'localhost' = PASSWORD('rfid');