-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: pay_my_buddy_db
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `balance` decimal(19,2) DEFAULT NULL,
  `fk_account_type_id` int DEFAULT NULL,
  `fk_account_currency_id` int DEFAULT NULL,
  `fk_account_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK77ie636vuxdcpbb83gg0o59r3` (`fk_account_type_id`),
  KEY `FKa958ymuau4ak3tycplr45exf1` (`fk_account_currency_id`),
  KEY `FKh6s3bm610t9gq1ciko6w4vitp` (`fk_account_user_id`),
  CONSTRAINT `FK77ie636vuxdcpbb83gg0o59r3` FOREIGN KEY (`fk_account_type_id`) REFERENCES `account_type` (`id`),
  CONSTRAINT `FKa958ymuau4ak3tycplr45exf1` FOREIGN KEY (`fk_account_currency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `FKh6s3bm610t9gq1ciko6w4vitp` FOREIGN KEY (`fk_account_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,269.85,NULL,NULL,1),(2,180.00,NULL,NULL,2),(3,249.75,NULL,1,3),(4,0.00,NULL,1,4),(5,299.50,NULL,1,5);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `account_type`
--

DROP TABLE IF EXISTS `account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_type` (
  `id` int NOT NULL,
  `account_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_type`
--

LOCK TABLES `account_type` WRITE;
/*!40000 ALTER TABLE `account_type` DISABLE KEYS */;
INSERT INTO `account_type` VALUES (1,'Normal'),(2,'Bank');
/*!40000 ALTER TABLE `account_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bank_account_number` varchar(255) DEFAULT NULL,
  `fk_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2k4h6j53tmuqis1wchkr06a83` (`fk_account_id`),
  CONSTRAINT `FK2k4h6j53tmuqis1wchkr06a83` FOREIGN KEY (`fk_account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (1,'bankacount1',1),(2,'bankacount2',3),(3,'bankacount3',5);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connection`
--

DROP TABLE IF EXISTS `connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `connection` (
  `id` int NOT NULL AUTO_INCREMENT,
  `connected_user_id` int DEFAULT NULL,
  `fk_user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9hh3paeg8e3xnpw7a88juo6g` (`fk_user_id`),
  CONSTRAINT `FK9hh3paeg8e3xnpw7a88juo6g` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connection`
--

LOCK TABLES `connection` WRITE;
/*!40000 ALTER TABLE `connection` DISABLE KEYS */;
INSERT INTO `connection` VALUES (1,1,2),(2,1,3),(3,3,1),(4,4,2),(5,4,3),(6,4,5);
/*!40000 ALTER TABLE `connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency` (
  `id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'Euro','EURO');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'User');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fee` decimal(19,2) DEFAULT NULL,
  `to_account_id` int DEFAULT NULL,
  `fk_from_account_id` int DEFAULT NULL,
  `fk_bank_account_id` int DEFAULT NULL,
  `fk_transaction_currency_id` int DEFAULT NULL,
  `fk_transaction_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK34tdnhhps1nadlps2jeec010s` (`fk_from_account_id`),
  KEY `FK5l07vs4in5l6nvcjbb8hdapb7` (`fk_bank_account_id`),
  KEY `FKildhbr0jcx70yr7hbx2wx07jq` (`fk_transaction_currency_id`),
  KEY `FKk07695l3mue05jgtoo82sjpgc` (`fk_transaction_type_id`),
  CONSTRAINT `FK34tdnhhps1nadlps2jeec010s` FOREIGN KEY (`fk_from_account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FK5l07vs4in5l6nvcjbb8hdapb7` FOREIGN KEY (`fk_bank_account_id`) REFERENCES `bank_account` (`id`),
  CONSTRAINT `FKildhbr0jcx70yr7hbx2wx07jq` FOREIGN KEY (`fk_transaction_currency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `FKk07695l3mue05jgtoo82sjpgc` FOREIGN KEY (`fk_transaction_type_id`) REFERENCES `transaction_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,0.00,NULL,'paymybuddy',0.00,2,1,NULL,NULL,NULL),(2,200.00,NULL,'Adding money from bank account.',0.00,0,1,1,NULL,2),(3,100.00,NULL,'Adding money from bank account.',0.00,0,1,1,NULL,2),(4,20.00,NULL,'paymybuddy',0.10,2,1,NULL,NULL,1),(5,10.00,NULL,'paymybuddy',0.05,2,1,NULL,1,1),(6,300.00,NULL,'Adding money from bank account.',0.00,0,3,2,1,2),(7,50.00,NULL,'paymybuddy',0.25,2,3,NULL,1,1),(8,400.00,NULL,'Adding money from bank account.',0.00,0,5,3,1,2),(9,100.00,NULL,'paymybuddy',0.50,2,5,NULL,1,1);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_type`
--

DROP TABLE IF EXISTS `transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction_type` (
  `id` int NOT NULL,
  `transaction_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_type`
--

LOCK TABLES `transaction_type` WRITE;
/*!40000 ALTER TABLE `transaction_type` DISABLE KEYS */;
INSERT INTO `transaction_type` VALUES (1,'PayMyBuddy'),(2,'CreditMyAccount'),(3,'TransferToBankAccount');
/*!40000 ALTER TABLE `transaction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `is_active` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `fk_user_role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKljasjfsnflb15o7aywlpkslwl` (`fk_user_role_id`),
  CONSTRAINT `FKljasjfsnflb15o7aywlpkslwl` FOREIGN KEY (`fk_user_role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mampoudou@user.fr',1,'Mamoudou','$2a$10$pIKOsXMjurT9N8l0H0i.3uLZMLQ1RFi7bTxnv3ruAlDCe.xjfkg4G',2),(2,'doro@user.fr',1,'Doro','$2a$10$K10sDqj8vmtmCMygaHuUYOi75H3D5A5o8Y3zaYfetZn7rdr3TOA.W',2),(3,'dieyna@user.fr',1,'Dieyna','$2a$10$Iq.E7dyRseKnUlYYSCUmiOEMWgPuC0ggR9PlIHILcKNYctfi70oKW',2),(4,'mariam@user.fr',1,'Mariam','$2a$10$hOfhfQLaGLBLXhH7WnP06OUkaHNphnPfG5UFBUgLiRlWf5nwVQL.W',2),(5,'mayi@user.fr',1,'Mayi','$2a$10$apvI.fEbsgtoIdWieBFeaeauOrtIdui.iDhUBmO6XXkNFmezQ.gxG',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-18 21:57:16