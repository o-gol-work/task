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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tabel_number` int(10) unsigned zerofill NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `telephone_number` varchar(15) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `worked` tinyint NOT NULL,
  `post_has_department_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `tabel_number_UNIQUE` (`tabel_number`),
  KEY `fk_post_department_idx` (`post_has_department_id`),
  CONSTRAINT `fk_post_department` FOREIGN KEY (`post_has_department_id`) REFERENCES `post_has_department` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='				';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,0000001111,'Олег','Олегович','Олегов','1111',0,1),(2,0000002222,'Игорь','Игоревич','Игорев','2222',0,20),(3,0000003333,'Виктор','Викторович','Викторов','3333',0,22),(4,0000004444,'Сергей','Сергеевич','Сергеев','4444',0,24),(5,0000005555,'Юрий','Юрьевич','Юрьев','5555',0,2),(6,0000006666,'Анна','Анновна','Аннова','6666',0,6),(7,0000007777,'Ольга','Ольговна','Ольгова','7777',0,12),(8,0000008888,'Ким','Кимович','Кимов','8888',0,26),(9,0000009999,'Кус','Кусович','Кусов','9999',0,23),(10,0000001010,'Мус','Мусович','Мусов','1010',0,25),(11,0000000101,'Зус','Зусович','Зусов','1111',0,42),(12,0000001212,'Гурус','Гурусович','Гурусов','1212',0,43),(13,0000001313,'Жор','Жорович','Жоров','1313',0,44),(14,0000001414,'Гор ','Горов ','Горович','1414',0,45),(15,0000001515,'Жих',' Хор',' Карович','1515',0,7);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-15 15:33:21
