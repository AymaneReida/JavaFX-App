-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 27 fév. 2021 à 01:55
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `compte_exam`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(15) CHARACTER SET utf8 NOT NULL,
  `prenom` varchar(15) CHARACTER SET utf8 NOT NULL,
  `numero_compte` varchar(15) CHARACTER SET utf8 NOT NULL,
  `telephone` varchar(15) CHARACTER SET utf8 NOT NULL,
  `email` varchar(40) CHARACTER SET utf8 NOT NULL,
  `adresse` varchar(50) CHARACTER SET utf8 NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `numero_compte`, `telephone`, `email`, `adresse`, `date`) VALUES
(4, 'Baba', 'Ali', '256B606060', '0666666666', 'ali@gmail.com', 'Hassan Rabat Maroc', '2021-01-31'),
(5, 'Baba', 'Ahmed', '236C808080', '0677777777', 'ahmed@gmail.com', 'Agdal Rabat Maroc', '2021-01-31'),
(6, 'Baba', 'Amine', '298D707060', '0688888888', 'amine@gmail.com', 'Hay Riad Rabat Maroc', '2021-01-31');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id` bigint(20) NOT NULL,
  `numero` varchar(15) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `solde` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `numero`, `nom`, `prenom`, `solde`) VALUES
(1, '256B606060', 'Baba', 'Ali', 0),
(2, '236C808080', 'Baba', 'Ahmed', 30000),
(6, '298D707060', 'Baba', 'Amine', 43000),
(7, '20BDRT1485', 'REIDA', 'Aymane', 0),
(8, '2057984345', 'NASRI', 'Adam', 10000);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `id` bigint(20) NOT NULL,
  `id_compte` bigint(20) NOT NULL,
  `type` varchar(6) NOT NULL,
  `montant` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`id`, `id_compte`, `type`, `montant`, `date`) VALUES
(1, 1, 'Vers', 10000, '2021-01-15'),
(2, 1, 'Retr', 2000, '2021-01-20'),
(3, 1, 'Vers', 5000, '2021-01-25'),
(4, 1, 'Retr', 3000, '2021-01-30'),
(5, 1, 'Vers', 18000, '2021-02-05'),
(8, 1, 'PDIST', 50000, '2021-02-19'),
(9, 7, 'PDIST', 50000, '2021-02-20');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_fk_1` (`id_compte`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `account_fk_1` FOREIGN KEY (`id_compte`) REFERENCES `compte` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
