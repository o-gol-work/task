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



    @Query("SELECT " +
            "new EmployeeEntity (" +

            "e.id,\n" +
            "e.tabelNumber ,\n" +
            "e.name ,\n" +
            "e.surname ,\n" +

            "e.telephoneNumber,\n" +
            "e.password ,\n" +
            "e.worked,\n" +

            "phd.id,\n" +
            "er.id,\n" +
            "er.title,\n" +
            "er.parentId,\n" +
            "de.parentId," +
            "de.id," +
            "de.title, " +
            "de.telephoneNumber" +
            ")" +
            " FROM EmployeeEntity e " +
            "left outer join  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
            "left outer join  PostEntity er on (er.id=phd.postId)" +
            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +
            " where e.tabelNumber=:tabelNumber "
    )
    Optional<EmployeeEntity> findEmployeeEntityByTabelNumber(@Param("tabelNumber") Integer tabelNumber);


//    Long id, String username, String name, String surname, String password, Collection<GrantedAuthority> authorities

    @Query("SELECT " +
            "new EmployeeEntity (" +

            "e.id,\n" +
            "e.tabelNumber ,\n" +
            "e.name ,\n" +
            "e.surname ,\n" +
            "e.password" +
            ")" +
            " FROM EmployeeEntity e " +
            "left outer join  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
            "left outer join  PostEntity er on (er.id=phd.postId)" +
            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +
            " where e.tabelNumber=:tabelNumber "
    )
    Optional<EmployeeEntity> findEmployeeEntityByTabelNumberDetails(@Param("tabelNumber") Integer tabelNumber);




    @Query("SELECT " +
            "new EmployeeEntity (" +

            "e.id,\n" +
            "e.tabelNumber ,\n" +
            "e.name ,\n" +
            "e.surname ,\n" +

            "e.telephoneNumber,\n" +
            "e.password ,\n" +
            "e.worked,\n" +

            "phd.id,\n" +
            "er.id,\n" +
            "er.title,\n" +
            "er.parentId,\n" +
            "de.parentId," +
            "de.id," +
            "de.title, " +
            "de.telephoneNumber" +
            ")" +
            " FROM EmployeeEntity e " +
            "left outer join  PostHasDepartmentEntity phd on (phd.id=e.postHasDepartmentId)" +
            "left outer join  PostEntity er on (er.id=phd.postId)" +
            "left outer join  DepartmentEntity de on (de.id=phd.departmentId)" +
            " where e.id=:id "
    )
    Optional<EmployeeEntity> findEmployeeEntityById(@Param("id") Long id);









//    Optional<EmployeeEntity> findEmployeeEntityByTabelNumber(Integer tabelNumber);
//    String query="select e from employee e where (:surname is null or :surname ='' or lower(e.surname) like lower(concat('%'+:surname+'%') )) order by e.surname asc";

    @Query("select e from EmployeeEntity e where " +
            "(:surname is null or :surname ='' or lower(e.surname) like lower(concat('%',:surname,'%') )) " +
            " order by e.id asc")
//    @Query(query)
    List<EmployeeEntity> findBySurname(@Param("surname")String surname);


    EmployeeEntity save(EmployeeEntity employeeEntity);





}
