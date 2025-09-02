-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 24 nov. 2024 à 18:05
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `recette`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Entrée'),
(2, 'Plat principal'),
(3, 'Dessert');

-- --------------------------------------------------------

--
-- Structure de la table `ingredient`
--

CREATE TABLE `ingredient` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `ingredient`
--

INSERT INTO `ingredient` (`id`, `name`, `unit`) VALUES
(1, 'Eau', 'ml'),
(2, 'Lait', 'ml'),
(3, 'Farine', 'g');

-- --------------------------------------------------------

--
-- Structure de la table `recipe`
--

CREATE TABLE `recipe` (
  `id` int(11) NOT NULL,
  `allow_comment` bit(1) NOT NULL,
  `cook_time` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `instructions` text DEFAULT NULL,
  `prep_time` int(11) NOT NULL,
  `servings` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `slug` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `recipe`
--

INSERT INTO `recipe` (`id`, `allow_comment`, `cook_time`, `created_at`, `description`, `image`, `instructions`, `prep_time`, `servings`, `title`, `category_id`, `user_id`, `slug`) VALUES
(2, b'1', 30, '2024-11-24 18:04:40.000000', 'Une délicieuse pâtisserie française, légère et sucrée, composée d\'une pâte à choux garnie de crème pâtissière onctueuse. Parfaite pour les grandes occasions ou simplement pour un moment gourmand à la maison.', 'choux-a-la-creme-673671421dd3f.jpg', 'Instructions (étapes de préparation) :\r\nPréparer la pâte à choux :\r\n\r\nPréchauffez votre four à 180°C.\r\nDans une casserole, faites chauffer 250 ml d\'eau, 100 g de beurre et une pincée de sel.\r\nLorsque le beurre est fondu, ajoutez 150 g de farine en une seule fois et mélangez vigoureusement jusqu\'à obtenir une pâte homogène qui se détache des parois de la casserole.\r\nRetirez du feu et incorporez les œufs un par un en mélangeant bien entre chaque ajout. Vous devez obtenir une pâte lisse et légèrement brillante.\r\nFormer les choux :\r\n\r\nSur une plaque de cuisson recouverte de papier sulfurisé, déposez des petits tas de pâte à l\'aide d\'une poche à douille ou de deux cuillères. Veillez à laisser un espace entre chaque tas.\r\nFaites cuire au four pendant 25 à 30 minutes, ou jusqu\'à ce que les choux soient bien dorés et gonflés.\r\nPréparer la crème pâtissière :\r\n\r\nDans une casserole, faites chauffer 500 ml de lait avec une gousse de vanille fendue et grattée.\r\nDans un bol, fouettez 4 jaunes d\'œufs avec 100 g de sucre jusqu\'à ce que le mélange blanchisse, puis ajoutez 40 g de fécule de maïs.\r\nVersez lentement le lait chaud sur le mélange d\'œufs en fouettant constamment.\r\nRemettez le tout dans la casserole et faites cuire à feu doux tout en fouettant, jusqu\'à ce que la crème épaississe.\r\nLaissez refroidir avant de l\'utiliser pour garnir les choux.\r\nMonter les choux :\r\n\r\nUne fois que les choux sont cuits et refroidis, coupez-les en deux.\r\nÀ l\'aide d\'une poche à douille, garnissez chaque moitié de choux de crème pâtissière.\r\nRefermez les choux et, si vous le souhaitez, saupoudrez-les de sucre glace.', 30, 4, 'Choux à la crème', 3, 1, 'choux-a-la-creme');

-- --------------------------------------------------------

--
-- Structure de la table `recipe_ingredient`
--

CREATE TABLE `recipe_ingredient` (
  `id` int(11) NOT NULL,
  `quantity` double NOT NULL,
  `unit` varchar(255) DEFAULT NULL,
  `ingredient_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'Membre'),
(2, 'Animateur'),
(3, 'Modérateur'),
(4, 'Admin'),
(5, 'Super Admin');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `last_ip` varchar(255) DEFAULT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `created_at`, `email`, `is_active`, `last_ip`, `last_login`, `password`, `username`, `role_id`) VALUES
(1, '2024-11-24 18:02:00.000000', 'cp@mangaluxe.com', b'1', NULL, NULL, '$2y$10$oyAi7EhOnwAZIX.TPOI0f.AX2yAKDkz/InJbqc467k1BnQdMWI7.K', 'admin', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ingredient`
--
ALTER TABLE `ingredient`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrufhnv33hpfxstx9x108553kj` (`category_id`),
  ADD KEY `FK5mx01yw4j003wisa2aqmwir6l` (`user_id`);

--
-- Index pour la table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9b3oxoskt0chwqxge0cnlkc29` (`ingredient_id`),
  ADD KEY `FKgu1oxq7mbcgkx5dah6o8geirh` (`recipe_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKiubw515ff0ugtm28p8g3myt0h` (`role_name`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4qu1gr772nnf6ve5af002rwya` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `ingredient`
--
ALTER TABLE `ingredient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `recipe`
--
ALTER TABLE `recipe`
  ADD CONSTRAINT `FK5mx01yw4j003wisa2aqmwir6l` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKrufhnv33hpfxstx9x108553kj` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Contraintes pour la table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  ADD CONSTRAINT `FK9b3oxoskt0chwqxge0cnlkc29` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredient` (`id`),
  ADD CONSTRAINT `FKgu1oxq7mbcgkx5dah6o8geirh` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`id`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK4qu1gr772nnf6ve5af002rwya` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
