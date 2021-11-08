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
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `parent_id` bigint DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_id_tasker` bigint NOT NULL,
  `task_problem_id` bigint NOT NULL,
  `task_comment_id` varchar(45) DEFAULT NULL,
  `date_begin` datetime NOT NULL,
  `employee_id_executer` bigint DEFAULT NULL,
  `department_id_executer` bigint NOT NULL,
  `data_finish` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `status_exec` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_task_employee_tasker_idx` (`employee_id_tasker`),
  KEY `fk_task_employee_executer_idx` (`employee_id_executer`),
  KEY `fk_task_task_problem_idx` (`task_problem_id`),
  KEY `fk_task_task_parent_idx` (`parent_id`),
  KEY `fk_task_department_id_executer_idx` (`department_id_executer`),
  CONSTRAINT `fk_task_department_id_executer` FOREIGN KEY (`department_id_executer`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_task_employee_executer` FOREIGN KEY (`employee_id_executer`) REFERENCES `employee` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_task_employee_tasker` FOREIGN KEY (`employee_id_tasker`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_task_task_parent` FOREIGN KEY (`parent_id`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_task_task_problem` FOREIGN KEY (`task_problem_id`) REFERENCES `task_problem` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=294 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (NULL,275,11,4,NULL,'2021-08-12 12:04:49',8,12,'2021-09-16 14:51:39',1,NULL),(NULL,276,5,6,NULL,'2021-08-12 12:04:50',NULL,12,NULL,0,NULL),(NULL,277,15,5,NULL,'2021-08-12 12:04:52',7,12,NULL,0,NULL),(NULL,278,3,5,NULL,'2021-08-12 12:04:58',NULL,27,NULL,0,NULL),(NULL,279,6,14,NULL,'2021-08-12 12:04:59',NULL,29,NULL,0,NULL),(275,280,5,6,NULL,'2021-08-12 12:05:16',NULL,12,NULL,0,NULL),(NULL,290,5,6,NULL,'2021-11-03 12:06:23',NULL,12,NULL,0,NULL),(NULL,291,5,6,NULL,'2021-11-03 12:08:01',NULL,12,NULL,0,NULL),(275,292,5,6,NULL,'2021-11-03 12:08:08',NULL,12,NULL,0,NULL),(280,293,11,4,NULL,'2021-11-03 12:12:21',NULL,27,NULL,0,NULL);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `task_BEFORE_INSERT` BEFORE INSERT ON `task` FOR EACH ROW BEGIN
set new.date_begin=now();
-- set new.department_id_executer=find_dep_exec_by_empl_and_probl(new.employee_id_tasker,new.task_problem_id);
set new.department_id_executer=find_dep_exec_by_empl_and_probl(new.employee_id_tasker,new.task_problem_id);


set new.status=0;





END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `task_AFTER_INSERT` AFTER INSERT ON `task` FOR EACH ROW BEGIN
if(new.parent_id is not null ) then
insert into task_status_tasker values (new.id,0);
insert into task_status_executer values (new.id,0);
-- update `task_vsr`.`task_status_tasker` set `task_vsr`.`task_status_tasker`.tasker_status=2 where id=new.parent_id;
end if;


if(new.parent_id is  null ) then
insert into task_status_tasker values (new.id,0);
insert into task_status_executer values (new.id,0);
end if;

-- if(
-- department_id_executer != (select department_id from post_has_department where id = (select post_has_department_id from employee where id= new.employee_id_executer) )
-- 
-- or 
-- (
-- new.department_id_executer != (select department_id from post_has_department where id = (select post_has_department_id from employee where id= new.employee_id_executer) )
-- )
-- ) then
-- SIGNAL SQLSTATE '10000'
--         SET MESSAGE_TEXT = 'Исполнитель не является работникам подрозделения которому порученно выполнение данного задания';
-- END IF;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `task_BEFORE_UPDATE` BEFORE UPDATE ON `task` FOR EACH ROW BEGIN
declare dep_exec bigint;


if(old.employee_id_tasker<>new.employee_id_tasker) then
set dep_exec=find_dep_exec_by_empl_and_probl(new.employee_id_tasker,new.task_problem_id);
IF dep_exec!=old.department_id_executer THEN
    SIGNAL SQLSTATE '10000'
        SET MESSAGE_TEXT = 'Работник не являетя сотрудником подрозделения либо не может доть заявку';
END IF;



 -- if(
 -- (old.employee_id_executer<>new.employee_id_executer) and 
 -- new.department_id_executer != (select department_id from post_has_department where id = (select post_has_department_id from employee where id= new.employee_id_executer) )
 -- ) then
 -- SIGNAL SQLSTATE '10000'
 --         SET MESSAGE_TEXT = 'Исполнитель не является работникам подрозделения которому порученно выполнение данного задания';
 -- END IF;


end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `task_AFTER_UPDATE` AFTER UPDATE ON `task` FOR EACH ROW BEGIN

 if(
 (
 (old.employee_id_executer<>new.employee_id_executer) and 
 new.department_id_executer != (select department_id from post_has_department where id = (select post_has_department_id from employee where id= new.employee_id_executer) )
 ) or 
 (
 new.department_id_executer != (select department_id from post_has_department where id = (select post_has_department_id from employee where id= new.employee_id_executer) )
 )
 ) then
 SIGNAL SQLSTATE '10000'
         SET MESSAGE_TEXT = 'Исполнитель не является работникам подрозделения которому порученно выполнение данного задания';
 END IF;



if (old.department_id_executer<>new.department_id_executer && old.status=0) then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=old.department_id_executer
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted-1,total=total-1 where id in (select sub_id from cte_dep_id);
    with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=new.department_id_executer
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted+1,total=total+1 where id in (select sub_id from cte_dep_id);
    
end if;

if (old.department_id_executer<>new.department_id_executer && old.status=1) then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=old.department_id_executer
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed-1,total=total-1 where id in (select sub_id from cte_dep_id);
    with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=new.department_id_executer
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed+1, total=total+1 where id in (select sub_id from cte_dep_id);
    
end if;

if (old.department_id_executer<>new.department_id_executer && old.status=2) then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=old.department_id_executer
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped-1,total=total-1 where id in (select sub_id from cte_dep_id);
    with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=new.department_id_executer
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped+1,total=total+1 where id in (select sub_id from cte_dep_id);
    
end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `task_BEFORE_DELETE` BEFORE DELETE ON `task` FOR EACH ROW BEGIN

call imp_del_task_child_from_statistic_by_kursor(old.id);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `task_AFTER_DELETE` AFTER DELETE ON `task` FOR EACH ROW BEGIN

call imp_del_statistic(old.id,old.department_id_executer,old.status);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-08 14:24:04
