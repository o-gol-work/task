package ru.olejkai.task_vsr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.olejkai.task_vsr.entity.EmployeeEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    Optional<EmployeeEntity> findEmployeeEntityByTabelNumber(Integer tabelNumber);
//    String query="select e from employee e where (:surname is null or :surname ='' or lower(e.surname) like lower(concat('%'+:surname+'%') )) order by e.surname asc";

    @Query("select e from EmployeeEntity e where " +
            "(:surname is null or :surname ='' or lower(e.surname) like lower(concat('%',:surname,'%') )) " +
            " order by e.surname asc")
//    @Query(query)
    List<EmployeeEntity> findBySurname(@Param("surname")String surname);





}
