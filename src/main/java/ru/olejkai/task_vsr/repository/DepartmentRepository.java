package ru.olejkai.task_vsr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.olejkai.task_vsr.entity.DepartmentEntity;

import java.util.Collection;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {




//    Collection<DepartmentEntity> getDepartmentEntitiesByParentId(Long parentId);
//    Collection<DepartmentEntity> getDepartmentsById(Long parentId);

//    DepartmentEntity getDepartmentEntityByParentId(Long parentId); // создает объект DepartmentEntity по радительскому id (parentId) со вложенными в него другими DepartmentEntity которые являются его родителями, вложенность продолжается до главного родителя
      DepartmentEntity getDepartmentEntityById(Long id);

}
