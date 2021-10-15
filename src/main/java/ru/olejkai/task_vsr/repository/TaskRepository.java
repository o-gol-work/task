package ru.olejkai.task_vsr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.search.TaskSearchValues;

import java.sql.Timestamp;
import java.util.Collection;

public interface TaskRepository extends JpaRepository< TaskEntity,Long> {

    final String queryAllChildren="WITH RECURSIVE r (parent_id, id, employee_id_tasker, task_problem_id,  date_begin,employee_id_executer,department_id_executer,data_finish,status,status_exec) AS\n" +
            "                  (SELECT parent_id, id, employee_id_tasker, task_problem_id,  date_begin,employee_id_executer,department_id_executer,data_finish,status,status_exec\n" +
            "                  FROM task\n" +
            "                   WHERE parent_id = :id\n" +
            "                   UNION ALL\n" +
            "                   SELECT t.parent_id, t.id, t.employee_id_tasker, t.task_problem_id,  t.date_begin,t.employee_id_executer,t.department_id_executer,t.data_finish,t.status,t.status_exec\n" +
            "                   FROM r INNER JOIN task t\n" +
            "                   ON r.id = t.parent_id)\n" +
            "                   SELECT * FROM r";

    final String queryAllParent ="WITH RECURSIVE r(parent_id, id, employee_id_tasker, task_problem_id,  date_begin,employee_id_executer,department_id_executer,data_finish,status,status_exec) AS\n" +
            "                    (SELECT tr.parent_id, tr.id, tr.employee_id_tasker, tr.task_problem_id,  tr.date_begin,tr.employee_id_executer,tr.department_id_executer,tr.data_finish,tr.status,tr.status_exec\n" +
            "                   FROM task tl\n" +
            "                   LEFT JOIN task tr \n" +
            "                   ON tl.parent_id = tr.id\n" +
            "                    WHERE tl.id = :id\n" +
            "                     UNION ALL\n" +
            "                     SELECT t.parent_id, t.id, t.employee_id_tasker, t.task_problem_id,  t.date_begin,t.employee_id_executer,t.department_id_executer,t.data_finish,t.status,t.status_exec\n" +
            "                     FROM task t, r\n" +
            "                     WHERE t.id = r.parent_id )\n" +
            "                     SELECT parent_id, id, employee_id_tasker, task_problem_id,  date_begin,employee_id_executer,department_id_executer,data_finish,status,status_exec\n" +
            "                    FROM r";

    TaskEntity getTaskEntityById(Long id);
    TaskEntity findTaskEntityById(Long id);



    @Query(value = queryAllChildren ,nativeQuery = true)
    Collection<TaskEntity> getAllChildren(Long id);

    @Query(value = queryAllParent ,nativeQuery = true)
    Collection<TaskEntity>  getAllParent(Long id);


    @Query("SELECT t FROM TaskEntity t \n" +
            "where\n" +
            "(:employeeIdTasker is null or :employeeIdTasker ='' or lower(t.employeeByEmployeeIdTasker.surname) like lower(concat('%',:employeeIdTasker,'%') ) and t.parentId is null) " +
            /*"and\n " +
            "(:taskProblemId is null or :taskProblemId ='' or lower(t.taskProblemByTaskProblemId.title) like lower(concat('%',:taskProblemId,'%') ) ) and\n " +
            "(:dateBegin is null or :dateBegin>=t.dateBegin) and\n" +
            "(:employeeIdExecuter is null or :employeeIdExecuter ='' or lower(t.employeeByEmployeeIdExecuter.surname) like lower(concat('%',:employeeIdExecuter,'%') ) ) and\n " +
            "(:departmentIdExecuter is null or :departmentIdExecuter ='' or lower(t.departmentByDepartmentIdExecuter.title) like lower(concat('%',:departmentIdExecuter,'%') ) ) and\n " +
            "(:dataFinish is null or :dataFinish>=t.dataFinish) and\n" +
            "(:status is null or :status=t.status)" +
            "order by t.dateBegin asc" +*/
            "")
    Collection<TaskEntity> findTaskEntitiesByParam(@Param("employeeIdTasker")String employeeIdTasker
            /*,@Param("taskProblemId")String taskProblemId
            ,@Param("dateBegin")Timestamp dateBegin
            ,@Param("employeeIdExecuter")String employeeIdExecuter
            ,@Param("departmentIdExecuter")String departmentIdExecuter
            ,@Param("dataFinish")Timestamp dataFinish
            ,@Param("status")Integer status*/
                                                         );



}
