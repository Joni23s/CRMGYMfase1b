CREATE DATABASE  IF NOT EXISTS `crmgym_test_f1b` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `crmgym_test_f1b`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: crmgym_test_f1b
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `document_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `current_plan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`document_id`),
  UNIQUE KEY `UK_srv16ica2c1csub334bxjjb59` (`email`),
  KEY `FK2osgio9nx8apphkbiaqnnr8jk` (`current_plan_id`),
  CONSTRAINT `FK2osgio9nx8apphkbiaqnnr8jk` FOREIGN KEY (`current_plan_id`) REFERENCES `plans` (`id_plan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (12345655,'pupi@mail.com',_binary '','Batiatto','Juan','S',1),(12345678,'balmi@mail.com',_binary '','Balmeco','Joaquin','125874963',2),(45789321,'monju@gmail.com',_binary '','montes','julieta','128529674',1),(78965432,'adi@mail.com',_binary '','Adtencia','Juana','538729584',3),(95148736,'jua@gmail.com',_binary '','Jua','Elvi','36987452',3),(96852741,'ale.jo@gmail.com',_binary '','Alen','Jonathan','2634569418',1);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historical_plans`
--

DROP TABLE IF EXISTS `historical_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historical_plans` (
  `id_historical` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `start_date` date NOT NULL,
  `document_id` int(11) NOT NULL,
  `id_plan` int(11) NOT NULL,
  PRIMARY KEY (`id_historical`),
  KEY `FK58gerebcsp9qlheo9yhwt7drr` (`document_id`),
  KEY `FKtn0gt2yg7hmdostvlrn651jsc` (`id_plan`),
  CONSTRAINT `FK58gerebcsp9qlheo9yhwt7drr` FOREIGN KEY (`document_id`) REFERENCES `clients` (`document_id`),
  CONSTRAINT `FKtn0gt2yg7hmdostvlrn651jsc` FOREIGN KEY (`id_plan`) REFERENCES `plans` (`id_plan`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historical_plans`
--

LOCK TABLES `historical_plans` WRITE;
/*!40000 ALTER TABLE `historical_plans` DISABLE KEYS */;
INSERT INTO `historical_plans` VALUES (1,'2025-07-10',_binary '\0','2025-07-10',12345678,1),(2,'2025-07-10',_binary '\0','2025-07-10',78965432,2),(3,NULL,_binary '','2025-07-10',95148736,3),(4,NULL,_binary '','2025-07-10',12345678,2),(5,NULL,_binary '','2025-07-10',78965432,3),(6,NULL,_binary '','2025-07-11',12345655,1),(7,'2025-07-13',_binary '\0','2025-07-13',96852741,1),(8,NULL,_binary '','2025-07-13',96852741,1),(9,NULL,_binary '','2025-07-13',45789321,1);
/*!40000 ALTER TABLE `historical_plans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plans`
--

DROP TABLE IF EXISTS `plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plans` (
  `id_plan` int(11) NOT NULL,
  `days_enabled` int(11) NOT NULL,
  `hours_enabled` int(11) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `name_plan` varchar(255) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `value` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id_plan`),
  UNIQUE KEY `UK_6rnlq5lwwmf7hqed190yxumbo` (`name_plan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plans`
--

LOCK TABLES `plans` WRITE;
/*!40000 ALTER TABLE `plans` DISABLE KEYS */;
INSERT INTO `plans` VALUES (1,3,6,_binary '','Basico','Plan de inicio',15000.00),(2,4,8,_binary '','Intermedio','Plan de avance',20000.00),(3,6,12,_binary '','Full','Para los pro',25000.00),(4,2,4,_binary '\0','Finde','Sabado y domingo',10000.00),(5,1,2,_binary '','Flojo','Plan de recomendación para sedentarios',8000.00);
/*!40000 ALTER TABLE `plans` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-13 18:01:34
