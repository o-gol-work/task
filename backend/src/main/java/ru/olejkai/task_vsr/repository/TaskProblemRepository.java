package ru.olejkai.task_vsr.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.olejkai.task_vsr.entity.TaskEntity;
import ru.olejkai.task_vsr.entity.TaskProblemEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface TaskProblemRepository extends JpaRepository<TaskProblemEntity,Long> {



    String QUERY_FIND_BY_EMPLOYEE_TASKER_ID=   "with recursive rec(id, parent_id) as (\n" +
            "select id,parent_id from task_problem where id in (\n" +
            "\n" +
            "select id from task_problem where id in (\n" +
            "select problem_id from department_problem where department_id in \n" +
            "(select dtde.departments_executers_ids from department_tasker_to_departments_executers dtde where dtde.department_tasker_id=\n" +
            "(select department_id from post_has_department where id=\n" +
            "(select post_has_department_id from employee where id=:employeeTaskerId)\n" +
            ")\n" +
            ")\n" +
            ")\n" +
            ")\n" +
            "\n" +
            "union all\n" +
            "select tp.id,tp.parent_id from task_problem tp, rec r where tp.id=r.parent_id\n" +
            ")\n" +
            "select * from task_problem where id in (select distinct id from rec);"    ;

    Optional<TaskProblemEntity> findById(Long id);


    @Override
    Page<TaskProblemEntity> findAll(Pageable pageable);

    @Query(value = QUERY_FIND_BY_EMPLOYEE_TASKER_ID ,nativeQuery = true)
    Collection<TaskProblemEntity> getTasksByEmployeeTaskerId(Long employeeTaskerId);
}
