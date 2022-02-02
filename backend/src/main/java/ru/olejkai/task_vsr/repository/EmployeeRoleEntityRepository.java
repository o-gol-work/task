package ru.olejkai.task_vsr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olejkai.task_vsr.entity.EmployeeRoleEntity;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EmployeeRoleEntityRepository extends JpaRepository<EmployeeRoleEntity,Long> {
     EmployeeRoleEntity findEmployeeRoleEntityByRole(String string);

     Collection<EmployeeRoleEntity> findEmployeeRoleEntityByEmployeeId(Long id);

     EmployeeRoleEntity save(EmployeeRoleEntity employeeRoleEntity);

}
