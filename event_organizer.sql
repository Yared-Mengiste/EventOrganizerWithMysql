CREATE DATABASE  IF NOT EXISTS `event_organizer` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `event_organizer`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: event_organizer
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `tellNo1` varchar(20) NOT NULL,
  `tellNo2` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `sex` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'YARED','MENGISTE','0922425744','0912432123','1234','M'),(3,'YASSER','ABDU','0987654321','0912345678','1234','M'),(4,'ABEL','CHERU','0911370897','0032439098','1234','M'),(5,'MEGERSA','FEYISSA','0945634563','0924352325','1234','M'),(7,'ABREHAM','ASEFFA','0911370932','0987123454','1234','M');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(100) DEFAULT NULL,
  `type_id` int NOT NULL,
  `venue_id` int NOT NULL,
  `event_date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `customer_id` int NOT NULL,
  `guest` int NOT NULL,
  `event_cost` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `venue_id` (`venue_id`),
  KEY `type_id` (`type_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`venue_id`) REFERENCES `venue` (`id`),
  CONSTRAINT `event_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `eventtype` (`id`),
  CONSTRAINT `event_ibfk_3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (3,'YARED',2,1,'2024-11-01','07:00:00','12:00:00',1,50,120000.00),(4,'ABEBE AND ABEBECH',1,3,'2024-05-22','01:00:00','04:00:00',1,500,300000.00),(5,'OSAMA\'S GRADUATION',3,4,'2024-05-12','07:30:00','09:30:00',1,200,150000.00),(6,'MUHDIN AND MULUALEM',1,1,'2024-02-23','07:00:00','12:00:00',1,1000,600000.00),(7,'MELKA AND LOMI',1,1,'2024-02-11','19:30:00','00:30:00',1,100,150000.00),(8,'YASSER AND IKRAM',1,2,'2024-12-15','19:00:00','23:00:00',3,100,170000.00),(9,'YASSER AND IKRAM',1,2,'2024-11-11','19:00:00','22:00:00',3,300,270000.00),(10,'NATHNAEL AND SELAMAWIT',1,4,'2024-11-21','19:00:00','22:00:00',3,250,205000.00),(11,'ABRHAM AND SARA',1,2,'2024-11-01','13:00:00','18:00:00',1,200,220000.00),(12,'OSAM AND ',1,2,'2024-12-11','19:30:00','00:00:00',1,100,170000.00),(13,'AVE',1,3,'2024-02-26','19:00:00','00:00:00',3,100,100000.00),(14,'IMRAN HASSEN',2,3,'2024-05-12','15:30:00','00:40:00',1,130,102000.00);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventguests`
--

DROP TABLE IF EXISTS `eventguests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventguests` (
  `event_id` int NOT NULL,
  `guest_id` int NOT NULL,
  PRIMARY KEY (`event_id`,`guest_id`),
  KEY `guest_id` (`guest_id`),
  CONSTRAINT `eventguests_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `eventguests_ibfk_2` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventguests`
--

LOCK TABLES `eventguests` WRITE;
/*!40000 ALTER TABLE `eventguests` DISABLE KEYS */;
INSERT INTO `eventguests` VALUES (3,6),(3,7),(3,8),(3,9),(3,10),(8,11),(8,12),(4,13),(4,15);
/*!40000 ALTER TABLE `eventguests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventstaff`
--

DROP TABLE IF EXISTS `eventstaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventstaff` (
  `event_id` int NOT NULL,
  `staff_id` int NOT NULL,
  PRIMARY KEY (`event_id`,`staff_id`),
  KEY `staff_id` (`staff_id`),
  CONSTRAINT `eventstaff_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`),
  CONSTRAINT `eventstaff_ibfk_2` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventstaff`
--

LOCK TABLES `eventstaff` WRITE;
/*!40000 ALTER TABLE `eventstaff` DISABLE KEYS */;
INSERT INTO `eventstaff` VALUES (4,6),(6,6),(7,6),(8,6),(13,6),(3,7),(5,8),(14,9),(10,10),(11,10),(12,10);
/*!40000 ALTER TABLE `eventstaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventtype`
--

DROP TABLE IF EXISTS `eventtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventtype` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL,
  `cost_per_person` decimal(6,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventtype`
--

LOCK TABLES `eventtype` WRITE;
/*!40000 ALTER TABLE `eventtype` DISABLE KEYS */;
INSERT INTO `eventtype` VALUES (1,'WEDDING',500.00),(2,'BIRTHDAY',400.00),(3,'GRADUATION',350.00);
/*!40000 ALTER TABLE `eventtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (6,'DENIEL','ABEBE'),(7,'LEUL','MENGISTE'),(8,'NATHNAEL','MESFIN'),(9,'YASSER','ABDULHAFIZ'),(10,'ZIKRA','YASIN'),(11,'YARED','MENGISTE'),(12,'OSAMA','YARED'),(13,'TIGIST','MENGISTE'),(15,'SELEMON','BOGALE'),(25,'BIRUK','NEGASH');
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `sex` varchar(1) NOT NULL,
  `position_id` int NOT NULL,
  `DOB` date NOT NULL,
  `supervisor_id` int DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `tellNo1` varchar(20) NOT NULL,
  `tellNo2` varchar(20) NOT NULL,
  `event_work` int DEFAULT (0),
  `appointed_date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `position_id` (`position_id`),
  KEY `supervisor_id` (`supervisor_id`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`position_id`) REFERENCES `staffposition` (`id`),
  CONSTRAINT `staff_ibfk_2` FOREIGN KEY (`supervisor_id`) REFERENCES `staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (2,'LEUL','MENGISTE','M',5,'2001-12-01',5,'1234','0922425744','0912432123',0,'2018-12-01'),(5,'ZIKRA','YASSIN','F',6,'2001-12-01',NULL,'1234','0922425744','0912432123',0,'2020-05-21'),(6,'YASSER','ABDULHAFIZ','M',1,'2001-12-01',5,'1234','0922425744','0912432123',5,'2018-09-09'),(7,'OSAMA','SUFIYAN','M',2,'2001-12-01',5,'1234','0922425744','0912432123',1,'2022-11-29'),(8,'NATHNAEL','MESFIN','M',3,'2001-12-01',5,'1234','0922425744','0912432123',1,'2021-02-11'),(9,'IMRAN','HAYREDIN','M',2,'2001-12-01',5,'1234','0922425744','0912432123',1,'2023-01-11'),(10,'SULEYMAN','ABEBE','M',1,'2000-11-21',5,'1234','0922425744','0912432123',3,'2017-04-26'),(11,'TEBEBE','ABEBE','M',3,'2000-11-21',5,'1234','0922425744','0912432123',0,'2022-04-05');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staffposition`
--

DROP TABLE IF EXISTS `staffposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staffposition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `position` varchar(100) NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staffposition`
--

LOCK TABLES `staffposition` WRITE;
/*!40000 ALTER TABLE `staffposition` DISABLE KEYS */;
INSERT INTO `staffposition` VALUES (1,'Wedding Planner',50000.00),(2,'Birthday Planner',48000.00),(3,'Graduation Planner',47000.00),(4,'Event Manager',55000.00),(5,'Human Resource',60000.00),(6,'Supervisor',65000.00),(7,'Waiter',30000.00),(8,'Security',40000.00),(9,'Valet',35000.00);
/*!40000 ALTER TABLE `staffposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venue`
--

DROP TABLE IF EXISTS `venue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `capacity` int NOT NULL,
  `state` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `street` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venue`
--

LOCK TABLES `venue` WRITE;
/*!40000 ALTER TABLE `venue` DISABLE KEYS */;
INSERT INTO `venue` VALUES (1,'Sheraton',10000,'Capital city ','Addis Ababa',100000.00,'Fel Wuha'),(2,'Hillton',9000,'Capital city ','Addis Ababa',120000.00,'Wuchi Guday'),(3,'Anteneh',1000,'Capital city ','Addis Ababa',50000.00,'Ayer Tena'),(4,'Kuriftu',5000,'Oromia','Adama',80000.00,'Main Road 04');
/*!40000 ALTER TABLE `venue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-10 13:05:43
