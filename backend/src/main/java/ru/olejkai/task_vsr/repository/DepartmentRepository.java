package ru.olejkai.task_vsr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.olejkai.task_vsr.entity.DepartmentEntity;

import java.util.Collection;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

     final String queryAllChildren="WITH RECURSIVE r (parent_id, id,  title, telephone_number) AS\n" +
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

     final String queryAllParent ="WITH RECURSIVE r(parent_id, id,  title, telephone_number) AS\n" +
             "        -- Initial Subquery\n" +
             "        (SELECT dr.parent_id, dr.id,  dr.title, dr.telephone_number\n" +
             "        FROM department dl\n" +
             "        LEFT JOIN department dr \n" +
             "        ON dl.parent_id = dr.id\n" +
             "        WHERE dl.id = :id\n" +
             "        UNION ALL\n" +
             "        -- Recursive Subquery\n" +
             "        SELECT d.parent_id, d.id,  d.title, d.telephone_number\n" +
             "        FROM department d, r\n" +
             "        WHERE d.id = r.parent_id )\n" +
             "        -- Result Query\n" +
             "        SELECT parent_id, id,  title, telephone_number\n" +
             "        FROM r\n";


      DepartmentEntity getDepartmentEntityById(Long id);
      Collection<DepartmentEntity> findAllByOrderByTitleAsc();


       @Query(value = queryAllChildren ,nativeQuery = true)
      Collection<DepartmentEntity>  getAllChildren(Long id);

       @Query(value = queryAllParent ,nativeQuery = true)
      Collection<DepartmentEntity>  getAllParent(Long id);
}
