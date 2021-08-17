package ru.olejkai.task_vsr.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "department_type", schema = "task_vsr")
@Data
@NoArgsConstructor
public class DepartmentTypeEntity {
    private Long id;
    private String title;
    private Collection<DepartmentEntity> departmentsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }


    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }


    @OneToMany(mappedBy = "departmentTypeByDepartmentTypeId")
    public Collection<DepartmentEntity> getDepartmentsById() {
        return departmentsById;
    }

    public void setDepartmentsById(Collection<DepartmentEntity> departmentsById) {
        this.departmentsById = departmentsById;
    }
}
