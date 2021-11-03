package ru.olejkai.task_vsr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.data.repository.query.Param;
import ru.olejkai.task_vsr.dto.TaskDto;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.search.TaskSearchValues;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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






    @Query("SELECT " +
//            "new TaskEntity (" +
//            "t" +
//            ---------------------------------------------------------------------------------------------------------
//            "t.parentId" +
//            ",t.id" +
//            ",t.employeeIdTasker" +
//            ",t.employeeByEmployeeIdTasker" +
//            ",t.taskProblemId" +
//            ",t.taskProblemByTaskProblemId" +
//            ",t.dateBegin" +
//            ",t.employeeIdExecuter" +
//            ",t.employeeByEmployeeIdExecuter" +
//            ",t.departmentIdExecuter" +
//            ",t.departmentByDepartmentIdExecuter" +
//            ",t.dataFinish" +
//            ",t.status" +
//            ---------------------------------------------------------------------------------------------------------

            "t.id" +
            ",t.dataFinish" +
            ",t.dateBegin" +
//            ",t.departmentIdExecuter" +
//            ",t.employeeIdExecuter" +
//            ",t.employeeIdTasker" +
            ",t.parentId" +
            ",t.status" +
//            ",t.taskProblemId" +
            ",d.id" +
//            ",d.parentId \n" +
//            ",d.telephoneNumber\n" +
            ",d.title ," +
            "e.id,\n" +
            "e.name ,\n" +
//            "e.password ,\n" +
//            "e.postHasDepartmentId ,\n" +
            "e.surname ,\n" +
            "e.tabelNumber ,\n" +
//            "e.telephoneNumber ,\n" +
//            "e.worked, " +
//            "erool.employeeId ,\n" +
//            "erool.id ,\n" +
//            "erool.id ,\n" +
//            "erool.employeeId ,\n" +
//            "erool.role," +
//            "phd.id ,\n" +
//            "phd.departmentId ,\n" +
//            "phd.postId ," +
            "ee.id,\n" +
            "ee.name ,\n" +
//            "ee.password ,\n" +
//            "ee.postHasDepartmentId ,\n" +
            "ee.surname ,\n" +
            "ee.tabelNumber ,\n" +
//            "ee.telephoneNumber ,\n" +
//            "ee.worked, " +
//            "eerool.employeeId ,\n" +
//            "eerool.id ,\n" +
//            "eerool.id ,\n" +
//            "eerool.employeeId ,\n" +
//            "eerool.role," +
//            "phde.id ,\n" +
//            "phde.departmentId ,\n" +
//            "phde.postId ," +
            "tp.id ,\n" +
//            "tp.parentId ,\n" +
            "tp.title " +
//            ")" +




            " FROM TaskEntity t " +
//            ---------------------------------------------------------------------------------------------------------
            " left outer join DepartmentEntity  d on t.departmentIdExecuter=d.id\n " +


            "left outer join  EmployeeEntity e on (t.employeeIdExecuter=e.id)\n" +
//            "left outer join  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +

            "left outer join  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "left outer join  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +

            " left outer join TaskProblemEntity tp on tp.id=t.taskProblemId\n" +





//            "left outer join  PostEntity er on (er.id=phd.postId)" +
//            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +

//            "left outer join  PostEntity ere on (ere.id=phde.postId)" +
//            "left outer join  DepartmentEntity dee on (dee.id=phde.departmentId)" +


//            ---------------------------------------------------------------------------------------------------------


//            "join fetch EmployeeEntity e on (e.id=t.employeeIdExecuter)\n" +
//            "join fetch  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
//            "join fetch  PostEntity er on (er.id=phd.postId)" +
//            "join fetch  DepartmentEntity de on (de.id=phd.departmentId)" +
//
//            "join fetch  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "join fetch  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +
//            "join fetch  PostEntity ere on (ere.id=phde.postId)" +
//            "join fetch  DepartmentEntity dee on (dee.id=phde.departmentId)" +
//
//
//            "join fetch TaskProblemEntity tp on tp.id=t.taskProblemId\n" +
//            "join fetch DepartmentEntity  d on d.id=t.departmentIdExecuter\n " +

            "where t.id=:aLong" +
            "" )
//            "order by t.dateBegin asc")
    public Optional<TaskDto> findTaskDto(@Param("aLong") Long aLong);


    @Override
    @Query("SELECT " +
            "new TaskEntity (" +


            "t.id" +
            ",t.dataFinish" +
            ",t.dateBegin" +
            ",t.parentId" +
            ",t.status" +

            ",d.parentId" +
            ",d.id" +
            ",d.title " +
            ",d.telephoneNumber" +

            ",e.id,\n" +
            "e.name ,\n" +
            "e.surname ,\n" +
            "e.tabelNumber ,\n" +
            "e.telephoneNumber,\n" +
            "e.worked,\n" +
            "phd.id,\n" +
            "er.id,\n" +
            "er.title," +
            "er.parentId\n" +
            ",de.parentId" +
            ",de.id" +
            ",de.title " +
            ",de.telephoneNumber," +

            "ee.id,\n" +
            "ee.name ,\n" +
            "ee.surname ,\n" +
            "ee.tabelNumber ,\n" +
            "ee.telephoneNumber,\n" +
            "ee.worked,\n" +
            "phde.id,\n" +
            "ere.id,\n" +
            "ere.title," +
            "ere.parentId\n" +
            ",dee.parentId" +
            ",dee.id" +
            ",dee.title " +
            ",dee.telephoneNumber," +

            "tp.id ,\n" +
            "tp.title " +
            ")" +







            " FROM TaskEntity t " +
//            ---------------------------------------------------------------------------------------------------------
            " left outer join DepartmentEntity  d on t.departmentIdExecuter=d.id\n " +


            "left outer join  EmployeeEntity e on (t.employeeIdExecuter=e.id)\n" +
//            "left outer join  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
            "left outer join  PostEntity er on (er.id=phd.postId)" +
            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +

            "left outer join  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "left outer join  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +
            "left outer join  PostEntity ere on (ere.id=phde.postId)" +
            "left outer join  DepartmentEntity dee on (dee.id=phde.departmentId)" +

            " left outer join TaskProblemEntity tp on tp.id=t.taskProblemId\n" +





//            "left outer join  PostEntity er on (er.id=phd.postId)" +
//            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +

//            "left outer join  PostEntity ere on (ere.id=phde.postId)" +
//            "left outer join  DepartmentEntity dee on (dee.id=phde.departmentId)" +


//            ---------------------------------------------------------------------------------------------------------


//            "join fetch EmployeeEntity e on (e.id=t.employeeIdExecuter)\n" +
//            "join fetch  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
//            "join fetch  PostEntity er on (er.id=phd.postId)" +
//            "join fetch  DepartmentEntity de on (de.id=phd.departmentId)" +
//
//            "join fetch  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "join fetch  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +
//            "join fetch  PostEntity ere on (ere.id=phde.postId)" +
//            "join fetch  DepartmentEntity dee on (dee.id=phde.departmentId)" +
//
//
//            "join fetch TaskProblemEntity tp on tp.id=t.taskProblemId\n" +
//            "join fetch DepartmentEntity  d on d.id=t.departmentIdExecuter\n " +


            "" )
    List<TaskEntity> findAll();







    /*@Override
    @Query("SELECT " +
            "new TaskEntity (" +
//            "t" +
//            ---------------------------------------------------------------------------------------------------------
//            "t.parentId" +
//            ",t.id" +
//            ",t.employeeIdTasker" +
//            ",t.employeeByEmployeeIdTasker" +
//            ",t.taskProblemId" +
//            ",t.taskProblemByTaskProblemId" +
//            ",t.dateBegin" +
//            ",t.employeeIdExecuter" +
//            ",t.employeeByEmployeeIdExecuter" +
//            ",t.departmentIdExecuter" +
//            ",t.departmentByDepartmentIdExecuter" +
//            ",t.dataFinish" +
//            ",t.status" +
//            ---------------------------------------------------------------------------------------------------------

            "t.id" +
            ",t.dataFinish" +
            ",t.dateBegin" +
//            ",t.departmentIdExecuter" +
//            ",t.employeeIdExecuter" +
//            ",t.employeeIdTasker" +
            ",t.parentId" +
            ",t.status" +
//            ",t.taskProblemId" +
            ",d.id" +
//            ",d.parentId \n" +
//            ",d.telephoneNumber\n" +
            ",d.title ," +
            "e.id,\n" +
            "e.name ,\n" +
//            "e.password ,\n" +
//            "e.postHasDepartmentId ,\n" +
            "e.surname ,\n" +
            "e.tabelNumber ,\n" +
//            "e.telephoneNumber ,\n" +
//            "e.worked, " +
//            "erool.employeeId ,\n" +
//            "erool.id ,\n" +
//            "erool.id ,\n" +
//            "erool.employeeId ,\n" +
//            "erool.role," +
//            "phd.id ,\n" +
//            "phd.departmentId ,\n" +
//            "phd.postId ," +
            "ee.id,\n" +
            "ee.name ,\n" +
//            "ee.password ,\n" +
//            "ee.postHasDepartmentId ,\n" +
            "ee.surname ,\n" +
            "ee.tabelNumber ,\n" +
//            "ee.telephoneNumber ,\n" +
//            "ee.worked, " +
//            "eerool.employeeId ,\n" +
//            "eerool.id ,\n" +
//            "eerool.id ,\n" +
//            "eerool.employeeId ,\n" +
//            "eerool.role," +
//            "phde.id ,\n" +
//            "phde.departmentId ,\n" +
//            "phde.postId ," +
            "tp.id ,\n" +
//            "tp.parentId ,\n" +
            "tp.title " +
            ")" +




            " FROM TaskEntity t " +
//            ---------------------------------------------------------------------------------------------------------
            " left outer join DepartmentEntity  d on t.departmentIdExecuter=d.id\n " +


            "left outer join  EmployeeEntity e on (t.employeeIdExecuter=e.id)\n" +
//            "left outer join  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +

            "left outer join  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "left outer join  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +

            " left outer join TaskProblemEntity tp on tp.id=t.taskProblemId\n" +





//            "left outer join  PostEntity er on (er.id=phd.postId)" +
//            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +

//            "left outer join  PostEntity ere on (ere.id=phde.postId)" +
//            "left outer join  DepartmentEntity dee on (dee.id=phde.departmentId)" +


//            ---------------------------------------------------------------------------------------------------------


//            "join fetch EmployeeEntity e on (e.id=t.employeeIdExecuter)\n" +
//            "join fetch  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
//            "join fetch  PostEntity er on (er.id=phd.postId)" +
//            "join fetch  DepartmentEntity de on (de.id=phd.departmentId)" +
//
//            "join fetch  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "join fetch  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +
//            "join fetch  PostEntity ere on (ere.id=phde.postId)" +
//            "join fetch  DepartmentEntity dee on (dee.id=phde.departmentId)" +
//
//
//            "join fetch TaskProblemEntity tp on tp.id=t.taskProblemId\n" +
//            "join fetch DepartmentEntity  d on d.id=t.departmentIdExecuter\n " +

            "where t.id=:aLong" +
            "" )
//            "order by t.dateBegin asc")
    Optional<TaskEntity> findById(@Param("aLong") Long aLong);*/











    @Override
    @Query("SELECT " +
            "new TaskEntity (" +


            "t.id" +
            ",t.dataFinish" +
            ",t.dateBegin" +
            ",t.parentId" +
            ",t.status" +

            ",d.parentId" +
            ",d.id" +
            ",d.title " +
            ",d.telephoneNumber" +

            ",e.id,\n" +
            "e.name ,\n" +
            "e.surname ,\n" +
            "e.tabelNumber ,\n" +
            "e.telephoneNumber,\n" +
            "e.worked,\n" +
            "phd.id,\n" +
            "er.id,\n" +
            "er.title," +
            "er.parentId\n" +
            ",de.parentId" +
            ",de.id" +
            ",de.title " +
            ",de.telephoneNumber," +

            "ee.id,\n" +
            "ee.name ,\n" +
            "ee.surname ,\n" +
            "ee.tabelNumber ,\n" +
            "ee.telephoneNumber,\n" +
            "ee.worked,\n" +
            "phde.id,\n" +
            "ere.id,\n" +
            "ere.title," +
            "ere.parentId\n" +
            ",dee.parentId" +
            ",dee.id" +
            ",dee.title " +
            ",dee.telephoneNumber," +

            "tp.id ,\n" +
            "tp.title " +
            ")" +







            " FROM TaskEntity t " +
//            ---------------------------------------------------------------------------------------------------------
            " left outer join DepartmentEntity  d on t.departmentIdExecuter=d.id\n " +


            "left outer join  EmployeeEntity e on (t.employeeIdExecuter=e.id)\n" +
//            "left outer join  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
            "left outer join  PostEntity er on (er.id=phd.postId)" +
            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +

            "left outer join  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "left outer join  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +
            "left outer join  PostEntity ere on (ere.id=phde.postId)" +
            "left outer join  DepartmentEntity dee on (dee.id=phde.departmentId)" +

            " left outer join TaskProblemEntity tp on tp.id=t.taskProblemId\n" +





//            "left outer join  PostEntity er on (er.id=phd.postId)" +
//            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +

//            "left outer join  PostEntity ere on (ere.id=phde.postId)" +
//            "left outer join  DepartmentEntity dee on (dee.id=phde.departmentId)" +


//            ---------------------------------------------------------------------------------------------------------


//            "join fetch EmployeeEntity e on (e.id=t.employeeIdExecuter)\n" +
//            "join fetch  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
//            "join fetch  PostEntity er on (er.id=phd.postId)" +
//            "join fetch  DepartmentEntity de on (de.id=phd.departmentId)" +
//
//            "join fetch  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "join fetch  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +
//            "join fetch  PostEntity ere on (ere.id=phde.postId)" +
//            "join fetch  DepartmentEntity dee on (dee.id=phde.departmentId)" +
//
//
//            "join fetch TaskProblemEntity tp on tp.id=t.taskProblemId\n" +
//            "join fetch DepartmentEntity  d on d.id=t.departmentIdExecuter\n " +

            "where t.id=:aLong" +
            "" )
//            "order by t.dateBegin asc")
    Optional<TaskEntity> findById(@Param("aLong") Long aLong);




    @Query("SELECT " +
            "new TaskEntity (" +


            "t.id" +
            ",t.dataFinish" +
            ",t.dateBegin" +
            ",t.parentId" +
            ",t.status" +

            ",d.parentId" +
            ",d.id" +
            ",d.title " +
            ",d.telephoneNumber" +

            ",e.id,\n" +
            "e.name ,\n" +
            "e.surname ,\n" +
            "e.tabelNumber ,\n" +
            "e.telephoneNumber,\n" +
            "e.worked,\n" +
            "phd.id,\n" +
            "er.id,\n" +
            "er.title," +
            "er.parentId\n" +
            ",de.parentId" +
            ",de.id" +
            ",de.title " +
            ",de.telephoneNumber," +

            "ee.id,\n" +
            "ee.name ,\n" +
            "ee.surname ,\n" +
            "ee.tabelNumber ,\n" +
            "ee.telephoneNumber,\n" +
            "ee.worked,\n" +
            "phde.id,\n" +
            "ere.id,\n" +
            "ere.title," +
            "ere.parentId\n" +
            ",dee.parentId" +
            ",dee.id" +
            ",dee.title " +
            ",dee.telephoneNumber," +

            "tp.id ,\n" +
            "tp.title " +
            ")" +







            " FROM TaskEntity tt " +
//            ---------------------------------------------------------------------------------------------------------
            " left outer join TaskEntity  t on tt.parentId=t.id\n " +


            " left outer join DepartmentEntity  d on t.departmentIdExecuter=d.id\n " +


            "left outer join  EmployeeEntity e on (t.employeeIdExecuter=e.id)\n" +
//            "left outer join  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
            "left outer join  PostEntity er on (er.id=phd.postId)" +
            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +

            "left outer join  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "left outer join  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
            "left outer join  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +
            "left outer join  PostEntity ere on (ere.id=phde.postId)" +
            "left outer join  DepartmentEntity dee on (dee.id=phde.departmentId)" +

            " left outer join TaskProblemEntity tp on tp.id=t.taskProblemId\n" +





//            "left outer join  PostEntity er on (er.id=phd.postId)" +
//            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +

//            "left outer join  PostEntity ere on (ere.id=phde.postId)" +
//            "left outer join  DepartmentEntity dee on (dee.id=phde.departmentId)" +


//            ---------------------------------------------------------------------------------------------------------


//            "join fetch EmployeeEntity e on (e.id=t.employeeIdExecuter)\n" +
//            "join fetch  EmployeeRoleEntity erool on (e.id=erool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
//            "join fetch  PostEntity er on (er.id=phd.postId)" +
//            "join fetch  DepartmentEntity de on (de.id=phd.departmentId)" +
//
//            "join fetch  EmployeeEntity ee on (ee.id=t.employeeIdTasker)\n" +
//            "join fetch  EmployeeRoleEntity eerool on (ee.id=eerool.employeeId)\n" +
//            "join fetch  PostHasDepartmentEntity phde on (phde.id=ee.postHasDepartmentId)" +
//            "join fetch  PostEntity ere on (ere.id=phde.postId)" +
//            "join fetch  DepartmentEntity dee on (dee.id=phde.departmentId)" +
//
//
//            "join fetch TaskProblemEntity tp on tp.id=t.taskProblemId\n" +
//            "join fetch DepartmentEntity  d on d.id=t.departmentIdExecuter\n " +

            "where tt.id=:aLong" +
            "" )
//            "order by t.dateBegin asc")
    Optional<TaskEntity> findDirectParentByChildId(@Param("aLong") Long aLong);




















    @Query(value = queryAllChildren ,nativeQuery = true)
    Collection<TaskEntity> getAllChildren(Long id);

    @Query(value = queryAllParent ,nativeQuery = true)
    Collection<TaskEntity>  getAllParent(Long id);


    /*@Query("SELECT t FROM TaskEntity t \n" +
            "where\n" +
            "(:employeeIdTasker is null or :employeeIdTasker ='' or lower(t.employeeByEmployeeIdTasker.surname) like lower(concat('%',:employeeIdTasker,'%') ) and t.parentId is null) " +
            "and\n " +
            "(:taskProblemId is null or :taskProblemId ='' or lower(t.taskProblemByTaskProblemId.title) like lower(concat('%',:taskProblemId,'%') ) ) and\n " +
            "(:dateBegin is null or :dateBegin>=t.dateBegin) and\n" +
            "(:employeeIdExecuter is null or :employeeIdExecuter ='' or lower(t.employeeByEmployeeIdExecuter.surname) like lower(concat('%',:employeeIdExecuter,'%') ) ) and\n " +
            "(:departmentIdExecuter is null or :departmentIdExecuter ='' or lower(t.departmentByDepartmentIdExecuter.title) like lower(concat('%',:departmentIdExecuter,'%') ) ) and\n " +
            "(:dataFinish is null or :dataFinish>=t.dataFinish) and\n" +
            "(:status is null or :status=t.status)" +
            "order by t.dateBegin asc" +
            "")*/

    @Query("SELECT t FROM TaskEntity t left join EmployeeEntity e on (e.id=t.employeeIdExecuter)\n" +
            "where\n" +
            "(:employeeIdTasker is null or :employeeIdTasker ='' or lower(t.employeeByEmployeeIdTasker.surname) like lower(concat('%',:employeeIdTasker,'%') ) and t.parentId is null) " +
            "and\n " +
            "(:taskProblemId is null or :taskProblemId ='' or lower(t.taskProblemByTaskProblemId.title) like lower(concat('%',:taskProblemId,'%') ) ) " +
            "and\n " +
            "(:dateBegin is null  or :dateBegin>=t.dateBegin) " +
            "and\n" +
            "(" +
            ":employeeIdExecuter is null or :employeeIdExecuter ='' or " +
//            "t.employeeIdExecuter is null and" +
            "  lower(e.surname) like lower(concat('%',:employeeIdExecuter,'%') ) " +
            ")" +
            "and\n " +
            "(:departmentIdExecuter is null or :departmentIdExecuter ='' or lower(t.departmentByDepartmentIdExecuter.title) like lower(concat('%',:departmentIdExecuter,'%') ) ) " +
            "and\n " +
            "(:dataFinish is null  or :dataFinish>=t.dataFinish) " +
            "and\n" +
            "(:status is null or :status=t.status)" +
//            "order by t.dateBegin asc" +
            "")
    Page<TaskEntity> findTaskEntitiesByParamOne(
            @Param("employeeIdTasker")String employeeIdTasker
            ,@Param("taskProblemId")String taskProblemId
            ,@Param("dateBegin")Timestamp dateBegin,
            @Param("employeeIdExecuter")String employeeIdExecuter
            ,@Param("departmentIdExecuter")String departmentIdExecuter
            ,@Param("dataFinish")Timestamp dataFinish
            ,@Param("status")Integer status
            , Pageable pageable
                                                         );


    @Query("SELECT t FROM TaskEntity t \n" +
            "where\n" +
            "(:employeeIdTasker is null or :employeeIdTasker ='' or lower(t.employeeByEmployeeIdTasker.surname) like lower(concat('%',:employeeIdTasker,'%') ) and t.parentId is null) " +
            "and\n " +
            "(:taskProblemId is null or :taskProblemId ='' or lower(t.taskProblemByTaskProblemId.title) like lower(concat('%',:taskProblemId,'%') ) ) " +
            "and\n " +
            "(:dateBegin is null  or :dateBegin>=t.dateBegin) " +
//            "and\n" +
//            "(:employeeIdExecuter is null or :employeeIdExecuter ='' or lower(t.employeeByEmployeeIdExecuter.surname) like lower(concat('%',:employeeIdExecuter,'%') ) ) " +
            "and\n " +
            "(:departmentIdExecuter is null or :departmentIdExecuter ='' or lower(t.departmentByDepartmentIdExecuter.title) like lower(concat('%',:departmentIdExecuter,'%') ) ) " +
            "and\n " +
            "(:dataFinish is null  or :dataFinish>=t.dataFinish) " +
            "and\n" +
            "(:status is null or :status=t.status)" +
            "order by t.dateBegin asc" +
            "")
    Collection<TaskEntity> findTaskEntitiesByParamTwo(@Param("employeeIdTasker")String employeeIdTasker
            , @Param("taskProblemId")String taskProblemId
            , @Param("dateBegin")Timestamp dateBegin
//            ,@Param("employeeIdExecuter")String employeeIdExecuter
            , @Param("departmentIdExecuter")String departmentIdExecuter
            , @Param("dataFinish")Timestamp dataFinish
            , @Param("status")Integer status

                                                      );



}
