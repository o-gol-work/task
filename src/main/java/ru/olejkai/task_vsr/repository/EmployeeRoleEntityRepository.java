package ru.olejkai.task_vsr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.olejkai.task_vsr.entity.EmployeeRoleEntity;

public interface EmployeeRoleEntityRepository extends JpaRepository<EmployeeRoleEntity,Long> {
     EmployeeRoleEntity findEmployeeRoleEntityByRole(String string);

     EmployeeRoleEntity save(EmployeeRoleEntity employeeRoleEntity);

}
