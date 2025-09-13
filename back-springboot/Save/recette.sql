-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 13 sep. 2025 à 18:42
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Entrée'),
(2, 'Plat principal'),
(3, 'Dessert'),
(4, 'Boisson');

-- --------------------------------------------------------

--
-- Structure de la table `ingredient`
--

CREATE TABLE `ingredient` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `ingredient`
--

INSERT INTO `ingredient` (`id`, `name`, `unit`) VALUES
(1, 'Eau', 'ml'),
(2, 'Lait', 'ml'),
(3, 'Farine', 'g'),
(4, 'Sucre', 'g'),
(5, 'Sel', 'g'),
(6, 'Mozzarella', 'g'),
(7, 'Tomates fraîches', 'pièce'),
(8, 'Mozzarella', 'g'),
(9, 'Tomates fraîches', 'pièce'),
(10, 'Mozzarella', 'g'),
(11, 'Tomates fraîches', 'pièce'),
(12, 'Tomate', 'g'),
(13, 'Tomate', 'g'),
(14, 'Eau', 'ml'),
(15, 'Sucre', 'g'),
(16, 'Gomme', 'g'),
(17, 'Farine', 'g'),
(18, 'Citron', 'pc'),
(19, 'Eau', 'ml'),
(20, 'Eau', 'ml'),
(21, 'Sucre', 'g'),
(22, 'Eau', 'ml'),
(23, 'Sucre', 'g'),
(24, 'Cacao', 'g'),
(25, 'Mozzarella', 'g'),
(26, 'Tomates fraîches', 'pièce'),
(27, 'Mozzarella', 'g'),
(28, 'Tomates fraîches', 'pièce'),
(29, 'Mozzarella', 'g'),
(30, 'Tomates fraîches', 'pièce'),
(31, 'Eau', 'ml'),
(32, 'Sucre', 'g');

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
  `slug` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `category_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `recipe`
--

INSERT INTO `recipe` (`id`, `allow_comment`, `cook_time`, `created_at`, `description`, `image`, `instructions`, `prep_time`, `servings`, `slug`, `title`, `category_id`, `user_id`) VALUES
(1, b'1', 20, '2025-09-02 18:55:17.000000', 'Pizza classique italienne', '1.jpg', 'Préchauffer le four, étaler la pâte, ajouter garniture...\r\nMettre la pizza dans le four.\r\nAttendre.\r\nSortir.', 15, 2, 'pizza-margherita', 'Pizza Margherita', 1, 1),
(4, b'1', 20, '2025-09-11 14:54:15.000000', 'Pizza classique italienne avec tomate, mozzarella et basilic', '2.jpg', '1. Préparez la pâte.\n2. Ajoutez la sauce tomate.\n3. Disposez la mozzarella et le basilic.\n4. Faites cuire au four.', 15, 2, 'pizza-margherita-2', 'Pizza Margherita', 1, 1),
(12, b'1', 33, '2025-09-11 17:27:41.000000', 'Super bonbon à faire.', '3.jpg', 'Faire sauter le riz.\nAttendre.\nManger.', 33, 6, 'bonbon-au-chocolat', 'Bonbon au chocolat', 3, 1),
(13, b'1', 2, '2025-09-11 17:35:05.000000', 'Boisson au citron pas bon.', '1.jpg', 'Presser le citron.\nSecouer.\nBuvez.', 2, 200, 'boisson-au-citron', 'Boisson au citron', 4, 1),
(14, b'1', 44, '2025-09-11 17:36:17.000000', 'Cacao au lait très bon.', '2.jpg', 'Secouer.\nAttendre.\nBuvez.', 44, 11, 'cacao-au-lait', 'Cacao au lait', 4, 1),
(15, b'1', 10, '2025-09-13 16:50:50.000000', 'Boisson pour toi.', '5.jpg', 'Mettre le cacao dans l\'eau.\nAjouter le sucre.\nMélanger le tout.\nMettre au four 10 minutes.', 5, 1, 'cacao-au-lait-1', 'Cacao au lait', 4, 1),
(16, b'1', 20, '2025-09-13 17:51:44.000000', 'Pizza classique italienne', '', 'Préparer...\nCuire...\nServir.', 15, 2, 'pizza-margherita-1', 'Pizza Margherita', 1, 1),
(18, b'1', 20, '2025-09-13 18:14:52.000000', 'Pizza classique italienne', 'pizza-margherita.jpg', 'Préparer...\nCuire...\nServir.', 15, 2, 'pizza-margherita-4', 'Pizza Margherita', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `recipe_ingredient`
--

CREATE TABLE `recipe_ingredient` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `recipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `recipe_ingredient`
--

INSERT INTO `recipe_ingredient` (`id`, `quantity`, `ingredient_id`, `recipe_id`) VALUES
(1, 200, 1, 1),
(2, 150, 2, 1),
(5, 200, 1, 4),
(6, 150, 2, 4),
(21, 200, 15, 12),
(22, 12, 16, 12),
(23, 600, 17, 12),
(24, 1, 18, 13),
(25, 200, 19, 13),
(26, 200, 20, 14),
(27, 20, 21, 14),
(28, 22, 22, 15),
(29, 33, 23, 15),
(30, 100, 24, 15),
(31, 150, 25, 16),
(32, 3, 26, 16),
(33, 200, 1, 16),
(37, 150, 29, 18),
(38, 3, 30, 18),
(39, 200, 1, 18);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(4, 'Admin'),
(2, 'Animateur'),
(1, 'Membre'),
(3, 'Modérateur'),
(5, 'Super Admin');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `created_at`, `email`, `password`, `username`, `role_id`) VALUES
(1, '2025-09-02 12:50:10.000000', 'adm@email.fr', '$2a$10$Sc9iO2q1K0wMGRa3zZNyberIKZNJAmMagJuCY90j6lj2UU4N4aSye', 'admin', 5),
(2, '2025-09-02 12:52:10.000000', 'modo@email.fr', '$2a$10$Sc9iO2q1K0wMGRa3zZNyberIKZNJAmMagJuCY90j6lj2UU4N4aSye', 'modo', 3),
(3, '2025-09-03 16:52:51.000000', 'anim@email.com', '$2a$12$fLJYLo8Nej6kCsMME5Lq5.AkBc.4R0cczvqPQ8Nlqbb4QFLq2lnBS', 'Anim', 2),
(8, '2025-09-09 22:58:22.000000', '1010@ss.ss', '$2a$10$YP/1Cvwn1cg6hxVKyKkfZOAtZ9TEj8laGDql6HgYA0hkaOx43ADeq', '1010', 1);

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
  ADD UNIQUE KEY `UKr6fr7vfovs1k2wq7bjeqberj5` (`slug`),
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `ingredient`
--
ALTER TABLE `ingredient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT pour la table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `recipe_ingredient`
--
ALTER TABLE `recipe_ingredient`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

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
