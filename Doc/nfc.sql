
	
CREATE TABLE `cours` (
	`id` INT NOT NULL,
    `heureDebut` TIME NOT NULL,
    `heureFin` TIME NOT NULL,
    `libelle` VARCHAR(45) NOT NULL,
    `salle` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`));
    
CREATE TABLE `passage` (
	`id` INT NOT NULL,
    `heureArrivee` TIMESTAMP NOT NULL,
    `heureDepart` DATETIME NULL,
    PRIMARY KEY (`id`));
    
CREATE TABLE `Eleve` (
	`id` INT NOT NULL,
    `prenom` VARCHAR(45) NOT NULL,
    `nom` VARCHAR(45) NOT NULL,
    `idCarte` INT NOT NULL,
    PRIMARY KEY (`idCarte`));
	
	CREATE TABLE `nfc2`.`presence` (
  `idCarte` INT NOT NULL,
  `idCours` INT NOT NULL,
  `presence` ENUM('r','p') NOT NULL,
  `idPassage` INT NULL,
  PRIMARY KEY (`idCarte`, `idCours`),
  FOREIGN KEY (`idCours`) REFERENCES `cours` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`idPassage`) REFERENCES `passage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`idCarte`) REFERENCES `eleve` (`idCarte`) ON DELETE CASCADE ON UPDATE CASCADE);