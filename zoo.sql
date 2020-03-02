-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 13 Août 2014 à 11:56
-- Version du serveur: 5.5.38-0ubuntu0.14.04.1
-- Version de PHP: 5.5.9-1ubuntu4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `zoo`
--
CREATE DATABASE IF NOT EXISTS `zoo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `zoo`;

-- --------------------------------------------------------

--
-- Structure de la table `animal`
--

DROP TABLE IF EXISTS `animal`;
CREATE TABLE IF NOT EXISTS `animal` (
  `idAnimal` tinyint(4) NOT NULL AUTO_INCREMENT,
  `codeAnimal` varchar(20) DEFAULT NULL,
  `nom` varchar(20) DEFAULT NULL,
  `age` tinyint(4) DEFAULT '0',
  `poids` float DEFAULT '0',
  `x` int(3) NOT NULL,
  `y` int(3) NOT NULL,
  PRIMARY KEY (`idAnimal`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `animal`
--

INSERT INTO `animal` (`idAnimal`, `codeAnimal`, `nom`, `age`, `poids`, `x`, `y`) VALUES
(1, 'Lion', 'clarence', 10, 170.4, 400, 400),
(2, 'Gazelle', 'GAGAG', 5, 135.2, 200, 400),
(3, 'Singe', 'chita', 4, 38, 500, 480),
(4, NULL, NULL, 0, 0, 300, 300),
(5, 'Gazelle', 'Beep Beep', 5, 135.2, 700, 230);

-- --------------------------------------------------------

--
-- Structure de la table `gazelle`
--

DROP TABLE IF EXISTS `gazelle`;
CREATE TABLE IF NOT EXISTS `gazelle` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `idAnimal` tinyint(4) NOT NULL,
  `lgCornes` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `gazelle`
--

INSERT INTO `gazelle` (`id`, `idAnimal`, `lgCornes`) VALUES
(1, 2, 34),
(2, 5, 23);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
