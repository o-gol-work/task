package ru.olejkai.task_vsr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.olejkai.task_vsr.entity.EmployeeRoleEntity;
@Repository
public interface EmployeeRoleEntityRepository extends JpaRepository<EmployeeRoleEntity,Long> {
     EmployeeRoleEntity findEmployeeRoleEntityByRole(String string);

     EmployeeRoleEntity save(EmployeeRoleEntity employeeRoleEntity);

}
