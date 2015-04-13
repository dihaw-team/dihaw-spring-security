--
-- Base de données: `dihawdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `city`
--

CREATE TABLE IF NOT EXISTS `city` (
  `CITY_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CITY_NAME` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`CITY_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `city`
--

INSERT INTO `city` (`CITY_ID`, `CITY_NAME`) VALUES
(1, 'Tunis'),
(2, 'Sousse'),
(3, 'Monastir'),
(4, 'Msaken'),
(5, 'Sfax'),
(6, 'Bizerte');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` int(11) DEFAULT NULL,
  `user_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3580769128426C` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id`, `role`, `user_id`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 1, 3),
(4, 1, 6);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) NOT NULL,
  `FIRST_NAME` varchar(10) NOT NULL,
  `USERNAME` varchar(10) NOT NULL,
  `LAST_CONNECTION` datetime DEFAULT NULL,
  `CREATION_DATE` datetime DEFAULT NULL,
  `GENDER` varchar(10) NOT NULL,
  `CITY_ID` int(10) NOT NULL,
  `STATUS` varchar(10) NOT NULL,
  `ACCOUNT_NON_EXPIRED` int(11) NOT NULL DEFAULT '1',
  `ACCOUNT_NON_LOKED` int(11) NOT NULL DEFAULT '1',
  `CREDENTIALS_NON_EXPIRED` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `CITY_ID` (`CITY_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`ID`, `EMAIL`, `PASSWORD`, `FIRST_NAME`, `USERNAME`, `LAST_CONNECTION`, `CREATION_DATE`, `GENDER`, `CITY_ID`, `STATUS`, `ACCOUNT_NON_EXPIRED`, `ACCOUNT_NON_LOKED`, `CREDENTIALS_NON_EXPIRED`) VALUES
(1, 'admin@email.com', 'password', 'admin', 'admin', '2015-04-10 15:34:34', '2015-03-26 00:00:00', 'Male', 4, 'Enabled', 1, 1, 1),
(2, 'user@email.com', 'password', 'user', 'user', '2015-04-10 14:12:30', '2015-03-30 00:00:00', 'Male', 3, 'Enabled', 1, 1, 1),
(3, 'wahid@email.com', 'password', 'wahid', 'wahid', '2015-04-10 13:36:10', '2015-04-01 00:00:00', 'Male', 4, 'Enabled', 1, 1, 1),
(6, 'test@email.com', 'password', 'test', 'test', '2015-04-17 00:00:00', '2015-04-09 00:00:00', 'Female', 4, 'Disabled', 1, 0, 1),
(7, 'first@email.com', 'password', 'first', 'name', NULL, '2015-04-10 00:00:00', 'Female', 3, 'Enabled', 0, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user_attempts`
--

CREATE TABLE IF NOT EXISTS `user_attempts` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `ATTEMPTS` int(11) NOT NULL,
  `LAST_MODIFIED` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Contenu de la table `user_attempts`
--

INSERT INTO `user_attempts` (`ID`, `USERNAME`, `ATTEMPTS`, `LAST_MODIFIED`) VALUES
(11, 'test@email.com', 3, '2015-04-09 13:18:19'),
(18, 'wael@email.com', 1, '2015-04-10 12:24:43');

-- --------------------------------------------------------


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`CITY_ID`) REFERENCES `city` (`CITY_ID`);
