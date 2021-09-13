package ru.olejkai.task_vsr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.olejkai.task_vsr.entity.DepartmentEntity;

import java.util.Collection;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

    String queryAllChildren="WITH RECURSIVE r (parent_id, id,  title, telephone_number) AS\n" +
            "        -- Initial Subquery\n" +
            "        (SELECT parent_id, id,  title, telephone_number\n" +
            "        FROM department\n" +
            "        WHERE parent_id = :id\n" +
            "        UNION ALL\n" +
            "        -- Recursive Subquery\n" +
            "        SELECT t.parent_id, t.id,  t.title, t.telephone_number\n" +
            "        FROM r INNER JOIN department t\n" +
            "        ON r.id = t.parent_id)\n" +
            "        -- Result Query\n" +
            "        SELECT * FROM r";

//    private String  getAllChildren(Long parent_id){
//        return String.format("WITH RECURSIVE r (id, parent_id, sub_title, level) AS\n" +
//                "        -- Initial Subquery\n" +
//                "        (SELECT id, parent_id, title, 1\n" +
//                "        FROM department\n" +
//                "        WHERE parent_id = :id\n" +
//                "        UNION ALL\n" +
//                "        -- Recursive Subquery\n" +
//                "        SELECT t.id, t.parent_id, t.title, r.level+1\n" +
//                "        FROM r INNER JOIN department t\n" +
//                "        ON r.id = t.parent_id)\n" +
//                "        -- Result Query\n" +
//                "        SELECT * FROM r",parent_id);
//    }




//    Collection<DepartmentEntity> getDepartmentEntitiesByParentId(Long parentId);
//    Collection<DepartmentEntity> getDepartmentsById(Long parentId);

//    DepartmentEntity getDepartmentEntityByParentId(Long parentId); // создает объект DepartmentEntity по радительскому id (parentId) со вложенными в него другими DepartmentEntity которые являются его родителями, вложенность продолжается до главного родителя
      DepartmentEntity getDepartmentEntityById(Long id);

//       @Query(value = queryAllChildren ,nativeQuery = true)
//      Collection<DepartmentEntity> getDepartmentEntitiesById(Long id);

       @Query(value = queryAllChildren ,nativeQuery = true)
      Collection<DepartmentEntity>  getAllChildren(Long id);
}
