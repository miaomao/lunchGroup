DROP TABLE IF EXISTS `expense_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expense_types` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `attr` enum('+','-') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(10) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `leftover` float DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `expense_items`;
CREATE TABLE `expense_items` (
  `eType_id` int(3) NOT NULL,
  `account_id` int(3) NOT NULL,
  `created` date NOT NULL DEFAULT '0000-00-00',
  `value` float DEFAULT NULL,
  PRIMARY KEY (`eType_id`,`account_id`,`created`),
  KEY `fk_eitem_user` (`account_id`),
  CONSTRAINT `fk_eitem_etype` FOREIGN KEY (`eType_id`) REFERENCES `expense_types` (`id`),
  CONSTRAINT `fk_eitem_user` FOREIGN KEY (`account_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;