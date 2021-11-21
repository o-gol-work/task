CREATE DATABASE  IF NOT EXISTS `task_vsr` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `task_vsr`;
-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: task_vsr
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.20.04.1

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
-- Table structure for table `task_status_tasker`
--

DROP TABLE IF EXISTS `task_status_tasker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_status_tasker` (
  `id` bigint NOT NULL,
  `tasker_status` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `fk_task_task_status` FOREIGN KEY (`id`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_status_tasker`
--

LOCK TABLES `task_status_tasker` WRITE;
/*!40000 ALTER TABLE `task_status_tasker` DISABLE KEYS */;
INSERT INTO `task_status_tasker` VALUES (275,1),(276,0),(277,0),(278,0),(279,0),(280,0),(290,0),(291,0),(292,0),(293,0);
/*!40000 ALTER TABLE `task_status_tasker` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `task_status_tasker_AFTER_INSERT` AFTER INSERT ON `task_status_tasker` FOR EACH ROW BEGIN
-- update task set task.status=new.tasker_status where id=new.id;
if(ifnull(new.tasker_status,0)=0) then 
	
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted+1 where id in (select sub_id from cte_dep_id);
end if;

/*если добавилось задание со статусом "выполненно", обновляем соответствующее поле в таблице department_task_stat*/
if(ifnull(new.tasker_status,0)=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id))) 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed+1 where id in (select sub_id from cte_dep_id);
end if;

/*если добавилось задание со статусом "остановленно", обновляем соответствующее поле в таблице department_task_stat*/
if(ifnull(new.tasker_status,0)=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped+1 where id in (select sub_id from cte_dep_id);
end if;

/*обновляем общее количество заданий по депортаментам в таблице department_task_stat  , после добавления нового задание в task */
if(ifnull(new.tasker_status,0)=0) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set total=total+1 where id in (select sub_id from cte_dep_id);
elseif (ifnull(new.tasker_status,0)=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id))) 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set total=total+1 where id in (select sub_id from cte_dep_id);
elseif (ifnull(new.tasker_status,0)=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set total=total+1 where id in (select sub_id from cte_dep_id);
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `task_status_tasker_AFTER_UPDATE` AFTER UPDATE ON `task_status_tasker` FOR EACH ROW BEGIN
IF New.tasker_status>2 THEN
    SIGNAL SQLSTATE '10000'
        SET MESSAGE_TEXT = 'check constraint on table failed during update';
END IF;


if(new.tasker_status<>old.tasker_status) then
update task set task.status=new.tasker_status where id=new.id;
end if;

if(
(ifnull(old.tasker_status,0)<>ifnull(new.tasker_status,0)) && 
(old.tasker_status=0 && new.tasker_status=1)
) 
then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted-1, completed=completed+1 where id in (select sub_id from cte_dep_id);
    update task set data_finish=now() where id=new.id;
    
    /*if (
    (select parent_id from task where id=new.id) is not null && 
    (select tasker_status from task_status_tasker where id=(select parent_id from task where id=new.id))!=1 
    ) 
    then
		update `task_vsr`.`task_status_tasker` set `task_vsr`.`task_status_tasker`.tasker_status=0 where id=(select parent_id from task where id=new.id);
	end if;*/
    
end if;


if(
(ifnull(old.tasker_status,0)<>ifnull(new.tasker_status,0)) && 
(old.tasker_status=0 && new.tasker_status=2)
) 
then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted-1, stoped=stoped+1 where id in (select sub_id from cte_dep_id);
    
end if;


if(
(ifnull(old.tasker_status,0)<>ifnull(new.tasker_status,0)) && 
(old.tasker_status=1 && new.tasker_status=0)
) 
then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed-1, uncompleted=uncompleted+1 where id in (select sub_id from cte_dep_id);
    update task set data_finish=null where id=new.id;
    
    /*if ((select parent_id from task where id=new.id) is not null &&
    (select tasker_status from task_status_tasker where id=(select parent_id from task where id=new.id))!=1 
    )
    then
		update `task_vsr`.`task_status_tasker` set `task_vsr`.`task_status_tasker`.tasker_status=2 where id=(select parent_id from task where id=new.id);
	end if;*/
    
end if;



if(
(ifnull(old.tasker_status,0)<>ifnull(new.tasker_status,0)) && 
(old.tasker_status=1 && new.tasker_status=2)
) 
then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed-1, stoped=stoped+1 where id in (select sub_id from cte_dep_id);
    update task set data_finish=null where id=new.id;
end if;


if(
(ifnull(old.tasker_status,0)<>ifnull(new.tasker_status,0)) && 
(old.tasker_status=2 && new.tasker_status=0)
) 
then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped-1, uncompleted=uncompleted+1 where id in (select sub_id from cte_dep_id);
    
end if;

if(
(ifnull(old.tasker_status,0)<>ifnull(new.tasker_status,0)) && 
(old.tasker_status=2 && new.tasker_status=1)
) 
then
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(
	select title, id, parent_id from department 
	where id=(select find_dep_exec_by_empl_and_probl(
    (select task.employee_id_tasker from task where task.id=new.id),
    (select task.task_problem_id from task where task.id=new.id)))
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped-1, completed=completed+1 where id in (select sub_id from cte_dep_id);
    update task set data_finish=now() where id=new.id;
    
    if ((select parent_id from task where id=new.id) is not null &&
    (select tasker_status from task_status_tasker where id=(select parent_id from task where id=new.id))!=1 
    )
    then
		update `task_vsr`.`task_status_tasker` set `task_vsr`.`task_status_tasker`.tasker_status=0 where id=(select parent_id from task where id=new.id);
	end if;
    
end if;


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

-- Dump completed on 2021-11-21 21:34:43
