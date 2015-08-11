-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: geology
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `caption`
--

DROP TABLE IF EXISTS `caption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `argument` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caption`
--

LOCK TABLES `caption` WRITE;
/*!40000 ALTER TABLE `caption` DISABLE KEYS */;
INSERT INTO `caption` VALUES (1,'Розмір покладу',34),(2,'Проникність колектора',35),(3,'Піщаність пласта',36),(4,'Розчленованість пласта',37),(5,'Продуктивність пласта',38),(6,'Гідропровідність пласта',39);
/*!40000 ALTER TABLE `caption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criteria`
--

DROP TABLE IF EXISTS `criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `criteria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `criteria_value` double DEFAULT NULL,
  `formula` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `weight_factor` double DEFAULT NULL,
  `foreign_to_therm` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_criteria_1_idx` (`foreign_to_therm`),
  CONSTRAINT `fk_criteria_1` FOREIGN KEY (`foreign_to_therm`) REFERENCES `therm_set` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteria`
--

LOCK TABLES `criteria` WRITE;
/*!40000 ALTER TABLE `criteria` DISABLE KEYS */;
INSERT INTO `criteria` VALUES (1,0.06,'Трикутна',0.1,1),(2,0.2,'Трапецієвидна',0.2,2),(3,0.25,'Z-подібна',0.3,3),(4,0.072,'S-подібна',0.4,4),(5,0.413,'Лінійна z-подібна',0.6,5),(6,0.225,'Лінійна s-подібна',0.7,2),(7,0.24,'Трикутна',0.4,7),(8,0.5,'Трапецієвидна',0.5,8),(9,0.5,'Z-подібна',0.6,9),(10,0.126,'S-подібна',0.7,10),(11,0.551,'Лінійна z-подібна',0.8,1),(12,0.29,'Лінійна s-подібна',0.9,4);
/*!40000 ALTER TABLE `criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flooding`
--

DROP TABLE IF EXISTS `flooding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flooding` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `image` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flooding`
--

LOCK TABLES `flooding` WRITE;
/*!40000 ALTER TABLE `flooding` DISABLE KEYS */;
INSERT INTO `flooding` VALUES (1,'Приконтурна',NULL,NULL),(2,'Законтурна',NULL,NULL);
/*!40000 ALTER TABLE `flooding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intermediate`
--

DROP TABLE IF EXISTS `intermediate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intermediate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foreign_to_criteria` int(11) NOT NULL,
  `foreign_to_caption` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_intermediate_1_idx` (`foreign_to_caption`),
  KEY `fk_intermediate_2_idx` (`foreign_to_criteria`),
  CONSTRAINT `fk_intermediate_1` FOREIGN KEY (`foreign_to_caption`) REFERENCES `caption` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_intermediate_2` FOREIGN KEY (`foreign_to_criteria`) REFERENCES `criteria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intermediate`
--

LOCK TABLES `intermediate` WRITE;
/*!40000 ALTER TABLE `intermediate` DISABLE KEYS */;
INSERT INTO `intermediate` VALUES (1,1,1),(2,7,1),(3,2,2),(4,8,2),(5,3,3),(6,9,3),(7,4,4),(8,10,4),(9,5,5),(10,11,5),(11,6,6),(12,12,6);
/*!40000 ALTER TABLE `intermediate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intermediatetoflooding`
--

DROP TABLE IF EXISTS `intermediatetoflooding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intermediatetoflooding` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `foreign_to_criteria` int(11) NOT NULL,
  `foreign_to_flooding` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_intermediatetoflooding_1_idx` (`foreign_to_criteria`),
  KEY `fk_intermediatetoflooding_2_idx` (`foreign_to_flooding`),
  CONSTRAINT `fk_intermediatetoflooding_1` FOREIGN KEY (`foreign_to_criteria`) REFERENCES `criteria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_intermediatetoflooding_2` FOREIGN KEY (`foreign_to_flooding`) REFERENCES `flooding` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intermediatetoflooding`
--

LOCK TABLES `intermediatetoflooding` WRITE;
/*!40000 ALTER TABLE `intermediatetoflooding` DISABLE KEYS */;
INSERT INTO `intermediatetoflooding` VALUES (1,1,1),(2,7,2),(3,2,1),(4,8,2),(5,3,1),(6,9,2),(7,4,1),(8,10,2),(9,5,1),(10,11,2),(11,6,1),(12,12,2);
/*!40000 ALTER TABLE `intermediatetoflooding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parameters`
--

DROP TABLE IF EXISTS `parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parameters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `value` float DEFAULT NULL,
  `foreign_to_criteria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parameters_1_idx` (`foreign_to_criteria`),
  CONSTRAINT `fk_parameters_1` FOREIGN KEY (`foreign_to_criteria`) REFERENCES `criteria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parameters`
--

LOCK TABLES `parameters` WRITE;
/*!40000 ALTER TABLE `parameters` DISABLE KEYS */;
INSERT INTO `parameters` VALUES (1,'Параметр А',10,1),(2,'Параметр В',50,1),(3,'Параметр C',100,1),(4,'Параметр А',10,2),(5,'Параметр B',25,2),(6,'Параметр C',50,2),(7,'Параметр D',100,2),(8,'Параметр А',10,3),(9,'Параметр B',100,3),(10,'Параметр А',10,4),(11,'Параметр B',100,4),(12,'Параметр А',10,5),(13,'Параметр B',100,5),(14,'Параметр А',10,6),(15,'Параметр B',100,6),(16,'Параметр А',10,7),(17,'Параметр В',50,7),(18,'Параметр C',100,7),(19,'Параметр А',10,8),(20,'Параметр B',25,8),(21,'Параметр C',50,8),(22,'Параметр D',100,8),(23,'Параметр А',10,9),(24,'Параметр B',100,9),(25,'Параметр А',10,10),(26,'Параметр B',100,10),(27,'Параметр А',10,11),(28,'Параметр B',100,11),(29,'Параметр А',10,12),(30,'Параметр B',100,12);
/*!40000 ALTER TABLE `parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `results` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `minimum` double DEFAULT NULL,
  `average` double(3,3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_results_1` FOREIGN KEY (`id`) REFERENCES `flooding` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (1,0.06,0.203),(2,0.126,0.368);
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `therm_set`
--

DROP TABLE IF EXISTS `therm_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `therm_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `therm_set`
--

LOCK TABLES `therm_set` WRITE;
/*!40000 ALTER TABLE `therm_set` DISABLE KEYS */;
INSERT INTO `therm_set` VALUES (1,'Малий'),(2,'Значний'),(3,'Великий'),(4,'Мала'),(5,'Середня'),(6,'Велика'),(7,'Слабка'),(8,'Значна'),(9,'Невелика'),(10,'Низька'),(11,'Висока'),(12,'Однорідний'),(13,'Неоднорідний'),(14,'Невиражена'),(15,'Виражена'),(16,'Невисоке'),(17,'Значне'),(18,'Високе');
/*!40000 ALTER TABLE `therm_set` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-11 10:11:17
