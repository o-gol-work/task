CREATE DATABASE  IF NOT EXISTS `task_vsr` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `task_vsr`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: task_vsr
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `department_tasker_to_departments_executers`
--

DROP TABLE IF EXISTS `department_tasker_to_departments_executers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department_tasker_to_departments_executers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `department_tasker_id` bigint DEFAULT NULL,
  `departments_executers_ids` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_department_tasker_deprtment_id_idx` (`department_tasker_id`),
  KEY `fk_departmemts_executers_idx` (`departments_executers_ids`),
  CONSTRAINT `fk_departmemts_executers` FOREIGN KEY (`departments_executers_ids`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_department_tasker_deprtment_id` FOREIGN KEY (`department_tasker_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_tasker_to_departments_executers`
--

LOCK TABLES `department_tasker_to_departments_executers` WRITE;
/*!40000 ALTER TABLE `department_tasker_to_departments_executers` DISABLE KEYS */;
INSERT INTO `department_tasker_to_departments_executers` VALUES (1,1,12),(2,1,13),(3,1,14),(4,1,26),(5,1,29),(6,2,12),(7,2,13),(8,2,14),(9,2,26),(10,27,22),(11,27,12),(12,27,13),(13,27,14),(14,27,26),(15,20,27),(16,20,22),(17,24,27),(18,24,22),(19,25,27),(20,25,22),(21,22,27),(22,2,29),(23,6,12),(24,6,13),(25,6,14),(26,6,26),(27,6,29),(28,7,12),(29,7,13),(30,7,14),(31,7,26),(32,7,29);
/*!40000 ALTER TABLE `department_tasker_to_departments_executers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-03 15:32:21
