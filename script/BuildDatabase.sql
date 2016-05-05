-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Client : localhost
-- Généré le : mar 02 Mai 2015
-- Version du serveur : 5.6.20-log

SET SQL_MODE = " NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de donnée rfid_badgeuse
--

CREATE DATABASE IF  NOT EXISTS 'rfid_badgeuse' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE rfid_badgeuse;
--
-- Table structure for table `cours`
--


CREATE TABLE IF NOT  EXISTS `cours` (
  `id_c` int(11) NOT NULL,
  `heureDebut` time NOT NULL,
  `heureFin` time NOT NULL,
  `libelle` varchar(45) NOT NULL,
  `salle` varchar(45) NOT NULL,
  PRIMARY KEY (`id_c`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `eleve`
--

CREATE TABLE IF NOT EXISTS `eleve` (
  `id` int(11) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `id_historisation` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `idCarte` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `passage`
--

CREATE TABLE IF NOT  EXISTS  `passage` (
  `id_p` int(11) NOT NULL,
  `heureArrivee` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `heureDepart` datetime DEFAULT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `presence`
--

CREATE TABLE IF NOT EXISTS `presence` (
  `idEtud` int(11) NOT NULL,
  `idCours` int(11) NOT NULL,
  `presence` enum('r','p') NOT NULL,
  `Date` datetime NOT NULL,
  `idPassage` int(11) NOT NULL,
  PRIMARY KEY (`idEtud`,`idCours`),
  KEY `idCours` (`idCours`),
  KEY `idPassage` (`idPassage`),
  CONSTRAINT `presence_ibfk_1` FOREIGN KEY (`idCours`) REFERENCES `cours` (`id_c`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `presence_ibfk_2` FOREIGN KEY (`idPassage`) REFERENCES `passage` (`id_p`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `presence_ibfk_3` FOREIGN KEY (`idEtud`) REFERENCES `eleve` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
