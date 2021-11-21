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
-- Dumping events for database 'task_vsr'
--

--
-- Dumping routines for database 'task_vsr'
--
/*!50003 DROP FUNCTION IF EXISTS `find_dep_exec_by_empl_and_probl` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `find_dep_exec_by_empl_and_probl`(employee_id bigint, task_problem_id bigint) RETURNS bigint
BEGIN
declare dep_id bigint;
set dep_id=
(select department_tasker_to_departments_executers.departments_executers_ids from department_tasker_to_departments_executers 
    where department_tasker_id=(select department_id from post_has_department where id=
    (select post_has_department_id from employee where id=employee_id))
    and department_tasker_to_departments_executers.departments_executers_ids in
    (select department_id from department_problem
    where problem_id=task_problem_id));
RETURN dep_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `all_child_element` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `all_child_element`(in table_named varchar(50), id_parent bigint)
BEGIN
with recursive tas (num,tas_id) as
(
	select parent_id,id from table_named where parent_id=id_parent
    union all
    select ifnull(num,0)+1,d.id
    from table_named d, tas dp
    where d.parent_id=dp.tas_id
    
) 
delete from table_named where id in (select tas_id from tas order by num desc) and parent_id>0;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `all_child_element_delete` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `all_child_element_delete`(in table_named varchar(40), id_parent bigint)
BEGIN
SET @stmt = concat(
"with recursive tas (num,tas_id) as
(
	select parent_id,id from ",table_named," where parent_id=",id_parent,
    " union all
    select ifnull(num,0)+1,d.id
    from ",table_named," d, tas dp
    where d.parent_id=dp.tas_id
    
) 
delete from ",table_named," where id in (select tas_id from tas order by num desc) and parent_id>0");
PREPARE stmt FROM @stmt;
EXECUTE STMT ;
DEALLOCATE PREPARE stmt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `del_task` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `del_task`(task_this_id bigint)
BEGIN
declare count bigint;
declare max_id bigint;
declare dep_id_decl bigint;
declare task_status_decl int;
declare id_this bigint;
SET SQL_SAFE_UPDATES=0;
DELETE FROM child_id_task_stat_tmp where true ;
SET SQL_SAFE_UPDATES=1;

-- TRUNCATE  table  child_id_task_stat_tmp;
insert into child_id_task_stat_tmp(dep_id_exec,tasker_status)
with recursive tas (tas_id,dep_id_exec) as
(
	select id,department_id_executer from task_vsr.task where parent_id=task_this_id
    union all
    select d.id,d.department_id_executer
    from task d, tas dp
    where d.parent_id=dp.tas_id
    
)
select t.dep_id_exec, ts.tasker_status from tas t inner join task_status_tasker ts on t.tas_id=ts.id;

select * from child_id_task_stat_tmp;
-- select count(*) from child_id_task_stat_tmp;
/*
set count = (select count(*) from child_id_task_stat_tmp);
set max_id =(select max(id) as max_id from child_id_task_stat_tmp);
if (count>0) then
	while count>0 do
    set dep_id_decl=(select dep_id_exec from child_id_task_stat_tmp where id=max_id);
    set task_status_decl=(select tasker_status from child_id_task_stat_tmp where id=max_id);
    call update_statistic_after_task_delete(dep_id_decl,task_status_decl);
    set max_id=max_id-1;
	set count=count-1;
    end while;
    
end if;*/
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `del_task_kursor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `del_task_kursor`(task_this_id bigint)
BEGIN
declare count bigint;
declare max_id bigint;
declare dep_id_decl bigint;
declare task_status_decl int;
declare id_this bigint;
 Declare done integer default 0;
  Declare task_kursure Cursor for Select dep_id_exec, tasker_status from child_id_task_stat_tmp ;
 DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done=1;

SET SQL_SAFE_UPDATES=0;
DELETE FROM child_id_task_stat_tmp ;
SET SQL_SAFE_UPDATES=1;

-- TRUNCATE  table  child_id_task_stat_tmp;
insert into child_id_task_stat_tmp(dep_id_exec,tasker_status)
with recursive tas (num,tas_id,dep_id_exec) as
(
	select parent_id,id,department_id_executer from task_vsr.task where parent_id=task_this_id
    union all
    select ifnull(num,0)+1,d.id,d.department_id_executer
    from task d, tas dp
    where d.parent_id=dp.tas_id
    
)
select t.dep_id_exec, ts.tasker_status from tas t inner join task_status_tasker ts on t.tas_id=ts.id;

-- select * from child_id_task_stat_tmp;
-- select count(*) from child_id_task_stat_tmp;





Open task_kursure;
FETCH task_kursure INTO dep_id_decl,task_status_decl;
WHILE done = 0 DO 
call update_statistic_after_task_delete(dep_id_decl,task_status_decl);
-- insert into child_id_task_stat_tmp(dep_id_exec,tasker_status) values(dep_id_decl,task_status_decl);
FETCH task_kursure INTO dep_id_decl,task_status_decl;
END WHILE;
Close task_kursure;
  -- select * from child_id_task_stat_tmp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `imp_del_statistic` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `imp_del_statistic`(task_id bigint, dep_id bigint,task_status int)
BEGIN
/*
if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=0) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted-1 where id in (select sub_id from cte_dep_id);
end if;

if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed-1 where id in (select sub_id from cte_dep_id);
end if;

if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped-1 where id in (select sub_id from cte_dep_id);
end if;
*/
if(ifnull(task_status,0)=0) then 

	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set uncompleted=uncompleted-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
elseif (ifnull(task_status,0)=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set completed=completed-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
elseif (ifnull(task_status,0)=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set stoped=stoped-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `imp_del_task_child_from_statistic_by_kursor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `imp_del_task_child_from_statistic_by_kursor`(task_this_id bigint)
BEGIN
declare count bigint;
declare max_id bigint;
declare dep_id_decl bigint;
declare task_status_decl int;
declare id_this bigint;
 Declare done integer default 0;
  Declare task_kursure Cursor for Select dep_id_exec, tasker_status from child_id_task_stat_tmp ;
 DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done=1;

SET SQL_SAFE_UPDATES=0;
DELETE FROM child_id_task_stat_tmp ;
SET SQL_SAFE_UPDATES=1;

-- TRUNCATE  table  child_id_task_stat_tmp;
insert into child_id_task_stat_tmp(dep_id_exec,tasker_status)
with recursive tas (num,tas_id,dep_id_exec) as
(
	select parent_id,id,department_id_executer from task_vsr.task where parent_id=task_this_id
    union all
    select ifnull(num,0)+1,d.id,d.department_id_executer
    from task d, tas dp
    where d.parent_id=dp.tas_id
    
)
select t.dep_id_exec, ts.tasker_status from tas t inner join task_status_tasker ts on t.tas_id=ts.id;

-- select * from child_id_task_stat_tmp;
-- select count(*) from child_id_task_stat_tmp;





Open task_kursure;
FETCH task_kursure INTO dep_id_decl,task_status_decl;
WHILE done = 0 DO 
call update_statistic_after_task_delete(dep_id_decl,task_status_decl);
-- insert into child_id_task_stat_tmp(dep_id_exec,tasker_status) values(dep_id_decl,task_status_decl);
FETCH task_kursure INTO dep_id_decl,task_status_decl;
END WHILE;
Close task_kursure;
  -- select * from child_id_task_stat_tmp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `test_del_statistic_stoped` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `test_del_statistic_stoped`(task_id bigint, dep_id bigint,task_status int)
BEGIN
/*
if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=0) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted-1 where id in (select sub_id from cte_dep_id);
end if;

if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed-1 where id in (select sub_id from cte_dep_id);
end if;

if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped-1 where id in (select sub_id from cte_dep_id);
end if;
*/
if(ifnull(task_status,0)=0) then 

	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set uncompleted=uncompleted-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
elseif (ifnull(task_status,0)=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set completed=completed-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
elseif (ifnull(task_status,0)=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set stoped=stoped-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `test_del_statistic_stoped_v2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `test_del_statistic_stoped_v2`(task_id bigint, dep_id bigint)
BEGIN
/*
if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=0) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted-1 where id in (select sub_id from cte_dep_id);
end if;

if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed-1 where id in (select sub_id from cte_dep_id);
end if;

if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped-1 where id in (select sub_id from cte_dep_id);
end if;
*/
declare idid bigint;
declare stat int;
set idid=221;
set stat=(select tasker_status from task_status_tasker where id=221);
IF task_id is null THEN
    SIGNAL SQLSTATE '10000'
        SET MESSAGE_TEXT = 'null';
elseif task_id>0 then
SIGNAL SQLSTATE '10000'
        SET MESSAGE_TEXT = stat;
END IF;

IF (select tasker_status from task_status_tasker where id=task_id)=0 THEN
    SIGNAL SQLSTATE '10000'
        SET MESSAGE_TEXT = '0';
elseif (select tasker_status from task_status_tasker where id=task_id)=1 then
SIGNAL SQLSTATE '10000'
        SET MESSAGE_TEXT = '1';
elseif (select tasker_status from task_status_tasker where id=task_id)=2 then
SIGNAL SQLSTATE '10000'
        SET MESSAGE_TEXT = '2';
elseif (select tasker_status from task_status_tasker where id=task_id)is null then
SIGNAL SQLSTATE '10000'
        SET MESSAGE_TEXT = 'null';
END IF;




/*
if(ifnull((select tasker_status from task_status_tasker where id=task_id),0)=0) then 




	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set uncompleted=uncompleted-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
elseif (ifnull((select tasker_status from task_status_tasker where id=task_id),0)=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set completed=completed-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
elseif (ifnull((select tasker_status from task_status_tasker where id=task_id),0)=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
    update department_task_stat set stoped=stoped-1,total=total-1 where id in (select sub_id from cte_dep_id);
	-- update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
end if;
*/
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_statistic_after_task_delete` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_statistic_after_task_delete`(dep_id bigint, tas_status int)
BEGIN
	if(tas_status=0) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set uncompleted=uncompleted-1 where id in (select sub_id from cte_dep_id);
end if;

/*если добавилось задание со статусом "выполненно", обновляем соответствующее поле в таблице department_task_stat*/
if(tas_status=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set completed=completed-1 where id in (select sub_id from cte_dep_id);
end if;

/*если добавилось задание со статусом "остановленно", обновляем соответствующее поле в таблице department_task_stat*/
if(tas_status=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set stoped=stoped-1 where id in (select sub_id from cte_dep_id);
end if;

/*обновляем общее количество заданий по депортаментам в таблице department_task_stat  , после добавления нового задание в task */
if(tas_status=0) then 

	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
elseif (tas_status=1) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
elseif (tas_status=2) then 
	with recursive cte_dep_id(sub_title,sub_id,sud_par_id) as(

	select title, id, parent_id from department 
	where id=dep_id 
	union all
	select e.title , e.id, e.parent_id
	from department e,cte_dep_id c 
	where e.id=c.sud_par_id
	)
	update department_task_stat set total=total-1 where id in (select sub_id from cte_dep_id);
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_status_parent_task` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_status_parent_task`(parent_id bigint )
BEGIN
update task set status=2 where id=parent_id;
END ;;
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
