package ru.olejkai.task_vsr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olejkai.task_vsr.entity.EmployeeEntity;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    Optional<EmployeeEntity> findEmployeeEntityByTabelNumber(Integer tabelNumber);



}
