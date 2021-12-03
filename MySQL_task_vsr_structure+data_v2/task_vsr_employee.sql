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
  `tabel_number` int unsigned NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='				';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,1111,'Олег','Олегович','Олегов','$2a$10$SDMkjTcLf0aJLAsMf1odL.iJe8kK6/ok4swhF7ERcp2NqCu7WFZpG',0,1),(2,2222,'Игорь','Игоревич','Игорев','$2a$10$W8zKa7mjH8NfrnQFITGwge7XpfM0mg.i3wdOk2RRZiv9eSCqQHLFi',0,20),(3,3333,'Виктор','Викторович','Викторов','$2a$10$J7Djx0/irGontxI6mOT2tO45zsedxMkYuIx1y8FYf3w7EInpmo36G',0,22),(4,4444,'Сергей','Сергеевич','Сергеев','$2a$10$7lrLKgH718Ig4m9wMabAMOsrEOD2WmqFAwhcCgdpC26oEy2FYQXU.',0,24),(5,5555,'Юрий','Юрьевич','Юрьев','$2a$10$O61JmF9PoAefGvRQGs5bhO5F/0SqlERS8.ZldZGzI3VFuL5etTjCS',0,2),(6,6666,'Анна','Анновна','Аннова','$2a$10$7An8b2mU7QeDFn93mWlJVuM8qs2RsP1AR/p4YD.Y6a3LwjM854pUG',0,6),(7,7777,'Ольга','Ольговна','Ольгова','$2a$10$ebO6vH.ZR9SsBxqX9tU/a.LfXZStp5/bsOqYMRm94HX0WX/Noszw6',0,12),(8,8888,'Ким','Кимович','Кимов','$2a$10$6cXE.UUVaoxWUDAYWHIVTO5R40DI8y2ffyGPJhK1c6M4trIXCkebq',0,26),(9,9999,'Кус','Кусович','Кусов','$2a$10$/9zvmVvMDGIKhjiSFspc0.zK9hTq1TcXtDEUDvwhO9YPLaQ8Mc4OW',0,23),(10,1010,'Мус','Мусович','Мусов','$2a$10$kHm62t4Zs2YUriin0xdOg.JEvsrsKCKjb5YeiSte5gomUHM5yZzKS',0,25),(11,101,'Зус','Зусович','Зусов','$2a$10$s7FCgrlPd0NKkBFvF7Cgcuu/QulSITDXIbkILKpeqpF7oeep79ON2',0,42),(12,1212,'Гурус','Гурусович','Гурусов','$2a$10$tqRb6e5UtXzqq.0U1s2Gw.kOGBYY56/kBZGWDzIkfN9ilrfAgVCzW',0,43),(13,1313,'Жор','Жорович','Жоров','$2a$10$LwGgKu5hRQpRtfM/oEnk5ObCGehF/m9BeCInmigoGpl3Sqadsv4su',0,44),(14,1414,'Гор ','Горов ','Горович','$2a$10$DmDDM3DTdMFgAvuIboRCiedfdnoDbNzSF2I7Qfl1vJf8wrzvW3O.6',0,45),(15,1515,'Жих',' Хор',' Карович','$2a$10$bFGlkq/jX/QjUmI5A6gPiOEhCA4pTnU5ap/3pMxrGvk4hsTFuL5oq',0,7),(19,1616,'zuz','зуззи',NULL,'$2a$10$LUtNoQ1Gy5by3PFYY9Lxn.gGsQNygpMDKgtapLTOfeFPl/WrTJ/va',1,20),(24,1717,'ziz','',NULL,'$2a$10$YDe6HflAFsuYJHPMovhY1eKJO4aZGy38NAvelZu6ClHYTGa5SUYgK',1,20),(26,1818,'ziztt','зiззиtt',NULL,'$2a$10$1eOnSil0ZGEb/VrSuLw4f.WQTApsAMxzVoSpAVehKkB0zqQJUkOWa',1,20),(27,1919,'sesese','ghghgh',NULL,'$2a$10$eeUTLOZuhpiJZ3AWEr1ka.kwLmhZ738z0w.mnV/8C3YMbu00S9XOe',1,24);
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

-- Dump completed on 2021-12-03 15:32:19
