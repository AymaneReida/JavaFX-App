-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 27 fév. 2021 à 01:56
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
-- Base de données : `magasin_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` bigint(20) NOT NULL,
  `intitule` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `intitule`) VALUES
(1, 'Téléphone'),
(2, 'TV');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(15) CHARACTER SET utf8 NOT NULL,
  `prenom` varchar(15) CHARACTER SET utf8 NOT NULL,
  `telephone` varchar(15) CHARACTER SET utf8 NOT NULL,
  `email` varchar(40) CHARACTER SET utf8 NOT NULL,
  `adresse` varchar(50) CHARACTER SET utf8 NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `prenom`, `telephone`, `email`, `adresse`, `date`) VALUES
(4, 'Baba', 'Ali', '0666666666', 'ali@gmail.com', 'Hassan Rabat Maroc', '2021-01-31'),
(5, 'Baba', 'Ahmed', '0677777777', 'ahmed@gmail.com', 'Agdal Rabat Maroc', '2021-01-31'),
(6, 'Baba', 'Amine', '0688888888', 'amine@gmail.com', 'Hay Riad Rabat Maroc', '2021-01-31'),
(12, 'REIDA', 'Aymane ', '0697059973', 'reida.aymane@gmail.com', 'CYM Rabat', '2021-02-20');

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande`
--

CREATE TABLE `ligne_commande` (
  `id` bigint(20) NOT NULL,
  `id_produit` bigint(20) NOT NULL,
  `id_vente` bigint(20) NOT NULL,
  `prix` double NOT NULL,
  `qte` int(11) NOT NULL,
  `sousTotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `ligne_commande`
--

INSERT INTO `ligne_commande` (`id`, `id_produit`, `id_vente`, `prix`, `qte`, `sousTotal`) VALUES
(48, 1, 28, 5000, 1, 5000),
(49, 3, 28, 8000, 3, 24000),
(50, 9, 29, 10000, 11, 110000),
(51, 7, 29, 6500, 9, 58500),
(52, 6, 29, 12000, 7, 84000),
(55, 9, 32, 9000, 9, 81000),
(56, 28, 32, 20000, 11, 220000),
(58, 29, 37, 20000, 19, 380000);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `id` bigint(20) NOT NULL,
  `id_vente` bigint(20) NOT NULL,
  `montant` double NOT NULL,
  `date` date NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 NOT NULL,
  `numero_cheque` varchar(50) CHARACTER SET utf8 NOT NULL,
  `date_echeance` date DEFAULT NULL,
  `proprietaire` varchar(50) CHARACTER SET utf8 NOT NULL,
  `banque` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`id`, `id_vente`, `montant`, `date`, `type`, `numero_cheque`, `date_echeance`, `proprietaire`, `banque`) VALUES
(72, 29, 30000, '2021-02-12', 'CHEQUE', 'ABC1234', '2024-02-29', 'Ali Baba', 'ATW'),
(73, 29, 20000, '2021-02-12', 'CHEQUE', 'ABC12345', '2024-02-17', 'Ali Baba', 'Crédit Agricole'),
(76, 29, 142500, '2021-02-18', 'ESPECE', '', NULL, '', ''),
(89, 29, 50000, '2021-02-19', 'CARTE BANCAIRE', '', NULL, '', ''),
(90, 32, 1000, '2021-02-20', 'ESPECE', '', NULL, '', ''),
(91, 32, 100000, '2021-02-20', 'CHEQUE', 'AZERTY50', '2021-02-20', 'Aymane REIDA', 'ATW'),
(92, 32, 100000, '2021-02-20', 'CHEQUE', 'QWERTY50', '2021-02-20', 'Aymane REIDA', 'Crédit Agricole'),
(93, 32, 50000, '2021-02-20', 'CARTE BANCAIRE', '', NULL, '', '');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` bigint(20) NOT NULL,
  `designation` varchar(50) CHARACTER SET utf8 NOT NULL,
  `qte` int(11) NOT NULL,
  `prix_achat` double NOT NULL,
  `prix_vente` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `designation`, `qte`, `prix_achat`, `prix_vente`, `date`) VALUES
(1, 'Galaxy S5', 51, 4500, 5000, '2021-01-30'),
(2, 'Galaxy S6', 77, 5500, 6000, '2021-01-30'),
(3, 'Galaxy S7', 64, 6500, 7000, '2021-01-30'),
(4, 'Galaxy S8', 87, 7500, 8000, '2021-01-30'),
(6, 'Galaxy S10', 58, 9500, 10000, '2021-01-30'),
(7, 'TV Samsung UHD 4K 42\"', 56, 6000, 6500, '2021-01-30'),
(9, 'Xiaomi', 91, 8500, 9000, '2021-01-31'),
(28, 'Xiaomi Redmi Note 9', 99, 10000, 17000, '2021-02-20'),
(29, 'Iphone 12', 121, 15000, 20000, '2021-02-22');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(50) CHARACTER SET utf8 NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`) VALUES
(1, 'aymane', 'aymane');

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

CREATE TABLE `vente` (
  `id` bigint(20) NOT NULL,
  `id_client` bigint(20) NOT NULL,
  `total` double NOT NULL,
  `paye` double NOT NULL,
  `reste` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`id`, `id_client`, `total`, `paye`, `reste`, `date`) VALUES
(28, 6, 29000, 0, 29000, '2021-02-11'),
(29, 4, 252500, 242500, 10000, '2021-02-11'),
(32, 12, 301000, 201000, 50000, '2021-02-20'),
(37, 12, 380000, 0, 380000, '2021-02-22');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_vente` (`id_vente`),
  ADD KEY `id_produit` (`id_produit`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_vente` (`id_vente`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `vente`
--
ALTER TABLE `vente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_client` (`id_client`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT pour la table `vente`
--
ALTER TABLE `vente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ligne_commande`
--
ALTER TABLE `ligne_commande`
  ADD CONSTRAINT `ligne_commande_ibfk_1` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `ligne_commande_ibfk_2` FOREIGN KEY (`id_vente`) REFERENCES `vente` (`id`),
  ADD CONSTRAINT `ligne_commande_ibfk_3` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `paiement_ibfk_1` FOREIGN KEY (`id_vente`) REFERENCES `vente` (`id`);

--
-- Contraintes pour la table `vente`
--
ALTER TABLE `vente`
  ADD CONSTRAINT `id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
